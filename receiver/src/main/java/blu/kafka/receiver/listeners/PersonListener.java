package blu.kafka.receiver.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PersonListener {

    @KafkaListener(topics = "person", groupId = "groupId")
    private void handlePerson(String in) {
        System.out.println("Received: " + in);
    }
}
