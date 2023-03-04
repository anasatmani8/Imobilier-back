package atmani.model;



import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data @Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
@Entity

public class Image implements Serializable { 
	

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_image;
	
	  @Lob
	  private byte[] image;

	  @ManyToMany(fetch= FetchType.EAGER)
	  private Set<Imobilier> imobiliers;
	  
	  private String name;
	  
	  private String type;

	public Image(byte[] image, String name, String type) {
		super();
		this.image = image;
		this.name = name;
		this.type = type;
	}
	  
	  

	

}
