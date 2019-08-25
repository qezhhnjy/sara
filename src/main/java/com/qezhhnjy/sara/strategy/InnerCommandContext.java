package com.qezhhnjy.sara.strategy;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author fuzy
 * create time 19-5-19-上午10:44
 */
public class InnerCommandContext {

    @Resource
    private ApplicationContext applicationContext;

    public InnerCommand getInstance(String command) {
        Map<String, String> clazzMap = SystemError.getAllClazz();
        String[] trim = command.trim().split(" ");
        String clazz = clazzMap.get(trim[0]);
        InnerCommand innerCommand = null;
        if (StringUtils.isEmpty(clazz)) {
            return null;
        }
        try {
            innerCommand = (InnerCommand) applicationContext.getBean(Class.forName(clazz));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return innerCommand;
    }
}
