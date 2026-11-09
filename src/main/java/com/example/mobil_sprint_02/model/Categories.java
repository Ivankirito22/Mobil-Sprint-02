package com.example.mobil_sprint_02.model;

import com.example.mobil_sprint_02.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Registro de todas las categorias y sus unidades.
 * Para anadir una categoria nueva basta con anadir una entrada en LIST:
 * no hay que tocar ni las Activities ni el conversor.
 *
 * El factor indica cuantas unidades BASE vale 1 unidad de esa fila.
 */
public final class Categories {

    private Categories() {}

    public static final List<Category> LIST = new ArrayList<>();

    static {
        LIST.add(new Category("distance", "Distancia", R.drawable.cat_distance, Arrays.asList(
                new Unit("Metro (m)", 1),
                new Unit("Kilómetro (km)", 1000),
                new Unit("Centímetro (cm)", 0.01),
                new Unit("Milímetro (mm)", 0.001),
                new Unit("Milla (mi)", 1609.344),
                new Unit("Yarda (yd)", 0.9144),
                new Unit("Pie (ft)", 0.3048),
                new Unit("Pulgada (in)", 0.0254)), false));

        // Base: grado Celsius. base = valor * factor + offset
        //   Fahrenheit: (F - 32) * 5/9  = F * 5/9 - 160/9
        //   Kelvin:      K - 273.15
        LIST.add(new Category("temperature", "Temperatura", R.drawable.cat_temperature, Arrays.asList(
                new Unit("Celsius (°C)", 1, 0),
                new Unit("Fahrenheit (°F)", 5.0 / 9.0, -160.0 / 9.0),
                new Unit("Kelvin (K)", 1, -273.15)), true));

        LIST.add(new Category("volume", "Volumen", R.drawable.cat_volume, Arrays.asList(
                new Unit("Litro (L)", 1),
                new Unit("Mililitro (mL)", 0.001),
                new Unit("Metro cúbico (m³)", 1000),
                new Unit("Galón US (gal)", 3.785411784),
                new Unit("Pinta US (pt)", 0.473176473),
                new Unit("Taza US (cup)", 0.2365882365)), false));

        LIST.add(new Category("mass", "Masa / Peso", R.drawable.cat_mass, Arrays.asList(
                new Unit("Kilogramo (kg)", 1),
                new Unit("Gramo (g)", 0.001),
                new Unit("Miligramo (mg)", 0.000001),
                new Unit("Tonelada (t)", 1000),
                new Unit("Libra (lb)", 0.45359237),
                new Unit("Onza (oz)", 0.028349523125)), false));

        LIST.add(new Category("time", "Tiempo", R.drawable.cat_time, Arrays.asList(
                new Unit("Segundo (s)", 1),
                new Unit("Minuto (min)", 60),
                new Unit("Hora (h)", 3600),
                new Unit("Día (d)", 86400),
                new Unit("Semana (sem)", 604800),
                new Unit("Milisegundo (ms)", 0.001)), false));

        LIST.add(new Category("digital", "Información digital", R.drawable.cat_digital, Arrays.asList(
                new Unit("Byte (B)", 1),
                new Unit("Bit (b)", 0.125),
                new Unit("Kilobyte (KB)", 1024),
                new Unit("Megabyte (MB)", 1024.0 * 1024),
                new Unit("Gigabyte (GB)", 1024.0 * 1024 * 1024),
                new Unit("Terabyte (TB)", 1024.0 * 1024 * 1024 * 1024)), false));

        // Tipos de cambio de EJEMPLO respecto al euro (no son en tiempo real).
        LIST.add(new Category("currency", "Moneda (tasas de ejemplo)", R.drawable.cat_currency, Arrays.asList(
                new Unit("Euro (EUR)", 1),
                new Unit("Dólar US (USD)", 0.92),
                new Unit("Libra (GBP)", 1.17),
                new Unit("Yen (JPY)", 0.0061),
                new Unit("Franco suizo (CHF)", 1.05),
                new Unit("Peso mexicano (MXN)", 0.050)), false));

        LIST.add(new Category("area", "Área", R.drawable.cat_area, Arrays.asList(
                new Unit("Metro² (m²)", 1),
                new Unit("Kilómetro² (km²)", 1_000_000),
                new Unit("Hectárea (ha)", 10_000),
                new Unit("Centímetro² (cm²)", 0.0001),
                new Unit("Pie² (ft²)", 0.09290304),
                new Unit("Acre (ac)", 4046.8564224)), false));

        LIST.add(new Category("speed", "Velocidad", R.drawable.cat_speed, Arrays.asList(
                new Unit("Metro/segundo (m/s)", 1),
                new Unit("Kilómetro/hora (km/h)", 1000.0 / 3600.0),
                new Unit("Milla/hora (mph)", 0.44704),
                new Unit("Nudo (kn)", 1852.0 / 3600.0)), false));

        LIST.add(new Category("pressure", "Presión", R.drawable.cat_pressure, Arrays.asList(
                new Unit("Pascal (Pa)", 1),
                new Unit("Kilopascal (kPa)", 1000),
                new Unit("Bar (bar)", 100_000),
                new Unit("Atmósfera (atm)", 101_325),
                new Unit("PSI (psi)", 6894.757293168),
                new Unit("mmHg", 133.322387415)), false));
    }

    /** Busca una categoria por su clave (la que viaja en el Intent). */
    public static Category byKey(String key) {
        for (Category c : LIST) {
            if (c.key.equals(key)) return c;
        }
        return null;
    }
}
