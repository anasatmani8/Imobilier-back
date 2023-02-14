package atmani.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import atmani.model.ImoAchat;

@Repository
public interface ImoAchatRepo extends JpaRepository<ImoAchat, Integer> {


}
