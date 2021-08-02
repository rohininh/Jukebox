package com.niit.userDefineExceptions;

public class NoPlaylistAvailableException extends Exception
{
    public NoPlaylistAvailableException(String message) {
        super(message);
    }
}
