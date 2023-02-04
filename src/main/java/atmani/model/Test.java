package atmani.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idd;
	
 private int id;
 private int imobilier_id;
 private String description;
 private byte[]  data;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getImobilier_id() {
	return imobilier_id;
}
public void setImobilier_id(int imobilier_id) {
	this.imobilier_id = imobilier_id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public byte[] getData() {
	return data;
}
public void setData(byte[] data) {
	this.data = data;
} 
 
 
}
