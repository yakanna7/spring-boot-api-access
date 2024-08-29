package com.skytech.api_access.service;


import com.skytech.api_access.dto.StudentDTO;
import com.skytech.api_access.util.WebClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class APIAccessService {


    WebClient webClient;

     public  String getAllStudents(){
          return  webClient.get().uri("/students").retrieve().bodyToMono(String.class).block();
      }

      public StudentDTO saveStudent(StudentDTO dto){
          return webClient.post().uri("/students/save").body(dto, StudentDTO.class).retrieve().bodyToMono(StudentDTO.class).block();
      }


    public String updateStudent(Long studentId, Map<String, Object> studentData){
        return webClient.put().uri("/students/update/{id}", studentId).header("Content-Type", "application/json").bodyValue(studentData).retrieve().bodyToMono(String.class).block();
    }

    public String updateStudentEmailId(Long id, String emailId){
         return webClient.patch().uri(uriBuilder -> uriBuilder
                 .path("/students/update/{id}")
                 .queryParam("emailId", emailId)
                 .build(id)).header("Content-Type", "application/json").bodyValue(Void.class).retrieve().bodyToMono(String.class).block();
    }

    public String deleteStudentById(Long studentId){
        return webClient.delete().uri("/students/delete/{studentId}", studentId).retrieve().bodyToMono(String.class).block();
    }
}
