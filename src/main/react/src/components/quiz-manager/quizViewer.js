import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import {Model} from "./model";
import QuestionEditor from "./questionEditor";
import {Link} from "react-router-dom";

class QuizViewer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            quizTitle: new Model().getModel()[0].quizTitle,
            quizId: new Model().getModel()[0].quizId,
            questions: new Model().getModel()[0].questions,
            isModalShow: false
        }
        this.onClick = this.deleteQuestion.bind(this);
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

    render() {
        return <div className="container">
            <div className="h2 text-center">{'Editing quiz: ' + this.state.quizTitle}
            </div>
            <ul className="list-group">
                {this.state.questions.map(question => {
                    return <li key={question.questionId} className="list-group-item list-group-flush">
                        <div className="h4 d-flex justify-content-sm-between">
                            {question.questionTitle}
                            <div className="d-flex justify-content-end">
                                <Link to="/admin/edit-question">Edit</Link>
                                <Link to="/admin/statistic">Statistic</Link>

                                <div className="btn btn-outline-danger" onClick={this.deleteQuestion(question)}>Delete</div>
                            </div>
                        </div>

                        {this.Answers(question.questionAnswers)}
                    </li>
                })}
            </ul>
            <div className="btn btn-primary mt-3">Add a question</div>
            <div className="btn btn-primary mt-3 float-right">Save</div>
            {/*<Answers props={(json) => this.fn(json)}/>*/}
        </div>
    }

    deleteQuestion = (question) => () => {
        console.log('deleting element' + question)
        let index = this.state.questions.indexOf(question);
        this.setState({
            questions: this.state.questions.filter((_, i) => i !== index)
        });
    }
}


export default QuizViewer
