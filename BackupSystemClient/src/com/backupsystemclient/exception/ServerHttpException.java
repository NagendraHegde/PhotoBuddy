package com.backupsystemclient.exception;

public class ServerHttpException extends RuntimeException{


    public ServerHttpException(final String message){
        super(message);
    }

    public ServerHttpException(final String message, final Exception cause){
        super(message, cause);
    }

}
