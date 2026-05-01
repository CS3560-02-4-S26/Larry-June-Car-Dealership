package com.larryjune.dealership;

import com.larryjune.dealership.model.Account;

/**
 * Holds the account for the customer session after login. Cleared on logout.
 */
public final class CustomerSession {
    private static Account loggedIn;

    private CustomerSession() {}

    public static void setLoggedIn(Account account) {
        loggedIn = account;
    }

    public static Account getLoggedIn() {
        return loggedIn;
    }

    public static void clear() {
        loggedIn = null;
    }
}
