mongo -- "$MONGO_INITDB_DATABASE" <<EOF
    var admin = db.getSiblingDB('admin');
    admin.auth(admin, secret);

    db.createUser({user: admin, pwd: secret, roles: ["readWrite"]});

    var discount_db = db.getSiblingDB('discount_db');

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
        "email": "abduallah.ahmad@hotmail.com",
        "purchaserType": "CUSTOMER",
        "registrationDate": "2017-07-11T07:26:44.269Z"
    },
    {
        "email": "m.a@hotmail.com",
        "purchaserType": "CUSTOMER",
        "registrationDate": "2022-05-01T07:26:44.269Z"
    }
    ]);
EOF