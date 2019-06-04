package com.petits_raids.northathleticfield.listener;

public interface HttpCallBackListener {

    void onSuccess(String response);

    void onError(Exception e);
}
