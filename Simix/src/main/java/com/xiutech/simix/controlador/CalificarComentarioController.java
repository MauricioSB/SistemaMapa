/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.*;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author MauricioSB
 */
@ManagedBean
public class CalificarComentarioController {
    private int id_marcador;
    private String correo_comentarista;
    private int calificacion;
    private String correo_calificador;
    //private ComentarioId id_comentario;
    public CalificarComentarioController() {
        
    }
    
    public CalificarComentarioController(int id_marcador, String correo_calificador, int calificacion, String correo_comentarista){
        this.id_marcador = id_marcador;
        this.correo_calificador = correo_calificador;
        this.calificacion = calificacion;
        this.correo_comentarista = correo_comentarista;
    }
    
    public int getIdMarcador() {
        return this.id_marcador;
    }
    
    public String getCorreoCalificador() {
        return this.correo_calificador;
    }
    
    public String getCorreoComentarista() {
        return this.correo_comentarista;
    }
    
    public int getCalificacion() {
        return this.calificacion;
    }
    
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    
    public void calificarComentario() {
        CalificacionDAO califao = new CalificacionDAO();
        Calificacion calif = new Calificacion();
        ComentarioDAO comdao = new ComentarioDAO();
        ComentarioId id = new ComentarioId(this.id_marcador, this.correo_comentarista);
        Comentario viejo = comdao.find(id);
        calif.getComentario();        
        calif.setCalificacion(calificacion++);
        califao.update(calif);
    }
}
