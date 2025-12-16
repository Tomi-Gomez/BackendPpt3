package com.proyecto_final_ppt3.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteMedicoDTO {
	private String nombreMedico;
	private String tipoUsuario;
	private String especialidad;
	private int cantidadTurnos;

}
