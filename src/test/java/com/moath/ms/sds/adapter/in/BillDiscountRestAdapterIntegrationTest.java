package com.moath.ms.sds.adapter.in;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import javax.annotation.PostConstruct;
import com.moath.ms.sds.adapter.out.mongo.document.PercentageDiscountConfigDocument;
import com.moath.ms.sds.adapter.out.mongo.document.PurchaserDocument;
import com.moath.ms.sds.adapter.out.mongo.document.ThresholdDiscountConfigDocument;
import com.moath.ms.sds.domain.bill.PurchaserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class BillDiscountRestAdapterIntegrationTest {


    public static final String THRESHOLD_CONFIGS = "threshold";

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {

        final var thresholdConfig = ThresholdDiscountConfigDocument.builder()
            .thresholdAmount(BigDecimal.valueOf(1000))
            .discountAmountPerThreshold(BigDecimal.valueOf(2.5))
            .build();

        mongoTemplate.save(thresholdConfig, THRESHOLD_CONFIGS);

        mongoTemplate.save(PurchaserDocument.builder()
            .purchaserType(PurchaserType.EMPLOYEE)
            .email("moath.alshorman@hotmail.com"))
            .registrationDate(Instant.now());

        mongoTemplate.save(PercentageDiscountConfigDocument.builder()
            .purchaserType(PurchaserType.EMPLOYEE)
            .percentage(BigDecimal.valueOf(30.000))
            .build());
    }

    @Test
    void testMongoDb_workingAsExpected() {

        // then
        final List<ThresholdDiscountConfigDocument> collection = mongoTemplate.findAll(ThresholdDiscountConfigDocument.class, THRESHOLD_CONFIGS);
        Assertions.assertEquals(1, collection.size());
    }
}