package com.example.morga.myteam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PreemptiveAuthorizationHttpRequestInterceptor;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class PartidoConfiguracionActivity extends AppCompatActivity {

    Button buttonEmpezarPartido;
    TextView txtFecha;
    EditText etNombreEquipoContrario;
    EditText etJornada;
    CheckBox cbLocal;

    String nombreEquipo;
    int idEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido_configuracion);

        buttonEmpezarPartido = (Button)findViewById(R.id.buttonEmpezarPartido);
        txtFecha = (TextView)findViewById(R.id.textViewFecha);
        etNombreEquipoContrario = (EditText)findViewById(R.id.editTextNombreEquipoContrario);
        etJornada = (EditText)findViewById(R.id.editTextJornada);
        cbLocal = (CheckBox) findViewById(R.id.checkBoxLocal);

        Bundle b = getIntent().getExtras();

        idEquipo = b.getInt("idEquipo");
        nombreEquipo = b.getString("nombreEquipo");

        //Llamo a un metodo para que coja el dia en el que se va a jugar el partido
        txtFecha.setText(ObtenerFecha());

        buttonEmpezarPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmpezarPartido();
            }
        });

    }

    //---------------------------------
    //Metodo para obtener el dia actual
    //---------------------------------
    public String ObtenerFecha () {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(cal.getTime());
        // Output "Wed Sep 26 14:23:28 EST 2012"

        String formatted = format1.format(cal.getTime());
        System.out.println(formatted);
        // Output "2012-09-26"

        return formatted;
    }

    //-------------
    //Metodo para empezar el partido
    //-------------
    public void EmpezarPartido() {
        AsyncHttpClient client = new AsyncHttpClient();

        if (etJornada.getText().toString().equals("") || etNombreEquipoContrario.getText().toString().equals("")) {
            Toast.makeText(this, "El nombre del equipo contrario o la jornada no estan asigndas", Toast.LENGTH_LONG).show();
        } else {
            //Añado los datos para insertar el partido
            RequestParams params = new RequestParams();
            params.put("FechaPartido", txtFecha.getText().toString());
            params.put("Jornada", etJornada.getText().toString());
            params.put("Rival", etNombreEquipoContrario.getText().toString());
            if (cbLocal.isChecked()==true){
                params.put("Local", true);
            } else {
                params.put("Local", false);
            }
            params.put("Equipo_IdEquipo", idEquipo);

            //Añado el token en la cabecera llamando al metodo del mainActivity
            client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Partidos/Partido", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String idPartido = new String(responseBody);

                    Toast.makeText(PartidoConfiguracionActivity.this, "El partido empazara en breve.", Toast.LENGTH_SHORT).show();

                    Bundle b = new Bundle();
                    b.putInt("idPartido", Integer.parseInt(idPartido));
                    b.putString("nombreEquipo", nombreEquipo);
                    b.putString("rival", etNombreEquipoContrario.getText().toString());
                    b.putBoolean("local", cbLocal.isChecked());


                    Intent intent = new Intent(PartidoConfiguracionActivity.this, EstadisticasActivity.class);
                    intent.putExtras(b);
                    startActivity(intent);

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(PartidoConfiguracionActivity.this, "Ha habido algun problema, comrpueba tu conexion a internet.", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
