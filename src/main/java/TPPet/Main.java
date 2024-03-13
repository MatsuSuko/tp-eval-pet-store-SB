package TPPet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

        Product product1 = new Product();
        product1.setCode("P001");
        product1.setLabel("Product1");
        product1.setType(ProdType.FOOD);
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setCode("P002");
        product2.setLabel("Product2");
        product2.setType(ProdType.ACCESSORY);
        product2.setPrice(20.0);

        Product product3 = new Product();
        product3.setCode("P003");
        product3.setLabel("Product3");
        product3.setType(ProdType.CLEANING);
        product3.setPrice(30.0);

        petStore.getProducts().add(product1);
        petStore.getProducts().add(product2);
        petStore.getProducts().add(product3);

        product1.getPetStores().add(petStore);
        product2.getPetStores().add(petStore);
        product3.getPetStores().add(petStore);

        em.persist(petStore);
        em.persist(product1);
        em.persist(product2);
        em.persist(product3);

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
