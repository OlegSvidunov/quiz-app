import React  from 'react';
import './header.css';

const  Header = ({quizTitle}) => {
    return (
      <div className='text-center h4 mb-3 mt-5'>
     <h2>{quizTitle}</h2>
    </div>
    );

}

export default Header;
