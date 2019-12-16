import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import {Model} from "./model";
import QuestionEditor from "./questionEditor";

class QuizViewer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            quizTitle: new Model().getModel()[0].quizTitle,
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
                <div className="btn btn-primary">Back to list of quizzes</div>
                <div className="h2 text-center">{this.state.quizTitle}
            </div>
            <ul className="list-group">
                {this.state.questions.map(question => {
                    return <li key={question.questionId} className="list-group-item list-group-flush">
                        <div className="h4 d-flex justify-content-sm-between">
                            {question.questionTitle}
                            <div className="d-flex justify-content-end">
                                <div className="btn btn-outline-primary mr-2"
                                     onClick={this.openEditForm(question)}>Edit
                                </div>
                                <div className="btn btn-outline-danger" onClick={this.deleteQuestion(question)}>Delete
                                </div>
                            </div>
                        </div>
                        {this.Answers(question.questionAnswers)}
                    </li>
                })}
            </ul>
            <div className="d-flex justify-content-between mt-3 mb-5">
                <div className="btn btn-primary" onClick={this.addNewQuestion}>Add a question</div>
                <div className="btn btn-primary">Save quiz</div>
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

    addNewQuestion = () => {
        let newQuestion = {
            questionId: this.state.questions.length + 1,
            questionTitle: "New question",
            questionAnswers: [
                {
                    answerId: 1,
                    answerTitle: "New answer",
                    correct: true,
                    operation: "add"
                }
            ]
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
}


export default QuizViewer
