# Stampli

[![Build Status](https://drone.at.hsp.sh/api/badges/oneacik/stampli/status.svg)](https://drone.at.hsp.sh/oneacik/stampli)

## Introduction

Stampli is an application for collecting stamps.

## Development

### Starting application

To start application locally you need to:  
- run `mvn package` in stampli-backend  
- run `docker-compose -f ./docker-compose.yml -f ./deployment/development.yml up`  

It will start application on port 80.

### Developing

#### Backend

Backend doesn't have yet quick reload.  
Java Package needs to be rebuilt every time and docker-compose needs to be restarted.  
TODO: fix this shit  

#### Frontend

Frontend will reload on change, however it is not using hot reload so page needs to be refreshed.

#### Problems

Logging via google isn't working locally.

### CI/CD

#### Drone

We are using drone for CI and CD.  
Please check out `.drone.yml`
