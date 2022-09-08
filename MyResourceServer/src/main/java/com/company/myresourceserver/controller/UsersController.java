package com.company.myresourceserver.controller;


import com.company.myresourceserver.dto.UserDto;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment environment;

    public UsersController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on port: " + environment.getProperty("local.server.port");
    }

    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    //@Secured("ROLE_developer")
    @DeleteMapping( "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok("Deleted user with id " + id + " and JWT subject " + jwt.getSubject());
    }


    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping( "/{id}")
    public UserDto getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new UserDto("Parvin", "Valizade","07901f9b-8050-49b4-9fd1-f54908fd6dae");
    }
}
