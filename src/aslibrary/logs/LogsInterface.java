package aslibrary.logs;

import java.util.Arrays;

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
    default void add(Exception e)
    {
        add((byte) 'E', e.getClass().getName(), Arrays.toString(e.getStackTrace()));
    }
    void save();
    void close();
    int length();
}
