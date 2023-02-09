package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Clientes(View view) {
        Intent intclientes = new Intent(this, ClientesActivity2.class);
        startActivity(intclientes);
    }

    public void Vehiculos(View view) {
        Intent intvehiculos = new Intent(this, VehiculoActivity2.class);
        startActivity(intvehiculos);
    }

    public void Ventas(View view) {
        Intent intventas = new Intent(this, VentaActivity2.class);
        startActivity(intventas);
    }
}