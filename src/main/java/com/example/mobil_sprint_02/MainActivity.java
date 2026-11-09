package com.example.mobil_sprint_02;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/** Activity 1: pantalla de seleccion de categoria (solo diseno, todavia sin logica). */
public class MainActivity extends AppCompatActivity {

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
    }
}
