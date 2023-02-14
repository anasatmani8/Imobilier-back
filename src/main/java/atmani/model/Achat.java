package atmani.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data @Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
@Entity
public class Achat extends Imobilier {

	
	private static final long serialVersionUID = 1L;
	
	
	private Date dateAchat;


	public Achat(String title, String description, double price, String available, String adresse,
			int surface, int rooms, Type type, Date dateAchat) {
		super(title, description, price, available, adresse, surface, rooms, type);
		this.dateAchat = dateAchat;
	}

	
	
	
	

}
