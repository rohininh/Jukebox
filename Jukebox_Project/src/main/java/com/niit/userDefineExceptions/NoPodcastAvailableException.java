package com.niit.userDefineExceptions;

public class NoPodcastAvailableException extends Exception
{
    public NoPodcastAvailableException(String message) {
        super(message);
    }
}
