import React from 'react';










class AddPersonForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }


    renderForm() {
        console.log(this.props.keys)

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
        const response = await fetch(this.props.api+"/add", {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                
            }
        })



        alert('Person was submitted');
        event.preventDefault();
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

