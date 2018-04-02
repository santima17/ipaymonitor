package com.iwtg.ipaymonitor.datalayer.model;
// Generated 24/03/2018 02:37:22 AM by Hibernate Tools 4.3.1



/**
 * CanalHasUsuariosId generated by hbm2java
 */
public class CanalHasUsuariosId  implements java.io.Serializable {


     private int idUsuario;
     private String idCanal;

    public CanalHasUsuariosId() {
    }

    public CanalHasUsuariosId(int idUsuario, String idCanal) {
       this.idUsuario = idUsuario;
       this.idCanal = idCanal;
    }
   
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getIdCanal() {
        return this.idCanal;
    }
    
    public void setIdCanal(String idCanal) {
        this.idCanal = idCanal;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CanalHasUsuariosId) ) return false;
		 CanalHasUsuariosId castOther = ( CanalHasUsuariosId ) other; 
         
		 return (this.getIdUsuario()==castOther.getIdUsuario())
 && ( (this.getIdCanal()==castOther.getIdCanal()) || ( this.getIdCanal()!=null && castOther.getIdCanal()!=null && this.getIdCanal().equals(castOther.getIdCanal()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdUsuario();
         result = 37 * result + ( getIdCanal() == null ? 0 : this.getIdCanal().hashCode() );
         return result;
   }   


}

