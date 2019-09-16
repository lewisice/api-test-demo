#!/bin/bash
docker ps | grep api-container &> /dev/null
if [ $? -ne 0 ]
then
   echo " not exist,we will start up it!!!"
else
    echo "exist!!!"
    docker stop api-container && docker rm api-container
fi

