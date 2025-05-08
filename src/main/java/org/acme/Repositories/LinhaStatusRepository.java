package org.acme.Repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Entities.LinhaStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@ApplicationScoped
public class LinhaStatusRepository {
    private final Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String apiUrl = "https://www.diretodostrens.com.br/api/status";

    public List<LinhaStatus> buscarSituacaoLinha() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "*/*")
                .header("User-Agent", "Thunder Client (https://www.thunderclient.com)")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                String responseBody = response.body();
                Type linhaStatusListType = new TypeToken<List<LinhaStatus>>() {
                }.getType();
                try {
                    return gson.fromJson(responseBody, linhaStatusListType);
                } catch (Exception e) {
                    System.out.println("Erro ao desserializar a resposta JSON com Gson: " + e.getMessage());
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao fazer a requisição para a API externa: " + e.getMessage());
            return null;

        }
        return null;
    }
}
