package com.moath.ms.sds.adapter.out.mongo.document;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import com.moath.ms.sds.domain.bill.PurchaserType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Percentage discounts configs document.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
@Document("percentages")
@Builder
public class PercentageDiscountConfigDocument {

    @Id
    private final ObjectId id;
    private final PurchaserType purchaserType;
    private final BigDecimal percentage;
}
