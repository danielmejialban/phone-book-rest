# Utiliza una imagen base de Java para construir la imagen de Docker
FROM adoptopenjdk:17-jdk-hotspot

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación en el contenedor
COPY target/phone-book.jar app.jar

# Expone el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
