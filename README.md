# Palindrome

Create a webservice in Spring Boot with REST API that calculates the longest
palindrome in the text content submitted to it. Only alphabetic characters counts, everything 
else is ignored, as from validity, as from length point of view.

Examples:
+ `abrakadabra` ->`ada`: 3
+ `abraka d abra` -> `ada`: 3
+ `abraka|$++d++$|abra` -> `ada` 3

## Requirements

### Running with Docker
+ Docker 

### Running without Docker

+ JDK 11
+ Postgres database

## Usage

Create a `.env` file in the root of the repository, then run
```shell
docker compose up
```
See [example](example.env) for the configuration possibilities.

## API documentation

The app has self-hosted OpenAPI documentation. [Browse](http://localhost:8080/swagger-ui/index.html) after starting the application.

## Running tests

The tests do not have a container, they can be run with Gradle (For requirements see: [Running without docker](#running-without-docker)). The tests require a database 
named `palindrome_test` by default (has to be created manually). This database is also migrated by Flyway, but it has additional
[migrations](./src/main/resources/db/test_data) to create initial content. These migrations will clean the
database before running the scripts.

Running the tests:
```shell
gradle test 
```

## Known limitations

The app does not keep track of what timezone the request had. This means that if your payload
contains a different timezone than UTC, the responses will still show the timestamps in UTC instead
of your local time.

The Hikari Pool complains about closed DB connections, it is probably something with the Docker networking
(like shorter connection TTL that Postgres would have itself).

## Changed longest palindrome algorithm

### Original algorithm

The initial algorithm created all possible substrings, sorted them by length descending,
and started to check the candidates one by one. This is consuming much more resources than needed, 
and runs pretty slow if a long text has a short palindrome.

**Test with 5 paragraph of Lorem ipsum:** appr. 27s

### Current algorithm

Instead of creating all possible substrings, creating them starting with the longest, and
testing it immediately.

**Test with the same 5 paragraph of Lorem ipsum:** appr. 3s

### Improvement ideas

In case we want even faster performance, and less CPU load, we could create a cache
(storage/memory heavier) with expiration, so frequently tested texts could be resolved faster.
