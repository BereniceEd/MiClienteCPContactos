package net.ivanvega.miclientecpcontactos;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class UpdateDeleteClass extends AppCompatActivity implements View.OnClickListener {

    private Button btnEliminarUD, btnEditarUD, btnFechaUD;
    private TextView txtFechaUD;
    private EditText txtUsuarioUD, txtEmailUD, txtTelefonoUD;

    private int idActual;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete_main);


        btnEditarUD = findViewById(R.id.btnEditarUD);
        btnEliminarUD = findViewById(R.id.btnEliminarUD);

        txtUsuarioUD = findViewById(R.id.txtUsuarioUD);
        txtEmailUD = findViewById(R.id.txtEmailUD);
        txtTelefonoUD = findViewById(R.id.txtTelefonoUD);


        btnEliminarUD.setOnClickListener(this);
        btnEditarUD.setOnClickListener(this);

        Bundle b = this.getIntent().getExtras();
        if (b != null){
            Contacto contacto = (Contacto)b.getSerializable("contacto");
            txtUsuarioUD.setText(contacto.getUsuario());
            txtEmailUD.setText(contacto.getEmail());
            txtTelefonoUD.setText(contacto.getTel());

            idActual = contacto.id;
        }else{
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btnEditarUD:
                modificar();
                break;
            case R.id.btnEliminarUD:
                Snackbar.make(view, "¿Eliminar?", Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.WHITE)
                        .setAction("Sí", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                eliminar();
                            }
                        })
                        .show();
                break;
        }
    }

    public void eliminar(){
        Intent intent = new Intent(UpdateDeleteClass.this, MainActivity.class);
        try {
            getContentResolver().delete(ContactosContractProvider.CONTENT_URI_CONTACTOS,"_id = ?", new String[]{String.valueOf(idActual)});
            setResult(RESULT_OK, null);
            finish();
        }catch (Exception ex){
            setResult(RESULT_CANCELED, null);
            finish();
        }
    }

    public void modificar(){
        ContentValues contentValues = new ContentValues();

        contentValues.put("_usuario", txtUsuarioUD.getText().toString());
        contentValues.put("_email", txtEmailUD.getText().toString());
        contentValues.put("_telefono", txtTelefonoUD.getText().toString());


        try {
            getContentResolver().update(ContactosContractProvider.CONTENT_URI_CONTACTOS, contentValues, "_id = ?", new String[]{String.valueOf(idActual)});
            setResult(RESULT_OK, null);
            finish();
        }catch (Exception ex){
            setResult(RESULT_CANCELED, null);
            finish();
        }
    }


}