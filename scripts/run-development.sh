#!/bin/bash
docker-compose -f ../docker-compose.yml -f ../deployment/development.yml up --force-recreate --remove-orphans 
