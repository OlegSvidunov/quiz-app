//returns a string with current host name, e.g: 'http://localhost:3000/' or 'https://quiz-blue-luxoft.herokuapp.com'
function getCurrentHostName() {
    const currentPageURL = window.location.href;
    const urlArray = currentPageURL.split("/");
    return urlArray[0] + "//" + urlArray[2];
}

export default getCurrentHostName;