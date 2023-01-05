package com.leeshuyun.app.workshop12_randomNum.exception;

public class RandNoException extends RuntimeException{
    //Runtime Exception doesn't need a contructor, we need to give it a serial version number
    //it's not required for latest versions 17 and 19 of jdk but just in case
    private static final long serialVersionUID = 1L;
}
