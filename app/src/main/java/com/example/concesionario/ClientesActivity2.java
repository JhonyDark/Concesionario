package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class ClientesActivity2 extends AppCompatActivity {
    ClsOpenHelper admin=new ClsOpenHelper(this, "Concecionario.db", null, 1);
    EditText jtxtidentificacion, jtxtemail;
    CheckBox jcheactivo;
    String identificacion, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes2);
        //ocultar barra de titulo por defecto y asociar objetos Xml con los de Java
        getSupportActionBar().hide();
        jtxtidentificacion=(EditText)findViewById(R.id.txtidentificacion);
        jtxtemail=(EditText)findViewById(R.id.txtemail);
        jcheactivo=(CheckBox)findViewById(R.id.cheactivo);
        identificacion=jtxtidentificacion.getText().toString();
        email=jtxtemail.getText().toString();
        jcheactivo.setChecked(true);
    }
    public void guardar(View view){
        if(identificacion.equals("")||email.equals("")){
            jtxtidentificacion.setError("Debe ingresar todos los campos");
        }else{
            admin.insertar(identificacion,email,jcheactivo.isChecked());
            finish();
        }
    }
}