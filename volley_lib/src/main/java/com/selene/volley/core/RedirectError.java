package com.selene.volley.core;

/**
 * Indicates that there was a redirection.
 */
@SuppressWarnings("serial")
public class RedirectError extends VolleyError {

    public RedirectError() {
    }

    public RedirectError(final Throwable cause) {
        super(cause);
    }

    public RedirectError(final NetworkResponse response) {
        super(response);
    }
}
