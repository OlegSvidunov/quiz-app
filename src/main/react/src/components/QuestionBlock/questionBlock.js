import React from 'react';
import {Quiz, Result, Warning} from '../RenderUI/renderUI';
import './questionBlock.css';
import Header from "../Header";
import getCurrentHostName from "../../util/getCurrentHostName";

export default class QuestionBlock extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            quiz: null,
            id: 0,
            correctAnswers: null,
            quizResultPercents: null,
            answered: null,
            answers: new Map(),
            finish: false,
            isButtonNextDisabled: true,
            currentQuestion: {}
        };
    }

    componentDidMount() {
        let getQuizByIdApi = "/api/quiz/";
        let targetURL = getCurrentHostName() + getQuizByIdApi + this.props.location.state.quizId;
        console.log("request: GET " + targetURL + this.props.location.state.quizId);
        fetch(targetURL)
            .then(response => response.json())
            .then(result => this.setState({
                quiz: result,
            }))
            .catch(e => {
                console.log('Unable to fetch data from server: ' + e);
            })
    }

    showTasks(arr, id) {
        let tempRes = [];
        arr.map((item) => {
            tempRes.push(item);
            return true;
        });
        return tempRes[id]
    }

    getAnswers(arr, id) {
        let answers = [];
        arr.map((item, index) => {
            if (index === id) {
                item.questionAnswers.map(i => answers.push(i))
            }
            return false;
        });
        return answers.map((el) => {
            return (
                <li className='my-list list-group-item list-group-item-action' key={el._id} id={el._id}>
                    {el.answerTitle}
                </li>)
        })
    }

    addChoiceStyle = (label) => {
        const clazz = document.querySelectorAll('li');
        const choiseClass = 'my-choise'
        clazz.forEach(
            (el) => {
                const add = el.classList;

                console.log(add, label, el)

                if(label === el.id) {
                    if(Array.from(el.classList).includes(choiseClass)){
                        add.remove(choiseClass)
                    }else {
                        add.add(choiseClass)
                    }
                }
            }
        )
    };

    removeChoiceStyle = () => {
        const clazz = document.querySelectorAll('li');
        clazz.forEach((el) => el.classList.remove('my-choise'));
    };

    writeAnswer = (el, id, task) => {
        this.addChoiceStyle(el.id);
        const asw = Array.from(document.querySelectorAll('.list-group-item'));
        const arrayAnswers = asw.map(el => {
            if (el.className.includes('my-choise')) {
                return el.id
            }
            return null
        }).filter(el => !!el)

        console.log('arrayAnswers',
            arrayAnswers)
        if (Object.keys(arrayAnswers).length > 0) {
            this.setState({isButtonNextDisabled: false})
        } else {
            this.setState({isButtonNextDisabled: true})
        }
        let map = new Map(this.state.answers);
        map.set(task._id, arrayAnswers);
        this.setState({answers: map});
        console.log("THIS STATE WITH ANSWERS: ", map)
    }

    onNextClick = () => {
        let {id, quiz, answers} = this.state;
        const lastQuestionIdNumber = quiz.questions.length - 1;
        console.log("ON_NEXT_CLICK compare is -", lastQuestionIdNumber);
        console.log("ON_NEXT_CLICK ID is -", id);
        console.log("ON_NEXT_CLICK QUESTIONS are -", quiz);
        console.log("ON_NEXT_CLICK ANSWERS are -", answers);
        if (id < lastQuestionIdNumber) {
            id++;
            this.setState({id});
            if (id === lastQuestionIdNumber) {
                document.getElementById('btn')
                    .innerHTML = 'Finish';
            }
        } else {
            const answered = this.countMark(answers);
            this.setState({
                finish: true,
                answered
            });
        }
        this.setState({isButtonNextDisabled: true})

        this.removeChoiceStyle()
    };

    countMark = (apiAnswers, variants) => {
        let counter = 0;
        const {quiz} = this.state;
        const j = quiz.length;
        for (let i = 0; i < j; i++) {
            apiAnswers[i] = +(apiAnswers[i]);
            variants[i] = +(variants[i]);
            if (apiAnswers[i] === variants[i]) {
                counter += 1;
            }
        }
        return counter;
    };

    sendDataOnServer = (result, email, quizId) => {
        let submitAnswerApi = "/api/quiz/";
        let targetURL = getCurrentHostName() + submitAnswerApi + quizId;
        console.log("request: POST " + targetURL)
        const answersObject = {
            emailAddress: email,
            questionIdToAnswerId: this.convertMapsToObject(this.state.answers)
        }
        const report = JSON.stringify(answersObject)
        console.log("RESULT", report);
        fetch(targetURL, {
            method: "POST",
            headers: {
                "Content-type": "application/json",
            },
            body: report
        })
            .then(response => response.json())
            .then(response => this.setState({quizResultPercents : response.result}))
            .then((responseJson) => console.log("Response from server", responseJson))
            .catch(error => console.log(error));
    };

    convertMapsToObject = (mapInstance) => {
        const obj = {};
        for(let prop of mapInstance){
            obj[prop[0]] = prop[1];
        }
        return obj;
    }

    render() {

        if (!this.state.quiz) {
            return <div className="h2 text-center">Error on loading data from server</div>
        }
        console.log("--------------", this.state.questions);
            const {id, quiz, answered, finish} = this.state;
            const task = this.showTasks(quiz.questions, id);
            const answers = this.getAnswers(quiz.questions, id);

        if (!finish) {
            console.log("-----!FINISH---------", finish);
            console.log("-----!TASK---------", task);
            console.log("DISABLED", this.state.isButtonNextDisabled)

            return (
                <div className="container">
                    <Header quizTitle={this.state.quiz.quizTitle}/>
                    <Quiz task={task}
                      answers={answers}
                      isDisabled={this.state.isButtonNextDisabled}
                      id={task._id}
                      btn={this.onNextClick}
                      write={this.writeAnswer}/>
                </div>)
        } else {
            if (answered >= 0) {
                if (this.state.quizResultPercents === null) {
                    this.sendDataOnServer(this.state.answers, this.props.email, this.state.quiz._id);
                }
                console.log("-----ANSWERS AFTER QUIZ---------", this.state.answers);
                console.log("-----QUIZ_ID AFTER QUIZ---------", this.state.quiz._id);
                return <Result result={this.state.quizResultPercents} />
            } else {
                return <Warning/>
            }
        }
    }
}
