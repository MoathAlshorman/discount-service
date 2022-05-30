package com.moath.ms.sds.adapter.out.mongo.repo;

import java.util.Optional;
import com.moath.ms.sds.adapter.out.mongo.document.PurchaserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring data repo for users information.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Repository
public interface PurchaserDocumentRepository extends MongoRepository<PurchaserDocument, String> {


    /**
     * Finds by {@link PurchaserDocument}'s email.
     *
     * @param email by which the {@link PurchaserDocument} is searched
     * @return the matched {@link PurchaserDocument} if any, otherwise returns Empty {@link Optional}
     */
    @Query("{email: '?0'}")
    Optional<PurchaserDocument> findByEmail(final String email);
}
