package com.moath.ms.sds.infrasructure;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.domain.discount.percentage.AffiliateUserDiscount;
import com.moath.ms.sds.domain.discount.percentage.CustomerUserDiscount;
import com.moath.ms.sds.domain.discount.percentage.EmployeeUserDiscount;
import com.moath.ms.sds.domain.discount.percentage.PercentageBasedDiscount;
import com.moath.ms.sds.port.out.PercentageDiscountConfigPort;
import com.moath.ms.sds.port.out.UserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A configuration of all strategies that would be applied to a bill as the business needs.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Configuration
@RequiredArgsConstructor
public class DiscountStrategiesConfig {

    private final PercentageDiscountConfigPort discountConfigPort;
    private final UserPort userPort;

    /**
     * A list of supported {@link PercentageBasedDiscount}.
     *
     * @return a {@link PercentageBasedDiscount} list
     */
    @Bean
    public List<PercentageBasedDiscount> basedDiscountBeansList() {
        return List.of(
            new EmployeeUserDiscount(discountConfigPort, userPort),
            new AffiliateUserDiscount(discountConfigPort, userPort),
            new CustomerUserDiscount(discountConfigPort, userPort));
    }
}
