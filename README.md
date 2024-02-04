<h1>Amadeus</h1>
Travel to Future Program | Backend (JAVA) developer case assignment.<br>
Author: <a href="https://github.com/selimsahindev">selimsahindev</a>

## Check out the Swagger API documentation 📦
You need to run the project on your local environment first.

<a href="http://localhost:8080/swagger-ui/index.html">localhost:8080/swagger-ui/index.html</a>
<br>

**Note:** Don't forget to set up a PostgreSQL server and seed your airports and flights using the `/api/v1/jobs/trigger` `[POST]` endpoint!

<br>

## You can also find the Postman Documentation here!
<a href="https://www.postman.com/selimsahindev/workspace/amadeus-flight-api/collection/9112554-c03fd498-a6e0-411c-9aea-91f1dc00b84b?action=share&creator=9112554"><strong>Run in Postman</strong></a> 🚀

<br>

# Flight Search API

## Description
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
