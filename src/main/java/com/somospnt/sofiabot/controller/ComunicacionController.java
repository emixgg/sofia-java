package com.somospnt.sofiabot.controller;

import com.somospnt.sofiabot.service.MensajeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComunicacionController {

    private static final Logger log = LoggerFactory.getLogger(ComunicacionController.class);

    @Autowired
    private MensajeService mensajeService;

    @GetMapping("/saludar")
    public String devolverSaludo() {
        return mensajeService.saludar();
    }

    @PostMapping("/charlar")
    public String charlar(@RequestBody String mensaje) {
        return mensajeService.responder(mensaje);
    }

}
