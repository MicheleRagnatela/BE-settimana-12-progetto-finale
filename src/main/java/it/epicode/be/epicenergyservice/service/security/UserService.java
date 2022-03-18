package it.epicode.be.epicenergyservice.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.epicenergyservice.model.security.User;
import it.epicode.be.epicenergyservice.repository.security.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

}
