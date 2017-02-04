package me.newsong.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.newsong.dao.UserRepository;
import me.newsong.domain.User;
import me.newsong.enums.UsernameExistedException;
import me.newsong.service.iface.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userDao;

	@Transactional
	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Transactional
	@Override
	public void addUser(User user) throws UsernameExistedException {
		if (findByUsername(user.getUsername()) != null) {
			throw new UsernameExistedException();
		}
		userDao.save(user);
	}

	@Transactional
	@Override
	public User findById(Integer id) {
		return userDao.findOne(id);
	}

	@Transactional
	@Override
	public void update(User user) {
		userDao.saveAndFlush(user);
	}
}
