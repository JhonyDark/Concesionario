package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ClientesActivity2 extends AppCompatActivity {
    ClsOpenHelper admin=new ClsOpenHelper(this, "Concecionario.db", null, 1);
    EditText jtxtidentificacion, jtxtemail;
    CheckBox jcheactivo;
    String identificacion, email;
    long respuesta;
    byte SW;
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
        jtxtidentificacion.requestFocus();
        SW=0;

    }
    public void guardar(View view){
        if(identificacion.equals("") ||email.equals("")){
            jtxtidentificacion.setError("Debe ingresar todos los campos");
        }else{
            if (SW == 0) {
                admin.insertar(identificacion, email, jcheactivo.isChecked());
                finish();
            }else{
                admin.actualizar(identificacion, email, jcheactivo.isChecked());
                finish();
                Limpiar_campos();
            }
        }
    }

    public void consultar(View view){
        identificacion=jtxtidentificacion.getText().toString();
        if(identificacion.isEmpty()){
            jtxtidentificacion.setError("Debe ingresar identificacion para consultar");
            jtxtidentificacion.requestFocus();
        }else{
            admin.consultar(identificacion);
            Cursor fila=admin.getReadableDatabase().rawQuery("selec * from Tblciente where identificacion='"+identificacion+"'", null);
            if(fila.moveToNext()) {
                SW=1;
                    jtxtemail.setText(fila.getString(1));
                    if (fila.getString(2).equals("Si")){
                        jcheactivo.setChecked(true);
                    }else {
                    Toast.makeText(this, "Registro anulado, para verlo se debe activar", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Metodo regresar
    public void Regresar (View view){
        Intent intmain = new Intent(this,MainActivity.class);
        startActivity(intmain);
    }

    //metodo cancelar
    public void Cancelar(View view){
        Limpiar_campos();
    }

    //Metodo anular
    public void Anular(View view){
        if (SW == 1){
            SW = 0;
            admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("activo", "No");
            respuesta = admin.actualizar("Tblcliente",registro,"identificacion"+identificacion+"'",null);
            if (respuesta > 0) {
                Toast.makeText(this, "Registro Anulado", Toast.LENGTH_SHORT).show();
                Limpiar_campos();
            }
        }else {
            Toast.makeText(this, "Debe primero consultar para anular", Toast.LENGTH_SHORT).show();
            jtxtidentificacion.requestFocus();
        }
    }

    public void DesAnular(View view){
        if (SW == 1){
            SW = 0;
            admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("activo", "Si");
            respuesta = admin.actualizar("Tblcliente",registro,"identificacion"+identificacion+"'",null);
            if (respuesta > 0) {
                Toast.makeText(this, "Registro Anulado", Toast.LENGTH_SHORT).show();
                Limpiar_campos();
            }
        }else {
            Toast.makeText(this, "Debe primero consultar para anular", Toast.LENGTH_SHORT).show();
            jtxtidentificacion.requestFocus();
        }
    }

    private void Limpiar_campos(){
        jtxtemail.setText("");
        jtxtidentificacion.setText("");
        jcheactivo.setChecked(false);
        jtxtidentificacion.requestFocus();
        SW=0;
    }
}