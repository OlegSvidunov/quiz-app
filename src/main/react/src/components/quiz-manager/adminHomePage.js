import "bootstrap/dist/css/bootstrap.min.css";
import React from "react"
import getCurrentHostName from "../../util/getCurrentHostName";
import {Link} from "react-router-dom";
import generateUUID from "../../util/generateUUID";

class AdminHomePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            apiData: [],
            wasFetched: true,
            error: null
        };
    }

    componentDidMount() {
        this.getListOfQuizzesFromServer()
    }

    render() {
        if (!this.state.wasFetched) {
            return <div className="h2 text-center">Error on loading data from server</div>
        }

        if (Object.keys(this.state.apiData).length < 1) {
                return (
                    <div>
                        <div className="h2 text-center mb-3"> Nobody has created a quiz yet :(</div>
                        <div className="h2 text-center"> Be the first one!</div>
                        {this.getCreateQuizButton()}
                    </div>)
        }

        return (
            <div className="container">
                <div className="text-center mb-5">
                    <div className="h2">Quiz administration page</div>
                    {this.getCreateQuizButton()}

                </div>
                {
                    this.state.apiData.map(quiz => {
                        return (
                            <div key={quiz._id}>
                            <li className="list-group-item list-group-item-action">
                                <div className="d-flex justify-content-sm-between">
                                <a>
                                    {quiz.quizTitle}
                                </a>
                                <div className="d-flex justify-content-end">
                                    <Link to={{
                                        pathname: '/admin/quiz-editor',
                                        state: {quiz}
                                    }}>
                                        <div className="btn btn-outline-primary mr-2">Open</div>
                                    </Link>
                                    <div className="btn btn-outline-danger" onClick={this.doServerDeleteQuizRequest(quiz)}>Delete</div>
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

    getCreateQuizButton() {
        let newQuiz = this.getNewQuiz()
        return (
            <Link to={{
                pathname: '/admin/quiz-editor',
                state: {quiz: newQuiz}
            }}>
                <div className="text-center">
                    <div className="btn btn-lg btn-primary mr-4">Create a quiz!</div>
                </div>
            </Link>
        )
    }

    getNewQuiz() {
        return {
            quizTitle: "New quiz",
            questions: [
                {
                    _id: generateUUID(),
                    questionTitle: "some question",
                    questionAnswers: [
                        {
                            _id: generateUUID(),
                            answerTitle: "some answer",
                            isCorrect: false
                        },
                        {
                            _id: generateUUID(),
                            answerTitle: "some answer",
                            isCorrect: false
                        },
                        {
                            _id: generateUUID(),
                            answerTitle: "some answer",
                            isCorrect: false
                        }
                    ]
                }
            ]
        }
    }

    doServerDeleteQuizRequest = (quiz) => () => {
        console.log(this.state)
        let quizDeleteApi = "/api/quiz/";
        let targetURL = getCurrentHostName() + quizDeleteApi + quiz._id.toString();
        // let targetURL = "http://localhost:8080/api/quiz/" + quiz._id
        console.log("request: DELETE " + targetURL)
        fetch(targetURL, {
            method: "DELETE",
            headers: {
                "Content-type": "application/json",
            },
        })
            .then(response => response.json())
            .then((responseJson) => console.log(responseJson))
            .then(this.getListOfQuizzesFromServer)
            .catch(error => console.log(error));

        document.location.reload()

    }

    getListOfQuizzesFromServer() {
        let allQuizzesList = "/api/quiz/all";
        let targetURL = getCurrentHostName() + allQuizzesList;
        // let targetUrl = "http://localhost:8080/api/quiz/all";
        console.log("request: GET " + targetURL);

        fetch(targetURL)
            .then(response => response.json())
            .then(result => this.setState({apiData: result}))
            .catch(e => {
                console.log('Unable to fetch data from server: ' + e);
                this.setState({apiData: null, wasFetched: false, error: e})
            })
    }

}

export default AdminHomePage
