package com.ibm.alan.ponto.servicos;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.alan.ponto.dtos.FotoDTO;
import com.ibm.alan.ponto.entidades.Foto;
import com.ibm.alan.ponto.entidades.Usuario;
import com.ibm.alan.ponto.repositorios.FotoRepositorio;

@Service
public class FotoServico {
	
	@Autowired
	private FotoRepositorio fotoRepositorio;
	
	public FotoDTO salvarFoto(MultipartFile imagem, Usuario usuario) throws IOException {
		
		String nomeImagem = imagem.getOriginalFilename();
		
		Foto foto = new Foto(UUID.randomUUID().hashCode(), imagem.getBytes(), imagem.getContentType(), 
				nomeImagem, usuario);
		fotoRepositorio.save(foto);
		return mapToFotoDTO(foto);
	}
	
	public Foto findByUsuario(Usuario usuario) {
		return fotoRepositorio.findByUsuario(usuario);
	}
	
	public List<Foto> findAllByUsuario(Usuario usuario){
		return fotoRepositorio.findAllByUsuario(usuario);
	}
	
	
	public FotoDTO mapToFotoDTO(Foto foto) {
		return new FotoDTO(foto.getIdFoto(), foto.getTipo(), foto.getNomeImagem());
	}
	
}
