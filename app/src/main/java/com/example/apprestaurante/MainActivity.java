package com.example.apprestaurante;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout containerLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.addButton);
        Button btnListo = findViewById(R.id.btnListo);
        containerLinear = findViewById(R.id.containerLinear);

        // Evento para agregar platos
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryDialog();
            }
        });

        // Evento para botón Listo
        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedirNombre();
            }
        });
    }

    private void showCategoryDialog() {
        final String[] categories = {
                getString(R.string.category_starters),
                getString(R.string.category_mains),
                getString(R.string.category_desserts)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione categoría")
                .setItems(categories, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                showDishDialog(R.array.starters);
                                break;
                            case 1:
                                showDishDialog(R.array.mains);
                                break;
                            case 2:
                                showDishDialog(R.array.desserts);
                                break;
                        }
                    }
                });
        builder.show();
    }

    private void showDishDialog(int arrayId) {
        final String[] dishes = getResources().getStringArray(arrayId);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione plato")
                .setItems(dishes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addDishToList(dishes[which]);
                    }
                });
        builder.show();
    }

    private void addDishToList(String dish) {
        // Crear un contenedor horizontal
        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        itemLayout.setPadding(0, 16, 0, 16);

        // Crear imagen
        ImageView imageView = new ImageView(this);
        int imageResId = getImageForDish(dish); // Método para mapear platos a imágenes
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
        imageView.setLayoutParams(params);
        imageView.setImageResource(imageResId);

        // Crear texto
        TextView textView = new TextView(this);
        textView.setText(dish);
        textView.setTextSize(18f);
        textView.setPadding(32, 0, 0, 0);

        // Agregar elementos al layout horizontal
        itemLayout.addView(imageView);
        itemLayout.addView(textView);

        // Agregar al contenedor principal
        containerLinear.addView(itemLayout);
    }

    // Mapea cada plato a una imagen en drawable
    private int getImageForDish(String dish) {
        switch (dish) {
            case "Papas fritas":
                return R.drawable.papas;
            case "Aros de cebolla":
                return R.drawable.aros;
            case "Langostinos":
                return R.drawable.langostinos;
            case "Empanadas":
                return R.drawable.empanadas;
            case "Ensalada César":
                return R.drawable.cesar;
            case "Carne (cerdo, vaca)":
                return R.drawable.carne;
            case "Cazuela de mariscos":
                return R.drawable.cazuela;
            case "Salmón":
                return R.drawable.salmon;
            case "Tiramisú":
                return R.drawable.tiramisu;
            case "Cheesecake":
                return R.drawable.cheesecake;
            case "Helado":
                return R.drawable.helado;
            case "Brownie":
                return R.drawable.brownie;
            default:
                return android.R.color.white;
        }
    }

    // Nuevo método para pedir nombre y confirmar orden
    private void pedirNombre() {
        final EditText input = new EditText(this);
        input.setHint("Escribí tu nombre");

        new AlertDialog.Builder(this)
                .setTitle("Ingresar nombre")
                .setMessage("Por favor, ingrese su nombre:")
                .setView(input)
                .setPositiveButton("Siguiente", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombre = input.getText().toString().trim();

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Orden registrada")
                                .setMessage("Tu orden ha sido registrada con éxito, el mozo se acercará por si hay alguna duda.")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
