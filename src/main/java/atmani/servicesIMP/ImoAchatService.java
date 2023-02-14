package atmani.servicesIMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atmani.model.ImoAchat;
import atmani.repository.ImoAchatRepo;

@Service
public class ImoAchatService {
	 @Autowired
	 ImoAchatRepo achatRepo;
	 
	 public ImoAchat addAchat(ImoAchat achat) {
		 return achatRepo.save(achat);
		  
	 }

}
