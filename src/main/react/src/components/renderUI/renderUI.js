import React from 'react';
import { ButtonNext } from '../ButtonNext';
import './renderUI.css';
import {Link} from "react-router-dom";

const Quiz = ({ task, answers, id, btn, write, isDisabled}) => {
  return <div className='main-div'>
    <h3 className="text-center " >
      {task.questionTitle}
    </h3>
    <div >
      <ul
        onClick={(e) => { write(e.target, id, task) }}
        className="list-group my-group">
        {answers}
      </ul>
    </div >
    <ButtonNext next={btn} isDisabled={isDisabled}/>
  </div >;
};

const Result = ({ result }) => {
  return (<div className="container mt-5">
    <div className='main-div my-result'>
      <h3 className="text-center my-vertical">
        {`Your result is ${result}%`}
          <Link to="/user/home">
            <button className="btn btn-lg btn-info my-bt">Back to home</button>
          </Link>
      </h3>
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