import "bootstrap/dist/css/bootstrap.min.css";
import React from "react"
import QuestionEditor from "./questionEditor";
import generateUUID from "../../util/generateUUID";
import getCurrentHostName from "../../util/getCurrentHostName";
import {Link} from "react-router-dom";

class QuizEditor extends React.Component {
    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            quizTitle: props.location.state.quiz.quizTitle,
            quizId: props.location.state.quiz._id,
            questions: props.location.state.quiz.questions,
            showEditFrom: false,
            currentQuestion: null,
        }
/*        this.state = {
            quizTitle: new MockQuizModel().getModel()[0].quizTitle,
            quizId: "123",
            questions: new MockQuizModel().getModel()[0].questions,
            showEditFrom: false,
            currentQuestion: null,
        }*/
    }

    render() {
        if (this.state.showEditFrom) {
            return (
                <QuestionEditor question={this.state.currentQuestion} callback={this.updateQuestion}/>
            )
        }

        return (
            <div className="container">
                <div className="h1 text-center">QUIZ EDITOR</div>

                <Link to="/admin/">
                    <div className="d-flex justify-content-between">
                        <div className="btn btn-light ju">Back to list of quizzes</div>
                    </div>
                </Link>

                <div className="input-group input-group-lg mt-4 mb-2">
                    <div className="input-group-prepend">
                        <span className="input-group-text">Title: </span>
                    </div>
                    <input type="text" className="form-control"
                           defaultValue={this.state.quizTitle}
                           onChange={(e) => this.setQuizTitle(e.target.value)}/>
                </div>

                <ul className="list-group">
                    {this.state.questions.map(question => {
                        console.log("render question: ", JSON.stringify(question))
                        return <li key={question._id}
                                   className="list-group-item list-group-item-light list-group-flush">
                            <div className="h4 d-flex justify-content-sm-between">
                                {question.questionTitle}
                                <div className="d-flex justify-content-end">
                                    <div className="btn btn-outline-primary mr-2"
                                         onClick={this.openEditForm(question)}>Edit
                                    </div>
                                    {
                                        Object.keys(this.state.questions).length > 1
                                            ? <div className="btn btn-outline-danger"
                                                   onClick={this.deleteQuestion(question)}>Delete</div>
                                            : false
                                    }
                                </div>
                            </div>
                            {this.Answer(question.questionAnswers)}
                        </li>
                    })}
                </ul>
                <div className="d-flex justify-content-between mt-3 mb-5">
                    <div className="btn btn-primary" onClick={this.addNewQuestion}>Add a question</div>
                    <Link to="/admin">
                        <div className="btn btn-primary" onClick={this.state.quizId === undefined
                            ? this.doServerQuizInsertRequest
                            : this.doServerQuizUpdateRequest}>Save quiz
                        </div>
                    </Link>
                </div>
            </div>
        )
    }

    Answer(answers) {
        return <ul className="list-group">
            {answers.map(answer => {
                return <li key={answer._id} className="list-group-item list-group-flush">
                    {console.log("render answer: ", JSON.stringify(answer))}
                    {<input className="mr-2" type="checkBox" checked={answer.isCorrect} readOnly/>}
                    {answer.answerTitle}
                </li>
            })}
        </ul>
    }

    newAnswer() {
        return {
            answerId: generateUUID(),
            answerTitle: "New answer",
            isCorrect: false
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
        console.log("update question: ", question);
        let list = [...this.state.questions];
        let index = list.indexOf(this.state.currentQuestion);
        console.log("list of question before update:", JSON.stringify(this.state.questions) )
        list[index] = question
        console.log("list of question after update:", JSON.stringify(list) )

        this.setState({
            questions: list
        })
        console.log("state after quiz update: ", this.state)
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
        });
        console.log(this.state)
    }

    getQuizForSendingToServer() {
        let questions = this.state.questions;
        questions.map((question) => {
            delete question._id;
            question.questionAnswers.map((answer) => {
                delete answer._id;
            })
        })
        let quiz = JSON.stringify({
            _id: this.state.quizId,
            quizTitle: this.state.quizTitle,
            questions: this.state.questions
        });
        console.log("sending quiz to server: ", quiz);
        return quiz
    }

    doServerQuizInsertRequest = () => {
        let quizInsertApi = "/api/quiz/add";
        let targetURL = getCurrentHostName() + quizInsertApi;
        console.log("request: POST " + targetURL);

        fetch(targetURL, {
            method: "POST",
            headers: {
                "Content-type": "application/json",
            },
            body: this.getQuizForSendingToServer()
        })
            .then(response => response.json())
            .then((responseJson) => console.log(responseJson))
            .catch(error => console.log(error));
    };

    doServerQuizUpdateRequest = () => {
        let quizUpdateApi = "/api/quiz/";
        let targetURL = getCurrentHostName() + quizUpdateApi + this.state.quizId;
        console.log("request: PUT " + targetURL);
        fetch(targetURL, {
            method: "PUT",
            headers: {
                "Content-type": "application/json",
            },
            body: this.getQuizForSendingToServer()
        })
            .then(response => response.json())
            .then((responseJson) => console.log(responseJson))
            .catch(error => console.log(error));
    }
}

export default QuizEditor
