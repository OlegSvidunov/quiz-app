import React from 'react';
import {ButtonNext} from '../ButtonNext';
import './renderUI.css';
import {Link} from "react-router-dom";
import {ResultBlock} from "../UserHome/UserQuizList";

const Quiz = ({task, answers, id, btn, write, isDisabled}) => {
    return (
        <div className='main-div'>
            <div className="text-left h4">
                {task.questionTitle}
            </div>
            <div>
                <ul
                    onClick={(e) => {
                        write(e.target, id, task)
                    }}
                    className="list-group my-group">
                    {answers}
                </ul>
            </div>
            <ButtonNext next={btn} isDisabled={isDisabled}/>
        </div>
    )
};

const Result = ({result}) => {
    return (
        <div className="container mt-5 text-center">
            <div className="h3 text-center">
                Your result is:
            </div>
            <div className="h1 text-center">
                {ResultBlock(result)}
            </div>
            <div>
                <Link to="/user/home">
                    <button className="btn btn-lg btn-secondary mt-5">Back to home</button>
                </Link>
            </div>
        </div>)
}

const Warning = () => {
    return (
        <div className='main-div  my-result'>
            <h3 className='text-center my-vertical'>
                Не так быстро! вы пропустили все вопросы
            </h3>
        </div>
    )
}

export {
    Quiz,
    Result,
    Warning
}