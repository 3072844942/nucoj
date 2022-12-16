#!/bin/sh

VOLUME /judge
docker build -t judge .
docker run -d -p 8080:8080 -v /usr/local/upload:/usr/local/upload --name "judge" judge