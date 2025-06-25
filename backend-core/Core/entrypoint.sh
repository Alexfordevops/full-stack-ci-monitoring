#!/bin/sh
set -e

# Espera o PostgreSQL ficar disponível
chmod +x /app/wait-for-db.sh
/app/wait-for-db.sh

# Lê o segredo do banco de dados
if [ -f /run/secrets/db_password ]; then
  export SPRING_DATASOURCE_PASSWORD=$(cat /run/secrets/db_password)
else
  echo "Erro: /run/secrets/db_password não encontrado."
  exit 1
fi

# Define o caminho do segredo JWT
if [ -f /run/secrets/jwt_secret ]; then
  export JWT_SECRET_FILE=/run/secrets/jwt_secret
else
  echo "Erro: /run/secrets/jwt_secret não encontrado."
  exit 1
fi

echo "Segredos carregados. Iniciando aplicação..."
exec java -jar /app/app.war
