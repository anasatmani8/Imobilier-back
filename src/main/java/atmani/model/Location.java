package atmani.model;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class Location extends Imobilier {

	
	private static final long serialVersionUID = 1L;

	private Date dateDebut;
	
	private Date dateFin;
	
	private double price;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
