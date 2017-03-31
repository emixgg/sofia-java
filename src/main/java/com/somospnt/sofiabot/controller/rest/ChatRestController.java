
package com.somospnt.sofiabot.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRestController {
    
    @GetMapping("/saludar")
    public String devolverSaludo(){
        return "Hola";
    }
    
    
}
