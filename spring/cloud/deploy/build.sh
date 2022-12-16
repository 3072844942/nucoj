#!/bin/sh

docker build -t cloud .
docker run -d -p 8100:8100 --name "cloud" cloud