import React from 'react';

const api = "http://localhost:8080/api/person";



class PersonTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {personData:[]};
      }

    fetchPersonData() {

        fetch(api)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            this.setState({
                personData: data
            });
        })

    }


    async componentDidMount() {
         await this.fetchPersonData();
    }

    render() {
        // var result = this.state.personData.map(item => {
        //     var valueJSX = "";
        //     for (const [key, value] of Object.entries(item)) {
        //     console.log(key,value)
        //     valueJSX +=
        //         <div>
        //             {key.toString()} : {value.toString()}
        //         </div>
        //       }
        //     return valueJSX;
        // })




        return (
            <div>

            
            <h1>JSON Response</h1>
            <h2>Keys: {Object.keys(this.state.personData)}</h2>
            {
                Object.keys(this.state.personData).map((key, i) => (
                    <p key={i}>
                      <span>Key Name: {key}</span>
                      <span>Value: {this.state.personData.object[key]}</span>
                    </p>
                  ))
            }
            </div>
        );
      }


}

export default PersonTable;