package atmani.model;



import java.sql.Blob;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
public class Image {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id_image;
	  @Lob
	  private Collection<byte[]>  data;
	  @JsonProperty(access = Access.WRITE_ONLY)
	  @ManyToOne
	  private Imobilier imobilier;

}
