package com.ibm.alan.ponto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "foto")
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idfoto")
	private Integer idFoto;
	
	@Column(name = "imagem", nullable = false)
	@Lob
	private byte[] imagem;
	
	@Column(name = "tipo", nullable = true)
	private String tipo;
	
	@Column(name = "nome_imagem", nullable = true)
	private String nomeImagem;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	private Usuario usuario;

}
