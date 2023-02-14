package atmani.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import atmani.model.Achat;
import atmani.model.Imobilier;
import atmani.model.Location;

@RepositoryRestResource
@Transactional
@Repository
public interface ImobilierRepo extends JpaRepository<Imobilier, Integer> {

	@Query(value = "SELECT * FROM imobilier.imobilier i , location l where l.id=i.id", nativeQuery = true)
	List<Location> getAllLocations();

	@Query(value = "SELECT * FROM imobilier.imobilier i , achat a where a.id=i.id", nativeQuery = true)
	List<Achat> getAllAchat();

	@Modifying
	@Query(value = "UPDATE imobilier p SET p.available =:status WHERE p.id =:id", nativeQuery = true)
	Integer updateStatus(@Param("status") String status, @Param("id") int id);
	
	
	
	

	@Modifying
	@Query(value = "UPDATE imobilier i, achat a SET i.rooms =:rooms, i.adresse=:adresse, i.available=:available, i.description=:desc, i.price=:price, i.surface=:surface,"
			+ " i.title=:title , a.date_achat=:date WHERE i.id =:id and a.id=:id", nativeQuery = true)
	Integer updateAchat(@Param("rooms") int rooms, @Param("adresse") String adresse,
			@Param("available") String available, @Param("desc") String description, @Param("price") double price,
			@Param("surface") double surface, @Param("title") String title, @Param("date") Date date,
			@Param("id") int id);
	
	

	/*@Query(value = "SELECT i.rooms , i.adresse, i.available, i.description, i.price, i.surface,i.title, im.image, im.name , a.date_achat FROM imobilier.achat a , imobilier i, image im where i.id=a.id and i.id=im.imobilier_id and i.id=:id", nativeQuery = true)
	List<Optional<?>> getAllAchatDetail(@Param("id") int id);
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
};