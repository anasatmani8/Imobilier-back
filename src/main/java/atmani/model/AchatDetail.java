package atmani.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AchatDetail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_achatDetail;
	private String title;

	private String description;

	private double price;

	private String available;

	private String adresse;

	private int surface;

	private int Rooms;
	
	@Lob
	private byte[] image;

	private Date dateAchat;
	
	private String nameImage;

	public AchatDetail(String title, String description, double price, String available, String adresse, int surface,
			int rooms, byte[] image, Date dateAchat, String nameImage) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.available = available;
		this.adresse = adresse;
		this.surface = surface;
		Rooms = rooms;
		this.image = image;
		this.dateAchat = dateAchat;
		this.nameImage = nameImage;
	}
	
	
	
	

}
