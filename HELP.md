#### Blue team quiz-platform application

Last app version deployed here: [https://quiz-blue-luxoft.herokuapp.com][app link]

Build and run on local machine(requires git and maven installation): <br />
`$ mkdir quiz-blue` <br />
`$ cd quiz-blue` <br />
`$ git clone https://oleg_svidunov@bitbucket.org/lux_blue/quiz-blue.git` <br />
`$ mvn spring-boot:run`

Default port is 8080, make sure it isn't busy by another processes.

First installation and build may take few minutes, due to local node and npm bootstrap. Consecutive builds are faster. <br />

Options:
1. Deploy to localhost with clearing work directory: <br />
`$ mvn clean spring-boot:run` <br />
2. Fast deploy, skipping React tasks: <br />
`$ mvn clean spring-boot:run -P dev` <br />


[app link]: https://quiz-blue-luxoft.herokuapp.com 