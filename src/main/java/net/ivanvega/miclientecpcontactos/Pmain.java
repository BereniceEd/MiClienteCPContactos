package net.ivanvega.miclientecpcontactos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Pmain extends AppCompatActivity {
    ListView lv;
    Contacto com;
    List<Contacto> lista=new ArrayList<>();
    int posicion=-1;

TextView txtNomnbre;
ImageButton ir;
//int cont;
//private EditText Nombre, Mail, Tel, editText6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pmain);






    rellenar();

    }



    public void rellenar(){

        Cursor cursor  =   getContentResolver().query(
                ContactosContractProvider.CONTENT_URI_CONTACTOS,
                ContactosContractProvider.PROJECTION_CONTACTOS,
                null, null,null);

        SimpleCursorAdapter simpleCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        android.R.layout.simple_list_item_2,
                        cursor,
                        new String[]{ContactosContractProvider.FIELD_USUARIO, ContactosContractProvider.FIELD_EMAIL},
                        new int[]{android.R.id.text1, android.R.id.text2
                        },
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
                );
        lv.setAdapter(simpleCursorAdapter);


    }
   public void Agregar(View view){



       Intent intent=new Intent(view.getContext(),Agregar.class);
       startActivity(intent);
    }
    public void Eliminar(View v){
        if(posicion>=0){

            Intent intent = new Intent(Pmain.this, MainActivity.class);
            try {
                getContentResolver().delete(ContactosContractProvider.CONTENT_URI_CONTACTOS,"_id = ?", new String[]{String.valueOf(posicion)});
                setResult(RESULT_OK, null);
                finish();
            }catch (Exception ex){
                setResult(RESULT_CANCELED, null);
                finish();
            }
        Actualizar();
        }else{
            Toast.makeText(this,"Seleccione uno primero",Toast.LENGTH_LONG).show();
        }

    }

    public void Actualizar(){
        Cursor cursor  =   getContentResolver().query(
                ContactosContractProvider.CONTENT_URI_CONTACTOS,
                ContactosContractProvider.PROJECTION_CONTACTOS,
                null, null,null);

        SimpleCursorAdapter simpleCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        android.R.layout.simple_list_item_2,
                        cursor,
                        new String[]{ContactosContractProvider.FIELD_USUARIO, ContactosContractProvider.FIELD_EMAIL},
                        new int[]{android.R.id.text1, android.R.id.text2
                        },
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
                );
        lv.setAdapter(simpleCursorAdapter);
    }

    public void Ir(View view){
   txtNomnbre.setVisibility(View.VISIBLE);
   ir.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == MainActivity.RESULT_OK){
            Toast.makeText(this, "Operación realizada con éxito.", Toast.LENGTH_SHORT).show();
            Actualizar();
        }else{
            Actualizar();
        }
    }
    public void Modificar(View view){
        if(posicion>=0){
            Intent intent=new Intent(getApplicationContext(),Actualizar.class);
            intent.putExtra("_id",lista.get(posicion).getId());
            startActivity(intent);
        }else{
            Toast.makeText(this,"NO ha seleccionado nada",Toast.LENGTH_LONG).show();
        }
    }

}
