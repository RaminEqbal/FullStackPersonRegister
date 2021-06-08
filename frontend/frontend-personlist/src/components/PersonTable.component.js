import React from 'react';

import "../css/tabledata.css"
import AddPersonForm from './AddPersonForm.components';


import globals from "../globals"



import {parseSQLDateToSimpleDate} from '../parser/DateParser'





class PersonTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {personData:null};
        this.reloadComponent = this.reloadComponent.bind(this);
      }

    fetchPersonData() {

        fetch(globals.api)
        .then(response => response.json())
        .then(data => {
            this.setState({
                personData: data,
            });
        })

    }


    
    async reloadComponent() {
        await this.fetchPersonData();
        await this.forceUpdate();
    }




    getPersonKeys() {
        if(globals.subscribableKeys == null) return null;
        else {
            return globals.subscribableKeys;
        }
    }



    displayPersonKeys() {
        if(globals.subscribableKeys == null) return null;
        else {
            return globals.subscribableKeys.map(item => <th>{item}</th>)
        }
    }



    async componentDidMount() {
         await this.fetchPersonData();
    }


    parseAddresses(object) {
        var result = "";
        for(var i=0; i<Object.keys(object).length;i++){
            // result+= "Addresse "+(i+1)+": ";
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





    displayPersonData() {
        if(this.state.personData == null) return null;
        else {
            
            var jsxValue = [];

            for(var i=0;i<Object.keys(this.state.personData).length;i++ ){
            
                var person = []
                for(var j=0;j<globals.subscribableKeys.length;j++) {
                    var val = this.state.personData[i][globals.subscribableKeys[j]];

                    if(globals.subscribableKeys[j] === "address"){
                        val = this.parseAddresses(this.state.personData[i][globals.subscribableKeys[j]]);
                    }

                    if(globals.subscribableKeys[j] === "dob"){
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

            
            <h1>JSON Response</h1>

            <br />
            <AddPersonForm reloadParent={this.reloadComponent}/>
            <table className="personTable centered">
            <tr>
            {this.displayPersonKeys()}
            </tr>
            {this.displayPersonData()}
            </table>


            </div>
        );
      }


}

export default PersonTable;

