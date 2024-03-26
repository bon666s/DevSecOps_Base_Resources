@echo off
echo Deteniendo el contenedor some-docker...
docker container stop some-docker

echo Eliminando el contenedor some-docker...
docker container rm some-docker

echo Eliminando el volumen some-docker-certs-ca...
docker volume rm some-docker-certs-ca

echo Eliminando el volumen some-docker-certs-client...
docker volume rm some-docker-certs-client

echo Eliminando la red some-network...
docker network rm some-network

echo Eliminando la imagen dockertrivy:latest...
docker image rm dockertrivy:latest

echo Eliminando la imagen docker:latest...
docker image rm docker:latest

echo Proceso completado.
pause
