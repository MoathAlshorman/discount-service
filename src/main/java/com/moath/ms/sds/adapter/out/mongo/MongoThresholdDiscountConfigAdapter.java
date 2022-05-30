package com.moath.ms.sds.adapter.out.mongo;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.adapter.out.mongo.repo.ThresholdDiscountConfigRepository;
import com.moath.ms.sds.common.annotation.PersistenceAdapter;
import com.moath.ms.sds.domain.discount.overall.OverallDiscountConfig;
import com.moath.ms.sds.port.out.ThresholdDiscountConfigPort;

/**
 * Persistence adapter to MongoDB to manage {@link OverallDiscountConfig}.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class MongoThresholdDiscountConfigAdapter implements ThresholdDiscountConfigPort {

    private final ThresholdDiscountConfigRepository repository;

    @Override
    public List<OverallDiscountConfig> findAllThresholdDiscountConfigs() {
        return repository.findAll()
            .stream()
            .map(document -> new OverallDiscountConfig(
                document.getThresholdAmount(),
                document.getDiscountAmountPerThreshold()))
            .collect(Collectors.toList());
    }
}
