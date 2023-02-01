package atmani.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
public class Achat extends Imobilier {

	
	private static final long serialVersionUID = 1L;
	
	
	private Date dateAchat;

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Achat(String title, String description, double price, boolean available, String adresse,
			int surface, int rooms, Type type, Date dateAchat) {
		super(title, description, price, available, adresse, surface, rooms, type);
		this.dateAchat = dateAchat;
	}

	public Achat() {
		super();
	}
	
	
	
	

}
