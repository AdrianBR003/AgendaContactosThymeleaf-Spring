package gm.contactos.controlador;

import gm.contactos.modelo.Contacto;
import gm.contactos.servicio.ContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    ContactoServicio contactoServicio; // Comunicacion con la BD

    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Contacto> contactos = contactoServicio.listarContactos();
        contactos.forEach((contacto -> logger.info(contacto.toString())));
        modelo.put("contactos",contactos);
        return "index"; // index.html
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(){
        logger.info("Ha entrado en agregar - Contacto Controlador");
        return "agregar"; // agregar.html
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForma") Contacto contacto){
        logger.info("Contacto a agregar: " + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable(value="id") int idContacto, ModelMap modelo){
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
        logger.info("Contacto a Editar (mostrar): " + contacto );
        modelo.put("contacto", contacto);
        return "editar"; // editar.html
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("contacto") Contacto contacto){
        logger.info("Contacto a guardar (editar) : " + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value ="id") int idContacto){
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
        logger.info("Contacto a eliminar: " + contacto);
        contactoServicio.eliminarContacto(contacto);
        return "redirect:/"; // Redirigir a la página de contactos
    }
}
