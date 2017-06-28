/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.account;

/**
 * @author gravit
 */
public final class AccountPermissions {
    private AccountPermissions() {
    }

    public static final long ADMIN = 1 << 2;
    public static final long SUPERUSER = 1 << 3;
}
