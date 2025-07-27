package com.ecommerce.producer;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ecommerce.producer.controller.UserActivityCart;
import com.ecommerce.producer.controller.UserActivityClick;
import com.ecommerce.producer.controller.UserActivityPage;
import com.ecommerce.producer.controller.UserActivityProducer;
import com.ecommerce.producer.controller.UserActivityPurchase;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class EventProducerMain {

    ArrayList<UserActivityProducer> userActivityProducers = new ArrayList<>();
    ArrayList<Thread> threads = new ArrayList<>();
    
    public EventProducerMain(UserActivityCart userActivityCart, 
                             UserActivityClick userActivityClick, 
                             UserActivityPage userActivityPage, 
                             UserActivityPurchase userActivityPurchase) {
        // A pub/sub design could work better if you want to decouple the event notification logic.
        // For example, you could define an EventBus interface and let producers subscribe to events.
        userActivityProducers.add(userActivityPage);
        userActivityProducers.add(userActivityClick);
        userActivityProducers.add(userActivityCart);    
        userActivityProducers.add(userActivityPurchase);
    }

    @PostConstruct
    public void startProduceUserActivityEvent() {
        for (UserActivityProducer producer : userActivityProducers) {
            Thread thread = new Thread(producer::startProduceUserActivityEvent);
            thread.start();
            threads.add(thread);
        }
    }

    @PreDestroy
    public void stopProduceUserActivityEvent() {
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                thread.interrupt(); // Interrupt the thread to stop producing events
            }
        }
        threads.clear(); // Clear the list of threads after stopping them
    }

}
