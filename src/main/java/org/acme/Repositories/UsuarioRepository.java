package org.acme.Repositories;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Entities.Estacao;
import org.acme.Entities.Usuario;


import java.util.Scanner;

@ApplicationScoped
public class UsuarioRepository {

    public void setarRotas(Usuario usuario, ValueGraph<Estacao,Double> grafo){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Olá, Bem vindo ao RouteHelper");
        System.out.println("Primeiro insira o seu ponto de origem");
        String pontoOrigem = scanner.nextLine();

        for (Estacao estacao:grafo.nodes()){
            if (pontoOrigem.equalsIgnoreCase(estacao.getNome())){
                usuario.setOrigem(estacao);
                System.out.println("Estação confirmada");
            }
        }

        System.out.println("Agora seleciona a seu Destino");
        String pontoDestino = scanner.nextLine();

        for (Estacao estacao:grafo.nodes()){
            if (pontoDestino.equalsIgnoreCase(estacao.getNome())){
                usuario.setDestino(estacao);
                System.out.println("Estação confirmada");
            }
        }




    }

}
