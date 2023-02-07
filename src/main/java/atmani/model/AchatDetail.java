package atmani.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	private byte[] image;

	private Date dateAchat;

}
