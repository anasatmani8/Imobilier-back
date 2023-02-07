package atmani.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

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

	  @ManyToOne(fetch = FetchType.LAZY)
	  private Imobilier imobilier;
	  
	  private String name;
	  
	  private String type;

	

}
