package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.Idao;
import fr.diginamic.jpa.entities.Livre;
import fr.diginamic.jpa.entities.Emprunt;

public class EmpruntDao extends Dao implements Idao<Emprunt> {

	public EmpruntDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Emprunt e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		} finally {
			fd.close(em);
		}
	}

	@Override
	public boolean update(Emprunt e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Emprunt etrans = em.find(Emprunt.class, e.getId());
			if (etrans != null) {
				etrans.setStartDate(e.getStartDate());
				etrans.setEndDate(e.getEndDate());
				etrans.setLoanPeriod(e.getLoanPeriod());
				etrans.setClient(e.getClient());
				etrans.setBooks(e.getBooks());
				em.merge(etrans);
				em.getTransaction().commit();
				return true;
			}
			return false;

		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		} finally {
			fd.close(em);
		}
	}

	@Override
	public boolean delete(Emprunt e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Emprunt.class, e.getId());
			if (e != null) {
				em.remove(e);
				em.getTransaction().commit();
				return true;
			}
			return false;

		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		} finally {
			fd.close(em);
		}
	}

	@Override
	public Emprunt getOne(Emprunt e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Emprunt> tqb = fd.getEm().createQuery("SELECT e FROM Emprunt e WHERE e.id = :id", // variables
				// OBJET
				Emprunt.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList().get(0);
	}

	@Override
	public List<Emprunt> getAll() throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Emprunt> tqb = fd.getEm().createQuery("SELECT e FROM Emprunt e", Emprunt.class);

		return tqb.getResultList();
	}

	public List<Livre> getBooks(Emprunt e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Livre> tqb = fd.getEm().createNamedQuery("Emprunt.getBooks", Livre.class);
		tqb.setParameter("livre", e);
		return tqb.getResultList();
	}
}
