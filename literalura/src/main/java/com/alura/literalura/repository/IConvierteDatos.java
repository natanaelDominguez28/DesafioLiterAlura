package com.alura.literalura.repository;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
