package com.example.morga.myteam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class FragmentEquipo extends Fragment {

    ListView lstEquipo;

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

    }

    //*****
    // Metodo para Cambiar de Activity
    //*****
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Voy a añadir los items de la lista
        Equipo[] datos = new Equipo[]{
                new Equipo("Real Betis", "Primera Division", "real_betis"),
        };

        MiAdapter adapterEquipo = new MiAdapter(getActivity(), datos);

        lstEquipo = (ListView) view.findViewById(R.id.listViewEquipo);

        lstEquipo.setAdapter(adapterEquipo);

        // Este metodo es para saber en que item de la lista han clicado y muestra un toast montrandolo
        lstEquipo.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                // Procedo a coger el titulo del elemento de la lista seleccionado,
                // y a enviarlo a la activity de pregunta para cargar la pregunta
                String tituloMonumentoSeleccionado = ((Equipo)a.getItemAtPosition(position)).getNombreEquipo();
                String imagenMonumentoSeleccionado = ((Equipo)a.getItemAtPosition(position)).getLogoEquipo();


                Bundle bundle = new Bundle();

                bundle.putString("titulo",tituloMonumentoSeleccionado);
                bundle.putString("imagen",imagenMonumentoSeleccionado);

                Intent intent = new Intent(getActivity(), EditarActivity.class);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipo, container, false);
    }

    //*****
    //Metodo que hace de adapter
    //*****
    public class MiAdapter extends ArrayAdapter<Equipo> {

        // Creamos una array de tipo Titular
        Equipo[] datos;

        public MiAdapter(Context context, Equipo[] datos) {
            //Lo primero que encontramos es el constructor
            // para nuestro adaptador, al que sólo pasaremos el contexto (que normalmente
            // será la actividad desde la que se crea el adaptador) y el array de datos a
            // mostrar, que en nuestro caso es un array de objetos de tipo Titular. En este
            // constructor tan sólo llamaremos al constructor padre tal como ya vimos al
            // principio de este artículo, pasándole nuestros dos parámetros (contexto y
            // datos) y el ID del layout que queremos utilizar (en nuestro caso el nuevo
            // que hemos creado, listitem_titular).
            super(context, R.layout.lista_equipo, datos);
            this.datos = datos;
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
            lblTitulo.setText(datos[position].getNombreEquipo());

            TextView lblSubtitulo = (TextView) item.findViewById(R.id.textViewCategoriaEquipo);
            lblSubtitulo.setText(datos[position].getCategoriaEquipo());

            //Imagen
            ImageView imagen = (ImageView) item.findViewById(R.id.imageViewLogoEquipo);

            String nombre=(datos[position].getLogoEquipo());

            switch (nombre) {
                case "real_betis":
                    imagen.setImageResource(R.drawable.real_betis);
                    break;



            }

            return (item);
        }
    }
}
