/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.license;

/**
 * @author gravit
 */
public class LicenseException extends Exception {
    private String value;
    private String lic;

    public LicenseException(String secs, String licen) {
        value = secs;
        lic = licen;
    }

    @Override
    public String getMessage() {
        return value;
    }

    public String getProblemLicense() {
        return lic;
    }
}
