package in.capgproject.appointment.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.capgproject.appointment.domain.User;
import in.capgproject.appointment.exception.ForBiddenException;
import in.capgproject.appointment.exception.UserCreationError;
import in.capgproject.appointment.exception.UserNotFoundException;
import in.capgproject.appointment.service.IUserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;

	@Autowired
	LoginController logCon;
	
	@PostMapping("/validateUser")
	public User validateUser(@RequestBody User user) throws Exception {
		return userService.validateUser(user.getUsername(), user.getPassword());
	}
	
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user) throws UserCreationError, ForBiddenException {
		return userService.addUser(user);
	}
	
	@DeleteMapping("/removeuser")
	public User removeUser(@RequestBody User user) throws ForBiddenException, UserNotFoundException {
		return userService.removeUser(user);
	}

	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User user) throws ForBiddenException, UserNotFoundException {
		return userService.updateUser(user);

	}
	
	@GetMapping("/getAll")
	public Iterable<User> getAll(){
		return userService.getAll();
	}

}

