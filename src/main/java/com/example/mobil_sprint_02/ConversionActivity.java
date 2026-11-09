package com.example.mobil_sprint_02;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobil_sprint_02.model.Categories;
import com.example.mobil_sprint_02.model.Category;
import com.example.mobil_sprint_02.model.Converter;
import com.example.mobil_sprint_02.model.Unit;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Activity 2: convierte un valor entre dos unidades de la categoria recibida por Intent. */
public class ConversionActivity extends AppCompatActivity {

    /** Clave del extra del Intent con la categoria (la misma que envia MainActivity). */
    private static final String EXTRA_CATEGORY = "com.example.mobil_sprint_02.CATEGORY";

    private Category category;

    private EditText etValue;
    private Spinner spFrom;
    private Spinner spTo;
    private TextView tvResult;
    private Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversion);

        View root = findViewById(R.id.conversionRoot);
        int pl = root.getPaddingLeft(), pt = root.getPaddingTop();
        int pr = root.getPaddingRight(), pb = root.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(pl + bars.left, pt + bars.top, pr + bars.right, pb + bars.bottom);
            return insets;
        });

        category = Categories.byKey(getIntent().getStringExtra(EXTRA_CATEGORY));
        if (category == null) {
            finish();
            return;
        }

        ((TextView) findViewById(R.id.tvTitle)).setText(getString(R.string.title_conversion, category.name));
        etValue = findViewById(R.id.etValue);
        spFrom = findViewById(R.id.spFrom);
        spTo = findViewById(R.id.spTo);
        tvResult = findViewById(R.id.tvResult);
        btnConvert = findViewById(R.id.btnConvert);

        setUpSpinner(spFrom);
        setUpSpinner(spTo);

        btnConvert.setOnClickListener(v -> convert());
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    /** Spinner con un primer elemento guia (posicion 0) => arranca "vacio". */
    private void setUpSpinner(Spinner spinner) {
        List<Object> items = new ArrayList<>();
        items.add(getString(R.string.select_unit));
        items.addAll(category.units);
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void convert() {
        int fromPos = spFrom.getSelectedItemPosition();
        int toPos = spTo.getSelectedItemPosition();

        if (fromPos == 0 || toPos == 0) {
            showMessage(R.string.msg_pick_units);
            return;
        }

        String text = etValue.getText().toString().trim().replace(',', '.');
        if (text.isEmpty()) {
            showMessage(R.string.msg_empty_value);
            return;
        }

        double value;
        try {
            value = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            showMessage(R.string.msg_invalid_value);
            return;
        }

        // Las posiciones van desplazadas 1 por el elemento guia.
        Unit from = category.units.get(fromPos - 1);
        Unit to = category.units.get(toPos - 1);
        double result = Converter.convert(value, from, to);

        tvResult.setText(String.format(Locale.getDefault(), "%.2f %s = %.2f %s",
                value, from.name, result, to.name));
    }

    private void showMessage(int stringRes) {
        Snackbar.make(btnConvert, stringRes, Snackbar.LENGTH_SHORT)
                .setAnchorView(btnConvert)
                .show();
    }
}
