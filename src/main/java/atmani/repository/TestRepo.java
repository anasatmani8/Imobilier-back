package atmani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import atmani.model.Test;
@Repository
public interface TestRepo extends JpaRepository<Test, Integer> {

	@Query(value = "SELECT i.id,i.description,im.data,im.imobilier_id FROM imobilier.imobilier i , achat a, image im where a.id=i.id and im.imobilier_id=i.id ", nativeQuery = true)
	List<Test> getAllAchat();
}
