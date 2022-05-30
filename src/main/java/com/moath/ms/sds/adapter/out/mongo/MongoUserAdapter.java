package com.moath.ms.sds.adapter.out.mongo;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.adapter.out.mongo.repo.PurchaserDocumentRepository;
import com.moath.ms.sds.common.annotation.PersistenceAdapter;
import com.moath.ms.sds.domain.bill.Purchaser;
import com.moath.ms.sds.port.out.UserPort;

/**
 * A mongo db adapter to handle user related operations.
 *
 * @author Moath.Alshorman
 * @since 30/05/2022
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class MongoUserAdapter implements UserPort {

    private final PurchaserDocumentRepository repository;

    @Override
    public Optional<Purchaser> findUser(final String email) {
        return repository.findByEmail(email)
            .map(document -> new Purchaser(
                document.getEmail(),
                document.getPurchaserType(),
                document.getRegistrationDate()
            ));
    }
}
