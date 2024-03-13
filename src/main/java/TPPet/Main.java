package TPPet;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Créer et persister des entités
        PetStore petStore = new PetStore();
        petStore.setName("PetStore1");
        petStore.setManagerName("Manager1");
        em.persist(petStore);

        Product product1 = new Product();
        product1.setCode("P001");
        product1.setLabel("Product1");
        product1.setType(ProdType.FOOD);
        product1.setPrice(10.0);
        em.persist(product1);

        Product product2 = new Product();
        product2.setCode("P002");
        product2.setLabel("Product2");
        product2.setType(ProdType.ACCESSORY);
        product2.setPrice(20.0);
        em.persist(product2);

        Product product3 = new Product();
        product3.setCode("P003");
        product3.setLabel("Product3");
        product3.setType(ProdType.CLEANING);
        product3.setPrice(30.0);
        em.persist(product3);

        petStore.getProducts().add(product1);
        petStore.getProducts().add(product2);
        petStore.getProducts().add(product3);

        product1.getPetStores().add(petStore);
        product2.getPetStores().add(petStore);
        product3.getPetStores().add(petStore);

        Fish fish1 = new Fish();
        fish1.setBirth(new Date());
        fish1.setColor("Red");
        fish1.setLivingEnv(FishLivEnv.FRESH_WATER);
        em.persist(fish1);

        Fish fish2 = new Fish();
        fish2.setBirth(new Date());
        fish2.setColor("Blue");
        fish2.setLivingEnv(FishLivEnv.SEA_WATER);
        em.persist(fish2);

        Fish fish3 = new Fish();
        fish3.setBirth(new Date());
        fish3.setColor("Green");
        fish3.setLivingEnv(FishLivEnv.FRESH_WATER);
        em.persist(fish3);

        petStore.getAnimals().add(fish1);
        petStore.getAnimals().add(fish2);
        petStore.getAnimals().add(fish3);

        fish1.getPetStores().add(petStore);
        fish2.getPetStores().add(petStore);
        fish3.getPetStores().add(petStore);

        Cat cat1 = new Cat();
        cat1.setBirth(new Date());
        cat1.setColor("Black");
        cat1.setChipId("C001");
        em.persist(cat1);

        Cat cat2 = new Cat();
        cat2.setBirth(new Date());
        cat2.setColor("White");
        cat2.setChipId("C002");
        em.persist(cat2);

        Cat cat3 = new Cat();
        cat3.setBirth(new Date());
        cat3.setColor("Gray");
        cat3.setChipId("C003");
        em.persist(cat3);

        petStore.getAnimals().add(cat1);
        petStore.getAnimals().add(cat2);
        petStore.getAnimals().add(cat3);

        cat1.getPetStores().add(petStore);
        cat2.getPetStores().add(petStore);
        cat3.getPetStores().add(petStore);

        Address address1 = new Address();
        address1.setNumber("1");
        address1.setStreet("Street1");
        address1.setZipCode("12345");
        address1.setCity("City1");
        em.persist(address1);

        Address address2 = new Address();
        address2.setNumber("2");
        address2.setStreet("Street2");
        address2.setZipCode("67890");
        address2.setCity("City2");
        em.persist(address2);

        Address address3 = new Address();
        address3.setNumber("3");
        address3.setStreet("Street3");
        address3.setZipCode("11111");
        address3.setCity("City3");
        em.persist(address3);

        petStore.getAddresses().add(address1);
        petStore.getAddresses().add(address2);
        petStore.getAddresses().add(address3);

        address1.getPetStores().add(petStore);
        address2.getPetStores().add(petStore);
        address3.getPetStores().add(petStore);

        // Réaliser une requête pour extraire tous les animaux d'une animalerie donnée
        List<Animal> animals = em.createQuery("SELECT a FROM Animal a JOIN a.petStores ps WHERE ps.id = :petStoreId", Animal.class)
                .setParameter("petStoreId", petStore.getId())
                .getResultList();

        em.getTransaction().commit();

        System.out.println("Animals in PetStore1:");
        for (Animal animal : animals) {
            System.out.println(animal.getClass().getSimpleName() + ": " + animal.getId());
        }

        em.close();
        emf.close();
    }
}
