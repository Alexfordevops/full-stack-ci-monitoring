# Etapa 1: build da aplicação
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia arquivos de build e resolve dependências com cache
COPY pom.xml .
COPY src ./src

# Compila a aplicação e gera o JAR
RUN mvn clean package -DskipTests

# Etapa 2: imagem final otimizada
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o JAR da etapa anterior
COPY --from=build /app/target/Core-0.0.1-SNAPSHOT.war app.war

# Copia o script de espera
COPY wait-for-db.sh ./wait-for-db.sh
COPY entrypoint.sh ./entrypoint.sh
RUN chmod +x ./wait-for-db.sh ./entrypoint.sh

#Instala o psql client
RUN apt-get update && apt-get install -y postgresql-client

# Expõe a porta
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["./entrypoint.sh"]
