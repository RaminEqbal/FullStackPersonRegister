import React from 'react';

import "../css/tabledata.css"
import AddPersonForm from './AddPersonForm.components';


import globals, { localeStrings, namespace } from "../globals"



import {parseSQLDateToSimpleDate} from '../parser/DateParser'




/**
 * Renders a Table based on the API calls
 * Maps keys specified in subscribableKeys(globals.js) to a table format containing all entries in the database
 */
class PersonTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {personData:null};
        this.reloadComponent = this.reloadComponent.bind(this);
      }

    /**
     * API Call to get all Persons in the database and write them to the state
     */
    fetchPersonData() {

        fetch(namespace.api.default)
        .then(response => response.json())
        .then(data => {
            this.setState({
                personData: data,
            });
        })

    }


    //Forced reloading of the component by refetching the API and forcefully rerendering the component
    async reloadComponent() {
        await this.fetchPersonData();
        await this.forceUpdate();
    }


    /**
     * Table Heading Render
     */
    displayPersonKeys() {
        if(globals.subscribableKeys == null) return null;
        else {
            return globals.subscribableKeys.map(item => <th>{localeStrings.german.keys[item]}</th>)
        }
    }



    /**
     * Make API call on rendering of the component
     */
    async componentDidMount() {
         await this.fetchPersonData();
    }


    /**
     * Parses Address Objects (requested from the API) to a String format
     */
    parseAddresses(object) {
        var result = "";
        for(var i=0; i<Object.keys(object).length;i++){
            
            for(var j=0;j<globals.addressKeys.length;j++) {

                result += object[i][globals.addressKeys[j]];
                if(j !== globals.addressKeys.length-1) result += ", "
            }
            result+=";"
        }
        return result.split(";").map(item => 
        <div>
            {item}
        </div>);
    }




    /**
     * Definition of the JSX used to render the collection of JSON retrieved from the API as a table
     */
    displayPersonData() {
        if(this.state.personData == null) return null;
        else {
            
            var jsxValue = [];

            for(var i=0;i<Object.keys(this.state.personData).length;i++ ){
            
                var person = []
                for(var j=0;j<globals.subscribableKeys.length;j++) {
                    var val = this.state.personData[i][globals.subscribableKeys[j]];

                    if(globals.subscribableKeys[j] === namespace.keys.ADDRESS){
                        val = this.parseAddresses(this.state.personData[i][globals.subscribableKeys[j]]);
                    }

                    if(globals.subscribableKeys[j] === namespace.keys.DATE_OF_BIRTH){
                        val = parseSQLDateToSimpleDate(val);
                    }


                    person.push(
                    <td>
                    {val}
                    </td>);
                }
                jsxValue.push(person);
            }
            return jsxValue.map(item => <tr>{item}</tr>);
            

        }
    }



    render() {

        return (
            <div>


            <br />
            <AddPersonForm reloadParent={this.reloadComponent}/>
            <table className="personTable centered">
            <thead>
            <tr>
            {this.displayPersonKeys()}
            </tr>
            </thead>
            <tbody>
            {this.displayPersonData()}
            </tbody>
            </table>


            </div>
        );
      }


}

export default PersonTable;

