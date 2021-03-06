# map-request-data-to-representation - dwbook-phonebook

maven-archetype-quickstart project

## Compile

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

## cURL HTTP requests

### GET request

> `curl http://localhost:8080/contact/123`

```
$ curl http://localhost:8080/contact/123
{"id":123,"firstName":"John","lastName":"Doe","phone":"+123456789"}
```

### PUT request

> `curl --header "Content-Type: application/json" -X PUT -d '{"firstName": "FOO", "lastName":"BAR", "phone":"987654321"}' http://localhost:8080/contact/123`

```
$ curl --header "Content-Type: application/json" -X PUT -d '{"firstName": "FOO", "lastName":"BAR", "phone":"987654321"}' http://localhost:8080/contact/123
{"id":123,"firstName":"FOO","lastName":"BAR","phone":"987654321"}
```
