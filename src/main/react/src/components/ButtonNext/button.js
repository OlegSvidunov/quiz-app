import React from 'react';
import './button.css';

const ButtonNext = ( { next, isDisabled } ) => {

  return (
    <div className="align-right">
  <button
    onClick={next}
    id='btn'
    className="btn btn-lg btn-info my-btn"
    disabled={isDisabled}>
    Next
  </button>
  </div>
)

}


export {
  ButtonNext
}