package com.example.miniproject.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniproject.model.CoworkDepartment;
import com.example.miniproject.model.Department;
import com.example.miniproject.model.DepartmentWithCowork;
import com.example.miniproject.model.request.SetVipRequest;
import com.example.miniproject.service.CoworkService;
import com.example.miniproject.service.PrivateDataService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
@CrossOrigin("*")
public class CoworkController {

	private final CoworkService coworkService;

	public CoworkController(CoworkService coworkService) {
		this.coworkService = coworkService;
	}

	@PostMapping("contacts/{userId}/coworkDepartments/{departmentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void register(@PathVariable Long userId, @PathVariable Long departmentId) {
		CoworkDepartment coworkDepartment = new CoworkDepartment(userId, departmentId);
		coworkService.register(coworkDepartment);
	}

	@DeleteMapping("contacts/{userId}/coworkDepartments/{departmentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deregister(@PathVariable Long userId, @PathVariable Long departmentId) {
		CoworkDepartment coworkDepartment = new CoworkDepartment(userId, departmentId);
		coworkService.deregister(coworkDepartment);
	}

	@GetMapping("contacts/{userId}/coworkDepartments")
	public List<Department> getCoworkDepartments(@PathVariable Long userId){
		return coworkService.getCoworkDepartments(userId);
	}

	@GetMapping("contacts/{userId}/departmentsWithCowork")
	public List<DepartmentWithCowork> getDepartmentsWithCowork(@PathVariable Long userId){
		return coworkService.getDepartmentsWithCowork(userId);
	}

	@GetMapping("contacts/{userId}/departments")
	public Department getDepartmentByUser(@PathVariable Long userId){
		return coworkService.getDepartmentByUser(userId);
	}

	@GetMapping("departments")
	public List<Department> getDepartments(){
		return coworkService.getDepartments();
	}

	@GetMapping("contacts/{userId}/coworkDepartments/{departmentId}")
	public Boolean isCoworkWIthUser(@PathVariable Long userId, @PathVariable Long departmentId){
		return coworkService.isCoworkWIthUser(userId, departmentId);
	}

	@PutMapping("contacts/{userId}/departments/{departmentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDepartment(@PathVariable Long userId, @PathVariable Long departmentId){
		coworkService.updateDepartment(userId, departmentId);
	}
}
