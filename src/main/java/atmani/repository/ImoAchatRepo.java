package atmani.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


import atmani.model.ImoAchat;

@Repository
public interface ImoAchatRepo extends JpaRepository<ImoAchat, Integer> {

	@Query(value = "SELECT * FROM imobilier.imo_achat", nativeQuery = true)
	List<ImoAchat> getAllAchat();

}
