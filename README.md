# Java Mentoring Program: Spring Core 

This maven-based project contains the exercises defined in the 'spring-code' module of Java Mentoring program.

0) To run the project one have the following options:

- mvn test. Compiles and runs the tests.
- mvn verify. Compiles, runs the test and generates the following reports: jacoco coverage, mutation coverage

1) The code is designed in the following layered approach:
- facade: entry point of the application. It makes use of multiple services in the Service layer.
- service: individual beans that provide specialized features on User, Event or Ticket entity.  
- dao: DB access to the each entity: User, Event, or Ticket. 
- db: pseudo repository. 

2) Unit testing covers 95% of the code and some integration tests added to cover different use cases. You can notice them by the suffix. For example:
- TicketService_bookTicket_Test: Set of unit tests around method: 'bookTicket' (mocks all external dependencies)
- BookingFacadeImpl_cancelTicket_IT: Integration test (loads Spring context based on test-configuration.xml) and runs a use case where a user and event are created, ticket is booked and then cancelled. 

3) Beans are injected via different methods: setter, constructor, @Autowired.

4) Lombok slf4j logging added at Service level. 

5) Spring statements are also displayed during ITs execution.

6) p-namespce used in the configuration.xml for the sake of compactness.

7) Example test data automatically populated via the classic 'init-method' in the configuration.xml 
