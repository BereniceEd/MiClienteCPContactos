package net.ivanvega.miclientecpcontactos;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Actualizar extends AppCompatActivity {
    private EditText nombre, mail, telefono, nac;
    int id;
    Contacto contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        nombre = (EditText)findViewById(R.id.nombre);
        mail= (EditText)findViewById(R.id.mail);
        telefono = (EditText)findViewById(R.id.tel);
        // nac = (EditText)findViewById(R.id.editText2);
        Bundle bundle=getIntent().getExtras();
id =contacto.id;
        nombre.setText(contacto.getUsuario());
        mail.setText((contacto.getEmail()));
        telefono.setText(contacto.getTel());
       // String[] arr=contacto.getFecNac().split("-");
        //dtpFecha.updateDate(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
    }

    public void Guardar(View v){
        ContentValues contentValues = new ContentValues();

        contentValues.put("_usuario", nombre.getText().toString());
        contentValues.put("_email", mail.getText().toString());
        contentValues.put("_telefono", telefono.getText().toString());


        try {
            getContentResolver().update(ContactosContractProvider.CONTENT_URI_CONTACTOS, contentValues, "_id = ?", new String[]{String.valueOf(id)});
            setResult(RESULT_OK, null);
            finish();
        }catch (Exception ex){
            setResult(RESULT_CANCELED, null);
            finish();
        }
        //String fecha=dtpFecha.getDayOfMonth()+"-"+dtpFecha.getMonth()+"-"+dtpFecha.getYear();
        Contacto obj=new Contacto(contacto.getId(),nombre.getText().toString(), mail.getText().toString(),telefono.getText().toString());

            Toast.makeText(this,"Se modifico",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

    }
}
