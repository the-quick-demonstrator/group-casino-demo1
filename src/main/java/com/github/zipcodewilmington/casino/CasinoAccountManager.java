package com.github.zipcodewilmington.casino;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by leon on 7/21/2020.
 * `CasinoAccountManager` stores, manages, and retrieves `CasinoAccount` objects
 * it is advised that every instruction in this class is logged
 */
public class CasinoAccountManager {
    private HashMap<String, CasinoAccount> accounts;

    public CasinoAccountManager() {
        this.accounts = new HashMap<>();
    }

    /**
     * @param accountName     name of account to be returned
     * @param accountPassword password of account to be returned
     * @return `CasinoAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount getAccount(String accountName, String accountPassword) {
        if (this.accounts.containsKey(accountName)) {
            CasinoAccount acct = this.accounts.get(accountName);
            if (acct.getPassword().equals(accountPassword)) {
                return acct;
            }
        }
        return null;
    }

    public Set<String> getAccountUsernames() {
        return accounts.keySet();
    }

    /**
     * logs & creates a new `CasinoAccount`
     *
     * @param accountName     name of account to be created
     * @param accountPassword password of account to be created
     * @return new instance of `CasinoAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount createAccount(String accountName, String accountPassword) {
        if (this.accounts.containsKey(accountName)) {
            return null;
        } else {
            CasinoAccount newAcct = new CasinoAccount(accountName, accountPassword);
            this.accounts.put(accountName, newAcct);
            return newAcct;
        }
    }

    /**
     * logs & registers a new `CasinoAccount` to `this.getCasinoAccountList()`
     *
     * @param CasinoAccount the CasinoAccount to be added to `this.getCasinoAccountList()`
     */
    public void registerAccount(CasinoAccount CasinoAccount) {
        this.accounts.put(CasinoAccount.getUserName(), CasinoAccount);
    }
}
