package org.acme;

import com.google.common.graph.MutableValueGraph;
import dtos.RouteRequest;
import dtos.RouteResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Entities.Estacao;
import org.acme.Entities.Usuario;
import org.acme.Repositories.*;
import org.acme.Services.GrafoService;


import java.util.List;
import java.util.stream.Collectors;


@Path("/rotas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteResource {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    AEstrelaRepository aEstrelaRepository;

    @Inject
    ConexaoRepository conexaoRepository;

    @Inject
    EstacaoRepository estacaoRepository;

    @Inject
    NoBuscaRepository noBuscaRepository;

    @Inject
    GrafoRepository grafoRepository;

    @Inject
    GrafoService grafoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setEstacoes(RouteRequest rota) {
        MutableValueGraph<Estacao, Double> grafo = grafoService.getGrafo();
        Usuario usuario = new Usuario();

        String estacaoOrigem = rota.estacaoOrigem();
        for (Estacao estacao:grafo.nodes()){
            if (estacaoOrigem.equalsIgnoreCase(estacao.getNome())){
                usuario.setOrigem(estacao);
            }
        }

        String estacaoDestino = rota.estacaoDestino();
        for (Estacao estacao:grafo.nodes()){
            if (estacaoDestino.equalsIgnoreCase(estacao.getNome())){
                usuario.setDestino(estacao);
            }
        }

        List<Estacao> resultado = aEstrelaRepository.calcularRota(usuario, grafo);

        if (resultado.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rota não encontrada")
                    .build();
        }
        List<String> caminhoEmNomes = resultado.stream()
                .map(estacao -> estacao.getNome())
                .toList();


        RouteResponse resultadoMapeado = new RouteResponse(caminhoEmNomes);
        return Response.ok(resultadoMapeado).build();

    }
}