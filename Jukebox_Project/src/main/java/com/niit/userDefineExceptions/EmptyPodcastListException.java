package com.niit.userDefineExceptions;

public class EmptyPodcastListException extends Exception
{
    public EmptyPodcastListException(String message) {
        super(message);
    }
}
