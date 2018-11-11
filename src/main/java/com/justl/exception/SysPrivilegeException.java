package com.justl.exception;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author buhuaqi
 * @date 2018-10-29 12:58
 */
public class SysPrivilegeException extends RuntimeException {

    /**
     * SysPrivilegeException.java
     */
    private static final long serialVersionUID = 1L;

    public SysPrivilegeException() {
        super();
    }

    public SysPrivilegeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysPrivilegeException(String message) {
        super(message);
    }

    public SysPrivilegeException(String message, Object... param) {
        FormattingTuple arrayFormat = MessageFormatter.arrayFormat(message, param);
        throw new RuntimeException(arrayFormat.getMessage());
    }

}

