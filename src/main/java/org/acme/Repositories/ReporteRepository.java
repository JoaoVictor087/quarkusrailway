package org.acme.Repositories;

import dtos.ReporteDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Infrastructure.DatabaseConfig;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

@ApplicationScoped
public class ReporteRepository {

    public void adicionarReporte(ReporteDTO dto) throws SQLException{
        String query = "INSERT INTO ROUTEHELPER_REPORTES (ID, NOME, LOCAL, DSCR, DATA) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            if (dto.dscr().isEmpty() || dto.local().isEmpty()){
                throw new IllegalArgumentException("campo não pode ser vazio");
            }

            UUID uuid = UUID.randomUUID();
            String nome;
            if (dto.nome().isEmpty()){
                nome = "Anônimo";
            }else{
                nome = dto.nome();
            }

            statement.setString(1, uuid.toString());
            statement.setString(2, nome);
            statement.setString(3, dto.local());
            statement.setString(4, dto.dscr());
            statement.setDate(5, Date.valueOf(LocalDate.now()));

            statement.executeUpdate();

        }
    }
}
