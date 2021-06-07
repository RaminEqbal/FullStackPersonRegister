import React from 'react';




import {parseSimpleDateToSQLDate} from '../parser/DateParser'





class AddPersonForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }


    renderForm() {


        var result = this.props.keys.map(item => {
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

        for(var i=0;i<this.props.keys.length;i++)
        {
            
            
            //Address Case
            if(this.props.keys[i] === "address"){
                var addressValues = []
                const entries = this.state["address"].split(";");
                
                //Loop through entries
                for(var j=0;j<entries.length;j++){
                    
                    //Loop through values of entry
                    const values = entries[j].split(",");
                    let addressObject = {}
                    
                    for(var k=0;k<values.length;k++){
                        
                        addressObject[this.props.addresskeys[k]] = values[k];
                    }
                    
                    console.log(addressObject);
                    addressValues.push(addressObject);
                }
                
                dataBody[this.props.keys[i]] = addressValues;

            }
            else if(this.props.keys[i] === "dob"){
                dataBody[this.props.keys[i]] = parseSimpleDateToSQLDate( this.state[this.props.keys[i]]);
            }
            else {
                dataBody[this.props.keys[i]] = this.state[this.props.keys[i]];
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
            const response = await fetch(this.props.api+"/add", {
                method: 'POST',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: jsonData
            }).then(res => console.log(res))


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

