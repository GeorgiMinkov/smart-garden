import React, { useState } from 'react';

import axios from 'axios';

import style from './History.css';

import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

import { Box } from '@material-ui/core';

const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});


const loadData = () => {
    let history = [];
    const apiCall = axios.get(`http://localhost:8080/history`).then(response => {
        history = response.data;
    });

    return history;
}

export default function History() {
    const [history, setHistory] = useState([]);
    const [visited, setVisited] = useState(false);

    if (!visited) {
        const apiCall = axios.get(`http://localhost:8080/history`).then(response => {
            setVisited(true);
            setHistory(response.data);
        });
    } 
    

    const classes = useStyles();
    return (
        <Box className="box">
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>#</TableCell>
                            <TableCell align="right">Payload</TableCell>
                            <TableCell align="right">Timestamp</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {history.map((row, index) => (
                            <TableRow key={index}>
                                <TableCell component="th" scope="row">
                                    {index}
                                </TableCell>
                                <TableCell align="right">{row.payload}</TableCell>
                                <TableCell align="right">{row.requestTime}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>

    );
}