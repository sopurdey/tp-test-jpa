package fr.diginamic.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "livre")
@NamedQueries({ @NamedQuery(name = "Emprunt.getBooks", query = "SELECT l FROM Livre l WHERE :emprunt MEMBER OF l.loans") })
public class Livre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "TITRE", length = 255, nullable = false)
	private String title;

	@Column(name = "AUTEUR", length = 50, nullable = false)
	private String author;

	@ManyToMany(mappedBy = "books")
	private Set<Emprunt> loans;

	public Livre() {
		loans = new HashSet<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Set<Emprunt> getLoans() {
		return loans;
	}

	public void setLoans(Set<Emprunt> loans) {
		this.loans = loans;
	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", title=" + title + "]";
	}

}
