import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import uuidv1 from "uuid/v1"

class QuestionEditor extends React.Component {
    constructor(props) {
        super(props);
        console.log(this.props);
        this.state = {
            _id: props.question._id,
            questionTitle: props.question.questionTitle,
            questionAnswers: props.question.questionAnswers,
        }
    }


    render() {
        return <div className="container ">
            <div className="h1 text-center mb-5">Question editor</div>


            <div className="input-group input-group-lg mb-5">
                <div className="input-group-prepend">
                    <span className="input-group-text" id="inputGroup-sizing-lg">Question: </span>
                </div>
                <input type="text" className="form-control"
                       defaultValue={this.state.questionTitle}
                       onChange={(e) => this.setQuestionTitle(e.target.value)}/>
            </div>

            {this.state.questionAnswers.map(answer => {
                return <div key={answer._id}>
                    <div className="input-group mb-3">
                        <div className="input-group-append">
                            <span className="input-group-text">Answer: </span>
                        </div>
                        <input type="text" className="form-control"
                               defaultValue={answer.answerTitle}
                               onChange={(e) => this.setAnswerTitle(answer, e.target.value)}/>

                        <div className="input-group-append">
                            <span className="input-group-text">Correct:</span>
                        </div>
                        <div className="input-group-prepend">
                            <div className="input-group-text">
                                <input type="checkbox" checked={answer.isCorrect}
                                       onChange={(e) => this.setIsCorrectStatus(answer, e.target.checked)}/>
                            </div>
                        </div>
                        {
                            Object.keys(this.state.questionAnswers).length > 2
                                ?  <div className="btn btn-danger" onClick={this.deleteAnswer(answer)}>Delete</div>
                                : false
                        }
                    </div>

                </div>;
            })}
            <div className="d-flex justify-content-between mt-3 mb-2">
                <div className="btn btn-primary" onClick={this.addNewAnswer}>Add answer</div>
                <div className="btn btn-primary" onClick={() => this.props.callback(this.state)}>Save question</div>
            </div>
        </div>
    }

    setAnswerTitle = (answer, value) => {
        console.log('text field value: ' + value)
        let list = [...this.state.questionAnswers];
        let index = list.indexOf(answer);
        let curAnswer = list[index];
        curAnswer.answerTitle = value;
        list[index] = curAnswer;

        this.setState({
            questionAnswers: list
        })
        console.log(this.state.questionAnswers)
    }

    setIsCorrectStatus = (answer, value) => {
        console.log('checkbox value: ' + value)
        let list = [...this.state.questionAnswers];
        let index = list.indexOf(answer);
        let curAnswer = list[index];
        curAnswer.isCorrect = value;
        list[index] = curAnswer;

        this.setState({
            questionAnswers: list
        })
        console.log(this.state.questionAnswers)
    }

    addNewAnswer = () => {
        let UUID = uuidv1();
        let newAnswer = {
            answerId: UUID,
            answerTitle: "New answer",
            isCorrect: false
        };

        this.setState(state => {
            const list = [...state.questionAnswers, newAnswer];
            return {
                questionAnswers: list,
            };
        })
    };

    deleteAnswer = (answer) => () => {
        console.log('deleting element' + answer);
        let index = this.state.questionAnswers.indexOf(answer);
        this.setState({
            questionAnswers: this.state.questionAnswers.filter((_, i) => i !== index)
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
