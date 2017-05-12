package com.example.morga.myteam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class FragmentEquipo extends Fragment implements View.OnClickListener {

    ListView lstEquipo;
    String token;
    Button btnCrearPartido;

    public FragmentEquipo() {
        // Required empty public constructor
    }

    public static FragmentEquipo newInstance() {
        FragmentEquipo fragment = new FragmentEquipo();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        token = MainActivity.DevolverToken();



    }

    //--------------------------------
    // Metodo para Cambiar de Activity
    //--------------------------------
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //Boton que abre una activity para crear un equipo
        btnCrearPartido = (Button)view.findViewById(R.id.buttonCrearEquipo);
        btnCrearPartido.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CrearEquipoActivity.class);
                startActivity(i);

            }
        });


        // Aqui hacemos el get de todos los equipos del usuario
        AsyncHttpClient client = new AsyncHttpClient();

        client.addHeader("Token", token);
        // Generamos la URL
        client.get("http://apimyfootballteamnuevawebapp.azurewebsites.net/API/Equipos",  new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //int idEquipo = -1;

                List<Equipo> listEquipos = new ArrayList<Equipo>();

                JSONObject temps = null;
                JSONArray arrayEquipos = new JSONArray();
                JSONObject equipo = new JSONObject();

                String str = new String(responseBody);

                try
                {
                    //Declaramos un objeto de la clase JSONArray y metemos la respuesta para despues
                    // recorrer todos los objetos del Json e ir metiendolos en un objeto de la clase
                    // Equipo y cargarlos en el listView
                    arrayEquipos = new JSONArray(str);

                    for (int contador = 0; contador < arrayEquipos.length(); contador++)
                    {
                        equipo = arrayEquipos.getJSONObject(contador);

                        Equipo obEquipo = new Equipo();
                        obEquipo.setIdEquipo(equipo.getInt("IdEquipo"));
                        obEquipo.setNombreEquipo(equipo.getString("NombreEquipo"));
                        obEquipo.setDireccion(equipo.getString("Direccion"));
                        obEquipo.setCategoria(equipo.getString("Categoria"));
                        obEquipo.setFotoEscudo(equipo.getString("FotoEscudo"));
                        obEquipo.setUsuario_IdUsuario(equipo.getInt("Usuario_IdUsuario"));

                        listEquipos.add(obEquipo);
                    }
                    MiAdapter adapterEquipo = new MiAdapter(getActivity(), listEquipos);

                    lstEquipo = (ListView) view.findViewById(R.id.listViewEquipo);
                    lstEquipo.setAdapter(adapterEquipo);

                    //---------------------------------------------------------------------------------------------
                    // Este metodo es para saber en que item de la lista han clicado y muestra un toast montrandolo
                    //---------------------------------------------------------------------------------------------
                    lstEquipo.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position, long id)
                        {
                            // Procedo a coger el titulo del elemento de la lista seleccionado,
                            // y a enviarlo a la activity de pregunta para cargar la pregunta
                            int idEquipoSeleccionado = ((Equipo)a.getItemAtPosition(position)).getIdEquipo();
                            String nombreEquipoSeleccionado = ((Equipo)a.getItemAtPosition(position)).getNombreEquipo();
                            //String imagenMonumentoSeleccionado = ((Equipo)a.getItemAtPosition(position)).getLogoEquipo();

                            Bundle bundle = new Bundle();

                            bundle.putInt("idEquipo",idEquipoSeleccionado);
                            bundle.putString("nombreEquipo", nombreEquipoSeleccionado);

                            Intent intent = new Intent(getActivity(), PartidoConfiguracionActivity.class);

                            intent.putExtras(bundle);

                            startActivity(intent);
                        }
                    });

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(getActivity(), "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipo, container, false);

    }

    @Override
    public void onClick(View v) {

    }

    //*****
    //Metodo que hace de adapter
    //*****
    public class MiAdapter extends ArrayAdapter<Equipo> {

        // Creamos una array de tipo Titular
        //List<Equipo> datos;
        List<Equipo> datos;

        public MiAdapter(Context context, List<Equipo> equipo) {
            //Lo primero que encontramos es el constructor
            // para nuestro adaptador, al que sólo pasaremos el contexto (que normalmente
            // será la actividad desde la que se crea el adaptador) y el array de datos a
            // mostrar, que en nuestro caso es un array de objetos de tipo Titular. En este
            // constructor tan sólo llamaremos al constructor padre tal como ya vimos al
            // principio de este artículo, pasándole nuestros dos parámetros (contexto y
            // datos) y el ID del layout que queremos utilizar (en nuestro caso el nuevo
            // que hemos creado, listitem_titular).
            super(context, R.layout.lista_equipo, (List<Equipo>) equipo);

            this.datos = equipo;

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
            View item = inflater.inflate(R.layout.lista_equipo, null);


            // Tras esto, tan sólo tendremos que obtener la referencia a cada una
            // de nuestras etiquetas como siempre lo hemos hecho y asignar su texto
            // correspondiente según los datos de nuestro array y la posición del
            // elemento actual (parámetro position del método getView()).
            TextView lblTitulo = (TextView) item.findViewById(R.id.textViewNombreEquipo);
            lblTitulo.setText(datos.get(position).getNombreEquipo());

            TextView lblSubtitulo = (TextView) item.findViewById(R.id.textViewCategoriaEquipo);
            lblSubtitulo.setText(datos.get(position).getCategoria());



            return (item);
        }
    }
}
