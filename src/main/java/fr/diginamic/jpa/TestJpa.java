package fr.diginamic.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.entities.Livre;

public class TestJpa {

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
			 * Réalisez un find simple permettant d’extraire un livre en fonction de son
			 * identifiant et affichez son titre et son auteur.
			 */
			Livre book = em.find(Livre.class, 1);
			System.out.println(book.getTitle() + " - " + book.getAuthor());

			/**
			 * Insérez un nouveau Livre de votre choix en base de données
			 */
			Livre newBook = new Livre();
			newBook.setTitle("Le Comte de Monte-Cristo");
			newBook.setAuthor("Alexandre Dumas");
			em.persist(newBook);
			em.getTransaction().commit();
			System.out.println("Ajout de livre : " + newBook.getTitle() + " - " + newBook.getAuthor());

			/**
			 * Modifiez le titre du livre d’identifiant 5 : le nouveau titre doit être « Du
			 * plaisir dans la cuisine » au lieu de « 1001 recettes de Cuisine ».
			 */
			em.getTransaction().begin();
			Livre editBook = em.find(Livre.class, 5);
			System.out.println("Livre à modifier : " + editBook.getTitle());
			if (editBook != null) {
				editBook.setTitle("Du plaisir dans la cuisine");
				em.merge(editBook);
				em.getTransaction().commit();
			}
			System.out.println("Titre modifié : " + editBook.getTitle());

			/**
			 * Faites une requête JPQL pour extraire de la base un livre en fonction de son
			 * titre.
			 */
			em.getTransaction().begin();
			TypedQuery<Livre> ql = em.createQuery("SELECT l from Livre l WHERE l.title='Guerre et paix'", Livre.class);
			Livre qBook = ql.getResultList().get(0);
			System.out.println("Livre JPQL titre : " + qBook.getTitle() + " - " + qBook.getAuthor());

			/**
			 * Faites une requête JPQL pour extraire de la base un livre en fonction de son
			 * auteur.
			 */
			TypedQuery<Livre> ql2 = em.createQuery("SELECT l from Livre l WHERE l.author='Emile Zola'", Livre.class);
			Livre qAuthor = ql2.getResultList().get(0);
			System.out.println("Livre JPQL auteur : " + qAuthor.getTitle() + " - " + qAuthor.getAuthor());

			/**
			 * Supprimez un livre de votre choix en base de données.
			 */
			Livre delBook = em.find(Livre.class, 4);
			System.out.println("Livre à supprimer : " + delBook.getTitle());
			if (delBook != null) {
				em.remove(delBook);
				em.getTransaction().commit();// Sinon RollBack
			}

			/**
			 * Affichez la liste de tous les livres présents en base de données (titre et
			 * auteur).
			 */
			em.getTransaction().begin();
			TypedQuery<Livre> qBooks = em.createQuery("SELECT l FROM Livre l", Livre.class);
			List<Livre> books = qBooks.getResultList();
			books.stream().forEach(b -> System.out.println(b.getTitle() + " - " + b.getAuthor()));
			em.getTransaction().commit();

			// Fermer le canal ouvert vers le SGBDR
			em.close();
			efm.close();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
