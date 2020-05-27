package com.example.miniproject.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.miniproject.model.CoworkDepartment;
import com.example.miniproject.model.Department;
import com.example.miniproject.model.DepartmentWithCowork;

@Mapper
@Repository
public interface CoworkMapper {
	void updateDepartment(@Param("userId") Long userId, @Param("departmentId") Long departmentId);
	void register(CoworkDepartment coworkDepartment);
	void deregister(CoworkDepartment coworkDepartment);

	List<Department> getCoworkDepartments(@Param("userId") Long userId);
	List<DepartmentWithCowork> getDepartmentsWithCowork(@Param("userId") Long userId);
	List<Department> getDepartments();
	Department getDepartmentByUser(@Param("userId") Long userId);
	Boolean isCoworkWIthUser(@Param("userId") Long userId, @Param("departmentId") Long departmentId);
}
