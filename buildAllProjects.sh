#!/bin/bash

REPO_PATH='/home/santiago/Documentos/IWTG/ipaymonitor'

cd $REPO_PATH'/datalayer'
mvn clean
mvn install

cd $REPO_PATH'/generic'
mvn clean
mvn install

cd $REPO_PATH'/busineslayer'
mvn clean
mvn install

cd $REPO_PATH'/serviceslayer'
mvn clean
mvn install

cd $REPO_PATH'/facades'
mvn clean
mvn install

cd $REPO_PATH'/ipaymonitor'
mvn clean
mvn install