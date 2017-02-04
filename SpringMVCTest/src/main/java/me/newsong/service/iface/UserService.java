package me.newsong.service.iface;

import me.newsong.domain.User;
import me.newsong.enums.UsernameExistedException;

public interface UserService {
	User findByUsername(String username);
	void addUser(User user) throws UsernameExistedException;
	User findById(Integer id);
	void update(User user);
}
