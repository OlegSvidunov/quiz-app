import "bootstrap/dist/css/bootstrap.min.css";
import React from "react"
import QuestionEditor from "./questionEditor";
import generateUUID from "../../util/generateUUID";
import getCurrentHostName from "../../util/getCurrentHostName";
import {Link} from "react-router-dom";

class QuizViewer extends React.Component {
    constructor(props) {
        super(props);
        console.log(props)
        this.state = {
            quizTitle: props.location.state.quiz.quizTitle,
            quizUnmodifiedTitle: props.location.state.quiz.quizTitle,
            quizId: props.location.state.quiz._id,
            questions: props.location.state.quiz.questions,
            showEditFrom: false,
            currentQuestion: null,
        }
    }

    render() {
        if (this.state.showEditFrom) {
            return (
                <QuestionEditor question={this.state.currentQuestion} callback={this.updateQuestion}/>
            )
        }

        return <div className="container">
            <div className="text-center">
                <div className="h2 d-inline"> <u> {this.state.quizUnmodifiedTitle}</u></div>
            </div>
                {/*<div className="btn btn-primary">Back to list of quizzes</div>*/}

            <div className="input-group input-group-lg mt-4 mb-2">
                <div className="input-group-prepend">
                    <span className="input-group-text">Title: </span>
                </div>
                <input type="text" className="form-control"
                       defaultValue={this.state.quizTitle} onChange={(e)=> this.setQuizTitle(e.target.value)}/>
            </div>

            <ul className="list-group">
                {this.state.questions.map(question => {
                    return <li key={question._id} className="list-group-item list-group-item-light list-group-flush">
                        <div className="h4 d-flex justify-content-sm-between">
                            {question.questionTitle}
                            <div className="d-flex justify-content-end">
                                <div className="btn btn-outline-primary mr-2" onClick={this.openEditForm(question)}>Edit</div>
                                {
                                    Object.keys(this.state.questions).length > 1
                                        ? <div className="btn btn-outline-danger" onClick={this.deleteQuestion(question)}>Delete</div>
                                        : false
                                }
                            </div>
                        </div>
                        {this.Answers(question.questionAnswers)}
                    </li>
                })}
            </ul>
            <Link to="/admin/">
            <div className="d-flex justify-content-between mt-3 mb-5">
                <div className="btn btn-primary" onClick={this.addNewQuestion}>Add a question</div>
                <div className="btn btn-primary" onClick={this.state.quizId === undefined
                    ? this.doServerQuizInsertRequest
                    : this.doServerQuizUpdateRequest }>Save quiz</div>
            </div>
            </Link>
        </div>
    }

    Answers(answers) {
        return <ul className="list-group">
            {answers.map(answer => {
                return <li key={answer._id} className="list-group-item list-group-flush">
                    {<input className="mr-2" type="checkBox" checked={!!answer.correct} readOnly/>}
                    {answer.answerTitle}
                </li>
            })}
        </ul>
    }

    newAnswer() {
        return {
            answerId: generateUUID(),
            answerTitle: "New answer",
            correct: false
        }
    }

    addNewQuestion = () => {
        let newQuestion = {
            questionId: generateUUID(),
            questionTitle: "New question",
            questionAnswers: [...new Array(3)].map(() => this.newAnswer())
        };

        this.setState(state => {
            const list = [...state.questions, newQuestion];
            console.log(list);
            return {
                questions: list,
                showEditFrom: true,
                currentQuestion: newQuestion
            };
        })
    };

    updateQuestion = (question) => {
        console.log("update quiz", question);
        this.closeEditForm()
    };

    deleteQuestion = (question) => () => {
        console.log("deleting element" + question);
        let index = this.state.questions.indexOf(question);
        this.setState({
            questions: this.state.questions.filter((_, i) => i !== index)
        });
    };

    openEditForm = (question) => () => {
        this.setState({
            showEditFrom: true,
            currentQuestion: question
        });
    };

    closeEditForm() {
        this.setState({
            showEditFrom: false
        });
    }

    setQuizTitle(value) {
        this.setState({
            quizTitle: value
        })
        console.log(this.state)
    }

    getQuizForSendingToServer() {
        let quiz = JSON.stringify({
            quizId: this.state.quizId,
            quizTitle: this.state.quizTitle,
            questions: this.state.questions
        })
        console.log("sending quiz to server: ", quiz)
        return quiz
    }

    doServerQuizInsertRequest = () => {
        let quizInsertApi = "/api/quiz/add";
        let targetURL = getCurrentHostName() + quizInsertApi;
        let mockTargetURL = "http://localhost:8080/api/quiz/add"
        console.log("request: POST " + mockTargetURL)

        fetch(mockTargetURL, {
            method: "POST",
            headers: {
                "Content-type": "application/json",
            },
            body: this.getQuizForSendingToServer()})
            .then(response => response.json())
            .then((responseJson) => console.log(responseJson))
            .catch(error => console.log(error));
    }

    doServerQuizUpdateRequest = () => {
        let quizUpdateApi = "/api/quiz/" + this.state.quizId;
        let targetURL = getCurrentHostName() + quizUpdateApi;
        let mockTargetURL = "http://localhost:8080/api/quiz/5df8ba86b3d3191324456cb8"
        console.log("request: PUT " + mockTargetURL)
        fetch(mockTargetURL, {
            method: "PUT",
            headers: {
                "Content-type": "application/json",
            },
            body: this.getQuizForSendingToServer()})
            .then(response => response.json())
            .then((responseJson) => console.log(responseJson))
            .catch(error => console.log(error));
    }
}

export default QuizViewer
