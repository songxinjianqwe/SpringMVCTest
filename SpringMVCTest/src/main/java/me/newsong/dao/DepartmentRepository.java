package me.newsong.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import me.newsong.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer>{
}
