db = db.getSiblingDB("db1")

db.createUser(
    {
        user: 'selim',
        pwd:  'q12we34r',
        roles: [{role: 'readWrite', db: 'db1'}],
    }
);

db = db.getSiblingDB("db2")
db.createUser(
    {
        user: 'selim2',
        pwd:  'q12we34r',
        roles: [{role: 'readWrite', db: 'db2'}],
    }
);