package main.java.model;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@SpringBootApplication(scanBasePackages = "model")
public class ModelController {
	@Autowired
	private ModelService modelService;

	//Fetches user by id
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") BigInteger id) {
		System.out.println("Get Model/users/id");
		User ob = modelService.getUserById(id);
		return new ResponseEntity<User>(ob, HttpStatus.OK);
	}
	//Fetches users
	@GetMapping("/users")
	public ResponseEntity<Users> getUsers() {
		System.out.println("Get Model/users");
		Users ob = modelService.getUsers();
		return new ResponseEntity<Users>(ob, HttpStatus.OK);
	}
	//Fetches user by country
	@GetMapping("/users/country/{country}")
	public ResponseEntity<Users> getUserByCountry(@PathVariable("country") String country) {
		System.out.println("Get Model/users/country/country");
		Users ob = modelService.getUsersByCountry(country);
		return new ResponseEntity<Users>(ob, HttpStatus.OK);
	}

	//Creates a new users
	@PostMapping("/users")
	public ResponseEntity<Void> addUsers(@RequestBody Users users, UriComponentsBuilder builder) {
		System.out.println("Post Model/users");
		for(User user: users.getUsers()){
			System.out.println(user.toString());

		}
		boolean flag = modelService.createUsers(users);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//Updates user
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable("id") BigInteger id, @RequestBody User user) {
		System.out.println("Put Model/users/id");
		User ob = modelService.updateUserById(user, id);
		return new ResponseEntity<User>(ob, HttpStatus.OK);
	}

	//delete user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Integer> updateChildrenById(@PathVariable("id") BigInteger id) {
		System.out.println("Delete Model/users/id");
		Integer ob = modelService.deleteById(id);
		return new ResponseEntity<Integer>(ob, HttpStatus.OK);
	}


} 