# Introduction
Microservice developed in Spring Boot which has 3 endpoints:
- Enriches the data provided by the provided external endpoints and stores them in the database:
POST http://localhost:8081/v1/albums/enrich
- Enriches the data provided by the provided external endpoints and returns it without storing it in database:
GET http://localhost:8081/v1/albums/enrich
- Returns all data stored in the database:
GET http://localhost:8081/v1/albums

# Decisions
The exercise has been tackled by implementing a Hexagonal architecture, adhering to the SOLID principles. 
This architecture aims to isolate the domain layer from infrastructure and external dependencies, minimizing 
the potential impact of future changes, such as a database switch. Consequently, even Lombok hasn't been utilized 
in the domain layer.

In the algorithm that enriches the albums, it has been decided to use ArrayList for the list of album's photos since
insertions are always made in the final position, therefore the time complexity is linear O(1).
LinkedList also has linear complexity if elements are inserted in the last position but uses more
memory as it is prepared to allow efficient insertions/deletions at any position in the list.
For the Albums Map, HashMap has been used because of its O(1) linear time complexity in 'put' operations and because
we do not need an order in the keys.

Unit tests have been performed (with 92% coverage of classes, 79% of methods, and 81% of tested lines) 
and integration tests for the repository layer.

# Technologies and libraries
- Spring Boot 3.2.2
- Java 17
- H2 2.2.224
- Open API 2.2.0
- Lombok 1.18.30
- Junit 5
- Mockito 5


- H2 has been used as an in-memory database which can be accessed at http://localhost:8081/h2-console
- The REST service is available at http://localhost:8081/swagger-ui/index.html