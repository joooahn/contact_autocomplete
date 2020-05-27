package com.example.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.miniproject.mapper.CoworkMapper;
import com.example.miniproject.model.CoworkDepartment;
import com.example.miniproject.model.Department;
import com.example.miniproject.model.DepartmentWithCowork;

@Service
public class CoworkService {

	private final CoworkMapper coworkMapper;

	public CoworkService(CoworkMapper coworkMapper) {
		this.coworkMapper = coworkMapper;
	}

	public void register(CoworkDepartment coworkDepartment){
		coworkMapper.register(coworkDepartment);
	}

	public void deregister(CoworkDepartment coworkDepartment){
		coworkMapper.deregister(coworkDepartment);
	}

	public Department getDepartmentByUser(Long userId){
		return coworkMapper.getDepartmentByUser(userId);
	}

	public List<Department> getCoworkDepartments(Long userId){
		return coworkMapper.getCoworkDepartments(userId);
	}

	public List<DepartmentWithCowork> getDepartmentsWithCowork(Long userId){
		return coworkMapper.getDepartmentsWithCowork(userId);
	}

	public List<Department> getDepartments(){
		return coworkMapper.getDepartments();
	}

	public Boolean isCoworkWIthUser(Long userId, Long departmentId){
		return coworkMapper.isCoworkWIthUser(userId, departmentId);
	}

	public void updateDepartment(Long userId, Long departmentId){
		coworkMapper.updateDepartment(userId, departmentId);
	}
}
