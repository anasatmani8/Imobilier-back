package atmani.repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import atmani.model.Imobilier;

@Repository

public interface ImobilierRepo extends JpaRepository<Imobilier, Integer> {

	@Modifying
	@Query(value = "DELETE FROM imobilier.achat WHERE id =:id",nativeQuery = true)
	Integer deleteAchat(@Param("id") int id);


}
