package me.newsong.service;

import me.newsong.domain.User;
import me.newsong.enums.UsernameExistedException;

public interface UserService {
	User findByUsername(String username);
	void addUser(User user) throws UsernameExistedException;
}
