import React from 'react';

import "../css/tabledata.css"
import AddPersonForm from './AddPersonForm.components';


import {parseSQLDateToSimpleDate} from '../parser/DateParser'




const api = "http://localhost:8080/api/person";

const subscribableKeys = [
    // "id",
    "surname",
    "name",
    "emailAddress",
    "dob",
    "address",
]




const addressKeys = [
    // "id",
    "streetName",
    "streetNo",
    "postalCode",
    "countryName"
]





class PersonTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {personData:null};
        this.reloadComponent = this.reloadComponent.bind(this);
      }

    fetchPersonData() {

        fetch(api)
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
        if(subscribableKeys == null) return null;
        else {
            return subscribableKeys;
        }
    }



    displayPersonKeys() {
        if(subscribableKeys == null) return null;
        else {
            return subscribableKeys.map(item => <th>{item}</th>)
        }
    }



    async componentDidMount() {
         await this.fetchPersonData();
    }


    parseAddresses(object) {
        var result = "";
        for(var i=0; i<Object.keys(object).length;i++){
            // result+= "Addresse "+(i+1)+": ";
            for(var j=0;j<addressKeys.length;j++) {

                result += object[i][addressKeys[j]];
                if(j != addressKeys.length-1) result += ", "
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
                for(var j=0;j<subscribableKeys.length;j++) {
                    var val = this.state.personData[i][subscribableKeys[j]];

                    if(subscribableKeys[j] === "address"){
                        val = this.parseAddresses(this.state.personData[i][subscribableKeys[j]]);
                    }

                    if(subscribableKeys[j] === "dob"){
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
            <AddPersonForm api={api} keys={this.getPersonKeys()} addresskeys={addressKeys} reloadParent={this.reloadComponent}/>
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

