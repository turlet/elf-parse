package com.turlet.elf.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * Create by Silen((myemail)) on 2019/8/21 15:40
 */
public class Log {

    private static final Logger log = Logger.getLogger("elf");

    private static Level sLevel = Level.ALL;

    static {
        log.setLevel(sLevel);

        for (Handler handler : log.getHandlers()) {
            log.removeHandler(handler);
        }
        LogManager.getLogManager().reset();
        if(Level.CONFIG.intValue() >= sLevel.intValue()) {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new LogHandler());
            consoleHandler.setLevel(Level.ALL);
            // 设定Logger的的Handler为ConsoleHandler
            log.addHandler(consoleHandler);
        }
    }

    public static void e(String msg){
        log.log(Level.SEVERE, msg);
    }

    public static void w(String msg){
        log.log(Level.WARNING, msg);
    }

    public static void i(String msg){
        log.log(Level.INFO, msg);
    }

    public static void d(String msg){
        log.log(Level.CONFIG, msg);
    }

    public static void v(String msg){
        log.log(Level.FINE, msg);
    }


    public static void log(Level level, String msg){
        if(sLevel.intValue() <= level.intValue())
            log.log(level, msg);
    }


    static class LogHandler extends Formatter {

        SimpleDateFormat format =new SimpleDateFormat("MM-dd HH:mm:ss:SSS");

        @Override
        public String format(LogRecord record) {
            return format.format(new Date()) + " " + record.getLevel() + "/" + log.getName() + ": " + record.getMessage() + "\n";
        }
    }
}
