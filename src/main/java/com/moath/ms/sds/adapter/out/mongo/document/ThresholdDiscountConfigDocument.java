package com.moath.ms.sds.adapter.out.mongo.document;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Overall discount configuration.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
@Builder
@Document("thresholds")
public class ThresholdDiscountConfigDocument {

    @Id
    private final ObjectId id;
    private final BigDecimal thresholdAmount;
    private final BigDecimal discountAmountPerThreshold;
}
