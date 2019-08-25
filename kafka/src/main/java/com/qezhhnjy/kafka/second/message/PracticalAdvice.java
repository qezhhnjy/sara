package com.qezhhnjy.kafka.second.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fuzy
 * create time 19-5-18-下午3:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticalAdvice {
    private String message;
    private int    identifier;
}
