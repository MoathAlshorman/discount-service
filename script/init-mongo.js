db.createUser({
    user: 'test',
    pwd: 'Pa$$w0rd',
    roles: [
        {
            role: 'readWrite',
            db: 'discount_db',
        },
    ],
});

db = new Mongo().getDB("discount_db");

db.createCollection('thresholds', {capped: false});
db.createCollection('percentages', {capped: false});
db.createCollection('purchasers', {capped: false});

db.thresholds.insert(
        {
            "thresholdAmount": 1000.000,
            "discountAmountPerThreshold": 25.000
        });

db.percentages.insert([
    {
        "purchaserType": "EMPLOYEE",
        "percentage": 20.000
    },
    {
        "purchaserType": "AFFILIATE",
        "percentage": 5.000
    },
    {
        "purchaserType": "CUSTOMER",
        "percentage": 2.000
    },
]);

db.purchasers.insert([
    {
        "email": "moath.alshorman@hotmail.com",
        "purchaserType": "EMPLOYEE",
        "registrationDate": "2015-07-11T07:26:44.269Z"
    },
    {
        "email": "ahmad.ali@hotmail.com",
        "purchaserType": "AFFILIATE",
        "registrationDate": "2016-07-11T07:26:44.269Z"
    },
    {
        "email": "moath.alshorman@hotmail.com",
        "purchaserType": "CUSTOMER",
        "registrationDate": "2017-07-11T07:26:44.269Z"
    },
    {
        "email": "moath.alshorman@hotmail.com",
        "purchaserType": "CUSTOMER",
        "registrationDate": "2022-05-01T07:26:44.269Z"
    },
]);


