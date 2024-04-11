package com.example.demo.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	public GenericResponse addUser(UserEntity user) {
		GenericResponse genericResponse = new GenericResponse();

		try {
			List<UserEntity> userDetails = userRepository.findAll();
			userDetails.forEach(response -> {
				if (response.getName().equalsIgnoreCase(user.getName())) {
					genericResponse.setStatusCode(1);
					genericResponse.setErrorMessage("User Already Exists");
				} else {
					userRepository.save(user);
					genericResponse.setStatusCode(0);
					genericResponse.setMessage("Added succesfully");
					genericResponse.setContents(user);
				}

			});

		} catch (Exception e) {
			String errorMessage = "Unknown error";
		}

		return genericResponse;

	}

	public GenericResponse getUserById(long id) {
		GenericResponse genericResponse = new GenericResponse();
		try {
			Optional<UserEntity> userDetails = userRepository.findById(id);
			if (userDetails.isPresent()) {
				UserEntity user = userDetails.get();
				genericResponse.setStatusCode(0);
				genericResponse.setMessage("Record found");
				genericResponse.setContents(user);

			} else {
				genericResponse.setStatusCode(1);
				genericResponse.setErrorMessage("User not found");
			}

		} catch (Exception e) {
			String errorMessage = ("Unknown error");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse getAllUsers() {
		GenericResponse genericResponse = new GenericResponse();
		List<UserEntity> userDetails = userRepository.findAll();
		genericResponse.setStatusCode(0);
		genericResponse.setMessage("Record found");
		genericResponse.setContents(userDetails);

		return genericResponse;
	}

	@Override
	public GenericResponse updateUser(UserEntity user) {
		GenericResponse genericResponse = new GenericResponse();
		try {
//			
			Optional<UserEntity> existUserOptional = userRepository.findById(user.getId());
			if (existUserOptional.isPresent()) {
				UserEntity existUser = existUserOptional.get();
				existUser.setName(user.getName());
				existUser.setAge(user.getAge());
				existUser.setGender(user.getGender());
				existUser.setAddress(user.getAddress());
				existUser.setMobileNo(user.getMobileNo());

				userRepository.save(existUser);

				genericResponse.setStatusCode(0);
				genericResponse.setContents(existUser);
				genericResponse.setMessage("User details updated successfully");
			} else {
				genericResponse.setStatusCode(1);
				genericResponse.setErrorMessage("user not found");
			}

		} catch (Exception e) {
			String errorMessage = ("Unknown error");
		}
		return genericResponse;
	}

}
