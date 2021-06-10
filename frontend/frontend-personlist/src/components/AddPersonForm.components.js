import React from 'react';

import globals, { localeStrings, namespace } from "../globals"


import {parseSimpleDateToSQLDate} from '../parser/DateParser'
import { isValidSimpleDate } from '../validators/DateValidator';
import { isValidEmail } from '../validators/EmailValidator';



const setLocale = localeStrings.german;


/**
 * The Form for adding a new person.
 * Depends on globals for the definition of the used API keys 
 * @property reloadParent: Gets the reload function of its Parent passed (in general PersonTable.component) so it can reload it after
 * a POST request has been made
 */
class AddPersonForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }




    /**
     * 
     * Definiton of the Form
     * @returns The complexe JSX for the form
     */
    renderForm() {


        var result = globals.subscribableKeys.map(item => {
            return (
            
            <div>
                <label>{setLocale.keys[item]}</label>
                <input type="text" placeholder={setLocale.placeholders[item]} name={item} onChange={this.handleInputChange} />
            </div>)
        })


        return (
            <div>
            <form className="addPersonForm" onSubmit={this.handleSubmit}>
                {result}
                <input type="submit" value="Submit" />
            </form>
            </div>
        );
    }

    /**
     * General purpose method for React Controlled Forms
     * Passes Value Changes into the Component State Model
     * @param {*} event 
     */
    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
    
        this.setState({
          [name]: value
        });
      }
    


    /**
     * Prepares a JSON Object according to API specification based on the values inside the form fields
     * Manual Parse and Validate Cases are deployed here, which can throw Errors
     * One-dimensionally extendable by expanding subscribableKeys in globals.js
     * @returns JSON Object according to API based on form values
     */
    prepareJSON() {

        var dataBody = {};

        try {

        for(var i=0;i<globals.subscribableKeys.length;i++)
        {
            
            
            //Address Case
            if(globals.subscribableKeys[i] === namespace.keys.ADDRESS){
                var addressValues = []
                const entries = this.state[namespace.keys.ADDRESS].split(";");
                
                //Loop through entries
                for(var j=0;j<entries.length;j++){
                    
                    //Loop through values of entry
                    const values = entries[j].split(",");
                    let addressObject = {}
                    
                    for(var k=0;k<values.length;k++){
                        
                        addressObject[globals.addressKeys[k]] = values[k];
                    }
                    
                    console.log(addressObject);
                    addressValues.push(addressObject);
                }
                
                dataBody[globals.subscribableKeys[i]] = addressValues;

            }
            else if(globals.subscribableKeys[i] === namespace.keys.DATE_OF_BIRTH){
                //Validate Date
                if(isValidSimpleDate(this.state[globals.subscribableKeys[i]])){
                    dataBody[globals.subscribableKeys[i]] = parseSimpleDateToSQLDate( this.state[globals.subscribableKeys[i]]);
                }
                else {
                    throw new Error(setLocale.errorMessages.PARSE_DATE_ERROR)
                }
                
            }
            else if(globals.subscribableKeys[i] === namespace.keys.EMAIL){

                //Validate Email
                if(isValidEmail(this.state[globals.subscribableKeys[i]])){
                    dataBody[globals.subscribableKeys[i]] = this.state[globals.subscribableKeys[i]]
                }
                else {
                    throw new Error(setLocale.errorMessages.PARSE_EMAIL_ERROR)
                }
            }
            else {
                //Default Case. Just add the input value to the corresponding JSON Key
                dataBody[globals.subscribableKeys[i]] = this.state[globals.subscribableKeys[i]];
            }
        }
        }
        catch(error){
            alert(setLocale.errorMessages.PARSE_FORM_ERROR+ error);
            return;
        }
        

        return JSON.stringify(dataBody);


    }




    /**
     * Method containing the actions when pressing the Submit Button on the form
     * Triggers a POST Request to the API to add an object
     * Notifies the user if the API accepted the object as a valid entry
     */
    async handleSubmit(event) {
        event.preventDefault();


        const jsonData = this.prepareJSON();        


        try {
            await fetch(namespace.api.add, {
                method: 'POST',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: jsonData
            }).then(async res => {
                let response = await res.json();
                if(!res.ok) throw new Error(setLocale.errorMessages.PERSON_NOT_ADD+response.message);
                return res;
            })


            alert(setLocale.messages.PERSON_ADD);
        } catch(error) {
            alert(setLocale.errorMessages.API_RETURNS_NOT_OK+ error);
            return;
        }
        

        
        

        console.log(this.props.reloadParent);
        this.props.reloadParent();


      }





    render() {

        return (
            <div className="centered pretty-border pretty-border-soothing">
            <h2>{setLocale.html.FORM_ADDPERSON_TITLE}</h2>

            {this.renderForm()}

            </div>
        );
      }


}

export default AddPersonForm;

