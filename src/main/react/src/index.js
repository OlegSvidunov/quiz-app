import React from 'react'
import ReactDOM from 'react-dom'
import { Route, Link, BrowserRouter as Router, Switch } from 'react-router-dom'
import Welcome from './welcome'
import NotFound from './notFound'
import UsersQuizStatistic from "./usersQuizStatistic";
import QuizCreator from "./components/quiz-manager/quizViewer";
import AdminHomePage from "./components/quiz-manager/adminHomePage";

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
                <li>
                    <Link to="/admin">Admin home page</Link>
                </li>
                <li>
                    <Link to="/admin/quiz-editor">Quiz editor</Link>
                </li>
            </ul>
            <Switch>
                <Route exact path="/" component={Welcome} />
                <Route exact path="/admin" component={AdminHomePage} />
                <Route path="/admin/statistic" component={UsersQuizStatistic} />
                <Route path="/admin/quiz-editor" component={QuizCreator} />
                {/* Add your component to router here*/}

                <Route component = {NotFound} />
            </Switch>
        </div>
    </Router>
);
ReactDOM.render(routing, document.getElementById('root'))