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

SET IMAGE_NAME=docker:dind

REM Paso 1: Eliminar contenedores (la línea específica de xargs se omite aquí)
FOR /f "tokens=*" %%i IN ('docker ps -a --filter "ancestor=%IMAGE_NAME%" --format "{{.ID}}"') DO docker rm -f %%i

REM Paso 2: Eliminar imagen
docker rmi %IMAGE_NAME%


echo Proceso completado.
pause
