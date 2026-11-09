package com.example.mobil_sprint_02.model;

/**
 * Conversion generica, sin if/else ni switch por unidad.
 *
 * Pasos: origen -> unidad base -> destino
 *   base      = valor * origen.factor + origen.offset
 *   resultado = (base - destino.offset) / destino.factor
 *
 * Con offset = 0 se reduce a la formula de las diapositivas:
 *   resultado = valor * origen.factor / destino.factor
 */
public final class Converter {

    private Converter() {}

    public static double convert(double value, Unit from, Unit to) {
        double base = value * from.factor + from.offset;
        return (base - to.offset) / to.factor;
    }
}
