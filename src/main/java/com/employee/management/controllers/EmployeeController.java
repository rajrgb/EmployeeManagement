package com.employee.management.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.employee.management.models.Employee;
import com.employee.management.models.Leave;
import com.employee.management.repositories.EmployeeRepository;
import com.employee.management.services.RestTemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmployeeController {

	@Autowired
	private RestTemplateService restTemplate;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping("/employee")
	@ResponseBody
	public Employee registerEmployee(@ModelAttribute Employee emp) {
		emp.setStatus("available");
		Leave leave=new Leave(10,10);
		emp.setLeave(leave);
		emp.setJoinDate(new Date().toString());
		employeeRepository.save(emp);
		return emp;
	}
    @GetMapping("/employee")
    public String getAllEmployees(Model m) {
    	List<Employee> list=employeeRepository.findAll();
    	m.addAttribute("employees",list);
    	return "home";
    }
    @GetMapping("/employee/details")
    public String getEmployeeById(@RequestParam("id") int id, Model m) {
    	Optional<Employee> obj=employeeRepository.findById(id);
    	m.addAttribute("employee",obj.get());
    	return "employee";
    }
    @PutMapping("/employee")
    public String updateEmployeeById(@ModelAttribute Employee emp) {
    	Optional<Employee> obj=employeeRepository.findById(emp.getId());
    	Employee employee=obj.get();
    	employee.setName(emp.getName());
    	employee.setRole(emp.getRole());
    	employee.setStatus(emp.getStatus());
    	employeeRepository.save(employee);
    	return "home";
    }
    @DeleteMapping("/employee/{id}")
    public String deleteEmployeeById(@PathVariable("id") int id) {
    	
    	employeeRepository.deleteById(id);
    	return "home";
    }
    @GetMapping("/employee/leaveDetails")
    @ResponseBody
    public Leave getLeaveDetails(@RequestParam("empId") int empId) throws URISyntaxException{
    	Optional<Employee> obj=employeeRepository.findById(empId);
    	Employee emp=obj.get();
    	int leaveId=emp.getLeave().getId();
    	String url="http://localhost:8282/leave?id="+leaveId;
    	MultiValueMap<String, String> headers=new LinkedMultiValueMap<>();
    	headers.add(HttpHeaders.ACCEPT, "application/json");
    	headers.add(HttpHeaders.HOST, "localhost:8080");
    	URI uri=new URI(url);
        RequestEntity<Void> requestEntity=new RequestEntity<>(headers, HttpMethod.GET,uri);
        System.out.println("Reached here");

        
        ResponseEntity<String> responseEntity=restTemplate.exchange(requestEntity, String.class);
        String response=responseEntity.getBody();
        ObjectMapper objectMapper=new ObjectMapper();
        Leave leave=null;
        try {
        	 leave=objectMapper.readValue(response, Leave.class);
        	System.out.println(leave);
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        return leave;
    }
}
