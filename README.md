README
======
Model - central microservice with unit tests crap name should have called it central!
Competition - records changes pushed from Model if the nickname changes
Search - Duplicate of Model items allows search by name (minimal implementation)

Process
=======
Development stage 1 produce a table USERS
Development stage 2 produce skeleton of the Controllers
Development stage 3 produce skelaton of Services
Development stage 4 produce tests for the happy path.
Development stage 5 produce ModelService to pass tests
Development stage 6 produce DbHelper to pass tests
Development stage 7 implement SearchService
Development stage 8 implement CompetitionService
Development stage 9 deploy on tomcat and use postman to integrate
couldn't figure out another way to perform integration tests
Each of the microservices were run seperately however most of the problems
found where in integration I need a better way to ferret out the 
integration problems.

Running the stuff
=================
Underneath the target directories you should find the Model.war, Competition.war and Search.war.
I used tomcat 8.5.15 and put these war files under the webapps directory and started and waited.

GET localhost:8080/Model/users gives what is in the Db
GET localhost:8080/Model/users/country/country gives users that match country from Db
POST localhost:8080/Model/users adds users to the Db { "users": [ {...},{...},...]}
PUT localhost:8080/Model/users/id modifies the user at the given id {...}
DELETE localhost:8080/Model/users/id deletes the user at the id

GET localhost:8080/Competition/users gives what is in the Db(RAM)
PUT localhost:8080/Competition/users/id modifies the user at the given id {...}
DELETE localhost:8080/Competition/users/id deletes the user at the id

GET localhost:8080/Search/users gives what is in the Db(RAM)
GET localhost:8080/Search/users/name gives what is in the Db matching the name
POST localhost:8080/Search/users adds users to the Db { "users": [ {...},{...},...]}
PUT localhost:8080/Search/users/id modifies the user at the given id {...}
DELETE localhost:8080/Search/users/id deletes the user at the id

Compiling the stuff
===================
mvn package produces the war in target
Note empty the Db and deploy Search and Competition before running the mvn package for Model (unless you turn off the tests)
this is a bit naff I should have used a different Db for the tests but I haven't so there it is it does leave clean

Decisions
=========
Model only link to Db Competition and Search just use RAM ArrayList.
Search is initialised when POST Model/users performed.
Competition not initialised users put in there when PUT Model/users/id with a change in nickname.
Simple search performed only country.
Search only performs name search.
Limited error handling and testing due to time taken so far. Some Syster.out.println's left in for debugging should be taken out.
Logging not used due to lack of progress yesterday had to learn how to deploy spring boot in tomcat completed today and it was easy.
Testing should be more extensive checking for correct error handling, although it takes long enough.
Some integration testing done in Model chose to use postman for integration testing of the 3 microservices.
Would have used the observer pattern for a general solution with multiple Search and Competition microservices.
Which means there would have needed to be register and unregister POST calls.
Note tests could be run in parallel if there were a seperate Db for each test and tomcat instance.
I'm not entirely happy with Model's POST command it works but dumps the entire db to Search to keep them in sync
perhaps Search should get on startup however, this would mean Search would always have to start after Model at
the moment they don't do anything on startup.
This is now fixed if Search has just been started along with the rest GET Search/init can be called.