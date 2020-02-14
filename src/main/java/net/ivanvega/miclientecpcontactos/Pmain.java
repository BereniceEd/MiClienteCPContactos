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

public class Pmain extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener{
    ListView lv;
    Contacto com;
    List<Contacto> lista=new ArrayList<>();
    int posicion=-1;

TextView txtNomnbre;
ImageButton ir;
//int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pmain);
lv = findViewById(R.id.lv);

        lv.setClickable(true);
        lv.setOnItemClickListener(this);



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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicion=position;
            }
        });


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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicion=position;
            }
        });
    }

    public void Ir(View view){
   txtNomnbre.setVisibility(View.VISIBLE);
   ir.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Actualizar();
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == MainActivity.RESULT_OK){
            Toast.makeText(this, "Operación realizada con éxito.", Toast.LENGTH_SHORT).show();
            Actualizar();
        }else{
            Actualizar();
        }
    }

    public void Modificar(View view){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicion=position;
            }
        });
        if(posicion>=0){
            Cursor cursor = (Cursor)lv.getItemAtPosition(posicion);
            String _id = cursor.getString( cursor.getColumnIndex("_id") );
            Cursor c2 = getContentResolver().query(ContactosContractProvider.CONTENT_URI_CONTACTOS, ContactosContractProvider.PROJECTION_CONTACTOS, "_id=?", new String[]{_id},null);
            Contacto contacto = null;
            if (c2.moveToFirst()) {
                do {
                    contacto =
                            new Contacto(c2.getInt(0), c2.getString(1),
                                    c2.getString(2), c2.getString(3));
                } while (c2.moveToNext());
            }
            c2.close();
            cursor.close();

            Intent intent = new Intent();
            Bundle bundle = new Bundle();

            bundle.putSerializable("contacto", contacto);
            intent.putExtras(bundle);
            intent.setClass(this, UpdateDeleteClass.class);
            startActivityForResult(intent, 10);
       }else {
            Toast.makeText(this, "NO ha seleccionado nada", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(parent == lv){
            Cursor cursor = (Cursor)lv.getItemAtPosition(position);
            String _id = cursor.getString( cursor.getColumnIndex("_id") );
            Cursor c2 = getContentResolver().query(ContactosContractProvider.CONTENT_URI_CONTACTOS, ContactosContractProvider.PROJECTION_CONTACTOS, "_id=?", new String[]{_id},null);
            Contacto contacto = null;
            if (c2.moveToFirst()) {
                do {
                    contacto =
                            new Contacto(c2.getInt(0), c2.getString(1),
                                    c2.getString(2), c2.getString(3));
                } while (c2.moveToNext());
            }
            c2.close();
            cursor.close();

            Intent intent = new Intent();
            Bundle bundle = new Bundle();

            bundle.putSerializable("contacto", contacto);
            intent.putExtras(bundle);
            intent.setClass(this, UpdateDeleteClass.class);
            startActivityForResult(intent, 10);
        }
    }
}
