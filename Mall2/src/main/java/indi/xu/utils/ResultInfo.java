package indi.xu.utils;

import java.io.Serializable;

/**
 * @author a_apple
 * @create 2019-12-12 18:18
 */
public class ResultInfo<T> implements Serializable {
    private boolean flag;
    private String info;
    private T obj;

    public ResultInfo() {
    }

    public ResultInfo(boolean flag) {
        this(flag, null);
    }

    public ResultInfo(boolean flag, String info) {
        this(flag, info, null);
    }

    public ResultInfo(boolean flag, String info, T obj) {
        this.flag = flag;
        this.info = info;
        this.obj = obj;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
