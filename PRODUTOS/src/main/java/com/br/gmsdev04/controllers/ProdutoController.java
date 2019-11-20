package com.br.gmsdev04.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import com.br.gmsdev04.dto.ProdutoDto;
import com.br.gmsdev04.entities.Produto;
import com.br.gmsdev04.entities.ProdutoKey;
import com.br.gmsdev04.repository.LojasRepository;
import com.br.gmsdev04.repository.ProdutosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.istack.internal.logging.Logger;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutosRepository produtos;
	@Autowired
	private LojasRepository lojas;
	@Autowired
	private ObjectMapper mapper;

	private static Logger LOGGER = Logger.getLogger(ProdutoController.class);

	@PostMapping("/apis/v1/lojas/{id_loja}/produtos")
	public ResponseEntity<ApiRoundTrip<ProdutoDto>> onPost(@PathVariable("id_loja") UUID idLoja, @RequestBody ApiRoundTrip<ProdutoDto> body)
	{
		try {
			if(!lojas.existsById(idLoja))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrada"));

			Produto produto = mapper.convertValue(body.getData(),Produto.class);
			produto.setId(new ProdutoKey(idLoja,UUID.randomUUID()));

			produtos.save(produto);

			return ResponseEntity.created(new URI("/apis/v1/lojas/"+produto.getId().getIdLoja()+"/produtos/"+produto.getId().getIdProduto())).body(new ApiRoundTrip<>("Produto criado com sucesso"));
		} catch (Exception e) {
			LOGGER.logSevereException(e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitacao"));
		}
	}

	@GetMapping("/apis/v1/lojas/{id_loja}/produtos/{id_produto}")
	public ResponseEntity<ApiRoundTrip<ProdutoDto>> getProdutoById(@PathVariable("id_loja") UUID idLoja, @PathVariable("id_produto") UUID idProduto)
	{
		try {
			if(!lojas.existsById(idLoja))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrada"));

			Optional<Produto> produtoBanco = produtos.findById(new ProdutoKey(idLoja,idProduto));

			if(produtoBanco.isPresent()) {
				Produto produto = produtoBanco.get();
				ProdutoDto dto = mapper.convertValue(produto,ProdutoDto.class);
				dto.setIdLoja(produto.getId().getIdLoja());
				dto.setIdProduto(produto.getId().getIdProduto());

				return ResponseEntity.ok().body(new ApiRoundTrip<>("Produto encontrado",dto));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Produto informado não foi encontrado"));
			}
		}catch(Exception e) {
			LOGGER.logSevereException(e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitacao"));
		}
	}

	@GetMapping("/apis/v1/lojas/{id_loja}/produtos")
	public ResponseEntity<ApiRoundTrip<List<ProdutoDto>>> getProdutoByIdLoja(@PathVariable("id_loja") UUID idLoja)
	{
		List<ProdutoDto> produtosDto = new ArrayList<>();
		try {
			if(!lojas.existsById(idLoja))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrada"));

			List<Produto> produtosDaLoja = produtos.findByIdLoja(idLoja);
			
			if(produtosDaLoja != null && produtosDaLoja.size() > 1) {
				for(Produto prodDaVez : produtosDaLoja) {
					ProdutoDto prodDtoDaVez = mapper.convertValue(prodDaVez,ProdutoDto.class);
					
					prodDtoDaVez.setIdLoja(prodDaVez.getId().getIdLoja());
					prodDtoDaVez.setIdProduto(prodDaVez.getId().getIdProduto());
					
					produtosDto.add(prodDtoDaVez);
				}
			}
			return ResponseEntity.ok().body(new ApiRoundTrip<>("Consulta realizada com sucesso",produtosDto));
		}catch(Exception e) {
			LOGGER.logSevereException(e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitacao"));
		}
	}
	
	@PatchMapping("/apis/v1/lojas/{id_loja}/produtos/{id_produto}")
	public ResponseEntity<ApiRoundTrip<ProdutoDto>> onPatch(@PathVariable("id_loja") UUID idLoja, 
															@PathVariable("id_produto") UUID idProduto,
															@RequestBody ApiRoundTrip<ObjectNode> body)
	{
		try {
			if(!lojas.existsById(idLoja))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrada"));

			Optional<Produto> produtoBanco = produtos.findById(new ProdutoKey(idLoja,idProduto));
			
			if(produtoBanco.isPresent()) {
				ObjectReader reader = mapper.readerForUpdating(produtoBanco.get());
				reader.readValue(body.getData());
				
				produtos.save(produtoBanco.get());
				
				return ResponseEntity.ok().body(new ApiRoundTrip<>("Produto atualizado"));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Produto informado não foi encontrado"));
			}
			

		} catch (Exception e) {
			LOGGER.logSevereException(e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitacao"));
		}
	}
	

}
