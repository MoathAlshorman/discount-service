package com.moath.ms.sds.domain.discount.percentage;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.common.exception.DefaultServiceError;
import com.moath.ms.sds.common.exception.ServiceException;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.domain.bill.Item;
import com.moath.ms.sds.domain.bill.ItemType;
import com.moath.ms.sds.domain.bill.Purchaser;
import com.moath.ms.sds.domain.bill.PurchaserType;
import com.moath.ms.sds.domain.discount.DiscountStrategy;
import com.moath.ms.sds.port.out.PercentageDiscountConfigPort;
import com.moath.ms.sds.port.out.UserPort;
import com.moath.ms.sds.util.DiscountCalculator;

/**
 * A client can calculate the payable total amount of the given items in the bill
 * after applying a supported {@link PercentageBasedDiscount}.
 *
 * <p>Please note that only one of the {@link PercentageBasedDiscount} implementations should be applied on the bill.
 *
 * @author Moath.Alshorman
 * @since 28/05/2022
 */
@RequiredArgsConstructor
public abstract class PercentageBasedDiscount implements DiscountStrategy {

    private final PercentageDiscountConfigPort discountConfigsPort;
    private final UserPort userPort;

    @Override
    public boolean supports(final Bill bill) {
        final var purchaserId = bill.getPurchaserId();
        final var purchaser = findPurchaser(purchaserId);
        return purchaser.getType() == getUserType();
    }

    @Override
    public BigDecimal calculateDiscount(final Bill bill) {
        final var filteredTotalAmount = bill.getItems()
            .stream()
            .filter(item -> item.getType() != ItemType.GROCERIES)
            .map(Item::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return DiscountCalculator.of().calculateDiscountAmount(filteredTotalAmount, getDiscountPercentage());
    }

    /**
     * Returns the configured discount.
     *
     * @return the percentage amount
     */
    protected BigDecimal getDiscountPercentage() {
        return discountConfigsPort.findPercentageDiscount(getUserType());
    }

    /**
     * Finds the purchaser for the given Id.
     *
     * @param purchaserId the search is against this id
     * @return the matched {@link Purchaser} or throw {@link DefaultServiceError#RECORD_NOT_FOUND} exception
     */
    protected final Purchaser findPurchaser(final String purchaserId) {
        return userPort.findUser(purchaserId)
            .orElseThrow(() -> ServiceException.badRequest(DefaultServiceError.RECORD_NOT_FOUND, "purchaserId", purchaserId));
    }

    /**
     * Returns {@link PurchaserType} based on a strategy implementation.
     *
     * @return a {@link PurchaserType}
     */
    protected abstract PurchaserType getUserType();
}
