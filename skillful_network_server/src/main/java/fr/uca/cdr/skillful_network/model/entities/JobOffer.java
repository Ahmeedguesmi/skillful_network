package fr.uca.cdr.skillful_network.model.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
public class JobOffer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
	private String company;
	private String description;
	private String type;
	private Date dateBeg;
	private Date dateEnd;
	private Date dateUpload;
	private String[] keywords;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDateBeg() {
		return dateBeg;
	}
	public void setDateBeg(Date dateBeg) {
		this.dateBeg = dateBeg;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Date getDateUpload() {
		return dateUpload;
	}
	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	public JobOffer() {
		super();
	}

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<JobApplication> jobAplicationSets = new HashSet<>();
	
	
	public JobOffer(Long id, String name, String company, String description, String type, Date dateBeg, Date dateEnd,
			Date dateUpload, String[] keywords, Set<JobApplication> jobAplicationSets) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.description = description;
		this.type = type;
		this.dateBeg = dateBeg;
		this.dateEnd = dateEnd;
		this.dateUpload = dateUpload;
		this.keywords = keywords;
		this.jobAplicationSets = jobAplicationSets;
	}
	
	
	public JobOffer(String name, String company, String description, String type, Date dateBeg, Date dateEnd,
			Date dateUpload, String[] keywords, Set<JobApplication> jobAplicationSets) {
		super();
		this.name = name;
		this.company = company;
		this.description = description;
		this.type = type;
		this.dateBeg = dateBeg;
		this.dateEnd = dateEnd;
		this.dateUpload = dateUpload;
		this.keywords = keywords;
		this.jobAplicationSets = jobAplicationSets;
	}
	public JobOffer(String name, String company, String description, String type, Date dateBeg, Date dateEnd,
			Date dateUpload, Set<JobApplication> jobAplicationSets) {
		super();
		this.name = name;
		this.company = company;
		this.description = description;
		this.type = type;
		this.dateBeg = dateBeg;
		this.dateEnd = dateEnd;
		this.dateUpload = dateUpload;
		this.jobAplicationSets = jobAplicationSets;
	}
	@Override
	public String toString() {
		return "JobOffer [" + id + "] name=" + name + ", company=" + company + ", description=" + description
				+ ", type=" + type + ", dateBeg=" + dateBeg + ", dateEnd=" + dateEnd + ", dateUpload=" + dateUpload
				+ ", keywords=" + keywords +
				", jobAplicationSets=" + jobAplicationSets;
	}
	
	
	
	
	
	
	
	
	

	
	
	
}
