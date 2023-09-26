# How to connect multiple Mongo DB Databases with Spring Boot

## docker-compose

```yaml
version: '3.7'

services:
  mongo:
    image: mongo:latest
    restart: always
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: password
      MONGO_INITDB_ROOT_DATABASE: root-db
    volumes:
      - ./init-mongo.js:/docker-entrypoint-initdb.d/init-mongo.js:ro
      - ./data:/data/db
```

## init-mongo.js
```js
db = db.getSiblingDB('db1');
db.createUser(
    {
        user: 'selim',
        pwd:  'q12we34r',
        roles: [{role: 'readWrite', db: 'db1'}],
    }
);

db = db.getSiblingDB('db2');
db.createUser(
    {
        user: 'selim',
        pwd:  'q12we34r',
        roles: [{role: 'readWrite', db: 'db2'}],
    }
);
```

## 출처

- https://hasangalakdinu.medium.com/how-to-connect-multiple-mongo-db-databases-with-spring-boot-239cb464a114