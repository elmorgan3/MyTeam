package com.example.morga.myteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MostrarEstadisticasActivity extends AppCompatActivity {

    TextView miToolBar;
    int idPartido;
    String nombreMiEquipo;
    String nombreEquipoRival;
    boolean localOVisitante;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_estadisticas);

        //Guardo los datos que me llegan del intent
        Bundle b = getIntent().getExtras();
        idPartido = b.getInt("idPartido");
        nombreMiEquipo = b.getString("nombreMiEquipo");
        nombreEquipoRival = b.getString("nombreEquipoRival");
        localOVisitante = b.getBoolean("localOVisitante");

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

        //----------------------------------------------------------------------
        // Voy ha hacer un get de todos los eventos de este partido y mostrarlos
        //----------------------------------------------------------------------
        AsyncHttpClient client = new AsyncHttpClient();

        final RequestParams params = new RequestParams();
        params.put("idPartido", idPartido);
        client.addHeader("Token", MainActivity.DevolverToken());
        // Generamos la URL
        client.get("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Eventos",params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        JSONArray arrayEventos = new JSONArray();
                        JSONObject evento = new JSONObject();

                        String str = new String(responseBody);

                        try{
                            arrayEventos = new JSONArray(str);

                            //Recorro la array y cada vez que haya el evento que me interesa incremento en uno su valor
                            for (int contador = 0; contador < arrayEventos.length(); contador++)
                            {
                                evento = arrayEventos.getJSONObject(contador);

                                if (evento.getString("NombreEvento").equals("Gol")){
                                    gol++;
                                }else if (evento.getString("NombreEvento").equals("GolPenalti")) {
                                    golPenalti++;
                                }else if (evento.getString("NombreEvento").equals("GolFalta")) {
                                    golFalta++;
                                }else if (evento.getString("NombreEvento").equals("GolPP")) {
                                    golPP++;
                                }else if (evento.getString("NombreEvento").equals("GolEnContra")) {
                                    golEnContra++;
                                }else if (evento.getString("NombreEvento").equals("Asistencia")) {
                                    asistencia++;
                                }else if (evento.getString("NombreEvento").equals("Tiro")) {
                                    tiro++;
                                }else if (evento.getString("NombreEvento").equals("TiroPuerta")) {
                                    tiroPuerta++;
                                }else if (evento.getString("NombreEvento").equals("Corner")) {
                                    corner++;
                                }else if (evento.getString("NombreEvento").equals("Pase")) {
                                    pase++;
                                }else if (evento.getString("NombreEvento").equals("PaseFallido")) {
                                    paseFallido++;
                                }else if (evento.getString("NombreEvento").equals("Robo")) {
                                    robo++;
                                }else if (evento.getString("NombreEvento").equals("Falta")) {
                                    falta++;
                                }else if (evento.getString("NombreEvento").equals("FueraJuego")) {
                                    fueraJuego++;
                                }else if (evento.getString("NombreEvento").equals("Mano")) {
                                    mano++;
                                }else if (evento.getString("NombreEvento").equals("TarjetaRoja")) {
                                    tarjetaRoja++;
                                }else if (evento.getString("NombreEvento").equals("TarjetaAmarilla")) {
                                    tarjetaAmarilla++;
                                }else if (evento.getString("NombreEvento").equals("Parada")) {
                                    parada++;
                                }else if (evento.getString("NombreEvento").equals("Cambio")) {
                                    cambio++;
                                }
                            }

                            //Cargo los datos recibidos en los textView
                            txtGol.setText("" + gol);
                            txtGolPenalti.setText("" + golPenalti);
                            txtGolFalta.setText("" + golFalta);
                            txtGolPP.setText("" + golPP);
                            txtGolEnContra.setText("" + golEnContra);
                            txtAsistencia.setText("" + asistencia);
                            txtTiro.setText("" + tiro);
                            txtTiroPuerta.setText("" + tiroPuerta);
                            txtCorner.setText("" + corner);
                            txtPase.setText("" + pase);
                            txtPaseFallido.setText("" + paseFallido);
                            txtRobo.setText("" + robo);
                            txtFalta.setText("" + falta);
                            txtFueraJuego.setText("" + fueraJuego);
                            txtMano.setText("" + mano);
                            txtTarjetaRoja.setText("" + tarjetaRoja);
                            txtTarjetaAmarilla.setText("" + tarjetaAmarilla);
                            txtParada.setText("" + parada);
                            txtCambio.setText("" + cambio);

                            miToolBar = (TextView)findViewById(R.id.toolbar_title);
                            CargarToolbar();
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(MostrarEstadisticasActivity.this, "Ha habido algun problema, comrpueba tu conexion a internet.", Toast.LENGTH_SHORT).show();

                    }
                });



    }

    public void CargarToolbar (){
        if (localOVisitante == true){
            miToolBar.setText(nombreMiEquipo + "(" + gol +") - (" + golEnContra +")" + nombreEquipoRival);
        } else {
            miToolBar.setText(nombreEquipoRival + "(" + golEnContra +") - (" + gol +")" + nombreMiEquipo);
        }
    }
}
