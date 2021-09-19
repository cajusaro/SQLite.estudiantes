package com.ean.SQLite.demo.service;

import com.ean.SQLite.demo.model.Estudiante;
import com.ean.SQLite.demo.repository.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    /**
     * Este metodo permite crear estudiantes, recibe como parametro un objeto estudiente
     * @param estudiante
     * @return
     */
    @Transactional
    public String createEstudiante(Estudiante estudiante){
        try {
            if (!estudianteRepository.existsByEmail(estudiante.getEmail())){
                //tudiante.setId(null == estudianteRepository.findMaxId()? 0 : estudianteRepository.findMaxId() + 1);
                estudianteRepository.save(estudiante);
                return "Registro Estudiante creado correctamente.";
            }else {
                return "Registro Estudiante ya existe en la base de datos.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * Permite leer todos los estudiantes de la tabla estudiante
     * @return
     */
    public List<Estudiante> readEstudiantes(){
        return estudianteRepository.findAll();
    }
    
    /**
     * Permite leer los estudiantes de un curso determinado, recibe como parametros el nombre del curso
     * @param lcurso
     * @return
     */
    public List<Estudiante> readByCurso(String lcurso){
    	
    	    List<Estudiante> estudiantesRes = new ArrayList<>();
    		List<Estudiante> estudiantesAll = estudianteRepository.findAll();

    		for (int i=0; i< estudiantesAll.size(); i++) {
    			if (estudiantesAll.get(i).getCurso().equalsIgnoreCase(lcurso)) {
    				estudiantesRes.add(estudiantesAll.get(i));
    			}
    		}
    		return estudiantesRes;
    }

    /**
     * Permite actualizar la infrmacion de un estudiante, el id no es editable, recibe como parametro el objeto estudiante
     * @param estudiante
     * @return
     */
    @Transactional
    public String updateEstudiante(Estudiante estudiante){
        if (estudianteRepository.existsById(estudiante.getId())){
            try {
                Estudiante estudianteToBeUpdate = estudianteRepository.findById(estudiante.getId()).get();
                estudianteToBeUpdate.setName(estudiante.getName());
                estudianteToBeUpdate.setEmail(estudiante.getEmail());
                estudianteToBeUpdate.setCurso(estudiante.getCurso());
                estudianteRepository.save(estudianteToBeUpdate);

                return "Registro Estudiante Modificado.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Estudiante no existe en la base de datos.";
        }
    }

    /**
     * Permite eliminar un registro de un estudiante
     * @param id
     * @return
     */
    @Transactional
    public String deleteEstudiante(Integer id){
        if (estudianteRepository.existsById(id)){
            try {
                estudianteRepository.deleteById(id);
                return "Registro Estudiante Eliminado Correctamente.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Estudiante no existe.....";
        }
    }
}
