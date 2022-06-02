package com.moath.ms.sds.config.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.domain.discount.DiscountProcessor;
import com.moath.ms.sds.domain.discount.overall.OverallDiscountStrategy;
import com.moath.ms.sds.domain.discount.percentage.PercentageBasedDiscount;
import com.moath.ms.sds.domain.discount.processor.OverAllDiscountProcessor;
import com.moath.ms.sds.domain.discount.processor.PercentageDiscountProcessor;
import com.moath.ms.sds.domain.discount.processor.TotalNetAmountCalculatorProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link DiscountProcessor} configs.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Configuration
@RequiredArgsConstructor
public class DiscountProcessorBeansConfig {

    private final List<PercentageBasedDiscount> basedDiscountList;
    private final OverallDiscountStrategy overallDiscountStrategy;

    /**
     * Creates a List of {@link DiscountProcessor} beans.
     *
     * @return DiscountProcessor list
     */
    @Bean
    public List<DiscountProcessor> discountProcessors() {
        return List.of(
            new PercentageDiscountProcessor(basedDiscountList),
            new OverAllDiscountProcessor(overallDiscountStrategy)
        );
    }

    @Bean
    public DiscountProcessor discountProcessor(List<DiscountProcessor> processors) {
        return new TotalNetAmountCalculatorProcessor(processors);
    }
}
