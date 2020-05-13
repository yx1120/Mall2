package indi.xu.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志
 * @author a_apple
 * @create 2020-03-08 20:52
 */
public class Log implements Serializable {

    private Integer logId;
    private String logType;
    private String remoteAddr;
    private String requestUri;
    private String errorCode;
    private String content;
    private Integer uid;
    private Date logDate;

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", logType='" + logType + '\'' +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", requestUri='" + requestUri + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", logDate=" + logDate +
                '}';
    }
}
