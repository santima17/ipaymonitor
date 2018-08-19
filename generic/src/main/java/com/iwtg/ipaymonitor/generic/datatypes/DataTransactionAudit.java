package com.iwtg.ipaymonitor.generic.datatypes;

import java.util.Date;

public class DataTransactionAudit {
	
	private Integer id;
    private String nroTransaccion;
    private String accion;
    private Date fecha;
    private String estado;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNroTransaccion() {
		return nroTransaccion;
	}
	public void setNroTransaccion(String nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    

}
