FROM node:17.8.0
EXPOSE 8080

COPY . ./app

WORKDIR /app
RUN npm install
CMD ["npm", "start"]
