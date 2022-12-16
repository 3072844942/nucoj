package com.q7g.judgehost.core.configuration;

import com.q7g.judgehost.core.factory.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 从配置文件中获取错误码对应的描述
 * 最终这个描述会被返回到前端
 */

@Component
@ConfigurationProperties(prefix = "judge-host-exceptions")
@PropertySource(value = "classpath:config/exception-codes.yml", factory = YamlPropertySourceFactory.class)

public class ExceptionCodeConfiguration {
    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }

    private Map<String, String> codes = new HashMap<>();

    public Map<String, String> getCodes() {
        return codes;
    }

    public String getMessage(String code) {
        return codes.get(code);
    }
}
