README
======
Model - central microservice with unit tests crap name shoul have called it central!
Competition - records changes pushed from Model if the nickname changes
Search - Duplicate of Model items allows search by name (minimal implementation)

Assumptions
===========
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