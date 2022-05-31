package com.moath.ms.sds.adapter.out.mongo.repo;

import java.util.Optional;
import com.moath.ms.sds.adapter.out.mongo.document.PercentageDiscountConfigDocument;
import com.moath.ms.sds.domain.bill.PurchaserType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring data repo for Percentage Configurations.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Repository
public interface PercentageConfigDocumentRepository extends MongoRepository<PercentageDiscountConfigDocument, ObjectId> {

    /**
     * Finds by Percentage type.
     *
     * @param purchaserType by which the {@link PercentageDiscountConfigDocument} is searched
     * @return the matched {@link PercentageDiscountConfigDocument} if any, otherwise returns Empty {@link Optional}
     */
    @Query("{purchaserType: '?0'}")
    Optional<PercentageDiscountConfigDocument> findByPurchaserType(PurchaserType purchaserType);
}
