package com.example.apprestaurante;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout container;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.containerLinear);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                TextView newItem = new TextView(MainActivity.this);
                newItem.setText("Plato agregado " + count);
                newItem.setTextSize(18f);
                container.addView(newItem);

                Toast.makeText(MainActivity.this, "Nuevo plato agregado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
