package it.epicode.be.epicenergyservice.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.epicenergyservice.model.security.Role;
import it.epicode.be.epicenergyservice.model.security.Roles;



public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(Roles role);
}
