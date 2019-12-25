import "bootstrap/dist/css/bootstrap.min.css";
import React from "react"
import getCurrentHostName from "../../util/getCurrentHostName";
import {Link} from "react-router-dom";
import uuidv1 from "uuid/v1"

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
            console.log("THIS STATE SIZE:", this.state)
                return (
                    <div>
                        <div className="h2 text-center text-secondary mb-3"> Nobody has created a quiz yet :(</div>
                        <div className="h2 text-center text-secondary"> Be the first  one!</div>
                        {this.getCreateQuizButton()}
                    </div>)
        }

        return (
            <div className="container">
                <div className="text-center mb-3">
                    <div className="h1">Quiz manager</div>
                    {this.getCreateQuizButton()}
                </div>
                {
                    this.state.apiData.map(quiz => {
                        return (
                            <div key={quiz._id}>
                            <li className="list-group-item list-group-item-action">
                                <div className="d-flex justify-content-sm-between">
                                    {quiz.quizTitle}
                                <div className="d-flex justify-content-end">
                                    {console.log("API DATA: ", JSON.stringify(this.state.apiData))}
                                    <Link to={{
                                        pathname: '/admin/quiz-editor',
                                        state: {quiz}
                                    }}>
                                        <div className="btn btn-primary mr-2">Open</div>
                                    </Link>
                                    <div className="btn btn-danger" onClick={() => this.doServerDeleteQuizRequest(quiz)}>Delete</div>
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
                    <div className="btn btn-lg btn-primary mt-1">Create a quiz!</div>
                </div>
            </Link>
        )
    }

    generateNewQuestion() {
        return {
            _id: uuidv1(),
            answerTitle: "New answer",
            isCorrect: false
        }
    }

    getNewQuiz() {
        return {
            quizTitle: "New quiz",
            questions: [
                {
                    _id: uuidv1(),
                    questionTitle: "New answer",
                    questionAnswers:  [...new Array(3)].map(() => this.generateNewQuestion())
                }
            ]
        }
    }

   async doServerDeleteQuizRequest(quiz) {
        console.log(this.state)
        let quizDeleteApi = "/api/quiz/";
        let targetURL = getCurrentHostName() + quizDeleteApi + quiz._id.toString();
        console.log("request: DELETE " + targetURL)
        await fetch(targetURL, {
            method: "DELETE",
            headers: {
                "Content-type": "application/json",
            },
        })
            .then(response => response.json())
            .then((responseJson) => console.log(responseJson))
            .then(this.getListOfQuizzesFromServer)
            .catch(error => console.log(error));

        this.getListOfQuizzesFromServer()
    }

    getListOfQuizzesFromServer() {
        let allQuizzesList = "/api/quiz/all";
        let targetURL = getCurrentHostName() + allQuizzesList;
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
