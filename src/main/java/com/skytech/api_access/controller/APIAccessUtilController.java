package com.skytech.api_access.controller;


import com.skytech.api_access.dto.StudentDTO;
import com.skytech.api_access.service.APIAccessServiceThroughUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api_access_util")
public class APIAccessUtilController {
    @Autowired
    private APIAccessServiceThroughUtil apiAccessService;

    @GetMapping
    public String getStudents() {
        return apiAccessService.getStudents();
    }


    @PostMapping
    public StudentDTO saveStudent(@RequestBody  StudentDTO request){
        return apiAccessService.saveStudent(request);
    }


}
