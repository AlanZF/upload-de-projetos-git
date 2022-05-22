package com.ibm.alan.ponto.controles;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.alan.ponto.dtos.FotoDTO;
import com.ibm.alan.ponto.entidades.Foto;
import com.ibm.alan.ponto.entidades.Usuario;
import com.ibm.alan.ponto.servicos.FotoServico;
import com.ibm.alan.ponto.servicos.UsuarioServico;

@RestController
@RequestMapping(value = "/api/foto")
public class FotoControle {
	
	@Autowired
	private FotoServico fotoServico;
	
	@Autowired
	private UsuarioServico usuarioServico;
	
	@RequestMapping(value = "/enviarFoto/{idUsuario}", method = RequestMethod.POST)
	public FotoDTO enviarFoto(@RequestParam("imagem") MultipartFile imagem, 
						   @PathVariable Integer idUsuario) throws IOException {
		
		Optional<Usuario> optUsuario = usuarioServico.findById(idUsuario);
		Usuario usuario = optUsuario.get();
		
		return fotoServico.salvarFoto(imagem, usuario);
	}
	
	@RequestMapping(value = "/retornarFoto/{idUsuario}", method = RequestMethod.GET)
	public Foto retornarFoto(@PathVariable Integer idUsuario) {
		
		Optional<Usuario> optUsuario = usuarioServico.findById(idUsuario);
		Usuario usuario = optUsuario.get();
		
		Foto foto = fotoServico.findByUsuario(usuario);
		return foto;
	}
	
	@RequestMapping(value = "/retornarFotoTodas/{idUsuario}", method = RequestMethod.GET)
	public List<Foto> retornarFotoTodas(@PathVariable Integer idUsuario) {
		
		Optional<Usuario> optUsuario = usuarioServico.findById(idUsuario);
		Usuario usuario = optUsuario.get();
		
		List<Foto> fotos = fotoServico.findAllByUsuario(usuario);
		return fotos;
	}
	
}
