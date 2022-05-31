# Store Discount Systems

### Calculates all discounts on a given bill.

A client can pass a bill as shown in the `curl` request example below to calculate the net payment amount based on configured discounts.

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

A response to the above response would be the net amount after applying all the preconfigured discounts (please check `init-mongo.sh` file if you
would like to change the discounts!).

```
{
    "status": "SUCCESS",
    "date": "2022-05-31T02:42:01.768947Z",
    "netPayableAmount": 80.000,
    "currency": "SAR"
}
```

## Let's Get Started!

You can get all required services up and running by executing `runner.sh` file which packages the service and starts up all required services using
`docker-compose`.

```
mvn clean package
docker-compose up -d --build
```

You can also shutdown all the services by executing `shutdown.sh`.

#### To run all the tests execute the following command

```
mvn clean test
```

#### To generate jacoco test report, execute the following command

```
mvn test jacoco:report
```

#### OpenApi docs

```
http://localhost:8080/sds/v1/swagger/swagger-ui/index.html
```

![API Documentation](https://github.com/MoathAlshorman/discount-service/blob/main/openapi-documentation.png)

#### Code Quality Checks

```
mvn checkstle:check spotbugs:check pmd:check
```

#### Generate Javadoc

```
mvn javadoc:javadoc
```
