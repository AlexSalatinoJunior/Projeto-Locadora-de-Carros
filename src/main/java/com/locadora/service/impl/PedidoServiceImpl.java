package com.locadora.service.impl;

import com.locadora.domain.entity.Carro;
import com.locadora.domain.entity.Pedido;
import com.locadora.domain.entity.Usuario;
import com.locadora.domain.enums.StatusPedido;
import com.locadora.domain.repository.Carros;
import com.locadora.domain.repository.Pedidos;
import com.locadora.domain.repository.Usuarios;
import com.locadora.exception.PedidoNaoEncontradoException;
import com.locadora.exception.RegraDeNegocioException;
import com.locadora.rest.dto.PedidoDTO;
import com.locadora.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@Service
public class PedidoServiceImpl implements PedidoService {

    private Carros carrosRepository;
    private Pedidos pedidosRepository;
    private Usuarios usuariosRepository;

    public PedidoServiceImpl(Carros carrosRepository, Pedidos pedidosRepository, Usuarios usuariosRepository) {
        this.carrosRepository = carrosRepository;
        this.pedidosRepository = pedidosRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Pedido pedido = new Pedido();
        pedido.setDiasLocacao(dto.getDiasLocacao());

        Carro carro = converterCarro(dto.getCarro());
        pedido.setValorTotal(carro.getValorDiaria() * pedido.getDiasLocacao());
        pedido.setCarro(carro);

        Integer idUsuario = dto.getUsuario();
        Usuario usuario = converterUsuario(idUsuario, carro);
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.REALIZADO);

        usuariosRepository.save(usuario);
        pedidosRepository.save(pedido);
        carrosRepository.save(carro);

        return pedido;
    }


    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);

                    if (statusPedido == StatusPedido.REALIZADO) {
                        pedido.getCarro().setDisponivel(false);
                        return pedidosRepository.save(pedido);
                    }

                    pedido.getCarro().setDisponivel(true);
                    pedido.getUsuario().setCarroAtual(null);

                    return pedidosRepository.save(pedido);
                }).orElseThrow(
                        () -> new PedidoNaoEncontradoException("pedido não encontrado")
                );
    }

    @Override
    public List<Pedido> obterTodosPedidos() {
        return pedidosRepository.findAll();
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findById(id);
    }


    private Usuario converterUsuario(Integer idUsuario, Carro carro) {
        Usuario usuario = new Usuario();

        return usuariosRepository.findById(idUsuario)
                .map(usuarioExistente -> {
                    if (usuarioExistente.getCarroAtual() != null) {
                        throw new RegraDeNegocioException("Usuario já está alugando um carro");
                    }

                    usuario.setId(usuarioExistente.getId());
                    usuario.setNome(usuarioExistente.getNome());
                    usuario.setCnh(usuarioExistente.getCnh());
                    usuario.setCarroAtual(carro);
                    return usuario;
                })
                .orElseThrow(
                        () -> new RegraDeNegocioException("Usuario não encontrado")
                );
    }

    public Carro converterCarro(Integer idCarro) {
        Carro carro = new Carro();

        return carrosRepository.findById(idCarro)
                .map(carroExistente -> {
                    if (carroExistente.isDisponivel()) {
                        carro.setId(carroExistente.getId());
                        carro.setModelo(carroExistente.getModelo());
                        carro.setPlaca(carroExistente.getPlaca());
                        carro.setValorDiaria(carroExistente.getValorDiaria());
                        carro.setDisponivel(false);
                        return carro;
                    }
                    throw new RegraDeNegocioException("Carro indisponivel");
                }).orElseThrow(
                        () -> new RegraDeNegocioException("Carro não encontrado")
                );
    }
}
