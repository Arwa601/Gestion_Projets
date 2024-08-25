package com.Cp.Stage.Controllers;

//Le terme "Rest" fait référence à RESTful (Representational State Transfer),
// un style d'architecture utilisé pour créer des services web qui communiquent via HTTP.


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Le terme "Rest" fait référence à RESTful
// un style d'architecture utilisé pour créer des services web qui communiquent via HTTP
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AuthController {

}
