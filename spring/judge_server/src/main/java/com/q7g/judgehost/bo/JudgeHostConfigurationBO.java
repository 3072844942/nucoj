package com.q7g.judgehost.bo;

/**
 * 判题服务器配置bo对象
 */
public class JudgeHostConfigurationBO {
    private String workPath;
    private String scriptPath;
    private String resolutionPath;
    private Integer port;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWorkPath() {
        return workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public String getResolutionPath() {
        return resolutionPath;
    }

    public void setResolutionPath(String resolutionPath) {
        this.resolutionPath = resolutionPath;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "JudgeHostConfigurationBO{" +
                "workPath='" + workPath + '\'' +
                ", scriptPath='" + scriptPath + '\'' +
                ", resolutionPath='" + resolutionPath + '\'' +
                ", port=" + port +
                '}';
    }
}
