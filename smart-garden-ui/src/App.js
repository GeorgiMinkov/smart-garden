import React from 'react';
import './App.css';

import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';

import GetDataPage from './GetDataPage/GetDataPage';
import ChangeConfiguration from './Configuration/ChangeConfiguration';
import History from './History/History';
import TriggerWaterCheck from './TriggerWaterCheck/TriggerWaterCheck';

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useRouteMatch,
  useParams,
  HashRouter
} from "react-router-dom";
import HistoryCustom from './History/HistoryCustom';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
  },
}));

function App() {
  return (
    <div className="App">
      
      <HashRouter>
        <Switch>
          <Route path={"/"} exact={true}>
              <HomeElement />
          </Route>
          <Route path={"/getData"}>
            <GetDataPage />
          </Route>

          <Route path={"/configure"}>
            <ChangeConfiguration />
          </Route>

          <Route path={"/history"}>
            <History />
          </Route>

          <Route path={"/triggerWaterCheck"}>
            <TriggerWaterCheck />
          </Route>
        </Switch>

      </HashRouter >
    </div>
  );
}

let HomeElement = () => {

  const classes = useStyles();

  return (
    <div className="center">
      <div className="wrapper">
        <Button className="element" variant="outlined" color="primary">
          <Link to="/getData">Get Current Data</Link>
        </Button>

        <Button className="element" variant="outlined" color="secondary">
          <Link to="/configure">Change Configuration</Link>
        </Button>

        <Button className="element" variant="outlined" color="secondary">
          <Link to="/history">History</Link>
        </Button>

        <Button className="element" variant="outlined" color="secondary">
          <Link to="/triggerWaterCheck">Trigger Water Check</Link>
        </Button>
      </div>
    </div>

  );
}



export default App;
