package model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.entity.Reporte;
import model.service.ReporteService;

@Controller
public class ReporteController {

    @Autowired
    private ReporteService rs;

    /**
     * Maneja las solicitudes que se le hacen a la raíz del sitio
     * 
     * @return un objeto {@link ModelAndView} con la respuesta al cliente
     */
    @RequestMapping(path = "/CrearReporte", method = RequestMethod.GET)
    public ModelAndView mostrarCrearReporte() {
        return new ModelAndView("crearReporte");
    }

    @RequestMapping(path = "/ListarReportes", method = RequestMethod.GET)
    public ModelAndView mostrarListarReportes(
            @RequestParam(name = "orderBy", required = false) String orderBy,
            @RequestParam(name = "orderDir", defaultValue = "asc") String orderDir) {
        
        boolean isAsc = "asc".equalsIgnoreCase(orderDir);
        
        List<Reporte> reportes = rs.getReportesOrdenados(orderBy, isAsc);

        ModelAndView modelAndView = new ModelAndView("listarReportes", "reportes", reportes);
        modelAndView.addObject("orderBy", orderBy); // Pasar el orderBy actual a la vista
        modelAndView.addObject("isAsc", isAsc);     // Pasar el flag de orden ascendente a la vista

        return modelAndView;
    }

    @RequestMapping(path = "/CrearReporte", method = RequestMethod.POST)
    public ModelAndView crearReporte(Reporte reporte, RedirectAttributes redirectAttributes) {
        try {
            String detalle = reporte.getDetalle();
            reporte.setDetalle(detalle);
            rs.crearReportes(reporte, detalle);

            redirectAttributes.addFlashAttribute("mensaje", "La información fue enviada correctamente.");
            
            // Redirigir al usuario a la página "crearReporte" después de guardar el reporte
            return new ModelAndView("redirect:/CrearReporte");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mensaje", "No se pudo enviar la información.");
            return new ModelAndView("redirect:/CrearReporte");
        }
    }
}
