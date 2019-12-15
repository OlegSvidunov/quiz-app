import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import {Model} from "./model";

class QuizViewer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            quizTitle: new Model().getModel()[0].quizTitle,
            quizId: new Model().getModel()[0].quizId,
            questions: new Model().getModel()[0].questions
        }
        this.onClick = this.deleteQuestion.bind(this);
    }


    Answers(answers) {
        return <ul className="list-group">
            {answers.map(answer => {
                return <li key={answer.answerId} className="list-group-item list-group-flush">
                    {this.renderIsCorrectCheckbox(answer.correct)}
                    {answer.answerTitle}
                </li>
            })}
        </ul>
    }

    renderIsCorrectCheckbox(isCorrect) {
        return isCorrect
            ? <input className="mr-2" type="checkBox" checked readOnly/>
            : <input className="mr-2" type="checkBox" disabled={true}/>
    }

    render() {
        return <div className="container">
            <div className="h2 text-center">{'Editing quiz: ' + this.state.quizTitle}
            </div>
            <ul className="list-group">
                {this.state.questions.map(question => {
                    return <li key={question.questionId} className="list-group-item list-group-flush">
                        <div className="h4">
                        {question.questionTitle}
                        </div>
                            <div className="col-sm">
                                <div className="d-flex justify-content-end">
                                    <div className="btn btn-outline-primary mr-1">Edit</div>
                                    <div className="btn btn-outline-danger" onClick={this.deleteQuestion(question)}>Delete</div>
                                </div>
                            </div>
                        {this.Answers(question.questionAnswers)}
                    </li>
                })}
            </ul>
            <br/>
            <div className="btn btn-primary">Add a question</div>
            <div className="btn btn-primary float-right">Save</div>
            <br/>
            <br/>

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
