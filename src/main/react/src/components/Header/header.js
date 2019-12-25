import React  from 'react';
import './header.css';

const  Header = ({quizTitle}) => {
    return (
      <div className='my-container'>
     <h2>{quizTitle}</h2>
    </div>
    );

}

export default Header;
