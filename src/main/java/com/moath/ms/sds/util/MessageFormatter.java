package com.moath.ms.sds.util;

import java.text.MessageFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Default message formatter.
 *
 * @author Moath.Alshorman
 * @since 30/05/2022
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageFormatter {

    private static final MessageFormatter INSTANCE = new MessageFormatter();

    /**
     * Gets an instance of {@link MessageFormatter}.
     *
     * @return a singleton instance of this class
     */
    public static MessageFormatter of() {
        return INSTANCE;
    }

    /**
     * Formats error message by concatenating the given arguments.
     *
     * @param arguments Error description arguments to be concatenated.
     * @return formatted error message.
     */
    public String format(final String message, final Object... arguments) {
        return arguments != null ? MessageFormat.format(message, arguments) : message;
    }
}
