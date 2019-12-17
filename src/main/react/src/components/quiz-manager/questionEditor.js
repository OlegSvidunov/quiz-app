import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import generateUUID from "../../util/generateUUID";

class QuestionEditor extends React.Component {
    constructor(props) {
        super(props);
        console.log(this.props);
        this.state = {
            questionId: props.question.questionId,
            questionTitle: props.question.questionTitle,
            answers: props.question.questionAnswers,
        }
    }


    render() {
        return <div className="container ">
            <div className="h2 text-center">Question editing</div>

            <div className="input-group input-group-lg mb-5">
                <div className="input-group-prepend">
                    <span className="input-group-text" id="inputGroup-sizing-lg">Question: </span>
                </div>
                <input type="text" className="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm"
                       defaultValue={this.state.questionTitle} onChange={(e)=> this.setQuestionTitle(e.target.value)}/>
            </div>

            {this.state.answers.map(answer => {
                return <div key={answer.answerId}>
                    <div className="input-group mb-3">
                        <div className="input-group-append">
                            <span className="input-group-text" id="basic-addon2">Answer: </span>
                        </div>
                        <input type="text" className="form-control" aria-label="Text input with checkbox"
                               defaultValue={answer.answerTitle} onChange={(e)=> this.setAnswerTitle(answer, e.target.value)}/>

                        <div className="input-group-append">
                            <span className="input-group-text" id="basic-addon2">Correct:</span>
                        </div>
                        <div className="input-group-prepend">
                            <div className="input-group-text">
                                <input type="checkbox" onChange={(e)=> this.setIsCorrectStatus(answer, e.target.checked)} aria-label="Checkbox for following text input"/>
                            </div>
                        </div>
                        {
                            Object.keys(this.state.answers).length > 2
                                ?  <div className="btn btn-outline-danger" onClick={this.deleteAnswer(answer)}>Delete</div>
                                : false
                        }
                    </div>

                </div>;
            })}
            <div className="d-flex justify-content-between mt-3 mb-5">
                <div className="btn btn-primary" onClick={this.addNewAnswer}>Add answer</div>
                <div className="btn btn-primary" onClick={() => this.props.callback(this.state)}>Save</div>
            </div>
        </div>
    }

    setAnswerTitle = (answer, value) => {
        console.log('text field value: ' + value)
        let list = [...this.state.answers];
        let index = list.indexOf(answer);
        let curAnswer = list[index];
        curAnswer.answerTitle = value;
        list[index] = curAnswer;

        this.setState({
            answers: list
        })
        console.log(this.state.answers)
    }

    setIsCorrectStatus = (answer, value) => {
        console.log('checkbox value: ' + value)
        let list = [...this.state.answers];
        let index = list.indexOf(answer);
        let curAnswer = list[index];
        curAnswer.isCorrect = value;
        list[index] = curAnswer;

        this.setState({
            answers: list
        })
        console.log(this.state.answers)
    }

    addNewAnswer = () => {
        let UUID = generateUUID();
        let newAnswer = {
            answerId: UUID,
            answerTitle: "New answer",
            isCorrect: false
        };

        this.setState(state => {
            const list = [...state.answers, newAnswer];
            return {
                answers: list,
            };
        })
    };

    deleteAnswer = (answer) => () => {
        console.log('deleting element' + answer);
        let index = this.state.answers.indexOf(answer);
        this.setState({
            answers: this.state.answers.filter((_, i) => i !== index)
        });
    };

    setQuestionTitle(value) {
        console.log("change question title: ", value)
        this.setState({
            questionTitle : value
        })
        console.log(this.state)
    }
}


export default QuestionEditor
