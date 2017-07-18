package aslibrary.logs;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Created by Gravit on 18.07.2017.
 */
public class FileLogger implements LogsInterface
{
    FileOutputStream file;
    SimpleDateFormat dateFormat;
    public FileLogger(String filename) throws FileNotFoundException {
        file = new FileOutputStream(filename);
        dateFormat = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss");
    }
    @Override
    public java.io.OutputStream getStream() {
        return file;
    }


    @Override
    public void save() {
        try {
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int length() {
        return 0;
    }
}
