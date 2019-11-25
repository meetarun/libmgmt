package com.apus.arunlib.model;
/**
 * @author Arun Kumar Raju
 */
public class LibMgmtWrappedResponse<T> {
    private static final long serialVersionUID = 789L;
    private int status;
    private String message;
    private T result;


    public LibMgmtWrappedResponse(){

    }

    public LibMgmtWrappedResponse(int status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
