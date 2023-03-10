package com.q7g.judgehost.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 语言类型验证
 *
 * @author yuzhanglong
 * @date 2020-6-22 15:44
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = LanguageTypeAcceptedValidator.class)
public @interface LanguageTypeAccepted {
    String message() default "不支持的编程语言，请检查后重试！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
