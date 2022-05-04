package fr.diginamic.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "client")
@NamedQueries({ @NamedQuery(name = "Client.getLoans", query = "SELECT e FROM Emprunt e WHERE e.client.id = :id") })
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOM", length = 50)
	private String lastname;

	@Column(name = "PRENOM", length = 50)
	private String firstname;

	@OneToMany(mappedBy = "client")
	private Set<Emprunt> loans;

	public Client() {
		loans = new HashSet<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Set<Emprunt> getLoans() {
		return loans;
	}

	public void setLoans(Set<Emprunt> loans) {
		this.loans = loans;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + "]";
	}

}
