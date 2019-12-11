import 'bootstrap/dist/css/bootstrap.min.css';
import getCurrentHostName from './util/getCurrentHostName'
import React from 'react'

class UsersQuizStatistic extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            statisticApiPath: '/api/statistic',
            apiData: [],
            wasFetched: true,
            error: null
            };

    }

    componentDidMount() {
        const currentHostName = getCurrentHostName();
        const statisticApiURL = currentHostName + this.state.statisticApiPath;
        console.log('Fetching data from resource: ' + statisticApiURL)

        fetch(statisticApiURL)
            .then(response => response.json())
            .then(result => this.setState({apiData : result}))
            .catch(e => {
                console.log('Unable to fetch data from server: ' + e);
                this.setState({apiData: null, wasFetched: false, error: e})
            })

    }

    //2019-12-11T12:23:44.996 -> 11.12.2019 12:23:44
    reformatDate(dateFromApi) {
        const date = new Date(dateFromApi);
        return date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
    }

    renderTableData() {
        console.log("Fetched data: " + JSON.stringify(this.state.apiData));
        return this.state.apiData.map((statistic) => {
            const {id, quizId, quizName, result, userEmail, finishedTime} = statistic;
            return (
                <tr key={id}>
                    <td>{id}</td>
                    <td>{userEmail}</td>
                    <td>{quizId}</td>
                    <td>{quizName}</td>
                    <td>{result}</td>
                    <td>{this.reformatDate(finishedTime)}</td>
                </tr>
            )
        })
    }

    renderTableHead() {
        return (
            <tr>
                <th>#</th>
                <th>User</th>
                <th>Quiz id</th>
                <th>Quiz title</th>
                <th>Result</th>
                <th>Passed time stamp</th>
            </tr>
        )
    }

    render() {
        if (!this.state.wasFetched) {
            return <div className="h2 text-center">Error on loading data from server</div>
        }

        return (
            <div className="container">
                <div id='title' className="h2 text-center">Users statistic</div>
                <table className="table">
                    <thead>
                    {this.renderTableHead()}
                    </thead>
                    <tbody>
                    {this.renderTableData()}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default UsersQuizStatistic
