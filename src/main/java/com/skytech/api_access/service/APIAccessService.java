package com.skytech.api_access.service;


import com.skytech.api_access.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class APIAccessService {
    @Autowired
    WebClient webClient;
     public  String getAllStudents(){
          return  webClient.get().uri("/students").retrieve().bodyToMono(String.class).block();
      }

      public StudentDTO saveStudent(StudentDTO dto){
          return webClient.post().uri("/students/save").body(Mono.just(dto), StudentDTO.class).retrieve().bodyToMono(StudentDTO.class).block();
      }


    public String updateStudent(Long studentId, Map<String, Object> studentData){
        return webClient.put().uri("/students/update/{id}", studentId).header("Content-Type", "application/json").bodyValue(studentData).retrieve().bodyToMono(String.class).block();
    }

    public String updateStudentEmailId(Long id, String emailId){
         return webClient.patch().uri(uriBuilder -> uriBuilder
                 .path("/students/update/{id}")
                 .queryParam("emailId", emailId)
                 .build(id)).header("Content-Type", "application/json").retrieve().bodyToMono(String.class).block();
    }
}
