package com.skytech.api_access.controller;


import com.skytech.api_access.dto.StudentDTO;
import com.skytech.api_access.service.APIAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api_access")
public class APIAccessController {

    @Autowired
    APIAccessService apiAccessService;
     @GetMapping("/all-students")
        public String getAllStudents() {
            return apiAccessService.getAllStudents();
     }


    @PostMapping("/save-student")
    public StudentDTO saveStudent(@RequestBody StudentDTO dto) {
         return apiAccessService.saveStudent(dto);
    }

    @PutMapping("/update-student/{id}")
    public  String updateStudent(@PathVariable Long id, @RequestBody Map<String, Object> studentData){
        return apiAccessService.updateStudent(id, studentData);
    }


    @PatchMapping("/update/{id}")
    public String updateStudentEmailId(@PathVariable Long id , @RequestParam String emailId){
        return apiAccessService.updateStudentEmailId(id, emailId);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable(name = "id") Long studentId){
        return apiAccessService.deleteStudentById(studentId);
    }


}
