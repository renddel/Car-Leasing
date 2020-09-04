package com.carLease.carLease.expections;

public class RequestProblemException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public RequestProblemException(String exception) {
        super(exception);
    }
}
