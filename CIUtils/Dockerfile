# Usar docker:latest como imagen base
FROM docker:latest

# Instalar dependencias necesarias para Trivy (wget y tar ya están disponibles)
# Instalar Trivy
COPY trivy_0.18.3_Linux-64bit.tar.gz trivy_0.18.3_Linux-64bit.tar.gz
RUN tar zxvf trivy_0.18.3_Linux-64bit.tar.gz && \
    mv trivy /usr/local/bin

# Limpiar archivos no necesarios
RUN rm trivy_0.18.3_Linux-64bit.tar.gz

# Tu Dockerfile no necesita más comandos para la instalación de Trivy.
# Los siguientes comandos son ejemplos de cómo podrías configurar tu contenedor para usar Trivy.
# Por ejemplo, para escanear una imagen, usarías un comando similar al siguiente cuando ejecutes tu contenedor:
# docker run --rm tu-imagen-personalizada trivy image [nombre_de_tu_imagen]
