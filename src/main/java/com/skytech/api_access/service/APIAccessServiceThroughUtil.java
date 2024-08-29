package com.skytech.api_access.service;

import com.skytech.api_access.dto.StudentDTO;
import com.skytech.api_access.util.WebClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class APIAccessServiceThroughUtil {

    @Value("${api.student.service.url}")
    private String baseUrl;

    public String getStudents(){
        return WebClientUtil.get(baseUrl+"/students", (Object) null,  String.class);
    }

    public StudentDTO saveStudent(StudentDTO request){
        return WebClientUtil.post(baseUrl+"/students/save", request, StudentDTO.class);
    }
}

