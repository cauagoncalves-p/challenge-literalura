package br.com.challenge_literalura.challenge_literalura.service;

import br.com.challenge_literalura.challenge_literalura.service.IConvertDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ConvertDado implements IConvertDados {
    private ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public <T> T obterDados(String json, Class<T> Class) {
        try {
            return mapper.readValue(json, Class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
