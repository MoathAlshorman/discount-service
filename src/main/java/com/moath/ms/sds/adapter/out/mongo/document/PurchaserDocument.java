package com.moath.ms.sds.adapter.out.mongo.document;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import com.moath.ms.sds.domain.bill.PurchaserType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * discounts system Purchasers configs.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
@Document("purchasers")
@Builder
public class PurchaserDocument {

    @Id
    private final ObjectId id;
    private final String email;
    private final PurchaserType purchaserType;
    private final Instant registrationDate;
}
