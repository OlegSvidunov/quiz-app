import React, { Component } from 'react';
import CatImage from "../../image/cute_kitty.png";

class Welcome extends Component {
    render() {
        return (
            <div
                id='hello' className="h2 text-center">
                <p>This is the best app ever: The Blue team quiz system</p>
                <img src={CatImage} alt="We've missed our kitty somewhere :("/>
            </div>
        );
    }
}

export default Welcome;