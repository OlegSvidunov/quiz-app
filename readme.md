#### Blue team quiz-platform application

Last stable app version deployed here: [https://quiz-blue-luxoft.herokuapp.com][app link]

Build and run on local machine(requires git and maven installation):
* `$ mkdir quiz-blue`  
* `$ cd quiz-blue`  
* `$ git clone https://username@bitbucket.org/lux_blue/quiz-blue.git`  
* `$ mvn spring-boot:run`

Default port is 8080, make sure it isn't busy by another processes.
First installation and build may take few minutes, due to local node and npm bootstrap. Consecutive builds are faster. 

* `$ mvn clean spring-boot:run` - Deploy to localhost with clearing work directory:   
* `$ mvn clean spring-boot:run -P dev` - Activate 'dev' profile(skips React tasks - much faster build): 


[app link]: https://quiz-blue-luxoft.herokuapp.com 