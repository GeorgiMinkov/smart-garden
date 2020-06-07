import React from 'react';

import { Slider } from '@material-ui/core';

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
        <div>Change moisture</div>
        <Slider className='slider'
            defaultValue={30}
            aria-labelledby="discrete-slider"
            valueLabelDisplay="auto"
            step={10}
            marks
            min={10}
            max={110}
            onChangeCommitted={props.sendData}
        />
    </div>

);

export default Form;