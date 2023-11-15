package com.github.zipcodewilmington.casino;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class CasinoAccount {

    private final String userName;
    private final String password;

    public CasinoAccount(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String  getUserName() {
        return userName;
    }

    public String getPassword(){
        return password;
    }
}
