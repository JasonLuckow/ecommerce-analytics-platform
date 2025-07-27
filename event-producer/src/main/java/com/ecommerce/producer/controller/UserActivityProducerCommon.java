package com.ecommerce.producer.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class UserActivityProducerCommon implements UserActivityProducer{

    protected abstract int getMinGenerationInterval();

    protected abstract int getMaxGenerationInterval();

    protected abstract String produceJsonActivityEvent();

    protected abstract void sendEvent(String event);


    private boolean isProducing;
    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledFuture;

    public UserActivityProducerCommon() {
        this.isProducing = false;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void startProduceUserActivityEvent() {
        if (isProducing) return;
        isProducing = true;
        scheduleNextEvent();
    }

    private void scheduleNextEvent() {
        if (!isProducing) return;
        int interval = (int) (Math.random() * (this.getMaxGenerationInterval() - this.getMinGenerationInterval()) + this.getMinGenerationInterval());
        scheduledFuture = scheduler.schedule(() -> {
            String event = produceJsonActivityEvent();
            System.out.println("Producing event: " + event);
            sendEvent(event);
            scheduleNextEvent();
        }, interval, TimeUnit.SECONDS);
    }

    @Override
    public void stopProduceUserActivityEvent() {
        this.isProducing = false;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    protected int generateRandomProduct() {
        return (int) (Math.random() * 10) + 1; // Generates a random product ID between 1 and 10
    }   

    protected int generateRandomQuantity() {
        return (int) (Math.random() * 5) + 1; // Generates a random quantity between 1 and 5
    }

    protected double generateRandomPrice() {
        return Math.random() * 100 + 1; // Generates a random price between 1 and 100
    }

    protected double generateTotalPrice(int quantity, double price) {
        return quantity * price; // Calculates total price based on quantity and individual price
    }

    protected String produceTransactionalJsonActivityEvent() {
        // Create a JSON string representing the user activity event
        int itemCount = (int) (Math.random() * 5) + 1; // 1 to 5 items
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < itemCount; i++) {
            int pid = generateRandomProduct();
            int qty = generateRandomQuantity();
            double prc = generateRandomPrice();
            double tprc = generateTotalPrice(qty, prc);
            sb.append(String.format("{\"productId\":%d,\"quantity\":%d,\"price\":%.2f,\"totalPrice\":%.2f,\"timestamp\":%d}", 
                        pid, qty, prc, tprc, System.currentTimeMillis()));
            if (i < itemCount - 1) {
            sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
