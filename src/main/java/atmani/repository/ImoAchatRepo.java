package atmani.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import atmani.model.ImoAchat;


@Repository
public interface ImoAchatRepo extends JpaRepository<ImoAchat, Integer> {

	@Query(value = "SELECT * FROM imobilier.imo_achat where type='ACHAT' and available='true'", nativeQuery = true)
	List<ImoAchat> getAllAchat();
	
	@Query(value = "SELECT * FROM imobilier.imo_achat where type='ACHAT'", nativeQuery = true)
	List<ImoAchat> getAllAchatAdmin();
	
	@Query(value = "SELECT * FROM imobilier.imo_achat where type='LOCATION' and available='true'", nativeQuery = true)
	List<ImoAchat> getAllLocation();
	
	@Modifying
	@Query(value = "UPDATE imo_achat p SET p.available =:status WHERE p.id =:id", nativeQuery = true)
	Integer updateStatus(@Param("status") String status, @Param("id") int id);

}
