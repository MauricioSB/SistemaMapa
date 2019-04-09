/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import javax.faces.bean.ManagedBean;
import com.xiutech.simix.modelo.Comentario;
import com.xiutech.simix.modelo.ComentarioDAO;
import com.xiutech.simix.modelo.ComentarioId;
import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.ComentaristaDAO;
import com.xiutech.simix.modelo.Marcador;
import com.xiutech.simix.modelo.MarcadorDAO;
/**
 *
 * @author MauricioSB
 */
@ManagedBean
public class ABMComentarioPropioController {
    
   private int id_marcador;
   private String correo_comentarista;
   private String texto;
   //private ComentarioId id_comentario;
   
   public int getIdMarcador() {
       return this.id_marcador;
   }
   
   public void setIdMarcador(int id_marcador) {
        this.id_marcador = id_marcador;
   }
   
   public String getCorreoComentarista() {
      return this.correo_comentarista;
   }
   
   public void setCorreoComentarista(String correo_comentarista) {
       this.correo_comentarista = correo_comentarista;
   }
   
   public String getTexto() {
       return this.texto;
   }
   
   public void setTexto(String texto) {
       this.texto = texto;
   }
   
    public String agregaComentario(){
        String error = "Ocurrio un error";
        String mensaje = "Se agrego exitosamente";
        
        try{
            ComentaristaDAO udb = new ComentaristaDAO ();
            Comentarista cmn = udb.find("mu.andrea@ciencias.unam.mx");

            MarcadorDAO udb_prima = new MarcadorDAO();
            Marcador ma = udb_prima.find(1);

            ComentarioId id = new ComentarioId();
            id.setIdMarcador(ma.getIdMarcador());
            id.setCorreoComentarista(cmn.getCorreo());

            //Se crea el objeto comentario para agregarlo a la base de datos
            Comentario u = new Comentario();
            u.setTexto(texto);
            u.setComentarista(cmn);
            u.setMarcador(ma);

            ComentarioDAO uu = new ComentarioDAO ();
             //Para agregar a la base de datos.
        uu.save(u);
        return "MensajeExitoIH?faces-redirect=true&mensaje=" + mensaje;
        
        }catch(Exception e){
            
         return "MensajeExitoIH?faces-redirect=true&mensaje="  + mensaje ;           
        }   
    }
    
    public void eliminaComentario(){}
    
    public String editaComentario(){
        String error = "Ocurrio un error";
        String mensaje = "Se modifico exitosamente";        
        try{
        ComentarioId id = new ComentarioId(this.id_marcador, this.correo_comentarista);
        ComentarioDAO comdao = new ComentarioDAO();  
        String comModificado = this.texto;
        Comentario viejo = comdao.find(id);
        viejo.setTexto(comModificado);
        comdao.update(viejo);
        return "MensejaExitoso?faces-redirect=true&mensaje=" + mensaje;
        } catch (Exception e) {
            return "MensejaExitoso?faces-redirect=true&mensaje=" + mensaje;
        }
    }
}
