#! /bin/bash

docker build -t spring .
docker run --name "spring" -v /usr/local/upload:/usr/local/upload -d -p 8090:8090 spring