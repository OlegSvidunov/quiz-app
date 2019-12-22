import React from 'react';

const ButtonNext = ({next, isDisabled}) => {

    return (
        <div>
            <button
                onClick={next}
                id='btn'
                className="btn btn-lg btn-primary float-right mt-3"
                disabled={isDisabled}>
                Next
            </button>
        </div>
    )
}

export {
    ButtonNext
}