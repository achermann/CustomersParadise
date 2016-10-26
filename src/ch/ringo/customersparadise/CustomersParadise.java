/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.ringo.customersparadise;

import ch.ringo.customersparadise.domain.Category;
import ch.ringo.customersparadise.domain.Customer;
import ch.ringo.customersparadise.domain.Merchant;
import ch.ringo.customersparadise.domain.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author achermann
 */
public class CustomersParadise {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Customer customer[] = new Customer[2];
        customer[0] = new Customer();
        customer[0].setName("Achermann");
        customer[0].setFirstname("Bernard");
        customer[1] = new Customer();
        customer[1].setName("Gilgen");
        customer[1].setFirstname("Nicole");

        Merchant merchant = new Merchant();
        merchant.setName("Loeb");
        merchant.setCategory(Category.CAT1);
        
        Transaction transaction = new Transaction();
        transaction.setCustomer(1L);
        transaction.setMerchant(1L);
        transaction.setAmount(25.25);

        CustomersParadise paradise = new CustomersParadise();
//        for (int i = 0; i < customer.length; i++) {
//            paradise.persist(customer[i]);
//        }
        paradise.persist(customer[1]);
        paradise.persist(merchant);
        paradise.persist(transaction);
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomersParadisePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
