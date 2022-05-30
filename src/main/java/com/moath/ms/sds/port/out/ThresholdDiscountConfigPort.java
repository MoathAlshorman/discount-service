package com.moath.ms.sds.port.out;

import java.util.List;
import com.moath.ms.sds.domain.discount.overall.OverallDiscountConfig;

/**
 * A port to manage all overall discount configuration in the system. it could be a database adapter or a client to a service, etc.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
public interface ThresholdDiscountConfigPort {

    /**
     * Finds overall configs.
     *
     * @return a list of {@link OverallDiscountConfig} instance if any, otherwise empty.
     */
    List<OverallDiscountConfig> findAllThresholdDiscountConfigs();
}
