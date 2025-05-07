package org.acme.Repositories;

import com.google.common.graph.MutableValueGraph;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Entities.Estacao;
import org.acme.Entities.NoBusca;
import org.acme.Entities.Usuario;

import java.util.*;

@ApplicationScoped
public class AEstrelaRepository {

    public List<Estacao> calcularRota(Usuario usuario, MutableValueGraph<Estacao, Double> grafo) {
        Estacao inicio = usuario.getOrigem();
        Estacao destino = usuario.getDestino();

        PriorityQueue<NoBusca> openSet = new PriorityQueue<>(); //opções em aberto para se seguir
        Set<Estacao> closedSet = new HashSet<>(); //opções que não podem se repetir

        NoBusca noInicial = new NoBusca(inicio, 0, calcularHeuristica(inicio, destino), null);
        openSet.add(noInicial);

        while (!openSet.isEmpty()) {
            NoBusca noAtual = openSet.poll();

            if (noAtual.getEstacao().equals(destino)) {
                return reconstruirCaminho(noAtual);
            }

            closedSet.add(noAtual.getEstacao());

            for (Estacao vizinhoConexao : grafo.adjacentNodes(noAtual.getEstacao())) {
                Estacao vizinho = vizinhoConexao;
                double novoCustoG = noAtual.getCustoG() + grafo.edgeValueOrDefault(noAtual.getEstacao(),vizinho,Double.MIN_VALUE);

                if (closedSet.contains(vizinho)) {
                    continue;
                }

                NoBusca novoNoVizinho = new NoBusca(vizinho, novoCustoG, calcularHeuristica(vizinho, destino), noAtual);

                if (!openSet.contains(novoNoVizinho)) {
                    openSet.add(novoNoVizinho);
                } else {
                    for (NoBusca noOpen : openSet) {
                        if (noOpen.getEstacao().equals(vizinho) && novoCustoG < noOpen.getCustoG()) {
                            openSet.remove(noOpen); // Remover o nó com custo antigo
                            openSet.add(novoNoVizinho); // Adicionar o nó com o novo custo
                            break; // Importante: parar a iteração após a atualização
                        }
                    }
                }
            }
        }

        return List.of();
    }
    public double calcularHeuristica(Estacao inicio, Estacao destino){
        double raioTerra = 6371.0;
        double radLat1 = Math.toRadians(inicio.getY());
        double radLon1 = Math.toRadians(inicio.getX());
        double radLat2 = Math.toRadians(destino.getY());
        double radLon2 = Math.toRadians(destino.getY());

        // Diferença entre as latitudes e longitudes
        double deltaLat = radLat2 - radLat1;
        double deltaLon = radLon2 - radLon1;

        // Fórmula de Haversine
        double ab = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(radLat1) * Math.cos(radLat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(ab), Math.sqrt(1 - ab));

        // Distância em quilômetros
        double distancia = raioTerra * c;

        return distancia;
    }

    public List<Estacao> reconstruirCaminho(NoBusca noAtual){
        LinkedList<Estacao> caminho = new LinkedList<>();
        while (noAtual != null) {
            caminho.addFirst(noAtual.getEstacao());
            noAtual = noAtual.getPai();
        }
        return caminho;
    }



}
