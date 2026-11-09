package com.example.mobil_sprint_02.model;

import java.util.List;

/** Categoria de conversion: clave (para el Intent), nombre, imagen y sus unidades. */
public class Category {
    public final String key;
    public final String name;
    public final int imageRes;
    public final List<Unit> units;
    /** Si es true se permiten valores negativos (p. ej. temperatura). */
    public final boolean allowsNegative;

    public Category(String key, String name, int imageRes, List<Unit> units, boolean allowsNegative) {
        this.key = key;
        this.name = name;
        this.imageRes = imageRes;
        this.units = units;
        this.allowsNegative = allowsNegative;
    }

    @Override
    public String toString() {
        return name; // lo que muestra el Spinner de categorias
    }
}
