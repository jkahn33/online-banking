package cis410.onlinebanking;

public class ResponseObject {
    private boolean success;
    private String message;

    public ResponseObject(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
