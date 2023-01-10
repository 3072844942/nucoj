package com.q7g.nucoj_spring.vo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class NUCOJVO {
    private String title;
    private String websocketUrl;
}
