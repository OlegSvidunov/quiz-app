import 'bootstrap/dist/css/bootstrap.min.css';
import getCurrentHostName from '../../util/getCurrentHostName'
import React from 'react'

class UsersQuizStatistic extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            statisticApiPath: '/api/administration/quiz/statistic',
            apiData: [],
            filteredApiData: [],
            wasFetched: true,
            error: null
            };

        this.filterStatisticByUserName = this.filterStatisticByUserName.bind(this)
    }

    componentDidMount() {
        const currentHostName = getCurrentHostName();
        const statisticApiURL = currentHostName + this.state.statisticApiPath;
        console.log('Fetching data from resource: ' + statisticApiURL);

        fetch(statisticApiURL)
            .then(response => response.json())
            .then(result => this.setState({apiData : result, filteredApiData: result}))
            .catch(e => {
                console.log('Unable to fetch data from server: ' + e);
                this.setState({apiData: null, wasFetched: false, error: e})
            })

    }

    //2019-12-11T12:23:44.996 -> 11.12.2019 12:23:44
    reformatDate(dateFromApi) {
        const date = new Date(dateFromApi);
        return `${date.toLocaleDateString()} ${date.toLocaleTimeString()}`;
    }

    renderTableData() {
        return this.state.filteredApiData.map((statistic) => {
            const {_id, quizId, quizName, result, userEmail, finishedTime} = statistic;
            return (
                <tr key={_id}>
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
                <th>User</th>
                <th>Quiz id</th>
                <th>Quiz title</th>
                <th>Result, %</th>
                <th>Passed time stamp</th>
            </tr>
        )
    }

    renderSearchForm() {
        return (
            <form>
                <div className="form-row align-items-center">
                    <div className="col-auto">
                        <label className="sr-only" htmlFor="inlineFormInputGroup">Username</label>
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">Filter by user:</div>
                            </div>
                            <input type="text" className="form-control" onChange={(event => this.filterStatisticByUserName(event.target.value))}/>
                        </div>
                    </div>
                </div>
            </form>
        )
    }

    filterStatisticByUserName(searchParam) {
        let copy = [...this.state.apiData]
        copy = copy.filter((statistic) => statistic.userEmail.includes(searchParam));
        this.setState({
            filteredApiData: copy
        })
    }

    render() {
        if (!this.state.wasFetched) {
            return <div className="h2 text-center">Error on loading data from server</div>
        }
        console.log(this.state)
        return (
            <div className="container">
                <div id='title' className="h1 text-center">Users statistic</div>
                {this.renderSearchForm()}
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
