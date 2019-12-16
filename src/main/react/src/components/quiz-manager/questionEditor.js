import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'

class QuestionEditor extends React.Component {
    constructor(props) {
        super(props);
    }



    render() {
        return <div className="container ">
            <div className="h2 text-center">Edit question</div>



                <div className="btn btn-primary" onClick={() => this.props.callback('asd')}>Save</div>
        </div>
    }
}


export default QuestionEditor
