#!/bin/sh
docker build -t vsftp .
docker run -d -it --name vsftp -p 2121:21 --restart=always --privileged=true vsftp /data/start.sh