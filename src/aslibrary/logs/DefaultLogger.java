package aslibrary.logs;

import java.util.Arrays;

public abstract class DefaultLogger implements LogsInterface {
    public void add(byte priority, byte[] module, String log) {
        add(priority, module, log.getBytes());
    }

    public void add(byte priority, String module, String log) {
        add(priority, module.getBytes(), log.getBytes());
    }

    public void add(char priority, String module, String log) {
        add((byte) priority, module.getBytes(), log.getBytes());
    }

    public void add(Exception e) {
        add((byte) 'E', e.getClass().getName(), Arrays.toString(e.getStackTrace()));
    }
}
