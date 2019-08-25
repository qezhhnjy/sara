package com.qezhhnjy.sara.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fuzy
 * create time 19-5-19-上午10:45
 */
public enum SystemError {
    QUIT(":quit", "", "PrintQuitCommand"),
    ALL(":all", "", "PrintAllCommand"),
    USER(":user", "", "PrintUserCommand"),
    ADMIN(":admin", "", "PrintAdminCommand"),
    AI(":ai", "", "PrintAiCommand"),
    QAI(":qai", "", "PrintQaiCommand"),
    INFO(":info", "", "PrintInfoCommand");

    private String code;
    private String desc;
    private String clazz;

    private static final Map<String, String> ERR_DESC = new HashMap<>();

    static {
        for (SystemError error : SystemError.values()) {
            ERR_DESC.put(error.code, error.clazz);
        }
    }

    SystemError(String code, String desc, String clazz) {
        this.code = code;
        this.desc = desc;
        this.clazz = clazz;
    }

    public static Map<String, String> getAllClazz() {
        return ERR_DESC;
    }
}
