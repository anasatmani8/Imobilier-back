package atmani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atmani.model.Imobilier;

@Repository
public interface ImobilierRepo extends JpaRepository<Imobilier, Integer> {

}
