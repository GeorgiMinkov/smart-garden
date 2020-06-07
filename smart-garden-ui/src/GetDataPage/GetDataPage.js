import React, { Component } from 'react';
import syle from '../GetDataPage/GetDataPage.css';
import axios from 'axios';

class GetDataPage extends Component {
    state = {
        temperature: undefined,
        waterLevel: undefined,
        moisture: undefined,
        index: 0
    };

    call_rest = () => {
        // e.preventDefault();
        const apiCall = axios.get(`http://localhost:8080/getData`).then(response => {
            console.log(response);
            if (response) {
          
                this.setState({
                  temperature: response.data.temperature,
                  moisture: response.data.moisture,
                  waterLevel: response.data.waterLevel,
                  index: 1
                });
              }
          });
    }

    render() {
        console.log("HERE");
        if (this.state.index == 0) {
            this.call_rest();
        }

        return (
            <div className="wrapper">
                <div className="element">
                    <div>Temperture</div>
                    <div>{this.state.temperature} °C</div>
                </div>

                <div className="element">
                    <div>Moister</div>
                    <div>{this.state.moisture} %</div>
                </div>

                <div className="element">
                    <div>Water Level</div>
                    <div>{this.state.waterLevel} %</div>
                </div>

            </div>
        );
    }
}

export default GetDataPage;