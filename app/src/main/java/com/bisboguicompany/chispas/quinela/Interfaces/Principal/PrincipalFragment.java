package com.bisboguicompany.chispas.quinela.Interfaces.Principal;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorJornadas;
import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorListJornadas;
import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorListResultados;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil.EditarPerfilActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas.EquiposJornadasActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.ResultadosJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseActivity;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;
import com.bisboguicompany.chispas.quinela.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalFragment  extends DaggerFragment implements PrincipalContract.View,View.OnClickListener, View.OnKeyListener {



    public static PrincipalActivity mactivity;
    Button btneditar;

    @Inject
    PrincipalContract.Presenter mPresenter;
    TextView txtchivita, txtGrande;
    AdaptadorListJornadas adaptarJornadas;
    ListView listJor,listJorResulado;
    TextView txtel, txtmail;
    TextView txtNombreUsuario,txtNombre;
    AdaptadorListResultados adaptadorResultados;

    @Inject
    public PrincipalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_principal, container, false);
        final View hView    = PrincipalActivity.navigationView.getHeaderView(0);

        txtNombreUsuario    = (TextView)hView.findViewById(R.id.txtNombreCompletePaciente);
        txtNombre           = (TextView)hView.findViewById(R.id.txtestado);




        txtchivita = v.findViewById(R.id.txtchivita);
        txtGrande = v.findViewById(R.id.txtgrande);
        listJor = v.findViewById(R.id.listJornadas);
        listJorResulado = v.findViewById(R.id.listJornadasR);
        txtel = v.findViewById(R.id.txttelefono);
        txtmail = v.findViewById(R.id.txtmail);



        Resources res = getResources();
        final TabHost tabs = (TabHost) v.findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("1");
        //spec.setContent(R.id.tab1Vi);
        //spec.setIndicator("Perfil",R.drawable.profile);
        tabs.addTab( tabs.newTabSpec("1")
                .setIndicator(createTabIndicator(inflater, tabs, R.string.pruebas, R.drawable.profile))
                .setContent(R.id.tab1Vi));

        tabs.getTabWidget().getChildAt(0).setBackgroundColor(getResources().getColor(R.color.color_transparant));
        TextView tv =  (TextView) tabs.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv.setAllCaps(false);
        //spec = tabs.newTabSpec("2");
        //spec.setContent(R.id.tab2Vi);
        //spec.setIndicator("Jornadas",getResources().getDrawable(R.drawable.journey));
        tabs.addTab(tabs.newTabSpec("2")
                .setIndicator(createTabIndicator(inflater, tabs, R.string.jornadas, R.drawable.journey))
                .setContent(R.id.tab2Vi));

        tabs.getTabWidget().getChildAt(1).setBackgroundColor(getResources().getColor(R.color.color_transparant));
        TextView tv2 =  (TextView) tabs.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv2.setAllCaps(false);
        //spec = tabs.newTabSpec("3");
        //spec.setContent(R.id.tab3Vi);
        //spec.setIndicator("Resultados",getResources().getDrawable(R.drawable.results));
        tabs.addTab(tabs.newTabSpec("3")
                .setIndicator(createTabIndicator(inflater, tabs, R.string.resultados, R.drawable.results))
                .setContent(R.id.tab3Vi));

        tabs.getTabWidget().getChildAt(2).setBackgroundColor(getResources().getColor(R.color.color_transparant));
        TextView tv3 =  (TextView) tabs.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        tv3.setAllCaps(false);


        tabs.setCurrentTab(0);

        tabs.getTabWidget().getChildAt(tabs.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.colorSobraOrange)); // selected


        //metodo que obtiene la posocion del tab
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                // Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
                int tab = tabs.getCurrentTab();
                if(tabId.equals("1")){



                    tabs.setCurrentTab(0);

                }else if(tabId.equals("2")){


                    tabs.setCurrentTab(1);
                }
                else if(tabId.equals("3")) {



                    tabs.setCurrentTab(2);

                }


                for(int i=0;i<tabs.getTabWidget().getChildCount();i++)
                {
                    tabs.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.color_transparant));
                   // TextView tv =  (TextView) tabs.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    //tv.setAllCaps(false);
                }
                tabs.getTabWidget().getChildAt(tabs.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.colorSobraOrange)); // selected
            }
        });


        listJor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                jornadas datos = (jornadas)listJor.getAdapter().getItem(position);


                if(datos.getStatus() == 0){
                    mensajeError("La quinela se encuentra cerrada.");
                }else{
                    Intent equipos =  new Intent(getActivity(), EquiposJornadasActivity.class);
                    equipos.putExtra("jornada",datos.getIdjornada());
                    startActivity(equipos);
                }

            }
        });


        listJorResulado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jornadas datos = (jornadas)listJor.getAdapter().getItem(position);

                Intent datosj =  new Intent(getActivity(), ResultadosJornadaActivity.class);
                datosj.putExtra("jornada",datos.getIdjornada());
                startActivity(datosj);

            }
        });

        //**************************** Final de los Tabs ********************************
        return  v;
    }


    public View createTabIndicator(LayoutInflater inflater, TabHost tabHost, int textResource, int iconResource) {
        View tabIndicator = inflater.inflate(R.layout.tab_indicator, tabHost.getTabWidget(), false);
        ((TextView) tabIndicator.findViewById(android.R.id.title)).setText(textResource);
        ((ImageView) tabIndicator.findViewById(android.R.id.icon)).setImageResource(iconResource);
        return tabIndicator;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Bind view to the presenter which will signal for the presenter to load the task.
        mPresenter.takeView(this);


    }

    @Override
    public void onPause() {
        mPresenter.dropView();
        super.onPause();
    }
    //metodo que muestra el mensaje el error si se produce alguno al enviar el usuario y contraseña
    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Jornadas");
            mdialogBuilder.setMessage(mensaje);

            mdialogBuilder.setNegativeButton("Aceptar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            if (malertDialog == null) {
                malertDialog = mdialogBuilder.create();
                malertDialog.show();
            }

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){



        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void cerrar() {

    }

    @Override
    public void ExitoCreditos(List<Creditos> list) {
       // txtchivita.setText(String.valueOf(chivita));
        //txtGrande.setText(String.valueOf(grande));

        for (int i = 0 ; i < list.size(); i++){

            if (list.get(i).getTipo() ==1){
                txtchivita.setText(String.valueOf(list.get(i).getCantidadchica()));
            }else if (list.get(i).getTipo() ==2){
                txtGrande.setText(String.valueOf(list.get(i).getCantidadgrande()));
            }
        }


    }



    @Override
    public void ExitoJornadas(List<jornadas> listJornadas) {
        List<jornadas> infos =  new ArrayList<>();
        for(int  i = 0 ; i < listJornadas.size(); i++){
            infos.add(listJornadas.get(i));
        }

        adaptarJornadas =  new AdaptadorListJornadas(getActivity(), infos);
        adaptarJornadas.notifyDataSetChanged();
        listJor.setAdapter(adaptarJornadas);


        adaptadorResultados =  new AdaptadorListResultados(getActivity(), infos);
        adaptadorResultados.notifyDataSetChanged();
        listJorResulado.setAdapter(adaptadorResultados);
    }

    @Override
    public void ErrorJorndas(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ExitoUsuario(Usuario datos) {

        txtel.setText(datos.getTelefono());
        txtmail.setText(datos.getCorreo());

        txtNombreUsuario.setText(datos.getNombreUsuario());
        txtNombre.setText(datos.getNombre());
    }
}
