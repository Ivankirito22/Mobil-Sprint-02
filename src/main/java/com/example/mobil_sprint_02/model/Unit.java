package com.example.mobil_sprint_02.model;

/**
 * Una unidad de medida expresada respecto a la unidad base de su categoria:
 *
 *     valorBase = valor * factor + offset
 *
 * Las unidades lineales (metros, kg...) usan offset = 0.
 * La temperatura necesita offset (por eso no basta con un simple factor).
 */
public class Unit {
    public final String name;
    public final double factor;
    public final double offset;

    public Unit(String name, double factor) {
        this(name, factor, 0);
    }

    public Unit(String name, double factor, double offset) {
        this.name = name;
        this.factor = factor;
        this.offset = offset;
    }

    @Override
    public String toString() {
        return name; // lo que muestra el Spinner
    }
}
