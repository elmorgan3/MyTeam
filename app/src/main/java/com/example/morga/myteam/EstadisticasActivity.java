package com.example.morga.myteam;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class EstadisticasActivity extends AppCompatActivity implements View.OnClickListener {

    TextView miToolBar;

    Chronometer cronometro;

    TextView txtParte;
    TextView txtGol;
    TextView txtGolPenalti;
    TextView txtGolFalta;
    TextView txtGolPP;
    TextView txtGolEnContra;
    TextView txtAsistencia;
    TextView txtTiro;
    TextView txtTiroPuerta;
    TextView txtCorner;
    TextView txtPase;
    TextView txtPaseFallido;
    TextView txtRobo;
    TextView txtFalta;
    TextView txtFueraJuego;
    TextView txtMano;
    TextView txtTarjetaRoja;
    TextView txtTarjetaAmarilla;
    TextView txtParada;
    TextView txtCambio;

    Button buttonEmpezar;
    Button buttonGol;
    Button buttonGolPenalti;
    Button buttonGolFalta;
    Button buttonGolPP;
    Button buttonGolEnContra;
    Button buttonAsistencia;
    Button buttonTiro;
    Button buttonTiroPuerta;
    Button buttonCorner;
    Button buttonPase;
    Button buttonPaseFallido;
    Button buttonRobo;
    Button buttonFalta;
    Button buttonFueraJuego;
    Button buttonMano;
    Button buttonTarjetaRoja;
    Button buttonTarjetaAmarilla;
    Button buttonParada;
    Button buttonCambio;

    int gol = 0;
    int golPenalti = 0;
    int golFalta = 0;
    int golPP = 0;
    int golEnContra = 0;
    int asistencia = 0;
    int tiro = 0;
    int tiroPuerta = 0;
    int corner = 0;
    int pase = 0;
    int paseFallido = 0;
    int robo = 0;
    int falta = 0;
    int fueraJuego = 0;
    int mano = 0;
    int tarjetaRoja = 0;
    int tarjetaAmarilla = 0;
    int parada = 0;
    int cambio = 0;

    boolean estadoPartido = false;
    boolean primeraParte = true;

    int idPartido;
    String nombreEquipo;
    String rival;
    boolean local;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        miToolBar = (TextView)findViewById(R.id.toolbar_title);
        cronometro = (Chronometer)findViewById(R.id.chronometer3);

        txtParte = (TextView)findViewById(R.id.textViewParte);
        txtGol = (TextView)findViewById(R.id.textViewGol);
        txtGolPenalti = (TextView)findViewById(R.id.textViewGolPenalti);
        txtGolFalta = (TextView)findViewById(R.id.textViewGolFalta);
        txtGolPP = (TextView)findViewById(R.id.textViewGolPP);
        txtGolEnContra = (TextView)findViewById(R.id.textViewGolEnContra);
        txtAsistencia = (TextView)findViewById(R.id.textViewAsistencia);
        txtTiro = (TextView)findViewById(R.id.textViewTiro);
        txtTiroPuerta = (TextView)findViewById(R.id.textViewTiroPuerta);
        txtCorner = (TextView)findViewById(R.id.textViewCorner);
        txtPase = (TextView)findViewById(R.id.textViewPase);
        txtPaseFallido = (TextView)findViewById(R.id.textViewPaseFallido);
        txtRobo = (TextView)findViewById(R.id.textViewRobo);
        txtFalta = (TextView)findViewById(R.id.textViewFalta);
        txtFueraJuego = (TextView)findViewById(R.id.textViewFueraJuego);
        txtMano = (TextView)findViewById(R.id.textViewMano);
        txtTarjetaRoja = (TextView)findViewById(R.id.textViewTarjetaRoja);
        txtTarjetaAmarilla = (TextView)findViewById(R.id.textViewTarjetaAmarilla);
        txtParada = (TextView)findViewById(R.id.textViewParada);
        txtCambio = (TextView)findViewById(R.id.textViewCambio);

        buttonEmpezar = (Button)findViewById(R.id.buttonEmpezar);
        buttonEmpezar.setOnClickListener(this);
        buttonGol = (Button)findViewById(R.id.buttonGol);
        buttonGol.setOnClickListener(this);
        buttonGolPenalti = (Button)findViewById(R.id.buttonGolPenalti);
        buttonGolPenalti.setOnClickListener(this);
        buttonGolFalta = (Button)findViewById(R.id.buttonGolFalta);
        buttonGolFalta.setOnClickListener(this);
        buttonGolPP = (Button)findViewById(R.id.buttonGolPP);
        buttonGolPP.setOnClickListener(this);
        buttonGolEnContra = (Button)findViewById(R.id.buttonGolEnContra);
        buttonGolEnContra.setOnClickListener(this);
        buttonAsistencia = (Button)findViewById(R.id.buttonAsistencia);
        buttonAsistencia.setOnClickListener(this);
        buttonTiro = (Button)findViewById(R.id.buttonTiro);
        buttonTiro.setOnClickListener(this);
        buttonTiroPuerta = (Button)findViewById(R.id.buttonTiroPuerta);
        buttonTiroPuerta.setOnClickListener(this);
        buttonCorner = (Button)findViewById(R.id.buttonCorner);
        buttonCorner.setOnClickListener(this);
        buttonPase = (Button)findViewById(R.id.buttonPase);
        buttonPase.setOnClickListener(this);
        buttonPaseFallido = (Button)findViewById(R.id.buttonPaseFallido);
        buttonPaseFallido.setOnClickListener(this);
        buttonRobo = (Button)findViewById(R.id.buttonRobo);
        buttonRobo.setOnClickListener(this);
        buttonFalta = (Button)findViewById(R.id.buttonFalta);
        buttonFalta.setOnClickListener(this);
        buttonFueraJuego = (Button)findViewById(R.id.buttonFueraJuego);
        buttonFueraJuego.setOnClickListener(this);
        buttonMano = (Button)findViewById(R.id.buttonMano);
        buttonMano.setOnClickListener(this);
        buttonTarjetaRoja = (Button)findViewById(R.id.buttonTarjataRoja);
        buttonTarjetaRoja.setOnClickListener(this);
        buttonTarjetaAmarilla = (Button)findViewById(R.id.buttonTarjetaAmarilla);
        buttonTarjetaAmarilla.setOnClickListener(this);
        buttonParada = (Button)findViewById(R.id.buttonParada);
        buttonParada.setOnClickListener(this);
        buttonCambio = (Button)findViewById(R.id.buttonCambio);
        buttonCambio.setOnClickListener(this);


        Bundle b = getIntent().getExtras();

        idPartido = b.getInt("idPartido");
        nombreEquipo = b.getString("nombreEquipo");
        rival = b.getString("rival");
        local = b.getBoolean("local");

        if (local == true){
            miToolBar.setText(nombreEquipo + "(" + gol +") - (" + golEnContra +")" + rival);
        } else {
            miToolBar.setText(rival + "(" + golEnContra +") - (" + gol +")" + nombreEquipo);
        }

    }

    public void CargarToolBar (){
        if (local == true){
            miToolBar.setText(nombreEquipo + "(" + gol +") - (" + golEnContra +")" + rival);
        } else {
            miToolBar.setText(rival + "(" + golEnContra +") - (" + gol +")" + nombreEquipo);
        }
    }

    public void EmpezarPartido(){

        if (primeraParte == true)
        {
            if (estadoPartido == false)
            {
                cronometro.start();
                estadoPartido = true;
                buttonEmpezar.setText("Descanso");
            }
            else if (estadoPartido == true)
            {
                txtParte.setText("Descanso");
                buttonEmpezar.setText("Empezar 2ª");
                cronometro.stop();
                estadoPartido = false;
                primeraParte = false;
            }
        }
        else if (primeraParte == false)
        {
            if (estadoPartido == false)
            {
                cronometro.setBase(SystemClock.elapsedRealtime() - (2700000));
                cronometro.start();
                estadoPartido = true;
                buttonEmpezar.setText("Final");
                txtParte.setText("2ª parte");
            }
            else if (estadoPartido == true)
            {
                txtParte.setText("Final del partido");
                cronometro.stop();
                estadoPartido = false;
                buttonEmpezar.setEnabled(false);
                SubirEstadisticas();

            }
        }
    }

    private void SubirEstadisticas() {
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();

        client.addHeader("Token", MainActivity.DevolverToken());

        //Gol
        while (gol > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Gol");
            params.put("Partido_IdPartido", idPartido);



            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            gol--;

        }

        //GolPenalti
        while (golPenalti > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "GolPenalti");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            golPenalti--;

        }

        //GolFalta
        while (golFalta > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "GolFalta");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            golFalta--;

        }

        //GolPP
        while (golPP > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "GolPP");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    golPP--;
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            golPP--;

        }

        //GolEnContra
        while (golEnContra > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "GolEnContra");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    golEnContra--;
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            golEnContra--;

        }

        //Asistencia
        while (asistencia > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Asistencia");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            asistencia--;

        }

        //Tiro
        while (tiro > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Tiro");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            tiro--;

        }

        //TiroPuerta
        while (tiroPuerta > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "TiroPuerta");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            tiroPuerta--;

        }

        //Corner
        while (corner > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Corner");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            corner--;

        }

        //Pase
        while (pase > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Pase");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            pase--;

        }

        //PaseFallido
        while (paseFallido > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "PaseFallido");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            paseFallido--;

        }

        //Robo
        while (robo > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Robo");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            robo--;

        }

        //Falta
        while (falta > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Falta");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            falta--;

        }

        //FueraJuego
        while (fueraJuego > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "FueraJuego");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            fueraJuego--;

        }

        //Mano
        while (mano > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Mano");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            mano--;

        }

        //TarjetaRoja
        while (tarjetaRoja > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "TarjetaRoja");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            tarjetaRoja--;

        }

        //TarjetaAmarilla
        while (tarjetaAmarilla > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "TarjetaAmarilla");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            tarjetaAmarilla--;

        }

        //Parada
        while (parada > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Parada");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            parada--;

        }

        //Cambio
        while (cambio > 0) {
            params.remove("NombreEvento");
            params.remove("Partido_IdPartido");

            params.put("NombreEvento", "Cambio");
            params.put("Partido_IdPartido", idPartido);

            //client.addHeader("Token", MainActivity.DevolverToken());

            client.post("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos/Evento", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            cambio--;

        }


        Toast.makeText(this, "Las estadisticas se han guardado.", Toast.LENGTH_LONG).show();



    }

    public void Gol(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            gol = gol + 1;
            txtGol.setText(""+gol);
            CargarToolBar();
        }
    }

    public void GolPenalti(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            golPenalti = golPenalti + 1;
            txtGolPenalti.setText("" + golPenalti);
            Gol();
        }
    }

    public void GolFalta(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            golFalta = golFalta + 1;
            txtGolFalta.setText(""+golFalta);
            Gol();
        }
    }

    public void GolPP(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            golPP = golPP + 1;
            txtGolPP.setText("" + golPP);
            Gol();
        }
    }

    public void GolEnContra(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            golEnContra = golEnContra + 1;
            txtGolEnContra.setText("" + golEnContra);
            CargarToolBar();
        }
    }

    public void Asistencia(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            asistencia = asistencia + 1;
            txtAsistencia.setText("" + asistencia);
        }
    }

    public void Tiro(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            tiro = tiro + 1;
            txtTiro.setText("" + tiro);
        }
    }

    public void TiroPuerta(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            tiroPuerta = tiroPuerta + 1;
            txtTiroPuerta.setText("" + tiroPuerta);
        }
    }

    public void Corner(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            corner = corner + 1;
            txtCorner.setText("" + corner);
        }
    }

    public void Pase(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            pase = pase + 1;
            txtPase.setText("" + pase);
        }
    }

    public void PaseFallido(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            paseFallido = paseFallido + 1;
            txtPaseFallido.setText("" + paseFallido);
        }
    }

    public void Robo(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            robo = robo + 1;
            txtRobo.setText("" + robo);
        }
    }

    public void Falta(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            falta = falta +1;
            txtFalta.setText("" + falta);
        }
    }

    public void FueraDeJuego(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            fueraJuego = fueraJuego + 1;
            txtFueraJuego.setText("" + fueraJuego);
        }
    }

    public void Mano(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            mano = mano + 1;
            txtMano.setText("" + mano);
        }
    }

    public void TarjetaRoja(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            tarjetaRoja = tarjetaRoja + 1;
            txtTarjetaRoja.setText("" + tarjetaRoja);
        }
    }

    public void TarjetaAmarilla(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            tarjetaAmarilla = tarjetaAmarilla + 1;
            txtTarjetaAmarilla.setText("" + tarjetaAmarilla);
        }
    }

    public void Parada(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            parada = parada + 1;
            txtParada.setText("" + parada);
        }
    }

    public void Cambio(){
        if (estadoPartido == false) {
            Toast.makeText(this, "No esta en juego el partido.", Toast.LENGTH_SHORT).show();
        }
        else {
            cambio = cambio + 1;
            txtCambio.setText("" + cambio);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonEmpezar:
                EmpezarPartido();
                break;
            case R.id.buttonGol:
                Gol();
                break;
            case R.id.buttonGolPenalti:
                GolPenalti();
                break;
            case R.id.buttonGolFalta:
                GolFalta();
                break;
            case R.id.buttonGolPP:
                GolPP();
                break;
            case R.id.buttonGolEnContra:
                GolEnContra();
                break;
            case R.id.buttonAsistencia:
                Asistencia();
                break;
            case R.id.buttonTiro:
                Tiro();
                break;
            case R.id.buttonTiroPuerta:
                TiroPuerta();
                break;
            case R.id.buttonCorner:
                Corner();
                break;
            case R.id.buttonPase:
                Pase();
                break;
            case R.id.buttonPaseFallido:
                PaseFallido();
                break;
            case R.id.buttonRobo:
                Robo();
                break;
            case R.id.buttonFalta:
                Falta();
                break;
            case R.id.buttonFueraJuego:
                FueraDeJuego();
                break;
            case R.id.buttonMano:
                Mano();
                break;
            case R.id.buttonTarjataRoja:
                TarjetaRoja();
                break;
            case R.id.buttonTarjetaAmarilla:
                TarjetaAmarilla();
                break;
            case R.id.buttonParada:
                Parada();
                break;
            case R.id.buttonCambio:
                Cambio();
                break;

        }
    }
}
