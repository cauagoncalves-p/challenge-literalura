package br.com.challenge_literalura.challenge_literalura.service;

public interface IConvertDados {
    <T> T obterDados(String json, Class<T> Class);
}
