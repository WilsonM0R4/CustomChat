package com.example.wilson.customchat.lib;

/**
 * Created by gparrrado on 7/13/16.
 */
public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);

}
