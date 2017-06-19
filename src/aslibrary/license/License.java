package aslibrary.license;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import aslibrary.crypto.aes.AESCrypt;
import aslibrary.formats.FastBinaryFormatter4;
import aslibrary.util.BinaryHelper;

/**
 *
 * @author gravit
 */
public class License {
    public String name;
    public long type;
    public String lictext;
    public long date_issue;
    public long date_end;
    
    public static final int TYPE_PERSONAL = 1;
    public static final int TYPE_SCHOOL = 2;
    public static final int TYPE_ENTERPRISE = 3;
    public static final int TYPE_DEVELOPER = 4;
    public static final int TYPE_FAKE = 5;
    private License()
    {
        
    }
    public static License newLicense(LicenseSecureInterface secure)
    {
        if(!secure.validation()) return null;
        return new License();
    }
    public static License getLicense(String lic,LicenseSecureInterface secure) throws Exception
    {
        if(!secure.validation()) return null;
        License newlic = new License();
        byte[] aes = AESCrypt.decrypt(secure.getEncryptKey(), lic);
        /*String[] d = aes.split("-");
        if(d.length != 4) throw new Exception("Unvalid license");
        newlic.date_issue = Long.parseLong( d[0] );
        newlic.name = d[1];
        newlic.type = Integer.parseInt(d[2]);
        newlic.date_issue = Long.parseLong( d[3] );
        LicenseBinaryFormatter fmt = new LicenseBinaryFormatter(aes.getBytes());
        System.out.println(Arrays.toString(aes.getBytes()));
        System.out.println(Arrays.toString(fmt.headbytes));
        System.out.println(Arrays.toString(fmt.bytes));
        newlic.name = new String(fmt.get(0));
        newlic.type = BinaryHelper.byteArrayToLong(fmt.get(1));
        newlic.date_issue = BinaryHelper.byteArrayToLong(fmt.get(2));
        newlic.date_end = BinaryHelper.byteArrayToLong(fmt.get(3,5));*/
        Object[] arr = FastBinaryFormatter4.decode(aes);
        newlic.name = new String((byte[])arr[0]);
        newlic.type = BinaryHelper.byteArrayToLong((byte[])arr[1]);
        newlic.date_issue = BinaryHelper.byteArrayToLong((byte[])arr[2]);
        newlic.date_end = BinaryHelper.byteArrayToLong((byte[])arr[3]);
        newlic.lictext = lic;
        return newlic;
    }
    public int validate()
    {
        long this_time =System.currentTimeMillis();
        if(date_issue > this_time) return 1;
        if(date_end < this_time) return 2;
        if(type == TYPE_FAKE) return 3;
        return 0;
    }
    public void cretaeKey(LicenseSecureInterface secure) throws Exception
    {
        if(!secure.validation()) return;
        String result;
        /*LicenseBinaryFormatter fmt = new LicenseBinaryFormatter();
        fmt.add(name.getBytes());
        fmt.add(type);
        fmt.add(date_issue);
        fmt.add(date_end);*/
        byte[][] arr = {name.getBytes(),
            BinaryHelper.longToByteArray(type),
            BinaryHelper.longToByteArray(date_issue),
            BinaryHelper.longToByteArray(date_end)};
        byte[] res = FastBinaryFormatter4.encode(arr);
        result = new String(AESCrypt.encrypt(secure.getEncryptKey(), res));
        lictext=result;
    }
    public void printLicense()
    {
        System.out.println("License:"+lictext);
            System.out.println("Name:"+name);
            String lictype = null;
            switch((int)type)
            {
                case License.TYPE_PERSONAL:
                {
                    lictype="Personal";
                    break;
                }
                case License.TYPE_ENTERPRISE:
                {
                    lictype="Enterprise";
                    break;
                }
                case License.TYPE_SCHOOL:
                {
                    lictype="School";
                    break;
                }
                case License.TYPE_DEVELOPER:
                {
                    lictype="Developer";
                    break;
                }
                default:
                {
                    lictype=String.valueOf(type);
                }
            }
            System.out.println("Type:"+lictype);
            System.out.println("Issue Date:"+String.valueOf( date_issue ));
            System.out.println("End Date:"+String.valueOf(date_end));
    }
}
