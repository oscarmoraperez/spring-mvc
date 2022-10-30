# Spring MVC 

This maven-based project contains a demo project to learn how to use MVC design pattern in a simple business case of Events / Tickets and Users.

0) To run the project one have the following options:

- mvn test. Compiles and runs the tests.
- mvn verify. Compiles, runs the test and generates the following reports: jacoco coverage

The code is based on the previous module (Srping Core) with the following improvements:
1) Extra controller layer added. It calls the existing BookingFacade under the hood.

2) Proper persistance layer added based on Spring JPA. Previous implementation based on in memory Map has been removed)

4) For the sake of simplicity H2 DB is being used. The nature of Spring JPA would make very easy to use other Vendors like PostgreSQL or MySQL.

5) Added Springboot and convention based configuration. No more XML.

6) Global Exception handler also added.

7) Dispatcher added.

8) PDF controller (BookingControllerPDF.java). It returns a PDF version of the list of tickets of a user (if the request contains the header: application/pdf)  

9) Simple views added using Thymeleaf. You will find the templates in /resources/templates/*html

10) Data loader using 2 mechanism:
- data.sql (Script SQL with all the insert statements)
- Spring OXM + JAX2B (package loader) that can load a set of Ticket from a XML file. It uses a programatic transactional manager.

9) Unit tests

10) Integration tests using MockMVC.

11) Logging via slf4j + lombok

How to run the application:

Locate the file: SpringMvcApplication and run it standalone. It will start the application including the population of the DB:

Examples:
- To see the event number 2, browse: http://localhost:8080/event/2
- To see event with title = 'Hamlet', browse: http://localhost:8080/eventsByTitle?title=Hamlet&pageNum=0&pageSize=4