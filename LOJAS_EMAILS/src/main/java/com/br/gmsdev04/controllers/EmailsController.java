package com.br.gmsdev04.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.gmsdev04.commons.ApiRoundTrip;
import com.br.gmsdev04.dto.EmailDto;
import com.br.gmsdev04.entities.Email;
import com.br.gmsdev04.entities.Loja;
import com.br.gmsdev04.repository.LojasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController()
public class EmailsController {

	private Logger LOGGER = Logger.getLogger(EmailsController.class);

	@Autowired
	private LojasRepository lojas;

	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/apis/v1/lojas/{id_loja}/emails")
	public ResponseEntity<ApiRoundTrip<EmailDto>> postEmail(
			@PathVariable("id_loja") UUID idLoja,
			@Valid() @RequestBody() ApiRoundTrip<EmailDto> request) {

		try {
			Optional<Loja> loja = lojas.findById(idLoja);

			if(loja.isPresent()) {
				Set<Email> emailsDoBanco = loja.get().getEmails();
				Email novoEmail = mapper.convertValue(request.getData(),Email.class);

				if(emailsDoBanco != null) {

					if(emailsDoBanco.stream().noneMatch(x->x.getEndereco().equals(novoEmail.getEndereco()))) {
						emailsDoBanco.add(novoEmail);
					}else {
						return ResponseEntity.badRequest().body(new ApiRoundTrip<>("Já existe um e-mail cadastrado com este endereço."));
					}
				}else {
					Set<Email> emails = new HashSet<>();
					emails.add(novoEmail);
					loja.get().setEmails(emails);
				}
				lojas.save(loja.get());
				return ResponseEntity.created(new URI("/apis/v1/lojas/"+idLoja+"/emails/"+novoEmail.getId().toString()))
						.body(new ApiRoundTrip<>("E-mail cadastrado com sucesso",mapper.convertValue(novoEmail,EmailDto.class)));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrada"));
			}
		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}

	@GetMapping("/apis/v1/lojas/{id_loja}/emails")
	public ResponseEntity<ApiRoundTrip<List<EmailDto>>> getEmailsByLoja(@PathVariable("id_loja") UUID idLoja){
		List<EmailDto> emailsEncontrados = new ArrayList<>();

		try {
			Optional<Loja> loja = lojas.findById(idLoja);

			if(loja.isPresent()) {
				if(loja.get().getEmails() != null && loja.get().getEmails().size() > 0) {
					loja.get().getEmails().forEach(x-> emailsEncontrados.add(mapper.convertValue(x,EmailDto.class)));
				}
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrada"));
			}
			return ResponseEntity.ok().body(new ApiRoundTrip<>("E-mails encontrados",emailsEncontrados));
		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}

	@GetMapping("/apis/v1/lojas/{id_loja}/emails/{id_email}")
	public ResponseEntity<ApiRoundTrip<EmailDto>> getEmailsById(@PathVariable("id_loja") UUID idLoja,@PathVariable("id_email") UUID idEmail){

		try {
			Optional<Loja> loja = lojas.findById(idLoja);

			if(loja.isPresent()) {
				if(loja.get().getEmails() != null && loja.get().getEmails().size() > 0) {
					Optional<Email> emailProcurado = loja.get().getEmails().stream().filter(x->x.getId().equals(idEmail)).findFirst();

					if(emailProcurado.isPresent()) {
						return ResponseEntity.ok().body(new ApiRoundTrip<>("E-mail encontrado",mapper.convertValue(emailProcurado.get(),EmailDto.class)));
					}
				}
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("E-mail informado não foi encontrada"));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrada"));
			}
		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}

	@PatchMapping("/apis/v1/lojas/{id_loja}/emails/{id_email}")
	public ResponseEntity<ApiRoundTrip<EmailDto>> patchEmailsById(
			@PathVariable("id_loja") UUID idLoja,
			@PathVariable("id_email") UUID idEmail,
			@RequestBody() @Valid ApiRoundTrip<EmailDto> body){

		try {
			Optional<Loja> loja = lojas.findById(idLoja);
			
			if(loja.isPresent()) {
				Set<Email> emailsLoja = loja.get().getEmails();
				
				if(emailsLoja!= null && emailsLoja.size() > 0) {
					Optional<Email> emailParaAtualizar = emailsLoja.stream().filter(x->x.getId().equals(idEmail)).findFirst();

					if(emailParaAtualizar.isPresent()) {
						ObjectReader reader = mapper.readerForUpdating(emailParaAtualizar.get());
						EmailDto dto = body.getData();
						
						//CASO TENTE ATUALIZAR O ENDERECO PARA UM JÁ EXISTENTE
						if(dto.getEndereco() != null && emailsLoja.stream().anyMatch(x->x.getEndereco().equals(dto.getEndereco())))
							return ResponseEntity.badRequest().body(new ApiRoundTrip<>("Já existe outro e-mail cadastrado com este endereço"));
						
						//CASO ATUALIZE O PRICIPAL PARA TRUE, ATUALIZA TODOS OS OUTROS PARA FALSE
						if(dto.isPrincipal() == true)
							emailsLoja.forEach(x->x.setPrincipal(false));
						
						ObjectNode data = mapper.convertValue(body.getData(),ObjectNode.class);
						
						reader.readValue(data);
						
						lojas.save(loja.get());
						
						return ResponseEntity.ok().body(new ApiRoundTrip<>("E-mail atualizado",mapper.convertValue(emailParaAtualizar.get(),EmailDto.class)));
					}
				}
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("E-mail informado não foi encontrada"));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrada"));
			}
		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}


}
