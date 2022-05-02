package org.diginamic.fr;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJpa {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	EntityManagerFactory emf = null;
	try {
	    emf= Persistence.createEntityManagerFactory("bddTestJPA");
	    EntityManager em = emf.createEntityManager();
	    
	    
	    em.close();
	    emf.close();
	} catch (Exception ex) {
	    System.err.println(ex.getMessage());
	}
    }

}
