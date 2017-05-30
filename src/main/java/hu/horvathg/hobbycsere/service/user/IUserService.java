package hu.horvathg.hobbycsere.service.user;

import hu.horvathg.hobbycsere.model.user.User;

public interface IUserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
