# Store Discount Systems

#### Calculates all discounts on a given bill.

A client can pass a bill with specific details as shown in the example below
to find out how the net payment amount .

```
curl --location --request POST 'http://127.0.0.1:8080/sds/v1/discounts/payable-amount' \
--header 'Content-Type: application/json' \
--data-raw '{
    "currency": "SAR",
    "purchasedAt": "2022-05-30T00:00:00.000Z",
    "purchaserId": "moath.alshorman@hotmail.com",
    "items": [
        {
            "type": "OTHER",
            "name": "shoes",
            "price": 100.00
        }
    ]
}'
```

A response to the above response would be the total amount after applying all the discounts.


####To Run the service along with mongodb instance, execute the following commands:

```
mvn clean package
docker-compose up -d --build
```

or run the `runner.sh` file in the command prompt to do the above command at once

#### To run all the tests execute the following command

```
mvn clean test
```

####To generate jacoco test report, execute the following command

```
mvn jacoco:report
```

####OpenApi docs

```
http://localhost:8080/sds/v1/swagger/swagger-ui/index.html
```

####Code Quality checks

```
mvn checkstle:check spotbugs:check pmd:check
```
