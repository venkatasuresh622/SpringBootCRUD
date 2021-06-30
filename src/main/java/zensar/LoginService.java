package com.zensar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    
    @Autowired
    LoginRepository loginRepository;

 

    public boolean checkLogin(String userId, String password) {
        boolean result = false;
        // connect to repository and confirm validity of credentials
        Login login = loginRepository.findById(userId).orElse(new Login());
        if(login.getPassword().equals(password)) {
            result=true;
        }
        return result;
    }

 

    public void registerUser(String userId, String password) {
        // connect to repository and save this new user into db
    	Login login= new Login();
    	login.setPassword(password);
    	login.setUserId(userId);
    	loginRepository.save(login);
        
    }

 

    
}
 



