package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.Idao;
import fr.diginamic.jpa.entities.Client;
import fr.diginamic.jpa.entities.Emprunt;

public class ClientDao extends Dao implements Idao<Client> {

	public ClientDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Client e) throws Exception {
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
	public boolean update(Client e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Client etrans = em.find(Client.class, e.getId());
			if (etrans != null) {
				etrans.setLastname(e.getLastname());
				etrans.setFirstname(e.getFirstname());
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
	public boolean delete(Client e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Client.class, e.getId());
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
	public Client getOne(Client e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Client> tqb = fd.getEm().createQuery("SELECT c FROM Client c WHERE c.id = :id", // variables
				// OBJET
				Client.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList().get(0);
	}

	@Override
	public List<Client> getAll() throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Client> tqb = fd.getEm().createQuery("SELECT c FROM Client c", Client.class);

		return tqb.getResultList();
	}
	
	public List<Emprunt> getLoans(Client e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Emprunt> tqb = fd.getEm().createNamedQuery("Client.getLoans", Emprunt.class);
		tqb.setParameter("id", e.getId());
		return tqb.getResultList();
	}

}
