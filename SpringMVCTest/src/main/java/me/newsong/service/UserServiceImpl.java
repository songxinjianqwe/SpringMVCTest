package me.newsong.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.newsong.dao.UserRepository;
import me.newsong.domain.User;
import me.newsong.enums.UsernameExistedException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userDao;

	@Transactional
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Transactional
	public void addUser(User user) throws UsernameExistedException {
		if (findByUsername(user.getUsername()) != null) {
			throw new UsernameExistedException();
		}
		userDao.save(user);
	}
}
