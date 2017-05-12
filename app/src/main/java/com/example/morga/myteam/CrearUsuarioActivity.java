package com.example.morga.myteam;

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

public class CrearUsuarioActivity extends AppCompatActivity {

    TextView miToolBar;
    EditText etEmail;
    EditText etPassword;
    EditText etName;
    EditText etApellido;
    EditText etNumero;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        etEmail = (EditText)findViewById(R.id.editTextEmail);
        etPassword = (EditText)findViewById(R.id.editTextPassword);
        etName = (EditText)findViewById(R.id.editTextName);
        etApellido = (EditText)findViewById(R.id.editTextApellido);
        etNumero = (EditText)findViewById(R.id.editTextNumero);
        btnRegistrar = (Button)findViewById(R.id.buttonRegistrar);

        miToolBar = (TextView)findViewById(R.id.toolbar_title);
        miToolBar.setText("Regístrate");
    }

    public void CrearUsuario (View view) {
        if (etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("")|| etName.getText().toString().equals("")|| etApellido.getText().toString().equals("")|| etNumero.getText().toString().equals("")) {
            Toast.makeText(CrearUsuarioActivity.this, "No puedes dejar ningun valor sin informar", Toast.LENGTH_SHORT).show();
        }else{

        }
        AsyncHttpClient client = new AsyncHttpClient();

        int numero = Integer.parseInt(etNumero.getText().toString());
        RequestParams params = new RequestParams();
        params.put("EmailUsuario", etEmail.getText().toString());
        params.put("Password", etPassword.getText().toString());
        params.put("NombreUsuario", etName.getText().toString());
        params.put("ApellidoUsuario", etApellido.getText().toString());
        params.put("TelefonoUsuario", numero);

        Toast.makeText(CrearUsuarioActivity.this, "Cargando...", Toast.LENGTH_SHORT).show();

        // Generamos la URL
        client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Usuarios/Usuario", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(CrearUsuarioActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(CrearUsuarioActivity.this, "Error. Ha habido un problema, comprueba tu conexion", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
