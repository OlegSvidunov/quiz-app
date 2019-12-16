import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'

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
            <div className="h2 text-center">Edit question</div>

            <div className="input-group input-group-lg mb-5">
                <div className="input-group-prepend">
                    <span className="input-group-text" id="inputGroup-sizing-lg">Question: </span>
                </div>
                <input type="text" className="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm"
                       defaultValue={this.state.questionTitle}/>
            </div>

            {this.state.answers.map(answer => {
                return <div key={answer.answerId}>
                    <div className="input-group mb-3">
                        <div className="input-group-append">
                            <span className="input-group-text" id="basic-addon2">Answer: </span>
                        </div>
                        <input type="text" className="form-control" aria-label="Text input with checkbox"
                               defaultValue={answer.answerTitle}/>

                        <div className="input-group-append">
                            <span className="input-group-text" id="basic-addon2">Correct:</span>
                        </div>
                        <div className="input-group-prepend">
                            <div className="input-group-text">
                                <input type="checkbox" aria-label="Checkbox for following text input"/>
                            </div>
                        </div>
                        <div className="btn btn-danger" >Delete</div>

                    </div>

                </div>;
            })}
            <div className="d-flex justify-content-between mt-3 mb-5">
                <div className="btn btn-primary pl-5" onClick={this.addNewAnswer}>Add answer</div>
                <div className="btn btn-primary pl-5" onClick={() => this.props.callback('asd')}>Save</div>
            </div>
        </div>
    }

    addNewAnswer = () => {
        let newAnswer = {
            answerId: this.state.answers.length + 1,
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
}


export default QuestionEditor
