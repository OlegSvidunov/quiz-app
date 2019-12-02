import React, { Component } from 'react';
import Welcome from './components/Welcome';
import 'bootstrap/dist/css/bootstrap.css';
import { BrowserRouter as Router, Route } from "react-router-dom";

class App extends Component {

    render() {
        return (

                <Router>
                    <Route exact path="/" component={Welcome} />
                </Router>

        );
    }
}

export default App
