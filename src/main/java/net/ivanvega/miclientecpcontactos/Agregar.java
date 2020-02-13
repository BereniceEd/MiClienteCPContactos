package net.ivanvega.miclientecpcontactos;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Agregar extends AppCompatActivity {
private EditText nombre, mail, telefono, nac;
Contacto contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        nombre = (EditText)findViewById(R.id.nombre2);
        mail= (EditText)findViewById(R.id.mail2);
        telefono = (EditText)findViewById(R.id.tel2);
       // nac = (EditText)findViewById(R.id.editText2);


}
    public void Agregar(View view){
        Intent intent = new Intent(Agregar.this, MainActivity.class);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[1],
                    nombre.getText().toString());
            contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[2],
                    mail.getText().toString());
            contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[3],
                    telefono.getText().toString());
            Uri miuri =  getContentResolver().insert(
                    ContactosContractProvider.CONTENT_URI_CONTACTOS,
                    contentValues);
            Log.d("MIURI", miuri.toString());
            setResult(RESULT_OK, null);
            finish();

        }catch (Exception ex){
            setResult(RESULT_CANCELED, null);
            finish();
        }


    }


}