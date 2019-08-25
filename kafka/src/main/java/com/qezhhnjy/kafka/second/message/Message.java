package com.qezhhnjy.kafka.second.message;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author fuzy
 * create time 19-5-18-下午7:27
 */
@Data
public class Message {
    private int       id;
    private String    name;
    private String    info;
    private long      time;
    private LocalDate date;
}
