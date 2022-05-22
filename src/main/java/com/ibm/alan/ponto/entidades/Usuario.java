package com.ibm.alan.ponto.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints= {@UniqueConstraint(columnNames = {"login","telefone"})})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idusuario")
	private Integer idUsuario;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Column(name = "login", nullable = false)
	private String login;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "created", nullable = true)
	@JsonFormat(pattern = "dd-MM-YY HH:mm:ss")
	private String created;
	
	@Column(name = "edited", nullable = true)
	@JsonFormat(pattern = "dd-MM-YY HH:mm:ss")
	private String edited;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "usuario")
	private List<Ponto> ponto;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "usuario")
	private Foto foto;
	
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "idusuario"))
    @Column(name = "idroles")
    private List<String> roles = new ArrayList<>();
	
	public Usuario(String nome, String telefone, String login, String senha) {
		this.nome = nome;
		this.telefone = telefone;
		this.login = login;
		this.senha = senha;
	}
	
}
