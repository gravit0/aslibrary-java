package aslibrary.logs;

/**
 * Created by gravit on 27.06.17.
 */
public interface LogsInterface {
    void add(byte priority, byte[] module, byte[] log);

    void add(byte priority, byte[] module, String log);

    void add(byte priority, String module, String log);

    void add(char priority, String module, String log);

    void add(Exception e);
    void save();
    void close();
    int length();
}
