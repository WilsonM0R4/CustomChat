package com.example.wilson.customchat.lib;

/**
 * Created by gparrrado on 7/13/16.
 */
public class GreenRobotEventBus implements EventBus{

    de.greenrobot.event.EventBus eventBus;

    private static class SingletonEventBusHolder {
        private static GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public GreenRobotEventBus(){
        this.eventBus = de.greenrobot.event.EventBus.getDefault();
    }

    public static GreenRobotEventBus getInstance(){
        return SingletonEventBusHolder.INSTANCE;
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }

}
