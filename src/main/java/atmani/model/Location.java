package atmani.model;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class Location extends Imobilier {

	
	private static final long serialVersionUID = 1L;

	private Date dateDebut;
	
	private Date dateFin;
	

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Location(String title, String description, double price, String available, String adresse, int surface,
			int rooms, Type type, Date dateDebut, Date dateFin) {
		super(title, description, price, available, adresse, surface, rooms, type);
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Location(String title, String description, double price, String available, String adresse, int surface,
			int rooms, Type type) {
		super(title, description, price, available, adresse, surface, rooms, type);
	}

	
	public Location() {
		
	}
	
	
}
