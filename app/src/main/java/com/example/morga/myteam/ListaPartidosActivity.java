package com.example.morga.myteam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.ParseException;

public class ListaPartidosActivity extends AppCompatActivity {

    ListView lstPartidos;
    TextView miToolBar;
    String nombreEquipo;
    int idEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_partidos);

        Bundle b = getIntent().getExtras();

        idEquipo = b.getInt("idEquipo");
        nombreEquipo = b.getString("nombreEquipo");

        miToolBar = (TextView)findViewById(R.id.toolbar_title);
        miToolBar.setText("Lista de partidos del "+ nombreEquipo);

        // Aqui hacemos el get de todos los equipos del usuario
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("idEquipo", idEquipo);
        client.addHeader("Token", MainActivity.DevolverToken());
        // Generamos la URL
        client.get("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Partidos",params,  new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                List<Partido> listPartidos = new ArrayList<Partido>();

                JSONArray arrayPartidos = new JSONArray();
                JSONObject partido = new JSONObject();

                String str = new String(responseBody);
                try {
                    //Declaramos un objeto de la clase JSONArray y metemos la respuesta para despues
                    // recorrer todos los objetos del Json e ir metiendolos en un objeto de la clase
                    // Equipo y cargarlos en el listView
                    arrayPartidos = new JSONArray(str);

                    for (int contador = 0; contador < arrayPartidos.length(); contador++)
                    {
                        partido = arrayPartidos.getJSONObject(contador);

                        Partido obPartido = new Partido();
                        obPartido.setIdPartido(partido.getInt("IdPartido"));
                        obPartido.setFechaPartido(partido.getString("FechaPartido"));
                        obPartido.setJornada(partido.getInt("Jornada"));
                        obPartido.setRival(partido.getString("Rival"));
                        obPartido.setLocal(partido.getBoolean("Local"));
                        obPartido.setEquipo_IdEquipo(partido.getInt("Equipo_IdEquipo"));

                        listPartidos.add(obPartido);
                    }
                    MiAdapter adapterPartido = new MiAdapter(ListaPartidosActivity.this, listPartidos);

                    lstPartidos = (ListView)findViewById(R.id.listViewPartidos);
                    lstPartidos.setAdapter(adapterPartido);


                    //---------------------------------------------------------------------------------------------
                    // Este metodo es para saber en que item de la lista han clicado y muestra un toast montrandolo
                    //---------------------------------------------------------------------------------------------
                    lstPartidos.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position, long id)
                        {
                            // Procedo a coger el titulo del elemento de la lista seleccionado,
                            // y a enviarlo a la activity de pregunta para cargar la pregunta
                            int idPartidoSeleccionado = ((Partido)a.getItemAtPosition(position)).getIdPartido();
                            String nombreEquipoRivalSeleccionado = ((Partido)a.getItemAtPosition(position)).getRival();
                            boolean localOVisitante = ((Partido)a.getItemAtPosition(position)).getLocal();


                            Bundle bundle = new Bundle();

                            bundle.putInt("idPartido",idPartidoSeleccionado );
                            bundle.putString("nombreEquipoRival", nombreEquipoRivalSeleccionado);
                            bundle.putBoolean("localOVisitante", localOVisitante);
                            bundle.putString("nombreMiEquipo", nombreEquipo);

                            Intent intent = new Intent(ListaPartidosActivity.this, MostrarEstadisticasActivity.class);

                            intent.putExtras(bundle);

                            startActivity(intent);
                        }
                    });



                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(ListaPartidosActivity.this, "Ha habido algun problema, comrpueba tu conexion a internet.", Toast.LENGTH_SHORT).show();

            }
        });
    }


    //*****
    //Metodo que hace de adapter
    //*****
    public class MiAdapter extends ArrayAdapter<Partido> {

        // Creamos una array de tipo Titular

        List<Partido> datos;

        public MiAdapter(Context context, List<Partido> partido) {
            //Lo primero que encontramos es el constructor
            // para nuestro adaptador, al que sólo pasaremos el contexto (que normalmente
            // será la actividad desde la que se crea el adaptador) y el array de datos a
            // mostrar, que en nuestro caso es un array de objetos de tipo Titular. En este
            // constructor tan sólo llamaremos al constructor padre tal como ya vimos al
            // principio de este artículo, pasándole nuestros dos parámetros (contexto y
            // datos) y el ID del layout que queremos utilizar (en nuestro caso el nuevo
            // que hemos creado, listitem_titular).
            super(context, R.layout.lista_partido, (List<Partido>) partido);

            this.datos = partido;

        }

        //Redefinimos el método encargado de generar y rellenar con nuestros datos todos
        // los controles necesarios de la interfaz gráfica de cada elemento de la lista.
        // Este método es getView().
        // El método getView() se llamará cada vez que haya que
        // mostrar un elemento de la lista.
        public View getView(int position, View convertView, ViewGroup parent) {
            // Lo primero que debe hacer es “inflar” el layout XML que hemos creado.
            // Esto consiste en consultar el XML de nuestro layout y crear e inicializar
            // la estructura de objetos java equivalente. Para ello, crearemos un nuevo
            // objeto LayoutInflater y generaremos la estructura de objetos mediante su
            // método inflate(id_layout).
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.lista_partido, null);


            // Tras esto, tan sólo tendremos que obtener la referencia a cada una
            // de nuestras etiquetas como siempre lo hemos hecho y asignar su texto
            // correspondiente según los datos de nuestro array y la posición del
            // elemento actual (parámetro position del método getView()).

            Partido pt=(Partido)datos.get(position);

            TextView textViewNombreEquipoRival = (TextView) item.findViewById(R.id.textViewNombreRival);


            textViewNombreEquipoRival.setText(datos.get(position).getRival());

            TextView textViewJornada = (TextView) item.findViewById(R.id.textViewJornadaa);

            textViewJornada.setText(""+datos.get(position).getJornada());

            ////////////Puede que esto pete
            TextView textViewFecha = (TextView) item.findViewById(R.id.textViewFecha);
            String fecha = datos.get(position).getFechaPartido();
            fecha = fecha.substring(0, fecha.length()-12);
            textViewFecha.setText(fecha);

            TextView textViewLocal = (TextView) item.findViewById(R.id.textViewLocal);
            Boolean casa = datos.get(position).getLocal();
            if (casa == true) {
                textViewLocal.setText("Casa");
            }
            else if (casa == false){
                textViewLocal.setText("Visitante");
            }



            return (item);
        }
    }
}
