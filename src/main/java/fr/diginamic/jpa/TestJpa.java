package fr.diginamic.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJpa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory efm = null;

		try {
			// Créer une instance d’entityManagerFactory
			efm = Persistence.createEntityManagerFactory("bddCompta");

			// Créer une instance d’entityManager
			EntityManager em = efm.createEntityManager();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
