package com.moath.ms.sds.adapter.out.mongo;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.adapter.out.mongo.document.PercentageDiscountConfigDocument;
import com.moath.ms.sds.adapter.out.mongo.repo.PercentageConfigDocumentRepository;
import com.moath.ms.sds.common.annotation.PersistenceAdapter;
import com.moath.ms.sds.domain.bill.PurchaserType;
import com.moath.ms.sds.port.out.PercentageDiscountConfigPort;

/**
 * Mongo Db adapter to manage the Percentage Discount Configurations.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class MongoPercentageDiscountConfigAdapter implements PercentageDiscountConfigPort {

    private final PercentageConfigDocumentRepository repository;

    @Override
    public BigDecimal findPercentageDiscount(final PurchaserType purchaserType) {
        return repository.findByPurchaserType(purchaserType)
            .map(PercentageDiscountConfigDocument::getPercentage)
            .orElse(BigDecimal.ZERO);
    }
}
