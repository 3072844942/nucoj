package com.q7g.nucoj_spring.exceotion;


import com.q7g.nucoj_spring.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.q7g.nucoj_spring.enums.ResultCodeEnum.FAIL;


/**
 * 业务异常
 */
@Getter
@AllArgsConstructor
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code = FAIL.getCode();

    /**
     * 错误信息
     */
    private final String message;

    public BizException(String message) {
        this.message = message;
    }

    public BizException(ResultCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }


}
