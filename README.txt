README:

--------------------------------------------------

- To compile the code from within an IDE, head to the following directory:

.../cs5031p2code

- Run: mvn package

--------------------------------------------------

- To run the Maven tests, head to the directory containing the 'pom.xml' file:

.../cs5031p2code

- Run: mvn test

--------------------------------------------------

- To run the application, head to the following directory:

.../cs5031p2code

- Run: mvn spring-boot:run

--------------------------------------------------

- To view the JavaScript UI, after running the application:

- Head to your browser (preferably Google Chrome to view our time and date dropdown menu correctly as Firefox seems to have issues)

- URL: localhost:8080

--------------------------------------------------

CURL commands:

- List all routes serving a given stop
	curl 'http://localhost:8080/buses?from=Broadway'

- List all routes serving a given stop at a certain time of day
	curl 'http://localhost:8080/buses?from=St.%20Andrews%20Main%20Stop&day=Monday&time=12:00'

- List all times through the day a stop has service
	curl 'http://localhost:8080/buses?from=St.%20Andrews%20Main%20Stop&day=Monday'
