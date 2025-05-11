package org.acme;

import dtos.ReporteDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Repositories.ReporteRepository;

import java.sql.SQLException;

@Path("/reportes")
public class ReporteResource {
    @Inject
    ReporteRepository reporteRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarReporte(ReporteDTO reporteDTO){
        try {
            reporteRepository.adicionarReporte(reporteDTO);
            return Response.status(Response.Status.CREATED)
                    .entity("Reporte enviado com sucesso")
                    .build();
        }catch (IllegalArgumentException illegalArgumentException){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Campos 'descrição' e/ou 'local' não podem ser vazios")
                    .build();
        }catch (SQLException s){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao conectar-se com o banco de dados")
                    .build();
        }
    }
}
