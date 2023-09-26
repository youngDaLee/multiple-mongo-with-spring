db = db.getSiblingDB('db_1');
db.createUser(
    {
        user: 'selim',
        pwd:  'q12we34r',
        roles: [{role: 'readWrite', db: 'db_1'}],
    }
);

db = db.getSiblingDB('db_2');
db.createUser(
    {
        user: 'selim',
        pwd:  'q12we34r',
        roles: [{role: 'readWrite', db: 'db_2'}],
    }
);