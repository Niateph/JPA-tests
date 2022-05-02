package org.diginamic.fr;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.diginamic.fr.entities.Livre;

public class TestJpa {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	EntityManagerFactory emf = null;
	try {
	    emf = Persistence.createEntityManagerFactory("bddTestJPA");
	    EntityManager em = emf.createEntityManager();

	    /**
	     * Simple display des infos d'un livre en donnant son index (et le entity
	     * manager passé en 2nd argument)
	     */
	    afficherInfos(1, em);
	    /**
	     * Insertion d'un livre dans la table
	     */
	    Livre livreToAdd = new Livre();
	    livreToAdd.setTitre("Neuromancer");
	    livreToAdd.setAuteur("William Gibson");
	    em.persist(livreToAdd);
	    em.getTransaction().commit();
	    /**
	     * Update d'un livre dans la table
	     */
	    em.getTransaction().begin();
	    Livre livreToUpdate = em.find(Livre.class, 5);
	    if (livreToUpdate != null) {
		livreToUpdate.setTitre("Du plaisir dans la cusine");
		
		em.merge(livreToUpdate);
		System.out.println("livre update en " + livreToUpdate.getId());
		em.getTransaction().commit();
	    } else {
		em.getTransaction().rollback();
	    }
	    /**
	     * Extraction d'un livre par titre
	     */
	    TypedQuery<Livre> queryTitre = em.createQuery("SELECT one FROM Livre one WHERE one.titre='Germinal'",
		    Livre.class);
	    List<Livre> resultat = queryTitre.getResultList();
	    System.out.println(
		    "Livre trouvé. Id =  " + resultat.get(0).getId() + " Autueur = " + resultat.get(0).getAuteur());

	    /**
	     * Extraction d'un livre par auteur
	     */
	    TypedQuery<Livre> queryAuteur = em.createQuery("SELECT l FROM Livre l WHERE l.auteur='Jules Verne'",
		    Livre.class);
	    resultat = queryAuteur.getResultList();
	    System.out.println(
		    "Livre trouvé. Id =  " + resultat.get(0).getId() + " Titre = " + resultat.get(0).getTitre());

	    em.getTransaction().begin();
	    Livre livreToDelete = em.find(Livre.class, 3);
	    em.remove(livreToDelete);
	    em.getTransaction().commit();

	    TypedQuery<Livre> querryDisplay = em.createQuery("SELECT l FROM Livre l", Livre.class);
	    List<Livre> listTousLesLivres = querryDisplay.getResultList();

	    listTousLesLivres.stream().forEach(livreTemp -> System.out
		    .println("Auteur : " + livreTemp.getAuteur() + "Titre : " + livreTemp.getTitre()));

	    em.close();
	    emf.close();
	} catch (Exception ex) {
	    System.err.println(ex.getMessage());
	}
    }

    public static void afficherInfos(int index, EntityManager em) {
	em.getTransaction().begin();
	Livre livreExtrait = em.find(Livre.class, index);
	System.out.println("titre : " + livreExtrait.getTitre() + " et auteur : " + livreExtrait.getAuteur());
    }
}
