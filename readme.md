# Blue team quiz-platform application

### Last stable app version deployed here: [https://quiz-blue-luxoft.herokuapp.com][app link]

#### Build and run on local machine(requires git and maven installation): 
* `$ git clone https://username@bitbucket.org/lux_blue/quiz-blue.git`  
* `$ cd quiz-blue`  
* `$ mvn spring-boot:run`

#### Option 1: deplot with clearing work directory:     
* `$ mvn clean spring-boot:run`   
#### Option 2: deploy wiht 'dev' profile(skip static files generation - much more faster):  
* `$ mvn clean spring-boot:run -P dev` 

Default port is 8080, make sure it isn't busy by another processes.  First installation and build may take few minutes, due to local node and npm bootstrap. Consecutive builds are faster. 


[app link]: https://quiz-blue-luxoft.herokuapp.com 