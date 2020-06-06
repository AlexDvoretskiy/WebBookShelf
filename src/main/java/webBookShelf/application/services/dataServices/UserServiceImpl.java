package webBookShelf.application.services.dataServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import webBookShelf.application.persistence.entities.SystemUser;
import webBookShelf.application.persistence.entities.data.Role;
import webBookShelf.application.persistence.entities.data.User;
import webBookShelf.application.persistence.repositories.RoleRepository;
import webBookShelf.application.persistence.repositories.UserRepository;
import webBookShelf.application.services.AuthService;
import webBookShelf.application.services.dataServices.interfaces.UserService;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
	private static final String DEFAULT_ROLE = "ROLE_USER";

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private AuthService authService;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		return userRepository.findOneByName(userName);
	}

	@Override
	@Transactional
	public void save(SystemUser systemUser) {
		User user = new User();
		user.setName(systemUser.getUserName());
		user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
		user.setEmail(systemUser.getEmail());
		user.setRoles(Collections.singletonList(roleRepository.findOneByName(DEFAULT_ROLE)));

		userRepository.save(user);
	}

	@Transactional
	public User getCurrentUser() {
		return userRepository.findOneByName(authService.getCurrentUsername());
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findOneByName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Autowired
	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
}
