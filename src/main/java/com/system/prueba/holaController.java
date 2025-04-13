package com.system.prueba;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class holaController {

    @GetMapping("/hola")
    public String saludar() {
        return "Hola este es mi primer programa";
    }
}
