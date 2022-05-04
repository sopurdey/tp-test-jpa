package fr.diginamic.jpa;

import fr.diginamic.jpa.dao.impl.ClientDao;
import fr.diginamic.jpa.dao.impl.EmpruntDao;
import fr.diginamic.jpa.dao.impl.FactoryDao;
import fr.diginamic.jpa.entities.Client;
import fr.diginamic.jpa.entities.Emprunt;

public class AppFactory {

	public static FactoryDao BIBLIO;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			AppFactory.BIBLIO = new FactoryDao("bddLivre");
			EmpruntDao edo = new EmpruntDao(AppFactory.BIBLIO);
			ClientDao cdo = new ClientDao(AppFactory.BIBLIO);

			/**
			 * Réalisez une requête qui permet d’extraire un emprunt et tous ses livres
			 * associés.
			 */
			Emprunt loan = edo.getAll().get(0);
			System.out.println(loan + " - " + loan.getBooks());

			/**
			 * Réalisez une requête qui permet d’extraire tous les emprunts d’un client
			 * donné.
			 */
			Client c = cdo.getAll().get(2);
			System.out.println(c + " - " + c.getLoans());
			
			

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
