package aslibrary.logs;

/**
 * Created by gravit on 27.06.17.
 */
public interface LogsInterface {
    void add(byte priority, byte[] module, byte[] log);
    default void add(byte priority, byte[] module, String log)
    {
        add(priority, module, log.getBytes());
    }
    default void add(byte priority, String module, String log)
    {
        add(priority, module.getBytes(), log.getBytes());
    }
    default void add(char priority, String module, String log)
    {
        add((byte)priority, module.getBytes(), log.getBytes());
    }
    void save();
    void close();
    int length();
}
