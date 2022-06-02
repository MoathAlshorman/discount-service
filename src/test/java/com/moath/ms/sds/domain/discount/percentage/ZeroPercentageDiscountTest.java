package com.moath.ms.sds.domain.discount.percentage;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import com.moath.ms.sds.domain.bill.PurchaserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test the non-reachable code by the business requirement
 *
 * @author Moath.Alshorman
 * @since 6/2/2022
 */
class ZeroPercentageDiscountTest {

    final ZeroPercentageDiscount zeroPercentageDiscount = new ZeroPercentageDiscount();

    @Test
    void testZeroPercentage() {
        Assertions.assertEquals(BigDecimal.ZERO, zeroPercentageDiscount.getDiscountPercentage());
        Assertions.assertEquals(PurchaserType.NOT_APPLICABLE, zeroPercentageDiscount.getUserType());
    }
}