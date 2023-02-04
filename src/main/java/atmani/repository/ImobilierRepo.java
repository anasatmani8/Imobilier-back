package atmani.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import atmani.model.Achat;
import atmani.model.Imobilier;
import atmani.model.Location;
@Transactional
@Repository
public interface ImobilierRepo extends JpaRepository<Imobilier, Integer> {

	@Query(value = "SELECT * FROM imobilier.imobilier i , location l where l.id=i.id", nativeQuery = true)
	List<Location> getAllLocations();
	
	@Query(value = "SELECT * FROM imobilier.imobilier i , achat a, image im where a.id=i.id and im.imobilier_id=i.id ", nativeQuery = true)
	List<Achat> getAllAchat();
	
	@Modifying
	@Query(value = "UPDATE imobilier p SET p.available =:status WHERE p.id =:id", nativeQuery = true)
	Integer updateStatus(@Param("status") String status, @Param("id") int id);

};