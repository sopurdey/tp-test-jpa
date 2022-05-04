package fr.diginamic.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.diginamic.jpa.entities.Emprunt;

public class TestBibliotheque {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory efm = null;

		try {
			// Créer une instance d’entityManagerFactory
			efm = Persistence.createEntityManagerFactory("bddLivre");

			// Créer une instance d’entityManager
			EntityManager em = efm.createEntityManager();

			// Ouverture de transaction JPA
			em.getTransaction().begin();

			/**
			 * Réalisez une requête qui permet d’extraire un emprunt et tous ses livres
			 * associés.
			 */
			Emprunt loan = em.find(Emprunt.class, 2);
			System.out.println(loan + " - " + loan.getBooks());
			

			// Fermer le canal ouvert vers le SGBDR
			em.close();
			efm.close();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
