package com.niit.userDefineExceptions;

public class NoSuchSongAvailableException extends Exception
{
    public NoSuchSongAvailableException(String message) {
        super(message);
    }
}
