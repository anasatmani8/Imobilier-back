package atmani.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ImageModel implements Serializable { 
	

	public ImageModel(byte[] image, String name, String type) {
		super();
		this.image = image;
		this.name = name;
		this.type = type;
	}

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	  @Column(length = 500000)
	  private byte[] image;

	 /* @ManyToMany(fetch= FetchType.EAGER)
	  private Set<Imobilier> imobiliers;*/
	  
	  private String name;
	  
	  private String type;
	  
	  

}
