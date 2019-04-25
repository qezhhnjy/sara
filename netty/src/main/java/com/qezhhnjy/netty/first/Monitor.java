package com.qezhhnjy.netty.first;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

interface Listener {
    void submit(Event event);
}

/**
 * @author qezhhnjy
 * @date 2019/4/17-22:41
 * 监听器模式的简单实现
 */
@Slf4j
public class Monitor {

    public static void main(String[] args) {
        Student student = new Student();
        student.addListener((e) -> log.info("event trigger {}", e));
        student.read();
    }
}

@Data
class Student {

    private List<Listener> listeners;

    public void addListener(Listener listener) {
        if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.add(listener);
    }

    public void read() {
        if (listeners != null) {
            listeners.forEach(listener -> listener.submit(new Event(this)));
        }
    }

}

@Data
@AllArgsConstructor
class Event {
    private Object source;
}
