package me.newsong.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import me.newsong.domain.User;
public interface UserRepository extends JpaRepository<User,Integer>{
	User findByUsername(String username);
}
