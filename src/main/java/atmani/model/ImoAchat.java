package atmani.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class ImoAchat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String description;

	private double price;

	private String adresse;

	private int surface;

	private int Rooms;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	private String available;
	

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "imoAchat_images", joinColumns = { @JoinColumn(name = "imoAchat_id") }, inverseJoinColumns = {
			@JoinColumn(name = "image_id") })
	private Set<ImageModel> imoAchatImages;

}
