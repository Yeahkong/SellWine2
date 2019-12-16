package com.hzxy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Program SmartHome
 * @Package com.hzxy.config
 * @ClassName UploadProperties
 * @Author liuningying
 * @Date 2019-09-09 16:57
 */
@Component
@ConfigurationProperties(prefix="upload")
public class UploadProperties {
    private String windows;
    private String linux;

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public String getLinux() {
        return linux;
    }

    public void setLinux(String linux) {
        this.linux = linux;
    }

    public String getBasePath() {
        String location = "";
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")) {
            location = windows;
        } else {
            location = linux;
        }
        return location;
    }
}
