import React, { Component } from 'react';

import axios from 'axios';

import Slider from '@material-ui/core/Slider';

import Form from './Form'


class ChangeConfiguration extends Component {

    sendData(e) {
        e.preventDefault();
        console.log(e);
        const moisture = e.toElement.ariaValueNow;
        console.log(moisture);

        // call the rest
        const apiCall = axios.get(`http://localhost:8080/configuration/${moisture}`).then(response => {
            console.log(response);
            
          });
    }

    render() {
        return (
            // <Form onSubmit={this.sendData} />
            <Form sendData={this.sendData} />
        );
    }
}

export default ChangeConfiguration;