package com.moath.ms.sds.domain.bill;

/**
 * The supported types of the bill items.
 *
 * <p>The enum explicitly declares only {@link ItemType#GROCERIES} for now.
 * Other types are under {@link ItemType#OTHER} and they could be declared later if needed.
 *
 * @author Moath.Alshorman
 * @since 28/05/2022
 */
public enum ItemType {

    GROCERIES,
    OTHER
}
