package model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.entity.Boletin;
import model.service.BoletinService;

@Controller
public class BoletinController {

    @Autowired
    private BoletinService bs;

    /**
     * Maneja las solicitudes que se le hacen a la raíz del sitio
     * 
     * @return un objeto {@link ModelAndView} con la respuesta al cliente
     */
    @RequestMapping(path = "/CrearBoletin", method = RequestMethod.GET)
    public ModelAndView mostrarCrearBoletin() {
        return new ModelAndView("crearBoletin");
    }

    @RequestMapping(path = "/Boletin", method = RequestMethod.GET)
    public ModelAndView mostrarListarBoletines() {
        List<Boletin> boletines = bs.getBoletines();

        ModelAndView modelAndView = new ModelAndView("boletin", "boletines", boletines);
        return modelAndView;
    }

    @RequestMapping(path = "/CrearBoletin", method = RequestMethod.POST)
    public ModelAndView crearBoletin(Boletin boletin, RedirectAttributes redirectAttributes) {
        try {
            String detalle = boletin.getDetalle();
            boletin.setDetalle(detalle);
            bs.crearBoletines(boletin, detalle);

            redirectAttributes.addFlashAttribute("mensaje", "La información fue enviada correctamente.");
            
            // Redirigir al usuario a la página "crearBoletin" después de guardar el boletin
            return new ModelAndView("redirect:/CrearBoletin");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mensaje", "No se pudo enviar la información.");
            return new ModelAndView("redirect:/CrearBoletin");
        }
    }
}
