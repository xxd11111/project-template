package com.xxd.exception;

/**
 * @Description UserNotExistException
 * @Author xxd
 * @Date 2022/2/8 15:24
 * @Version 1.0
 */
public class UserNotExistException  extends RuntimeException{

    private static final long serialVersionUID = -1574716826948451793L;

    private String id;

    public UserNotExistException(String id){
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}