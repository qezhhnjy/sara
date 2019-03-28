package com.qezhhnjy.login.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fuzy
 * create time 19-3-26-下午10:01
 */
@Data
@Accessors(chain = true)
public class LoginResult {
    private boolean login;
    private String result;
}
