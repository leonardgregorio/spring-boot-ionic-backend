package com.araleo02.cursomc.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.araleo02.cursomc.domain.ItemPedido;
import com.araleo02.cursomc.domain.PagamentoComBoleto;
import com.araleo02.cursomc.domain.Pedido;
import com.araleo02.cursomc.domain.enums.EstadoPagamento;
import com.araleo02.cursomc.repositories.ClienteRepository;
import com.araleo02.cursomc.repositories.ItemPedidoRepository;
import com.araleo02.cursomc.repositories.PagamentoRepository;
import com.araleo02.cursomc.repositories.PedidoRepository;
import com.araleo02.cursomc.repositories.ProdutoRepository;
import com.araleo02.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository PedidoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + "Tipo: " + Pedido.class.getName());
		}
		return obj;
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId())); //aula 59 - Implementando toString do pedido
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId())); //aula 59 - Implementando toString do pedido
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		System.out.println(obj); //aula 59 - Implementando toString do pedido
		
		emailService.sendOrderConfirmationEmail(obj);
		
		return obj;
	}

}
