package com.moath.ms.sds.adapter.in.rest;

import java.math.BigDecimal;
import java.util.List;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.domain.bill.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapping between {@link Bill} and {@link BillDiscountRequest}
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Mapper(componentModel = "spring")
public interface BillMapper {

    /**
     * Maps the given {@link BillDiscountRequest} to a new instance of {@link Bill}.
     *
     * @param request the {@link BillDiscountRequest} you would like to map
     * @return a new mapped instance of {@link Bill}
     */
    @Mapping(target = "netAmount", source = "request.items", qualifiedByName = "totalPriceCalculator")
    Bill map(final BillDiscountRequest request);

    /**
     * Calculate total prices of all given items.
     *
     * @param items to be calculated
     * @return the net amount
     */
    @Named("totalPriceCalculator")
    default BigDecimal calculateAmount(final List<Item> items) {
        return items
            .stream()
            .map(Item::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
