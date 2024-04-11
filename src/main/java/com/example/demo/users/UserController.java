package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/getUser")
	public String getUserInfo() {

		return "Hi welcome to Java";

	}

	@GetMapping("/getUserName/{name}")
	public String getUserName(@PathVariable(value = "name") String name) {
		return "Hi " + name;
	}

	@GetMapping("/getUserNameAddress/{name}/{address}")
	public String getUserNameAddress(@PathVariable(value = "name") String name,
			@PathVariable(value = "address") String address) {

		return "Hi I'm " + name + " from " + address + " welcome to Java.";
	}

	@PostMapping("/addUser")
	public GenericResponse addUser(@RequestBody UserEntity user) {
		return userService.addUser(user);
	}

	@GetMapping("/getUserById/{id}")
	public GenericResponse getUserById(@PathVariable(value = "id") long id) {
		return userService.getUserById(id);
	}
	@GetMapping("/getAllUsers")
	public GenericResponse getAllUsers() {
		return userService.getAllUsers();
	}
	@PutMapping("/updateUser")
	public GenericResponse updateUser(@RequestBody UserEntity user) {
		return userService.updateUser(user);
	}
}
