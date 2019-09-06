package blu.kafka.sender.controllers;

import blu.kafka.sender.model.Person;
import blu.kafka.sender.senders.PersonSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private PersonSender personSender;

    public PersonController(PersonSender personSender) {
        this.personSender = personSender;
    }

    @GetMapping("/")
    public void sendPerson() {
        Person person = new Person("Luke", 19);
        personSender.sendPerson(person);
    }
}
