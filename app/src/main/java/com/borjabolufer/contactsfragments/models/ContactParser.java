package com.borjabolufer.contactsfragments.models;

import android.content.Context;
import android.util.Log;


import com.borjabolufer.contactsfragments.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * ContactParser es una clase de utilidad que proporciona funcionalidad para analizar una lista de contactos
 * desde un archivo JSON ubicado en los recursos de la aplicación.
 *
 * El parser lee un archivo JSON desde el directorio raw de los recursos, extrae los detalles de cada contacto,
 * y los devuelve como una lista de objetos {@link Contact}.
 */
public class ContactParser {

    /**
     * Analiza un archivo JSON que contiene información de contactos desde los recursos raw de la aplicación.
     *
     * @param context El contexto de la aplicación utilizado para acceder a los recursos.
     * @return Una lista de objetos {@link Contact} analizados desde el archivo JSON. Si ocurre un error
     *         durante el análisis, se devuelve null.
     */
    public static List<Contact> parse(Context context) {
        List<Contact> result;
        // Abre el archivo JSON de contactos ubicado en los recursos raw
        InputStream in = context.getResources().openRawResource(R.raw.contacts);

        try {
            // Lee el contenido del archivo en un buffer
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();

            // Convierte el buffer en una cadena codificada en UTF-8
            String content = new String(buffer, StandardCharsets.UTF_8);

            // Analiza el contenido como un arreglo JSON
            JSONTokener jsonTokener = new JSONTokener(content);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            // Inicializa la lista de resultados con el tamaño del arreglo JSON
            result = new ArrayList<>(jsonArray.length());

            // Recorre cada objeto JSON en el arreglo y extrae la información del contacto
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("name");
                String firstSurname = jsonObject.getString("firstSurname");
                String secondSurname = jsonObject.getString("secondSurname");
                String address = jsonObject.getString("address");
                String birthday = jsonObject.getString("birth");
                String company = jsonObject.getString("company");
                String phone1 = jsonObject.getString("phone1");
                String phone2 = jsonObject.getString("phone2");
                String email = jsonObject.getString("email");

                // Agrega el contacto a la lista de resultados
                result.add(new Contact(name, firstSurname, secondSurname, address, company, birthday, phone1, phone2, email));
            }
        } catch (IOException | JSONException ioe) {
            // Registra cualquier error que ocurra durante el análisis
            Log.e(ContactParser.class.getSimpleName(), ioe.getMessage());
            result = null;
        }

        // Devuelve la lista de contactos o null si ocurrió un error
        return result;
    }
}