package com.example.morga.myteam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class CrearEquipoActivity extends AppCompatActivity {

    TextView miToolBar;
    Button btnCrearEquipo;
    EditText etNombreEquipo;
    EditText etDireccion;
    EditText etCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_equipo);

        miToolBar = (TextView)findViewById(R.id.toolbar_title);
        miToolBar.setText("Crear nuevo equipo");

        btnCrearEquipo = (Button)findViewById(R.id.buttonCrearEquipo);
        etNombreEquipo = (EditText) findViewById(R.id.etNombreEquipo);
        etDireccion = (EditText)findViewById(R.id.etDireccion);
        etCategoria = (EditText)findViewById(R.id.etCategoria);
    }

    public void CrearEquipo (View v){
        if (etNombreEquipo.getText().toString().equals("") || etDireccion.getText().toString().equals("") || etCategoria.getText().toString().equals(""))
        {
            Toast.makeText(this, "Hay algun valor sin informar", Toast.LENGTH_LONG).show();
        }
        else {
            AsyncHttpClient client = new AsyncHttpClient();

            RequestParams params = new RequestParams();
            params.put("NombreEquipo", etNombreEquipo.getText().toString());
            params.put("Direccion", etDireccion.getText().toString());
            params.put("Categoria", etCategoria.getText().toString());

            client.addHeader("Token", MainActivity.DevolverToken());
            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Equipos/Equipo", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Toast.makeText(CrearEquipoActivity.this, "El equipo se ha creado correctamente.", Toast.LENGTH_SHORT).show();

                    finish();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }
    }
}
