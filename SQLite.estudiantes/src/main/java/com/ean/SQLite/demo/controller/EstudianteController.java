package com.ean.SQLite.demo.controller;

import com.ean.SQLite.demo.model.Estudiante;
import com.ean.SQLite.demo.service.EstudianteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@CrossOrigin("*")
@Controller
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;
    
    @GetMapping("/estudiantes")
	public String estudiantes() {
		return "estudiantes";
	}
    
    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    public String info(){
        return "La Aplicacion EAN-Estudiantes esta en ejecucion...";
    }

    @RequestMapping(value = "createestudiante", method = RequestMethod.POST)
    @ResponseBody
    public String createEstudiante(@RequestBody Estudiante estudiante){
        return estudianteService.createEstudiante(estudiante);
    }

    @RequestMapping(value = "/readestudiantes", method = RequestMethod.GET)
    @ResponseBody
    public List<Estudiante> readEstudiantes(){
        return estudianteService.readEstudiantes();
    }
    
    @GetMapping("/readbycurso/{curso}")
    @ResponseBody
    public List<Estudiante> readEstudiantesByCurso(@PathVariable("curso") String curso){
    	
        return estudianteService.readByCurso(curso);
    }
    
    @RequestMapping(value = "updateestudiante", method = RequestMethod.PUT)
    @ResponseBody
    public String updateEstudiante(@RequestBody Estudiante estudiante){
        return estudianteService.updateEstudiante(estudiante);
    }

    @DeleteMapping("/deleteestudiante/{id}")
    @ResponseBody
    public String deleteEstudiante(@PathVariable("id") Integer id){
        return estudianteService.deleteEstudiante(id);
    }
}
