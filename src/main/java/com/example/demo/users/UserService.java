package com.example.demo.users;

public interface UserService {

	
	
	public GenericResponse addUser(UserEntity user);

	public GenericResponse getUserById(long id);

	public GenericResponse getAllUsers();

	public GenericResponse updateUser(UserEntity user);
}
