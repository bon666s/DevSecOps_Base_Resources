@echo off
echo Creando la red some-network...
docker network create some-network

echo Creando el volumen some-docker-certs-ca...
docker volume create some-docker-certs-ca

echo Creando el volumen some-docker-certs-client...
docker volume create some-docker-certs-client

echo Iniciando el contenedor some-docker...
docker run --privileged --name some-docker -d --network some-network --network-alias docker -e DOCKER_TLS_CERTDIR=/certs -v some-docker-certs-ca:/certs/ca -v some-docker-certs-client:/certs/client docker:dind

echo Contenedor some-docker iniciado correctamente.
pause
