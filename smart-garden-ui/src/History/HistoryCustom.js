import React, { Component } from 'react';

import axios from 'axios';

import Slider from '@material-ui/core/Slider';

import Table from 'react-bootstrap/Table'


class HistoryCustom extends Component {
    state = {
        history: [{
            payload: "",
            requestTime: ""
        }]
    }

    loadData() {
        let history = [];
        // call the rest
        const apiCall = axios.get(`http://localhost:8080/history`).then(response => {
            console.log(response);
            
        });
    }

    render() {
        return (
            // <Form onSubmit={this.sendData} />
            <Table striped bordered hover size="sm">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Username</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td colSpan="2">Larry the Bird</td>
                        <td>@twitter</td>
                    </tr>
                </tbody>
            </Table>
        );
    }
}

export default HistoryCustom;