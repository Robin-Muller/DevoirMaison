package com.inti.formation.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inti.formation.Repository.UserRepository;
import com.inti.formation.entities.User;

@Service
public class UserMetier {

	@Autowired
	UserRepository userrepo;
	
	public List<User> getAllUsers(){
		
		return this.userrepo.findAll();
	}
	
	public User addUser(User user) {
		return this.userrepo.save(user);
		
	}
	
	public int getUserNbrHalf(List<User>users) {
		System.out.println("***********" + users.size() / 2);
		return users.size() / 2;	
		
	}
	public User getUserById(long userId) {
		
		return this.userrepo.findById(userId);
	}
	
	public void updateUser(User utilisateur) {
		
		this.userrepo.save(utilisateur);
	}
	 public void deleteUser(User utilisateur) {
	        this.userrepo.delete(utilisateur);
	    }
}
