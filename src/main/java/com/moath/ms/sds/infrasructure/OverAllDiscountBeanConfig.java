package com.moath.ms.sds.infrasructure;

import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.domain.discount.overall.OverallDiscountStrategy;
import com.moath.ms.sds.domain.discount.overall.ThresholdDiscountStrategy;
import com.moath.ms.sds.port.out.ThresholdDiscountConfigPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Required configs for {@link OverallDiscountStrategy} bean.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Configuration
@RequiredArgsConstructor
public class OverAllDiscountBeanConfig {

    private final ThresholdDiscountConfigPort thresholdDiscountConfigPort;

    /**
     * Creates a Bean of type {@link OverallDiscountStrategy}
     *
     * @return an instantiated bean of type {@link OverallDiscountStrategy}
     */
    @Bean
    public OverallDiscountStrategy overallDiscountStrategy() {
        return new ThresholdDiscountStrategy(thresholdDiscountConfigPort);
    }
}
