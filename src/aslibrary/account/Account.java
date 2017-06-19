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
public class Account {
    public long id;
    public String name;
    protected long permissions;
    protected long flags;
    public void addPermission(long perm)
    {
        permissions = permissions | perm;
    }
    public boolean isPermission(long perm)
    {
        return (permissions & perm) != 0;
    }
    public void rmPermission(long perm)
    {
        permissions = (permissions ^ perm);
    }
    public void addFlag(long flag)
    {
        flags = flags | flag;
    }
    public boolean isFlag(long flag)
    {
        return (flags & flag) != 0;
    }
    public void rmFlag(long flag)
    {
        flags = flags ^ flag;
    }
}
