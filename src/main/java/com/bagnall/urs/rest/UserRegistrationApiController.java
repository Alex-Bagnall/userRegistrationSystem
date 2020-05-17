package com.bagnall.urs.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bagnall.urs.model.User;
import com.bagnall.urs.repository.UserJpaRepository;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationApiController {

	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationApiController.class);

	@Autowired
	private UserJpaRepository userJpaRepository;

	public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userJpaRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable("id") final Long id) {
		Optional<User> user = userJpaRepository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " does not exist");
		}

	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable("id") final Long id, @RequestBody User updatedUser) {
		Optional<User> existingUser = userJpaRepository.findById(id);
		if (existingUser.isPresent()) {
			User user = existingUser.get();
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			user.setSport(updatedUser.getSport());
			user.setCountry(updatedUser.getCountry());
			userJpaRepository.saveAndFlush(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " does not exist");
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Optional<User>> deleteUser(@PathVariable("id") final Long id) {
		Optional<User> user = userJpaRepository.findById(id);
		if (user.isPresent()) {
			userJpaRepository.deleteById(id);
			return new ResponseEntity<Optional<User>>(HttpStatus.NO_CONTENT);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + id + " does not exist");
		}

	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@Valid @RequestBody final User user) {
		if (userJpaRepository.findByName(user.getName()) != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"User with name " + user.getName() + " already exists");
		} else {
			userJpaRepository.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}
}
