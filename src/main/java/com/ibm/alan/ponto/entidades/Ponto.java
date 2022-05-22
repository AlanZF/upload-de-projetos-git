package com.ibm.alan.ponto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ponto")
public class Ponto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idponto")
	private Integer idPonto;
	
	@Column(name = "data")
	@JsonFormat(pattern = "dd-MM-YY")
	private String data;
	
	@Column(name = "dia")
	@JsonFormat(pattern = "dd")
	private String dia;
	
	@Column(name = "horario")
	@JsonFormat(pattern = "HH:mm:ss")
	private String horario;
	
	@Column(name = "mes")
	@JsonFormat(pattern = "MM")
	private String mes;
	
	@Column(name = "horas_trabalhadas")
	@JsonFormat(pattern = "HH:mm:ss")
	private String horasTrabalhadas;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	private Usuario usuario;
	
}
