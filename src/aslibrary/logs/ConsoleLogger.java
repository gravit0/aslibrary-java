package aslibrary.logs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gravit on 27.06.17.
 */
public class ConsoleLogger implements LogsInterface {
    int length;
    SimpleDateFormat dateFormat;
    public ConsoleLogger()
    {
        dateFormat = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss");
    }
    public ConsoleLogger(SimpleDateFormat fmt)
    {
        dateFormat = fmt;
    }
    @Override
    public void add(byte priority, byte[] module, byte[] log) {
        ByteArrayOutputStream s = new ByteArrayOutputStream(module.length + log.length + 16);
        Date current = new Date();
        s.write('[');
        try {
            s.write(dateFormat.format(current).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.write(']');
        s.write('[');
        s.write(priority);
        s.write(']');
        s.write('[');
        try {
            s.write(module);
            s.write(']');
            s.write(log);
            s.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(priority == 'E' || priority == 'C') System.err.write(s.toByteArray());
            else System.out.write(s.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ++length;
    }

    @Override
    public void save() {

    }

    @Override
    public void close() {

    }

    @Override
    public int length() {
        return length;
    }
}
