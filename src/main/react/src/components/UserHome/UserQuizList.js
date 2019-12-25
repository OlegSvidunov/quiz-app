import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import getCurrentHostName from "../../util/getCurrentHostName";
import {Link} from "react-router-dom";

class UserQuizList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            quizList: undefined,
            usersStatistic: undefined,
            wasFetched: true,
            error: null,
            username: undefined
        };
    }

    componentDidMount() {
        this.fetchQuizListFromServer();
        this.fetchUsersStatistic();
        this.fetchUserName();
    }

    render() {
        if (!this.state.username || !this.state.quizList || !this.state.usersStatistic) {
            return <div className="h2 text-center">Error on loading data from server</div>
        }

        return (
            <div className="container mt-5 quiz-list">
                <div className="h2 text-center pb-3">Welcome, {this.state.username}</div>
                {
                    this.state.quizList.map(quiz => {
                        return (
                            <div key={quiz._id}>
                                <li className="list-group-item list-group-item-action">
                                    <div className="d-flex justify-content-sm-between">
                                        {quiz.quizTitle}
                                        <div className="d-flex justify-content-end">
                                            Best result: {ResultBlock(this.getBestQuizResult(quiz))}
                                            <Link to={{
                                                pathname: '/user/pass-quiz',
                                                state: {quizId: quiz._id}
                                            }}>
                                                <div className="btn btn-primary mr-2 ml-3">Start</div>
                                            </Link>
                                        </div>
                                    </div>
                                </li>
                            </div>
                        )
                    })
                }
            </div>
        )
    }

    getBestQuizResult(quiz) {
        let stats = [...this.state.usersStatistic]
        stats = stats.filter(stat => stat.quizId === quiz._id && stat.userEmail === this.state.username);
        stats.sort(function (a, b) {
            return a.result - b.result
        });
        return stats.length > 0
            ? stats[stats.length - 1].result + "%"
            : undefined
    }

    fetchQuizListFromServer() {
        const getAllQuizApi = "/api/quiz/all";
        const currentHostName = getCurrentHostName();
        const targetURL = currentHostName + getAllQuizApi;
        console.log('Fetching data from resource: ' + targetURL);

        fetch(targetURL)
            .then(response => response.json())
            .then(result => this.setState({quizList : result}))
            .catch(e => {
                console.log('Unable to fetch data from server: ' + e);
                console.log(this.state);
                this.setState({quizList: null, wasFetched: false, error: e})
            })
    }

    fetchUsersStatistic() {
        const getStatisticApi = "/api/administration/quiz/statistic"
        const currentHostName = getCurrentHostName();
        const targetURL = currentHostName + getStatisticApi;
        console.log('Fetching data from resource: ' + targetURL);

        fetch(targetURL)
            .then(response => response.json())
            .then(result => this.setState({usersStatistic : result}))
            .catch(e => {
                console.log('Unable to fetch data from server: ' + e);
                this.setState({apiData: null, wasFetched: false, error: e})
            })
    }

    fetchUserName() {
        const getUserNameApi = "/api/user/name"
        const currentHostName = getCurrentHostName();
        const targetURL = currentHostName + getUserNameApi;
        console.log('Fetching data from resource: ' + targetURL);

        fetch(targetURL)
            .then(response => response.text())
            .then(result => this.setState({username : result}))
            .catch(e => {
                console.log('Unable to fetch data from server: ' + e);
                this.setState({apiData: null, wasFetched: false, error: e})
            })
    }
}

export default UserQuizList

export function ResultBlock(resultString) {
    console.log(resultString)
    if (resultString === undefined) {
        return (<div className="text-secondary">Not passed yes</div>)
    }
    else {
        let resultNumber = parseInt(resultString)
        if(resultNumber < 50) return (<div className="text-danger">{resultNumber}%</div>);
        else if(resultNumber >= 50 && resultNumber < 75) return (<div className="text-warning">{resultNumber}%</div>);
        else if(resultNumber >= 75) return (<div className="text-success">{resultNumber}%</div>)
    }
}