import React, { Component } from 'react';
import syle from '../TriggerWaterCheck/TriggerWaterCheck.css';
import axios from 'axios';

class TriggerWaterCheck extends Component {
    state = {
        temperature: undefined,
        waterLevel: undefined,
        moisture: undefined,
        moisterLevelForWater: undefined,
        humidity: undefined,
        index: 0
    };

    call_rest = () => {
        // e.preventDefault();
        const apiCall = axios.get(`http://localhost:8080/waterCheck`).then(response => {
            console.log(response);
            if (response) {
          
                this.setState({
                  temperature: response.data.temperature,
                  moisture: response.data.moisture,
                  waterLevel: response.data.waterLevel,
                  moisterLevelForWater: response.data.moisterLevelForWater,
                  humidity: response.data.humidity,
                  text: response.data.text,
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
                    <div>Humidity</div>
                    <div>{this.state.humidity} %</div>
                </div>

                <div className="element">
                    <div>Water Level</div>
                    <div>{this.state.waterLevel} %</div>
                </div>

                
                <div className="element">
                    <div>Current Moister level</div>
                    <div>{this.state.moisture} %</div>
                </div>

                <div className="element">
                    <div>Preset moisture level</div>
                    <div>{this.state.moisterLevelForWater} %</div>
                </div>

                <div className="element">
                    <div>Watering status</div>
                    <div className="water-text">{this.state.text}</div>
                </div>

            </div>
        );
    }
}

export default TriggerWaterCheck;