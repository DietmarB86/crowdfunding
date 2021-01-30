package be.hogent.crowdfund.service;

import be.hogent.crowdfund.business.PersonEntity;

import javax.persistence.*;
import java.util.List;

public class PersonService {
    List<PersonEntity> personEntityList;
    //set up JPA
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Persons");
    EntityManager em = emf.createEntityManager();

    private EntityManager getEntityManager(){
        if (emf.isOpen()){
            em = emf.createEntityManager();
        }
        else{
            emf = Persistence.createEntityManagerFactory("JPA_Persons");
            em = emf.createEntityManager();
        }
        return em;
    }

    private EntityTransaction getEntityTransaction(){
        return getEntityManager().getTransaction();
    }

    private int getExistingPersonIdByEmailCheck(PersonEntity personEntity){
        int result = -1;
        try{
            em = getEntityManager();
            personEntityList = em.createQuery("select p from PersonEntity p where p.email = '" + personEntity.getEmail() + "'", PersonEntity.class).getResultList();

            if (!personEntityList.isEmpty()){
                result = personEntityList.get(0).getPersonID();
            }
        }
        catch (Exception e){
            //logging nog voorzien
            System.out.println(e.getMessage());
        }
        return result;
    }

    public PersonEntity createPerson(PersonEntity personEntity){
        PersonEntity savedPersonEntity = null;
        try {
            EntityTransaction et = getEntityTransaction();
            et.begin();

            personEntity.setPersonID(getExistingPersonIdByEmailCheck(personEntity));

            savedPersonEntity = em.merge(personEntity);

            et.commit();
        }
        catch (PersistenceException persistenceException){
            System.out.println(persistenceException.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
        return savedPersonEntity;
    }

    public boolean checkPersonCredentials(PersonEntity personEntity){
        try {
            em = getEntityManager();
            PersonEntity personExists = em.createQuery("select p from PersonEntity p where p.email = '" + personEntity.getEmail() + "' and p.password = '" + personEntity.getPassword() + "'", PersonEntity.class).getSingleResult();
            if (personExists != null){
                return true;
            }
        }
        catch (Exception e){
            //logging nog voorzien
            if (e.getClass() != NoResultException.class){
                System.out.println(e.getMessage());
            }
            return false;
        }
        finally {
            em.close();
            emf.close();
        }
        return false;
    }
}
