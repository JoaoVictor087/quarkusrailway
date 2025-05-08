package org.acme;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Entities.LinhaStatus;
import org.acme.Repositories.LinhaStatusRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/statusLinha")

public class StatusLinhaResource {

    Gson gson = new Gson();
    @Inject
    LinhaStatusRepository linhaStatusRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gerarStatusLinha() throws IOException, InterruptedException {
        List<LinhaStatus> listaDeStatus = linhaStatusRepository.buscarSituacaoLinha();
        if (listaDeStatus != null) {
            return Response.ok(listaDeStatus).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao obter status das linhas externas.")
                    .build();
        }
    }

}
