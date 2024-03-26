#!/bin/bash

# Descargar Trivy utilizando wget
max_retries=3
attempt_num=1

while [ $attempt_num -le $max_retries ]; do
    wget https://github.com/aquasecurity/trivy/releases/download/v0.18.3/trivy_0.18.3_Linux-64bit.tar.gz -O trivy.tar.gz
    if [ $? -eq 0 ]; then
        echo "Descarga completada con éxito."
        break
    else
        echo "Intento $attempt_num de descarga falló."
        if [ $attempt_num -eq $max_retries ]; then
            echo "Error al descargar Trivy tras $max_retries intentos."
            exit 1
        fi
        echo "Reintentando..."
        attempt_num=$((attempt_num+1))
        sleep 5 # Espera 5 segundos antes de reintentar
    	fi
done

# Descomprimir Trivy
echo "Descomprimiendo Trivy..."
tar zxvf trivy.tar.gz

if [ $? -eq 0 ]; then
    echo "Descompresión completada con éxito."
else
    echo "Error al descomprimir Trivy."
    exit 1
fi

# Mover Trivy a /usr/local/bin
echo "Moviendo Trivy a /usr/local/bin..."
mv trivy /usr/local/bin

if [ $? -eq 0 ]; then
    echo "Trivy se ha movido con éxito."
else
    echo "Error al mover Trivy."
    exit 1
fi

echo "Instalación de Trivy completada con éxito."
