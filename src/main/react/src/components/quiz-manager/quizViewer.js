import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import {Model} from "./model";
import QuestionEditor from "./questionEditor";
import generateUUID from "../../util/generateUUID";
import getCurrentHostName from "../../util/getCurrentHostName";

class QuizViewer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            quizTitle: new Model().getModel()[0].quizTitle,
            quizUnmodifiedTitle: new Model().getModel()[0].quizTitle,
            quizId: new Model().getModel()[0].quizId,
            questions: new Model().getModel()[0].questions,
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
            <div className="text-center h3 pb-2b"> Edit quiz: {this.state.quizUnmodifiedTitle}</div>
                {/*<div className="btn btn-primary">Back to list of quizzes</div>*/}

            <div className="input-group input-group-lg mt-4 mb-2">
                <div className="input-group-prepend">
                    <span className="input-group-text" id="inputGroup-sizing-lg">Title: </span>
                </div>
                <input type="text" className="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm"
                       defaultValue={this.state.quizTitle} onChange={(e)=> this.setQuizTitle(e.target.value)}/>
            </div>

            <ul className="list-group">
                {this.state.questions.map(question => {
                    return <li key={question.questionId} className="list-group-item list-group-item-light list-group-flush">
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
            <div className="d-flex justify-content-between mt-3 mb-5">
                <div className="btn btn-primary" onClick={this.addNewQuestion}>Add a question</div>
                <div className="btn btn-primary" onClick={this.doServerQuizUpdateRequest}>Save quiz</div>
            </div>
        </div>
    }

    Answers(answers) {
        return <ul className="list-group">
            {answers.map(answer => {
                return <li key={answer.answerId} className="list-group-item list-group-flush">
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
        console.log('update quiz', question);
        this.closeEditForm()
    };

    deleteQuestion = (question) => () => {
        console.log('deleting element' + question);
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

    doServerQuizUpdateRequest = () => {
        console.log(this.state)
        let currentHostName = getCurrentHostName();
        let quizUpdateApi = "/api/quiz/" + this.state.quizId;
        let targetURL = currentHostName + quizUpdateApi;
        let mocktargetUrl = "http://localhost:8080/api/quiz/" + this.state.quizId
        console.log("request: PUT " + mocktargetUrl)
        fetch(mocktargetUrl, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json',
            },
            body: this.getQuizForSendingToServer()})
            .then(response => response.json())
            .then((responseJson) => console.log(responseJson))
            .catch(error => console.log(error));
    }
}


export default QuizViewer
