package atmani.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Imobilier implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	//private List<Blob> images;
	
	private String description;
	
	
	private double price;
	
	private boolean available;
	
	private String adresse;
	
	private int surface;
	
	private int Rooms;
	
	@Enumerated(EnumType.STRING)
	private Type type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public int getRooms() {
		return Rooms;
	}

	public void setRooms(int rooms) {
		Rooms = rooms;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Imobilier(String title, String description , double price, boolean available, String adresse,
			int surface, int rooms, Type type) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.available = available;
		this.adresse = adresse;
		this.surface = surface;
		this.Rooms = rooms;
		this.type = type;
	}

	public Imobilier() {
		super();
	}
	
	
	

}
