package com.niit.userDefineExceptions;

public class EmptyPlaylistException extends Exception
{
    public EmptyPlaylistException(String message) {
        super(message);
    }
}
