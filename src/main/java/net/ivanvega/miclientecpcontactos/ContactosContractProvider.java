package net.ivanvega.miclientecpcontactos;

import android.net.Uri;

public class ContactosContractProvider {


    public static final Uri CONTENT_URI_CONTACTOS =
            Uri.parse("content://net.ivanvega.mibasedatosp77a.provider/contactos");

    public static final String[] PROJECTION_CONTACTOS =
            {"_id", "_usuario", "_email", "_telefono"};

    public static final  String FIELD_ID = "_id";
    public static final  String FIELD_USUARIO = "_usuario";
    public static final  String FIELD_EMAIL = "_email";
    public static final  String FIELD_TELEFONO = "_telefono";


}
