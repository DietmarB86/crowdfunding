package be.hogent.crowdfund;

import be.hogent.crowdfund.business.PersonEntity;
import be.hogent.crowdfund.business.PersonFunction;
import be.hogent.crowdfund.business.PersonStatus;
import be.hogent.crowdfund.service.PersonService;

import javax.persistence.*;

public class Runner {
    public static void main(String[] args) {
        PersonService personService = new PersonService();
        //create objects
        PersonEntity p1 = new PersonEntity(
                "Dietmar",
                "Bauwens",
                "dietmar.bauwens@gmail.com",
                null,
                PersonFunction.ADMIN,
                PersonStatus.APPROVED
        );
        PersonEntity p2 = new PersonEntity(
                "Melissa",
                "schelfaut",
                "melsch@gmail.com",
                null, PersonFunction.FUNDER,
                PersonStatus.DISAPPROVED
        );

        PersonEntity savedPersonEntity1 = personService.createPerson(p1);
        PersonEntity savedPersonEntity2 = personService.createPerson(p2);

        System.out.println(savedPersonEntity1);
        System.out.println(savedPersonEntity2);

        PersonEntity p3 = new PersonEntity(
                "dietmar.bauwens@gmail.com",
                "hogent"
        );
        System.out.println(personService.checkPersonCredentials(p3)); // should return true
        PersonEntity p4 = new PersonEntity(
                "dfqsjkmdqsf@sdqfjkqms.com",
                "test"
        );
        System.out.println(personService.checkPersonCredentials(p4)); // should return false
    }


}
