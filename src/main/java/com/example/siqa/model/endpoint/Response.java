package com.example.siqa.model.endpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Response {
    HttpStatus status;
    String message;
    Object body;


    public static ResponseEntity<Response> getOk(String message , Object body) {
        return ResponseEntity.ok(new Response(HttpStatus.OK , message , body));
    }

    public static  ResponseEntity<Response> getBadRequest(String message , Object body) {
        return ResponseEntity.ok(new Response(HttpStatus.BAD_REQUEST , message , body));
    }
}
