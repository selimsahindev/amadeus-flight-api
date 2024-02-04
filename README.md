<h1>About the project</h1>
This is the Backend (Java) Developer Case Assignment within the Future Talent Program of Amadeus & Coderspace.
<br>
<br>
Author: <a href="https://github.com/selimsahindev" target="_blank">selimsahindev</a>
<br>
<br>

## Check out the Swagger API documentation 📦
You need to run the project on your local environment first.
<br>

<a href="http://localhost:8080/swagger-ui/index.html" target="_blank"><strong>localhost:8080/swagger-ui/index.html</strong></a>
<br>

Prepare your PostgreSQL server and then seed your airports and flights using the `/api/v1/jobs/trigger` `[POST]` endpoint!

<br>

## You can also find the Postman documentation here!
<a href="https://www.postman.com/selimsahindev/workspace/amadeus-flight-api/collection/9112554-c03fd498-a6e0-411c-9aea-91f1dc00b84b?action=share&creator=9112554" target="_blank"><strong>Run in Postman</strong></a> 🚀

You will need to set up Basic Authentication in Postman before executing any requests. To do this, you should create an API user with appropriate credentials.

<br>


# How to use?

### Description
This API provides access to comprehensive flight data based on given parameters. It allows users to search for flights between specified departure and arrival airports on a given departure date. Optionally, users can also retrieve return flights for the same airports by providing a return date.

### Search Endpoint
`/api/v1/flights/search`

### Method
`GET`

## Request Parameters

1. `departureAirportId` (required)
   - Type: Integer
   - Description: The unique identifier of the departure airport.
   - Example: `1`

2. `arrivalAirportId` (required)
   - Type: Integer
   - Description: The unique identifier of the arrival airport.
   - Example: `2`

3. `departureDate` (required)
   - Type: String (ISO 8601 Date Format: "YYYY-MM-DD")
   - Description: The departure date for the outbound flight.
   - Example: `"2024-02-20"`

4. `returnDate` (optional)
   - Type: String (ISO 8601 Date Format: "YYYY-MM-DD")
   - Description: The return date for the inbound flight. If provided, it fetches return flights with the same airports.
   - Example: `"2024-02-20"`
