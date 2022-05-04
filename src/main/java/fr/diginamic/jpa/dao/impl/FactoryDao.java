package fr.diginamic.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe de type service de la Factory de l'unité de persistence
 * 
 * @author AP
 *
 */
public class FactoryDao {

	private final EntityManagerFactory emf;

	public FactoryDao(String unitepersistence) throws Exception {
// TODO Auto-generated constructor stub
		emf = Persistence.createEntityManagerFactory(unitepersistence);
	}

	/**
	 * 
	 * public EntityManagerFactory getEmf() { return emf; }
	 */

	public EntityManager getEm() throws Exception {
		return emf.createEntityManager();
	}

	public void close(EntityManager em) throws Exception {
		if (em.isOpen()) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	private void close() throws Exception {
		if (emf.isOpen()) {
			emf.close();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		close();
	}
}