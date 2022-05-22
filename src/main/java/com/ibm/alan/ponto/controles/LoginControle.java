package com.ibm.alan.ponto.controles;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.alan.ponto.dtos.Login;
import com.ibm.alan.ponto.dtos.Sessao;
import com.ibm.alan.ponto.dtos.UsuarioDTO;
import com.ibm.alan.ponto.dtos.UsuarioDTOResposta;
import com.ibm.alan.ponto.entidades.Usuario;
import com.ibm.alan.ponto.formatacoes.FormatacaoDatas;
import com.ibm.alan.ponto.seguranca.JWTCreator;
import com.ibm.alan.ponto.seguranca.JWTObjeto;
import com.ibm.alan.ponto.seguranca.JWTSegurancaConfig;
import com.ibm.alan.ponto.servicos.LoginServico;

@RestController
@RequestMapping(value = "/api/login")
public class LoginControle {

	@Autowired
	private LoginServico loginServico;

	@Autowired
	private PasswordEncoder encoder;

	private FormatacaoDatas formatacaoData;

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public ResponseEntity<UsuarioDTOResposta> cadastrar(@RequestBody UsuarioDTO usuarioDTO) throws Exception {

		formatacaoData = new FormatacaoDatas();

		Usuario usuario = usuarioDTO.transformaDTOParaEntidade();

		usuario.setCreated(formatacaoData.getDataHoraHojeFormatados());
		usuario.setEdited(formatacaoData.getDataHoraHojeFormatados());
		
		loginServico.cadastrar(usuario);
		
		UsuarioDTOResposta usuarioResp = new UsuarioDTOResposta();
		return ResponseEntity.ok().body(usuarioResp.transformaEntidadeParaDTO(usuario));
	}

	@RequestMapping(value = "/logar", method = RequestMethod.POST)
	public Sessao logar(@RequestBody Login login) {

		Optional<Usuario> optUsuario = loginServico.findByLogin(login.getLogin());

		Usuario usuario = optUsuario.get();

		if (optUsuario.isPresent()) {
			boolean senhaOk = encoder.matches(login.getSenha(), usuario.getSenha());

			if (!senhaOk) {
				throw new RuntimeException("Senha inválida para usuário " + login.getLogin());
			}

			Sessao sessao = new Sessao();
			sessao.setLogin(usuario.getLogin());
			sessao.setIdUsuario(usuario.getIdUsuario());

			JWTObjeto jwtObjeto = new JWTObjeto();

			jwtObjeto.setIssuedAt(new Date(System.currentTimeMillis()));
			jwtObjeto.setExpiration((new Date(System.currentTimeMillis() + JWTSegurancaConfig.EXPIRATION)));
			jwtObjeto.setRoles(usuario.getRoles());

			sessao.setToken(JWTCreator.create(JWTSegurancaConfig.PREFIX, JWTSegurancaConfig.KEY, jwtObjeto));
			return sessao;

		} else {
			throw new RuntimeException("Erro para fazer login.");
		}
	}

}
