package net.ivanvega.miclientecpcontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ContentValues contentValues = new ContentValues();

        contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[1],
                "cablecito" );
        contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[2],
                "cablecito@gmail.com" );
        contentValues.put(ContactosContractProvider.PROJECTION_CONTACTOS[3],
                "4454563213" );


        Uri   miuri =  getContentResolver().insert(
                ContactosContractProvider.CONTENT_URI_CONTACTOS,
                contentValues
        );

        Log.d("MIURI", miuri.toString());





        lv = findViewById(R.id.lv);

        Cursor cursor  =   getContentResolver().query(
                ContactosContractProvider.CONTENT_URI_CONTACTOS
                //Uri.parse(ContactosContractProvider.CONTENT_URI_CONTACTOS.toString() + "/21")
                ,
                ContactosContractProvider.PROJECTION_CONTACTOS,
                null, null,null);

        SimpleCursorAdapter adp = new SimpleCursorAdapter(
                this,android.R.layout.simple_list_item_2, cursor,
                new String[]{
                        ContactosContractProvider.FIELD_USUARIO,
                        ContactosContractProvider.FIELD_EMAIL
                },
                 new int[]{   android.R.id.text1, android.R.id.text2},
SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        );

        lv.setAdapter(adp);


    }
}
