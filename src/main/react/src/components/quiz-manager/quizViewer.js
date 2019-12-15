import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import {Model} from "./model";

class QuizViewer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            model: new Model().getModel()[0]
        }
    }


    Answers(answers) {
        return <ul className="list-group">
                {answers.map (answer => {
                    return <li className="list-group-item list-group-flush">
                            <input type="checkBox" value={}/>
                             - {answer.answerTitle}
                           </li>
                })}
               </ul>
    }

    render() {
        return <div className="container">
                    <div className="h2 text-center">{this.state.model.quizTitle}</div>
                    <ul className="list-group">
                        {this.state.model.questions.map (question => {
                            return <li className="list-group-item list-group-flush">
                                      {question.questionTitle}
                                      {this.Answers(question.questionAnswers)}
                                   </li>
                        })}
                    </ul>

               </div>
    }
}


export default QuizViewer
