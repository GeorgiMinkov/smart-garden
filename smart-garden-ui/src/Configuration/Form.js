import React from 'react';

import { Slider, Box } from '@material-ui/core';

import style from '../Configuration/style.css';


const Form = (props) => (
    // <form onSubmit={props.sendData}>
    //     <div>

    //         <div className="card">
    //             <div>Moisture level</div>
    //             <Slider
    //                 defaultValue={30}
    //                 aria-labelledby="discrete-slider"
    //                 valueLabelDisplay="auto"
    //                 step={10}
    //                 marks
    //                 min={10}
    //                 max={110}

    //             />
    //             <input>{}</input>
    //         </div>
    //     </div>
    //     <button >Get Weather</button>
    // </form>
    <div>
        <Box color="white" bgcolor="palevioletred" p={1}>Change water based on moisture</Box>
        <Box width="60%" height="50" position="center"><Slider className='slider'
            defaultValue={30}
            aria-labelledby="discrete-slider"
            valueLabelDisplay="auto"
            step={10}
            marks
            min={10}
            max={110}
            onChangeCommitted={props.sendData}
            height="inherit"
        /></Box>
    </div>

);

export default Form;