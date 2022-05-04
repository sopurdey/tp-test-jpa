package fr.diginamic.jpa.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "emprunt")
public class Emprunt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_DEBUT", nullable = false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_FIN", nullable = true)
	private Date endDate;

	@Column(name = "DELAI", length = 10, nullable = true)
	private Integer loanPeriod;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENT", nullable = false)
	private Client client;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "COMPO", joinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID"))
	private Set<Livre> books;

	public Emprunt() {
		books = new HashSet<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(Integer loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<Livre> getBooks() {
		return books;
	}

	public void setBooks(Set<Livre> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Emprunt [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", loanPeriod=" + loanPeriod
				+ "]";
	}

}
