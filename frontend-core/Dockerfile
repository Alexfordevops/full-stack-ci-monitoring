# Etapa 1: build da aplicação
FROM node:20 as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Etapa 2: servir com nginx
FROM nginx:alpine
COPY --from=build /app/dist/frontend-core/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
