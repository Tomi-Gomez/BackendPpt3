package com.proyecto_final_ppt3.Model.DTO;

import lombok.Getter;
import lombok.Setter;


public class ReporteMedicoDTO {
	private String nombreMedico;
	private String especialidad;
	private int cantidadTurnos;

	public ReporteMedicoDTO(String nombreMedico,String especialidad,int cantidadTurnos){
		this.nombreMedico = nombreMedico;
		this.especialidad = especialidad;
		this.cantidadTurnos = cantidadTurnos;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public int getCantidadTurnos() {
		return cantidadTurnos;
	}

	public void setCantidadTurnos(int cantidadTurnos) {
		this.cantidadTurnos = cantidadTurnos;
	}
}
