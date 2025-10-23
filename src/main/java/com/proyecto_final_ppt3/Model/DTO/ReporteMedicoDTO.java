package com.proyecto_final_ppt3.Model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteMedicoDTO {
	private String nombreMedico;
	private String especialidad;
	private int cantidadTurnos;

	public ReporteMedicoDTO(String nombreMedico,String especialidad,int cantidadTurnos){
		this.nombreMedico = nombreMedico;
		this.especialidad = especialidad;
		this.cantidadTurnos = cantidadTurnos;
	}
}
