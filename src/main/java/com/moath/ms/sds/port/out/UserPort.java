package com.moath.ms.sds.port.out;

import java.util.Optional;
import com.moath.ms.sds.domain.bill.Purchaser;

/**
 * A port to any users operation.
 * An implementation of this port would be any adapter whose compatible with this port regardless the type of technology it uses.
 * for example a Database persistence to get the user or an HTTP service call, etc.
 *
 * @author Moath.Alshorman
 * @since 28/05/2022
 */
public interface UserPort {

    /**
     * Finds a user which has the given id.
     *
     * @param email user's id for which the user you would like to find is belong
     * @return the matched user if any, otherwise returns an empty {@link Optional}
     */
    Optional<Purchaser> findUser(final String email);
}
