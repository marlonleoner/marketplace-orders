package br.com.ufsm.pedidoapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ufsm.pedidoapi.controller.dto.PedidoDto;
import br.com.ufsm.pedidoapi.controller.form.AtualizacaoPedidoForm;
import br.com.ufsm.pedidoapi.controller.form.PedidoForm;
import br.com.ufsm.pedidoapi.modelo.Pedido;
import br.com.ufsm.pedidoapi.modelo.Usuario;
import br.com.ufsm.pedidoapi.repository.PedidoRepository;
import br.com.ufsm.pedidoapi.services.FormPedidoServices;
import exception.ParamInvalidoException;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public List<PedidoDto> lista(Usuario usuario) {
		if (usuario == null) {
			List<Pedido> pedidos = pedidoRepository.findAll();
			return PedidoDto.converter(pedidos);
		} else {
			List<Pedido> pedidos = pedidoRepository.findByUsuario(usuario);
			return PedidoDto.converter(pedidos);
		}
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) throws MethodArgumentNotValidException, ParamInvalidoException {
		FormPedidoServices formServices = new FormPedidoServices();
		Pedido pedido = formServices.criar(form);
		pedidoRepository.save(pedido);
		
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> detalhar(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			return ResponseEntity.ok(new PedidoDto(pedido.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PedidoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoPedidoForm form) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		if (optional.isPresent()) {
			Pedido p = optional.get();
			p.setPrecoTotal(form.getPrecoTotal());
			pedidoRepository.save(p);
			return ResponseEntity.ok(new PedidoDto(p));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		if (optional.isPresent()) {
			pedidoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
