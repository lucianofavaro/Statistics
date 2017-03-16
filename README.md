# Super Cool :-) Statistics API

Allows statics collecting and querying through API REST.

# Building

mvn clean install

** Currently there is no specific profile for integration tests so "mvn clean install" takes around 1 minute. **

# Running

mvn spring-boot:run

# Usage

*POSTing statistics*

curl -H "Content-Type: application/json" -X POST -d '{"amount":"10","timestamp":"1489635490477"}' http://localhost:8081/statistics

*Where:*
amount: could be any double 
timestamp: 13 digits Unix timestamp, milliseconds timestamp

Statistics are accumulated for the last 60 seconds, if timestamp is older than 60 seconds, it will be discarded by application.

You can get current timestamp here for sake of testing: https://currentmillis.com/


*GETing statistics*

http://localhost:8081/statistics 
