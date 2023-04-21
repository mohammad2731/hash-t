package com.ht.cqrs.core;

public class Request<R> {

    private R response;

    public R getResponse() {
        return response;
    }

    public void setResponse(R response) {
        this.response = response;
    }

}
