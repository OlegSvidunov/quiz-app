import React from 'react'
import ReactDOM from 'react-dom'
import { Route, Link, BrowserRouter as Router, Switch } from 'react-router-dom'
import Welcome from './welcome'
import NotFound from './notFound'
import UsersQuizStatistic from "./usersQuizStatistic";
const routing = (
    <Router>
        <div>
            <ul>
                <li>
                    <Link to="/">Welcome</Link>
                </li>
                <li>
                    <Link to="/admin/statistic">Statistic</Link>
                </li>
            </ul>
            <Switch>
                <Route exact path="/" component={Welcome} />

                <Route path="/admin/statistic" component={UsersQuizStatistic} />
                {/* Add your component to router here*/}

                <Route component = {NotFound} />
            </Switch>
        </div>
    </Router>
);
ReactDOM.render(routing, document.getElementById('root'))