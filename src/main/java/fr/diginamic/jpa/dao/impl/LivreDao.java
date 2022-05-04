package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.Idao;
import fr.diginamic.jpa.entities.Livre;

public class LivreDao extends Dao implements Idao<Livre> {

	public LivreDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Livre e) throws Exception {
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
	public boolean update(Livre e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Livre etrans = em.find(Livre.class, e.getId());
			if (etrans != null) {
				etrans.setTitle(e.getTitle());
				etrans.setAuthor(e.getAuthor());
				etrans.setLoans(e.getLoans());
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
	public boolean delete(Livre e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Livre.class, e.getId());
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
	public Livre getOne(Livre e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Livre> tqb = fd.getEm().createQuery("SELECT l FROM Livre l WHERE l.id = :id", // variables
				// OBJET
				Livre.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList().get(0);
	}

	@Override
	public List<Livre> getAll() throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Livre> tqb = fd.getEm().createQuery("SELECT l FROM Livre l", Livre.class);

		return tqb.getResultList();
	}

}
