import React from 'react';

import globals from "../globals"


import {parseSimpleDateToSQLDate} from '../parser/DateParser'





class AddPersonForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }


    renderForm() {


        var result = globals.subscribableKeys.map(item => {
            return (
            
            <div>
                <label>{item}</label>
                <input type="text" name={item} onChange={this.handleInputChange} />
            </div>)
        })


        return (
            <div>
            <form onSubmit={this.handleSubmit}>
                {result}
                <input type="submit" value="Submit" />
            </form>
            </div>
        );
    }


    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
    
        this.setState({
          [name]: value
        });
      }
    
      async handleSubmit(event) {
        event.preventDefault();
        console.log("Submit Triggered")
        var dataBody = {};



        try {

        for(var i=0;i<globals.subscribableKeys.length;i++)
        {
            
            
            //Address Case
            if(globals.subscribableKeys[i] === "address"){
                var addressValues = []
                const entries = this.state["address"].split(";");
                
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
            else if(globals.subscribableKeys[i] === "dob"){
                dataBody[globals.subscribableKeys[i]] = parseSimpleDateToSQLDate( this.state[globals.subscribableKeys[i]]);
            }
            else {
                dataBody[globals.subscribableKeys[i]] = this.state[globals.subscribableKeys[i]];
            }




        }

        console.log(dataBody);


        }
        catch(error){
            alert("Faulty Form");
            return;
        }
        

        const jsonData = JSON.stringify(dataBody);


        try {
            await fetch(globals.api+"/add", {
                method: 'POST',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: jsonData
            }).then(res =>{
                if(!res.ok) throw new Error("Failed to add Person. Code:"+res.status);
                return res;
            })


            alert("Person has been added");
        } catch(error) {
            alert("There was a problem adding the data with the API\n"+ error);
            return;
        }
        

        
        

        console.log(this.props.reloadParent);
        this.props.reloadParent();


      }





    render() {

        return (
            <div>
            <h2>Form</h2>

            {this.renderForm()}

            </div>
        );
      }


}

export default AddPersonForm;

