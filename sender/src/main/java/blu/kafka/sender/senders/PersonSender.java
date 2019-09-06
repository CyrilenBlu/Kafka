package blu.kafka.sender.senders;

import blu.kafka.sender.model.Person;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class PersonSender {

    private KafkaTemplate<String, String> kafkaTemplate;

    public PersonSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPerson(Person person) {
//        kafkaTemplate.send("person", person.toString());
//        System.out.println("Person " + person.getName() + " sent!");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("person", person.toString());

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Unable to send message: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                System.out.println("Sent message=[" + person.toString() + "] with offset=["
                + stringStringSendResult.getRecordMetadata().offset() + "]");
            }
        });
    }
}
