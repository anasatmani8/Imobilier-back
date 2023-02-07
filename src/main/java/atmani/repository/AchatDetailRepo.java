package atmani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import atmani.model.AchatDetail;

public interface AchatDetailRepo extends JpaRepository<AchatDetail, Integer> {
	
	@Query(value = "SELECT i.rooms , i.adresse, i.available, i.description, i.price, i.surface,i.title, im.image, im.name , a.date_achat FROM imobilier.achat a , imobilier i, image im where i.id=a.id and i.id=im.imobilier_id and i.id=:id", nativeQuery = true)
	List<AchatDetail> getAllAchatDetail(@Param("id") int id);

}
