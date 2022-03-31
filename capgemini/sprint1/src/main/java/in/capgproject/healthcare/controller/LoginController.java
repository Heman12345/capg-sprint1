package in.capgproject.appointment.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.capgproject.appointment.domain.User;
import in.capgproject.appointment.exception.ForBiddenException;
import in.capgproject.appointment.exception.UserNotFoundException;
import in.capgproject.appointment.serviceimpl.ILoginServiceImpl;


@CrossOrigin("http://localhost:4200")
@RestController
public class LoginController {

	@Autowired
	ILoginServiceImpl logServ;
	
	@PostMapping("/Login")
	public User loginUser(@RequestBody User user) throws UserNotFoundException {
		return logServ.loginWithData(user.getUsername(), user.getPassword());
	}
	
	@PostMapping("/Logout")
	public User logOut(@RequestBody User user) throws ForBiddenException, UserNotFoundException {
			return logServ.logoutPresentUser(user.getUsername());
	}

}
