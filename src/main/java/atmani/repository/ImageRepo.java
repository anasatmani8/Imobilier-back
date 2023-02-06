package atmani.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import atmani.model.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {
	
	Optional<Image> findByName(String name);

}
