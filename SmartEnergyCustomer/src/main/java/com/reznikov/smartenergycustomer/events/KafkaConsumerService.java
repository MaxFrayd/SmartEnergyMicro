package com.reznikov.smartenergycustomer.events;



import com.reznikov.smartenergycustomer.dto.CustomerFullDto;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


@EnableBinding(Sink.class)
public class KafkaConsumerService {

    @StreamListener(Sink.INPUT)
    public void consumeDressesTopic(CustomerFullDto customer) {
        System.out.println("Received message from 'dresses' topic: " + customer);
    }

//    @Bean
//    public Consumer<Message<String>> orgChangeTopic() {
//        return message -> {
//            System.out.println("Received message from 'dresses' topic: " + message.getPayload());
//        };
//    }


}
