import React from 'react'
import ReactDOM from 'react-dom'
import {Route, Link, BrowserRouter as Router, Switch, Redirect} from 'react-router-dom'
import Welcome from './components/AdminHome/welcome'
import NotFound from './notFound'
import UsersQuizStatistic from "./components/AdminHome/usersQuizStatistic";
import AdminHomePage from "./components/QuizManager/adminHomePage";
import QuizEditor from "./components/QuizManager/quizEditor";

import UserQuizList from "./components/UserHome/UserQuizList";
import QuestionBlock from "./components/QuestionBlock";
import getCurrentHostName from "./util/getCurrentHostName";

const style = {
    position: 'absolute',
    top: 10,
    right: 10
}

const adminRouting = (
    <React.Fragment>
        <Router>
            <div>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                    <li>
                        <Link to="/admin">Quiz manager</Link>
                    </li>
                    <li>
                        <Link to="/admin/statistic">Statistic</Link>
                    </li>
                    <li>
                        <Link to="/user/home">User page</Link>
                    </li>
                </ul>
                <Switch>
                    <Route exact path="/" component={Welcome} />
                    <Route exact path="/admin" component={AdminHomePage} />
                    <Route path="/admin/statistic" component={UsersQuizStatistic} />
                    <Route path="/admin/quiz-editor" component={QuizEditor} />
                    <Route path="/user/home" component={UserQuizList} />
                    <Route path="/user/pass-quiz" component={QuestionBlock} />

                    <Route component = {NotFound} />
                </Switch>
            </div>
        </Router>
        <form>
            <button type="submit" formAction="/logout" style={style} className='btn btn-outline-secondary'>Logout</button>
        </form>
    </React.Fragment>
);

const userRouting = (
    <div className="quiz-list">
    <React.Fragment>
        <Router>
            <div>
                <Switch>
                    <Route exact path="/">
                        <Redirect to="/user/home" />
                    </Route>
                    <Route path="/user/home" component={UserQuizList} />
                    <Route path="/user/pass-quiz" component={QuestionBlock} />
                    <Route component = {NotFound} />
                </Switch>
            </div>
        </Router>
        <form>
            <button type="submit" formAction="/logout" style={style} className='btn btn-outline-secondary'>Logout</button>
        </form>
    </React.Fragment>
        </div>
);

    function getUserRole() {
        const currentHost = getCurrentHostName();
        let xhr = new XMLHttpRequest();
        xhr.open('GET', currentHost + "/api/user/role", false);
        xhr.send();
        return xhr.responseText;
    }


    document.body.style = 'background: #EEEEEE;';
    getUserRole().includes("ADMIN")
        ? ReactDOM.render(adminRouting, document.getElementById('root'))
        : ReactDOM.render(userRouting, document.getElementById('root'))

