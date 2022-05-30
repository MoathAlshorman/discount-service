package com.moath.ms.sds.adapter.out.mongo.repo;

import com.moath.ms.sds.adapter.out.mongo.document.ThresholdDiscountConfigDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring data repo for OverallDiscountConfigDocument mongo operations.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Repository
public interface ThresholdDiscountConfigRepository extends MongoRepository<ThresholdDiscountConfigDocument, String> {
}