package atmani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import atmani.model.Achat;
import atmani.model.Imobilier;
import atmani.model.Location;

@Repository
public interface ImobilierRepo extends JpaRepository<Imobilier, Integer> {

	@Query(value = "SELECT * FROM imobilier.imobilier i , location l where l.id=i.id", nativeQuery = true)
	List<Location> getAllLocations();
	
	@Query(value = "SELECT * FROM imobilier.imobilier i , achat a where a.id=i.id", nativeQuery = true)
	List<Achat> getAllAchat();
};