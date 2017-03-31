package com.somospnt.sofiabot.service;

import com.somospnt.sofiabot.SofiaApplicationTest;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MensajeServiceTest extends SofiaApplicationTest {

    @Autowired
    private MensajeService mensajeService;

    @Test
    public void saludar_devuelveSaludo() {
        String respuesta = mensajeService.saludar();
        assertNotNull(respuesta);
        assertEquals("I have no answer for that.", respuesta);
    }

    @Test
    public void responder_conMensajeEnviado_responde() {
        String mensajeAEnviar = "hi";
        String respuesta = mensajeService.responder(mensajeAEnviar);
        assertNotNull(respuesta);
    }

}
