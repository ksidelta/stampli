FROM node:16.13.1
EXPOSE 8080

COPY . ./app

WORKDIR /app
RUN npm install
CMD ["npm", "start"]
