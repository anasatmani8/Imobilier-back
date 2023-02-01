package atmani.model;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class Vente extends Imobilier {
 
	
	private static final long serialVersionUID = 1L;
	
	private Date  dateVente;

	public Date getDateVente() {
		return dateVente;
	}

	public void setDateVente(Date dateVente) {
		this.dateVente = dateVente;
	}
	
	
	

}
