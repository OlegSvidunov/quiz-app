#### Blue team quiz-platform application

Last app version deployed here: [https://quiz-blue-luxoft.herokuapp.com][app link]

Build and run on local machine(requires git and maven installation):
`$ mkdir quiz-blue`
`$ cd quiz-blue`
`$ git clone https://oleg_svidunov@bitbucket.org/lux_blue/quiz-blue.git`
`$ mvn spring-boot:run`

Default port is 8080, make sure it isn't busy by another processes.
First installation and build may take few minutes, due to local node and npm bootstrap. Consecutive builds are faster. 

Options:
1. Deploy to localhost with clearing work directory:
`$ mvn clean spring-boot:run`
2. Activate 'dev' profile(skips React tasks - much faster build):
`$ mvn clean spring-boot:run -P dev`


[app link]: https://quiz-blue-luxoft.herokuapp.com 