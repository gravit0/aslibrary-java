/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.account;

/**
 *
 * @author gravit
 */
public final class AccountFlags {
    private AccountFlags()
    {
        
    }
    public static final long SYSTEM = 1;
    public static final long HIDDEN = 1 << 1;
    public static final long DEVELOPER = 1 << 2;
}
