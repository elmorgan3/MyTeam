package com.example.morga.myteam;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.Header;


public class LoginActivity extends AppCompatActivity {

    Button btnAcceder;
    TextView txtNombre;
    TextView txtPassword;
    Button btnIrARegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnAcceder = (Button)findViewById(R.id.buttonAcceder);
        txtNombre = (TextView)findViewById(R.id.editTextNombre);
        txtPassword = (TextView)findViewById(R.id.editTextPassword);
        btnIrARegistrarse = (Button)findViewById(R.id.buttonIrActivityRegistrar);
    }

    // ----------------------------------------------------------------------
    // Metodo que manda el nombre de usuario y el password y recibe el token
    // ----------------------------------------------------------------------
    public void Loguearse (View v) throws IOException {
        AsyncHttpClient client = new AsyncHttpClient();


        String nameUser = txtNombre.getText().toString();
        String password = txtPassword.getText().toString();

        RequestParams params = new RequestParams();
        params.put("EmailUsuario", nameUser);
        params.put("Password", password);

        Toast.makeText(LoginActivity.this, "Cargando...", Toast.LENGTH_SHORT).show();

        // Generamos la URL
        client.get("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Login", params,  new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String token = new String(responseBody);
                try {
                    token = token.substring(1, token.length()-1);
                    Acceder(token);
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña erroneas.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(LoginActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();

                txtNombre.setText("");
                txtPassword.setText("");

            }
        });

    }

    public void IrARegistrar (View view){
        Intent intent = new Intent(LoginActivity.this, CrearUsuarioActivity.class);

        startActivity(intent);
    }



    public void Acceder(String token) {
        Bundle b = new Bundle();
        b.putString("Token",token);

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

}
