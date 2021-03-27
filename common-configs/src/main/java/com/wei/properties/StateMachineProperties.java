package com.wei.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "statemachine.plugins.path")
public class StateMachineProperties {
    private String umlFilePath1;
    private String umlFilePath2;
    private String umlFilePath3;

    public StateMachineProperties() {
    }

    public String getUmlFilePath1() {
        return umlFilePath1;
    }

    public void setUmlFilePath1(String umlFilePath1) {
        this.umlFilePath1 = umlFilePath1;
    }

    public String getUmlFilePath2() {
        return umlFilePath2;
    }

    public void setUmlFilePath2(String umlFilePath2) {
        this.umlFilePath2 = umlFilePath2;
    }

    public String getUmlFilePath3() {
        return umlFilePath3;
    }

    public void setUmlFilePath3(String umlFilePath3) {
        this.umlFilePath3 = umlFilePath3;
    }
}
