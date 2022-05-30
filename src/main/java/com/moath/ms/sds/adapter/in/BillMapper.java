package com.moath.ms.sds.adapter.in;

import com.moath.ms.sds.domain.bill.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    Bill map(final BillDiscountRequest request);
}
