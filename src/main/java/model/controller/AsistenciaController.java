package model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.entity.Asistencia;
import model.service.AsistenciaService;

@Controller
public class AsistenciaController {

    @Autowired
    private AsistenciaService as;

    /**
     * Maneja las solicitudes que se le hacen a la raíz del sitio
     * 
     * @return un objeto {@link ModelAndView} con la respuesta al cliente
     */
    @RequestMapping(path = "/CrearAsistencia", method = RequestMethod.GET)
    public ModelAndView mostrarCrearAsistencia() {
        return new ModelAndView("crearAsistencia");
    }

    @RequestMapping(path = "/ListarAsistencias", method = RequestMethod.GET)
    public ModelAndView mostrarListarAsistencias() {
        // Obtener la lista de asistencias desde el servicio AsistenciaService
        List<Asistencia> asistencias = as.getAsistencias();

        ModelAndView modelAndView = new ModelAndView("listarAsistencias", "asistencias", asistencias);
        return modelAndView;
    }

    @RequestMapping(path = "/CrearAsistencia", method = RequestMethod.POST)
    public ModelAndView crearAsistencia(Asistencia asistencia, RedirectAttributes redirectAttributes) {
        try {
            String detalle = asistencia.getDetalle();
            asistencia.setDetalle(detalle);
            as.crearAsistencias(asistencia, detalle);

            redirectAttributes.addFlashAttribute("mensaje", "La información fue enviada correctamente.");
            
            // Redirigir al usuario a la página "crearAsistencia" después de guardar la asistencia
            return new ModelAndView("redirect:/CrearAsistencia");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mensaje", "No se pudo enviar la información.");
            return new ModelAndView("redirect:/CrearAsistencia");
        }
    }
}
