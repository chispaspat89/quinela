package com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorCreditos;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.NuevoVendedor.NuevoVendedorActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.NuevoVendedor.NuevoVendedorContract;
import com.bisboguicompany.chispas.quinela.Models.Apuesta;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EquiposJornadasFragment extends DaggerFragment implements EquiposJornadasContract.View,View.OnClickListener {

    public static EquiposJornadasActivity mactivity;


    @Inject
    EquiposJornadasContract.Presenter mPresenter;

    Button btnRegistro;
    EditText txtnombre, txtnombreUsuario, txttelefono, txtcorreo, txtpass1,txtpass2;

    ProgressDialog ringProgressDialog;


    Button btnP1B1,btnP1B2,btnP1B3;

    int clavejornada;

    LinearLayout ln1,ln2,ln3,ln4,ln5,ln6,ln7,ln8,ln9;
    ImageView mgP1E1,mg2P1E2;
    ImageView mgP2E1,mg2P2E2;
    ImageView mgP3E1,mg2P3E2;
    ImageView mgP4E1,mg2P4E2;
    ImageView mgP5E1,mg2P5E2;
    ImageView mgP6E1,mg2P6E2;
    ImageView mgP7E1,mg2P7E2;
    ImageView mgP8E1,mg2P8E2;
    ImageView mgP9E1,mg2P9E2;


    Button btnP1E1, btnP1E2,btnP1E3;
    Button btnP2E1, btnP2E2,btnP2E3;
    Button btnP3E1, btnP3E2,btnP3E3;
    Button btnP4E1, btnP4E2,btnP4E3;
    Button btnP5E1, btnP5E2,btnP5E3;
    Button btnP6E1, btnP6E2,btnP6E3;
    Button btnP7E1, btnP7E2,btnP7E3;
    Button btnP8E1, btnP8E2,btnP8E3;
    Button btnP9E1, btnP9E2,btnP9E3;


    int apuesta1,apuesta2,apuesta3,apuesta4,apuesta5,apuesta6,apuesta7,apuesta8,apuesta9 = 0;
    Spinner spinTipoCredito;
    AdaptadorCreditos adaptadorCreditos;
    int tipoCreditoSeleccionado = 0;
    Button btnGuardarApuesta;

    List<Apuesta> generalApuestas =  new ArrayList<>();
    Usuario datosUsuario;
    int finalizado = 0;


    @Inject
    public EquiposJornadasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {
            clavejornada = 0;
        } else {
            clavejornada = extras.getInt("jornada");
            mPresenter.obtenerPartidos(clavejornada);
        }


        Activity aa = getActivity();
        mactivity = (EquiposJornadasActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Partidos de la jornada");

        // Inflate the layout for this fragment
        View v           =  inflater.inflate(R.layout.fragment_equipos_jornadas, container, false);
        spinTipoCredito  = v.findViewById(R.id.spinnerApuestas);
        btnGuardarApuesta = v.findViewById(R.id.btnGuardar);

        ln1    =  v.findViewById(R.id.lyGroup);
        ln2    =  v.findViewById(R.id.lyGroup2);
        ln3    = v.findViewById(R.id.lyGroup3);
        ln4    = v.findViewById(R.id.lyGroup4);
        ln5    = v.findViewById(R.id.lyGroup5);
        ln6    = v.findViewById(R.id.lyGroup6);
        ln7    = v.findViewById(R.id.lyGroup7);
        ln8    = v.findViewById(R.id.lyGroup8);
        ln9    = v.findViewById(R.id.lyGroup9);


        ln1.setVisibility(View.GONE);
        ln2.setVisibility(View.GONE);
        ln3.setVisibility(View.GONE);
        ln4.setVisibility(View.GONE);
        ln5.setVisibility(View.GONE);
        ln6.setVisibility(View.GONE);
        ln7.setVisibility(View.GONE);
        ln8.setVisibility(View.GONE);
        ln9.setVisibility(View.GONE);


        //partido 1 botones
        btnP1B1 = v.findViewById(R.id.button);
        btnP1B2  = v.findViewById(R.id.button2);
        btnP1B3 = v.findViewById(R.id.button3);

        //partido 2 botones
        btnP2E1 =  v.findViewById(R.id.btnP2E1);
        btnP2E2 = v.findViewById(R.id.btnEmpP2);
        btnP2E3= v.findViewById(R.id.btnP2E3);

        //partido 3 botones
        btnP3E1 = v.findViewById(R.id.btnP3E1);
        btnP3E2 = v.findViewById(R.id.btnEmpP3);
        btnP3E3 = v.findViewById(R.id.btnP3E3);

        //partido 4 botones
        btnP4E1 =  v.findViewById(R.id.btnP4E1);
        btnP4E2 = v.findViewById(R.id.btnEmpP4);
        btnP4E3 = v.findViewById(R.id.btnP4E3);

        //partido 5 botones
        btnP5E1 =  v.findViewById(R.id.btnP5E1);
        btnP5E2 = v.findViewById(R.id.btnEmpP5);
        btnP5E3 = v.findViewById(R.id.btnP5E3);

        //partido 6 botones
        btnP6E1 =  v.findViewById(R.id.btnP6E1);
        btnP6E2 = v.findViewById(R.id.btnEmpP6);
        btnP6E3 = v.findViewById(R.id.btnP6E3);

        //partidos 7 botones
        btnP7E1 =  v.findViewById(R.id.btnP7E1);
        btnP7E2 = v.findViewById(R.id.btnEmpP7);
        btnP7E3 = v.findViewById(R.id.btnP7E3);

        //partidos 8 botones
        btnP8E1 = v.findViewById(R.id.btnP8E1);
        btnP8E2 = v.findViewById(R.id.btnEmpP8);
        btnP8E3= v.findViewById(R.id.btnP8E3);

        //partidos 9 botones
        btnP9E1 = v.findViewById(R.id.btnP9E1);
        btnP9E2 = v.findViewById(R.id.btnEmpP9);
        btnP9E3 = v.findViewById(R.id.btnP9E3);


        //partido1
        mgP1E1  = v.findViewById(R.id.imgP1E1);
        mg2P1E2 = v.findViewById(R.id.img2P1E2);

        //partido2
        mgP2E1 = v.findViewById(R.id.imgP2E1);
        mg2P2E2 = v.findViewById(R.id.imgP2E3);

        //partido3
        mgP3E1 = v.findViewById(R.id.imgP3E1);
        mg2P3E2 = v.findViewById(R.id.imgP3E3);

        //partido4
        mgP4E1 = v.findViewById(R.id.imgP4E1);
        mg2P4E2 = v.findViewById(R.id.imgP4E3);

        //partido5
        mgP5E1 = v.findViewById(R.id.imgP5E1);
        mg2P5E2 = v.findViewById(R.id.imgP5E3);

        //partido6
        mgP6E1 = v.findViewById(R.id.imgP6E1);
        mg2P6E2 = v.findViewById(R.id.imgP6E3);

        //partido7
        mgP7E1 = v.findViewById(R.id.imgP7E1);
        mg2P7E2 = v.findViewById(R.id.imgP7E3);

        //partido8
        mgP8E1 = v.findViewById(R.id.imgP8E1);
        mg2P8E2 = v.findViewById(R.id.imgP8E3);

        //partido9
        mgP9E1 = v.findViewById(R.id.imgP9E1);
        mg2P9E2 = v.findViewById(R.id.imgP9E3);

        btnP1B1.setOnClickListener(this);
        btnP1B2.setOnClickListener(this);
        btnP1B3.setOnClickListener(this);


        btnP2E1.setOnClickListener(this);
        btnP2E2.setOnClickListener(this);
        btnP2E3.setOnClickListener(this);

        //partido 3 botones
        btnP3E1.setOnClickListener(this);
        btnP3E2.setOnClickListener(this);
        btnP3E3.setOnClickListener(this);

        //partido 4 botones
        btnP4E1.setOnClickListener(this);
        btnP4E2.setOnClickListener(this);
        btnP4E3.setOnClickListener(this);

        //partido 5 botones
        btnP5E1.setOnClickListener(this);
        btnP5E2.setOnClickListener(this);
        btnP5E3.setOnClickListener(this);

        //partido 6 botones
        btnP6E1.setOnClickListener(this);
        btnP6E2.setOnClickListener(this);
        btnP6E3.setOnClickListener(this);

        //partidos 7 botones
        btnP7E1.setOnClickListener(this);
        btnP7E2.setOnClickListener(this);
        btnP7E3.setOnClickListener(this);

        //partidos 8 botones
        btnP8E1.setOnClickListener(this);
        btnP8E2.setOnClickListener(this);
        btnP8E3.setOnClickListener(this);

        //partidos 9 botones
        btnP9E1.setOnClickListener(this);
        btnP9E2.setOnClickListener(this);
        btnP9E3.setOnClickListener(this);

        btnGuardarApuesta.setOnClickListener(this);

        spinTipoCredito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){

                    tipoCreditoSeleccionado = 0;
                }else{

                     Creditos selec             = (Creditos)spinTipoCredito.getAdapter().getItem(position);
                     tipoCreditoSeleccionado    = selec.getIdcredito();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return v;
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


    @Override
    public void onClick(View v) {
        Button defbtn=new Button(getActivity());

        switch (v.getId()){
            case R.id.button:
                btnP1B1.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP1B2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP1B3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta1 = 1;
                break;
            case R.id.button2:
                btnP1B2.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP1B1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP1B3.setBackgroundColor(Color.parseColor("#D7D7D7"));
                apuesta1 = 2;
                break;
            case  R.id.button3:
                btnP1B3.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP1B2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP1B1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                apuesta1 = 3;
                break;
                //******* partido 2

            case R.id.btnP2E1:
                btnP2E1.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP2E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP2E3.setBackgroundColor(Color.parseColor("#D7D7D7"));
                apuesta2 = 1;
                break;
            case R.id.btnEmpP2:

                btnP2E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP2E2.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP2E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta2 = 2;
                break;
            case R.id.btnP2E3:
                btnP2E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP2E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP2E3.setBackgroundColor(Color.parseColor("#4FC3F7"));

                apuesta2 = 3;

                break;
                //para el partido 3

            case R.id.btnP3E1:
                btnP3E1.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP3E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP3E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta3 =1;

                break;
            case R.id.btnEmpP3:
                btnP3E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP3E2.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP3E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta3 = 2;

                break;
            case R.id.btnP3E3:
                btnP3E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP3E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP3E3.setBackgroundColor(Color.parseColor("#4FC3F7"));

                apuesta3 = 3;

                break;

                //para el partido 4
            case R.id.btnP4E1:
                btnP4E1.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP4E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP4E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta4 = 1;


                break;
            case R.id.btnEmpP4:
                btnP4E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP4E2.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP4E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta4 = 2;

                break;
            case R.id.btnP4E3:
                btnP4E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP4E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP4E3.setBackgroundColor(Color.parseColor("#4FC3F7"));

                apuesta4 = 3;

                break;

                //para el partido 5
            case R.id.btnP5E1:

                btnP5E1.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP5E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP5E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta5 = 1;

                break;
            case R.id.btnEmpP5:
                btnP5E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP5E2.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP5E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta5 = 2;

                break;
            case R.id.btnP5E3:
                btnP5E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP5E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP5E3.setBackgroundColor(Color.parseColor("#4FC3F7"));

                apuesta5 = 3;

                break;
                //para el partido 6
            case R.id.btnP6E1:

                btnP6E1.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP6E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP6E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta6 = 1;

                break;
            case R.id.btnEmpP6:
                btnP6E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP6E2.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP6E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta6 = 2;
                break;
            case R.id.btnP6E3:
                btnP6E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP6E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP6E3.setBackgroundColor(Color.parseColor("#4FC3F7"));

                apuesta6 = 3;
                break;

                //para el partido 7
            case R.id.btnP7E1:
                btnP7E1.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP7E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP7E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta7 = 1;
                break;
            case R.id.btnEmpP7:
                btnP7E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP7E2.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP7E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta7 = 2;

                break;
            case R.id.btnP7E3:
                btnP7E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP7E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP7E3.setBackgroundColor(Color.parseColor("#4FC3F7"));

                apuesta7 = 3;
                break;
                //para el partido 8
            case R.id.btnP8E1:
                btnP8E1.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP8E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP8E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta8 = 1;

                break;
            case R.id.btnEmpP8:
                btnP8E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP8E2.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP8E3.setBackgroundColor(Color.parseColor("#D7D7D7"));
                apuesta8 = 2;

                break;
            case R.id.btnP8E3:
                btnP8E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP8E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP8E3.setBackgroundColor(Color.parseColor("#4FC3F7"));

                apuesta8 = 3;

                break;
                //para el partido 9

            case R.id.btnP9E1:
                btnP9E1.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP9E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP9E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta9 = 1;
                break;

            case R.id.btnEmpP9:
                btnP9E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP9E2.setBackgroundColor(Color.parseColor("#4FC3F7"));
                btnP9E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

                apuesta9 = 2;
                break;
            case R.id.btnP9E3:
                btnP9E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP9E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
                btnP9E3.setBackgroundColor(Color.parseColor("#4FC3F7"));

                apuesta9 = 3;
                break;
            case R.id.btnGuardar:

                if(tipoCreditoSeleccionado == 0){

                    mensajeError("Debe de seleccionar el tipo de credito que desea apostar.");
                }else{

                    if (apuesta1 > 0 && apuesta2 > 0 && apuesta3 > 0 && apuesta4 > 0 && apuesta5 > 0 && apuesta6 > 0 && apuesta7 > 0 && apuesta8 > 0 && apuesta9 > 0){

                        JSONObject juego1 = new JSONObject();
                        JSONObject juego2 = new JSONObject();
                        JSONObject juego3 = new JSONObject();
                        JSONObject juego4 = new JSONObject();
                        JSONObject juego5 = new JSONObject();
                        JSONObject juego6 = new JSONObject();
                        JSONObject juego7 = new JSONObject();
                        JSONObject juego8 = new JSONObject();
                        JSONObject juego9 = new JSONObject();
                        JSONArray finalApuesta =  new JSONArray();


                        JSONObject complet =  new JSONObject();

                        try {
                            juego1.put("id",0);
                            juego1.put("id_matches",generalApuestas.get(0).getPartidos());
                            juego1.put("response",apuesta1);



                            //partido 2
                            juego2.put("id",0);
                            juego2.put("id_matches",generalApuestas.get(1).getPartidos());
                            juego2.put("response",apuesta2);

                            //partido 3
                            juego3.put("id",0);
                            juego3.put("id_matches",generalApuestas.get(2).getPartidos());
                            juego3.put("response",apuesta3);

                            //partido 4
                            juego4.put("id",0);
                            juego4.put("id_matches",generalApuestas.get(3).getPartidos());
                            juego4.put("response",apuesta4);

                            //partido5
                            juego5.put("id",0);
                            juego5.put("id_matches",generalApuestas.get(4).getPartidos());
                            juego5.put("response",apuesta5);


                            //partido 6
                            juego6.put( "id",0);
                            juego6.put( "id_matches",generalApuestas.get(5).getPartidos());
                            juego6.put( "response",apuesta6);


                            //partido 7
                            juego7.put("id",0);
                            juego7.put("id_matches",generalApuestas.get(6).getPartidos());
                            juego7.put("response",apuesta7);

                            //partudi 8
                            juego8.put("id",0);
                            juego8.put("id_matches",generalApuestas.get(7).getPartidos());
                            juego8.put("response",apuesta8);


                            //patido 9
                            juego9.put("id",0);
                            juego9.put("id_matches",generalApuestas.get(8).getPartidos());
                            juego9.put("response",apuesta9);

                            finalApuesta.put(juego1);
                            finalApuesta.put(juego2);
                            finalApuesta.put(juego3);
                            finalApuesta.put(juego4);
                            finalApuesta.put(juego5);
                            finalApuesta.put(juego6);
                            finalApuesta.put(juego7);
                            finalApuesta.put(juego8);
                            finalApuesta.put(juego9);

                            complet.put("id_users",datosUsuario.getUsuarioId());
                            complet.put("id_credits",tipoCreditoSeleccionado);
                            complet.put("id_journeys",clavejornada);
                            complet.put("matchs",finalApuesta);


                            mPresenter.guardarApuesta(complet);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }else{
                        mensajeError("Debe de seleccionar las 9 opciones de los 9 partidos.");
                    }
                }

                break;
        }
    }

    @Override
    public void ExitoPartidos(List<Partidos> listpartidos) {

        mPresenter.obtenrEquipos(listpartidos);
    }

    @Override
    public void ErrorPartidos(String mensaje) {
        ln1.setVisibility(View.GONE);
        ln2.setVisibility(View.GONE);
        ln3.setVisibility(View.GONE);
        ln4.setVisibility(View.GONE);
        ln5.setVisibility(View.GONE);
        ln6.setVisibility(View.GONE);
        ln7.setVisibility(View.GONE);
        ln8.setVisibility(View.GONE);
        ln9.setVisibility(View.GONE);

        mensajeError(mensaje);
    }

    @Override
    public void ExitoEquipos(List<Partidos> listpartidos, List<Equipos> listEquipos) {

        List<Apuesta> listadoApuestas =  new ArrayList<>();
        String url = "";
        String url2 = "";
        String nombre1 = "";
        String nombre2 = "";

        int contador = 0;
        for (int i = 0 ; i < listpartidos.size(); i++){

            for (int j = 0 ; j  < listEquipos.size(); j++){

                if (listpartidos.get(i).getIdEquipoLocal() == listEquipos.get(j).getIdteam()){
                    contador = contador + 1;

                    url = listEquipos.get(j).getLogo();
                    nombre1 = listEquipos.get(j).getNombre();

                }else if (listpartidos.get(i).getIdEquipoVisitante() == listEquipos.get(j).getIdteam()){
                    contador = contador + 1;

                    url2 = listEquipos.get(j).getLogo();
                    nombre2 = listEquipos.get(j).getNombre();
                }

                if(contador == 2){
                    break;
                }
            }

            if(contador == 2){

                if(listpartidos.get(i).getStatus() == 0){
                    finalizado = 1;
                }


                Apuesta apos  =  new Apuesta(0,
                        listpartidos.get(i).getIdjornada(),
                        listpartidos.get(i).getIdEquipoLocal(),
                        listpartidos.get(i).getIdEquipoVisitante(),
                        listpartidos.get(i).getFecha(),
                        listpartidos.get(i).getStatus(),
                        listpartidos.get(i).getPartidos(),
                        0,
                        url,
                        url2,
                        nombre1,
                        nombre2);


                listadoApuestas.add(apos);
                generalApuestas.add(apos);

                url  = "";
                url2 = "";
                nombre1 = "";
                nombre2 = "";
                contador = 0;
            }

        }




        if(listadoApuestas.size() > 0){

            for(int a = 0 ; a < listadoApuestas.size(); a++){

                if(a == 0){

                    //partido 1
                    btnP1B1.setText(listadoApuestas.get(a).getNombrelocal());
                    btnP1B3.setText(listadoApuestas.get(a).getNombrevisitante());
                    ln1.setVisibility(View.VISIBLE);

                    Picasso.get().load(listadoApuestas.get(a).getUrlLocal()).into(mgP1E1);
                    Picasso.get().load(listadoApuestas.get(a).getUrlVisitante()).into(mg2P1E2);



                }else if(a == 1){

                    //partido 2
                    ln2.setVisibility(View.VISIBLE);

                    btnP2E1.setText(listadoApuestas.get(a).getNombrelocal());
                    btnP2E3.setText(listadoApuestas.get(a).getNombrevisitante());


                    Picasso.get().load(listadoApuestas.get(a).getUrlLocal()).into(mgP2E1);
                    Picasso.get().load(listadoApuestas.get(a).getUrlVisitante()).into(mg2P2E2);
                }else if(a == 2){

                    //partido 3
                    ln3.setVisibility(View.VISIBLE);

                    btnP3E1.setText(listadoApuestas.get(a).getNombrelocal());
                    btnP3E3.setText(listadoApuestas.get(a).getNombrevisitante());



                    Picasso.get().load(listadoApuestas.get(a).getUrlLocal()).into(mgP3E1);
                    Picasso.get().load(listadoApuestas.get(a).getUrlVisitante()).into(mg2P3E2);
                }else if(a == 3){

                    //partido 4
                    ln4.setVisibility(View.VISIBLE);

                    btnP4E1.setText(listadoApuestas.get(a).getNombrelocal());
                    btnP4E3.setText(listadoApuestas.get(a).getNombrevisitante());


                    Picasso.get().load(listadoApuestas.get(a).getUrlLocal()).into(mgP4E1);
                    Picasso.get().load(listadoApuestas.get(a).getUrlVisitante()).into(mg2P4E2);
                }else if(a == 4){
                    //partido 5

                    ln5.setVisibility(View.VISIBLE);
                    btnP5E1.setText(listadoApuestas.get(a).getNombrelocal());
                    btnP5E3.setText(listadoApuestas.get(a).getNombrevisitante());


                    Picasso.get().load(listadoApuestas.get(a).getUrlLocal()).into(mgP5E1);
                    Picasso.get().load(listadoApuestas.get(a).getUrlVisitante()).into(mg2P5E2);
                }else if(a == 5){

                    //partido 6
                    ln6.setVisibility(View.VISIBLE);
                    btnP6E1.setText(listadoApuestas.get(a).getNombrelocal());
                    btnP6E3.setText(listadoApuestas.get(a).getNombrevisitante());


                    Picasso.get().load(listadoApuestas.get(a).getUrlLocal()).into(mgP6E1);
                    Picasso.get().load(listadoApuestas.get(a).getUrlVisitante()).into(mg2P6E2);
                }else if(a == 6){

                    //partido 7
                    ln7.setVisibility(View.VISIBLE);

                    btnP7E1.setText(listadoApuestas.get(a).getNombrelocal());
                    btnP7E3.setText(listadoApuestas.get(a).getNombrevisitante());

                    Picasso.get().load(listadoApuestas.get(a).getUrlLocal()).into(mgP7E1);
                    Picasso.get().load(listadoApuestas.get(a).getUrlVisitante()).into(mg2P7E2);
                }else if(a == 7){

                    //partido 8
                    ln8.setVisibility(View.VISIBLE);

                    btnP8E1.setText(listadoApuestas.get(a).getNombrelocal());
                    btnP8E3.setText(listadoApuestas.get(a).getNombrevisitante());

                    Picasso.get().load(listadoApuestas.get(a).getUrlLocal()).into(mgP8E1);
                    Picasso.get().load(listadoApuestas.get(a).getUrlVisitante()).into(mg2P8E2);
                }else if(a == 8){

                    //partido 9
                    ln9.setVisibility(View.VISIBLE);

                    btnP9E1.setText(listadoApuestas.get(a).getNombrelocal());
                    btnP9E3.setText(listadoApuestas.get(a).getNombrevisitante());

                    Picasso.get().load(listadoApuestas.get(a).getUrlLocal()).into(mgP9E1);
                    Picasso.get().load(listadoApuestas.get(a).getUrlVisitante()).into(mg2P9E2);
                }

            }


        }

    }



    @Override
    public void ErrorEquipos(String mensaje) {
        mensajeError(mensaje);
    }



    @Override
    public void ExitoCreditos(List<Creditos> list) {

        List<Creditos> listCreditos =  new ArrayList<>();

        Creditos nuevo =  new Creditos(0,0,0,0,"Seleccionar credito",0,0);
        listCreditos.add(nuevo);

        for (int i = 0 ; i < list.size(); i++){
            listCreditos.add(list.get(i));
        }

        adaptadorCreditos = new AdaptadorCreditos(getActivity(),listCreditos);
        adaptadorCreditos.notifyDataSetChanged();
        spinTipoCredito.setAdapter(adaptadorCreditos);
    }

    @Override
    public void ErrorCreditos(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ExitoUsuario(Usuario user) {
            datosUsuario = user;
    }

    @Override
    public void ExitoCrearApuesta(String mensaje) {

        spinTipoCredito.setSelection(0);
        tipoCreditoSeleccionado = 0;

        apuesta1 = 0;
        apuesta2 = 0;
        apuesta3 = 0;
        apuesta4 = 0;
        apuesta5 = 0;
        apuesta6 = 0;
        apuesta7 = 0;
        apuesta8 = 0;
        apuesta9 = 0;


        //partido 1
        btnP1B1.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP1B2.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP1B3.setBackgroundColor(Color.parseColor("#D7D7D7"));


        //partido 2
        btnP2E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP2E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP2E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

        //partido 3
        btnP3E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP3E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP3E3.setBackgroundColor(Color.parseColor("#D7D7D7"));


        //partido 4
        btnP4E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP4E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP4E3.setBackgroundColor(Color.parseColor("#D7D7D7"));


        //partido 5
        btnP5E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP5E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP5E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

        //partido 6
        btnP6E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP6E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP6E3.setBackgroundColor(Color.parseColor("#D7D7D7"));


        //partido 7
        btnP7E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP7E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP7E3.setBackgroundColor(Color.parseColor("#D7D7D7"));


        //partido 8
        btnP8E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP8E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP8E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

        //partido 9
        btnP9E1.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP9E2.setBackgroundColor(Color.parseColor("#D7D7D7"));
        btnP9E3.setBackgroundColor(Color.parseColor("#D7D7D7"));

        mensajeError(mensaje);
    }

    @Override
    public void ErrorCrearApuesta(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void cargarmensaje() {
        ringProgressDialog = ProgressDialog.show(getActivity(), "", "Espere un momento ...", true);
        ringProgressDialog.setCancelable(true);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        ringProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_BACK && !event.isCanceled()) {
                    if(ringProgressDialog.isShowing()) {
                        //your logic here for back button pressed event
                    }
                    return true;
                }
                return false;
            }
        });

        ringProgressDialog.onStart();
    }

    @Override
    public void cancelarmensaje() {
        if(ringProgressDialog != null){

            ringProgressDialog.dismiss();
        }
    }


    //metodo que muestra el mensaje el error si se produce alguno al enviar el usuario y contraseña
    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Partidos");
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
}
