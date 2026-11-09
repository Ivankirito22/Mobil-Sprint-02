package com.example.mobil_sprint_02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobil_sprint_02.model.Categories;
import com.example.mobil_sprint_02.model.Category;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/** Activity 1: el usuario elige una categoria, ve su imagen (Bitmap) y pasa a la conversion. */
public class MainActivity extends AppCompatActivity {

    /** Clave del extra del Intent con la categoria elegida. */
    public static final String EXTRA_CATEGORY = "com.example.mobil_sprint_02.CATEGORY";

    private static final int IMAGE_WIDTH_PX = 600;

    private Spinner spCategory;
    private ImageView ivCategory;
    private Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View root = findViewById(R.id.main);
        int pl = root.getPaddingLeft(), pt = root.getPaddingTop();
        int pr = root.getPaddingRight(), pb = root.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(pl + bars.left, pt + bars.top, pr + bars.right, pb + bars.bottom);
            return insets;
        });

        spCategory = findViewById(R.id.spCategory);
        ivCategory = findViewById(R.id.ivCategory);
        btnOpen = findViewById(R.id.btnOpen);

        // Posicion 0 = texto guia ("Selecciona..."), asi el Spinner arranca sin seleccion real.
        List<Object> items = new ArrayList<>();
        items.add(getString(R.string.select_category));
        items.addAll(Categories.LIST);
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

        showBitmap(R.drawable.cat_default);

        // La imagen cambia en cuanto se elige otra categoria.
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showBitmap(position == 0 ? R.drawable.cat_default : Categories.LIST.get(position - 1).imageRes);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnOpen.setOnClickListener(v -> openConversion());
    }

    private void openConversion() {
        int position = spCategory.getSelectedItemPosition();
        if (position == 0) {
            Snackbar.make(btnOpen, R.string.msg_pick_category, Snackbar.LENGTH_SHORT)
                    .setAnchorView(btnOpen)
                    .show();
            return;
        }
        Category category = Categories.LIST.get(position - 1);

        // TODO: abrir ConversionActivity con category.key (se conecta al unir las dos paginas).
    }

    /**
     * Carga el recurso como Bitmap (BitmapFactory), lo reescala (createScaledBitmap)
     * y lo pinta en el ImageView con setImageBitmap.
     */
    private void showBitmap(int drawableRes) {
        Bitmap original = BitmapFactory.decodeResource(getResources(), drawableRes);
        int height = Math.round(IMAGE_WIDTH_PX * (original.getHeight() / (float) original.getWidth()));
        Bitmap scaled = Bitmap.createScaledBitmap(original, IMAGE_WIDTH_PX, height, true);
        ivCategory.setImageBitmap(scaled);
        if (scaled != original) {
            original.recycle();
        }
    }
}
