package org.simplilearn.workshop.service;

import org.simplilearn.workshop.model.Admin;
import org.simplilearn.workshop.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	    private AdminRepository adminRepository;

	    public AdminService(AdminRepository adminRepository) {
	        this.adminRepository = adminRepository;
	    }

	    public Admin findById(Long id) {
	        return adminRepository.findById(id).orElse(null);
	    }

	    public Admin findByUsernameAndPassword(String username, String password) {
	        return adminRepository.findByLoginUsernameAndLoginPassword(username, password);
	    }

	    public Admin save(Admin admin) {
	        return adminRepository.save(admin);
	    }
	    
	   
	    public void updatePassword(Admin admin) {
			adminRepository.save(admin);
		}
	    

 	public Admin findByLoginUsername(String username) {
	    	
	    	return adminRepository.findByLoginUsername(username);
	    }

}

