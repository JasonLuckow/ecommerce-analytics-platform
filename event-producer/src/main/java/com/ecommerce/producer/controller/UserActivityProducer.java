package com.ecommerce.producer.controller;

public interface UserActivityProducer {

    void startProduceUserActivityEvent();
    void stopProduceUserActivityEvent();

}
