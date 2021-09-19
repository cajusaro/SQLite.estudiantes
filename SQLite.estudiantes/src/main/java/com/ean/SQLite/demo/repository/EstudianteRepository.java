package com.ean.SQLite.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ean.SQLite.demo.model.Estudiante;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    public boolean existsByEmail(String email);

    public List<Estudiante> findByEmail(String email);
    
    public List<Estudiante> findByCurso(String curso);

    @Query("select max(s.id) from Estudiante s")
    public Integer findMaxId();
}
