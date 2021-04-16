FROM node:15.12.0
EXPOSE 8080

COPY . ./node

WORKDIR /node
RUN npm install
CMD ["npm", "start"]
