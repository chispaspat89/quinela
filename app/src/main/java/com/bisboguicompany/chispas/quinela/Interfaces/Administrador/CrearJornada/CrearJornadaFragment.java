package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorEquipos;
import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorJornadas;
import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.jornadas;
import com.bisboguicompany.chispas.quinela.Models.seleccionados;
import com.bisboguicompany.chispas.quinela.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */



@ActivityScoped
public class CrearJornadaFragment extends DaggerFragment implements CrearJornadaContract.View, AdapterView.OnItemSelectedListener {



    public static CrearJornadaActivity mactivity;
    FloatingActionButton btnFloat;
    AdaptadorJornadas adaptarJornadas;
    Spinner spinJornadas;
    jornadas jorourneySeleccionada;
    Spinner spin1,spin2,spin3,spin4,spin5,spin6,spin7,spin8,spin9,spin10,spin11,spin12,spin13,spin14,spin15,spin16,spin17,spin18;

    AdaptadorEquipos adapEquipo1,adaptEquip2;

    int idEquipo1,idEquipo2 = 0;
    Button btnCrear;

    @Inject
    CrearJornadaContract.Presenter mPresenter;
    ProgressDialog ringProgressDialog;

    List<Equipos> global =  new ArrayList<>();
    static List<Equipos>copiaGlobal =  new ArrayList<>();
    List<Integer> datos =  new ArrayList<>();

    int partido1E1,parido1E2 = 0;
    int partido2E1,parido2E2 = 0;
    int partido3E1,parido3E2 = 0;
    int partido4E1,parido4E2 = 0;
    int partido5E1,parido5E2 = 0;
    int partido6E1,parido6E2 = 0;
    int partido7E1,parido7E2 = 0;
    int partido8E1,parido8E2 = 0;
    int partido9E1,parido9E2 = 0;

    Equipos nuevo1,nuevo2;
    Equipos nuevo3,nuevo4;
    Equipos nuevo5,nuevo6;
    Equipos nuevo7,nuevo8;
    Equipos nuevo9,nuevo10;
    Equipos nuevo11,nuevo12;
    Equipos nuevo13,nuevo14;
    Equipos nuevo15,nuevo16;
    Equipos nuevo17,nuevo18;



    @Inject
    public CrearJornadaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Activity aa = getActivity();
        mactivity = (CrearJornadaActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Crear jornada");



        View v = inflater.inflate(R.layout.fragment_crear_jornada, container, false);
        spinJornadas =  v.findViewById(R.id.spinnerJorndas);
        btnCrear = v.findViewById(R.id.btnguardar);

        spin1 = v.findViewById(R.id.equipo1);
        spin2 = v.findViewById(R.id.equipo2);
        spin3 = v.findViewById(R.id.equipo3);
        spin4 = v.findViewById(R.id.equipo4);
        spin5 = v.findViewById(R.id.equipo5);
        spin6 = v.findViewById(R.id.equipo6);
        spin7 = v.findViewById(R.id.equipo7);
        spin8 = v.findViewById(R.id.equipo8);
        spin9 = v.findViewById(R.id.equipo9);
        spin10 = v.findViewById(R.id.equipo10);
        spin11 = v.findViewById(R.id.equipo11);
        spin12 = v.findViewById(R.id.equipo12);
        spin13 = v.findViewById(R.id.equipo13);
        spin14 = v.findViewById(R.id.equipo14);
        spin15 = v.findViewById(R.id.equipo15);
        spin16 = v.findViewById(R.id.equipo16);
        spin17 = v.findViewById(R.id.equipo17);
        spin18 = v.findViewById(R.id.equipo18);

        spinJornadas.setOnItemSelectedListener(this);


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray jsonArray =  new JSONArray();
                String mensaje = "";

            if(nuevo1 != null || nuevo2 != null){
                if(nuevo1.getIdteam() == nuevo2.getIdteam()){
                    mensaje = "En el partido 1 no puede jugar contra el mismo \n";
                }else{
                    JSONObject datos =  new JSONObject();

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date());

                        datos.put("id","0");
                        datos.put("id_journeys",jorourneySeleccionada.getIdjornada());
                        datos.put("id_team_local",partido1E1);
                        datos.put("id_team_visiting",parido1E2);
                        datos.put("match_date",currentDateandTime);

                        jsonArray.put(datos);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                mensaje = mensaje + "En el partido 1 debe de seleccionar los equipos para en encuentro. \n";
            }


            //********************************** Partido 2 **********************************************
                if(nuevo3 != null || nuevo4 != null){
                    if(nuevo3.getIdteam() == nuevo4.getIdteam()){
                        mensaje = mensaje + "En el partido 2 no puede jugar contra el mismo \n";
                    }else{
                        JSONObject datos =  new JSONObject();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            datos.put("id","0");
                            datos.put("id_journeys",jorourneySeleccionada.getIdjornada());
                            datos.put("id_team_local",partido2E1);
                            datos.put("id_team_visiting",parido2E2);
                            datos.put("match_date",currentDateandTime);

                            jsonArray.put(datos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    mensaje = mensaje + "En el partido 2 debe de seleccionar los equipos para en encuentro. \n";
                }

                //********************************** para el partido  3 **********************************************
                if(nuevo5 != null || nuevo6 != null){
                    if(nuevo5.getIdteam() == nuevo6.getIdteam()){
                        mensaje = mensaje + "En el partido 3 no puede jugar contra el mismo \n";
                    }else{
                        JSONObject datos =  new JSONObject();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            datos.put("id","0");
                            datos.put("id_journeys",jorourneySeleccionada.getIdjornada());
                            datos.put("id_team_local",partido3E1);
                            datos.put("id_team_visiting",parido3E2);
                            datos.put("match_date",currentDateandTime);

                            jsonArray.put(datos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    mensaje = mensaje +  "En el partido 3 debe de seleccionar los equipos para en encuentro. \n";
                }


                // int partido4E1,parido4E2 = 0;
                //******************************* para el partido 4 *********************************************
                if(nuevo7 != null || nuevo8 != null){
                    if(nuevo7.getIdteam() == nuevo8.getIdteam()){
                        mensaje =mensaje + "En el partido 4 no puede jugar contra el mismo \n";
                    }else{
                        JSONObject datos =  new JSONObject();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            datos.put("id","0");
                            datos.put("id_journeys",jorourneySeleccionada.getIdjornada());
                            datos.put("id_team_local",partido4E1);
                            datos.put("id_team_visiting",parido4E2);
                            datos.put("match_date",currentDateandTime);
                            jsonArray.put(datos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    mensaje = mensaje +"En el partido 4 debe de seleccionar los equipos para en encuentro. \n";
                }



                // int partido5E1,parido5E2 = 0;
                //***************************** para el partido  5 ************************************************
                if(nuevo9 != null || nuevo10 != null){
                    if(nuevo9.getIdteam() == nuevo10.getIdteam()){
                        mensaje = mensaje +"En el partido 5 no puede jugar contra el mismo \n";
                    }else{
                        JSONObject datos =  new JSONObject();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            datos.put("id","0");
                            datos.put("id_journeys",jorourneySeleccionada.getIdjornada());
                            datos.put("id_team_local",partido5E1);
                            datos.put("id_team_visiting",parido5E2);
                            datos.put("match_date",currentDateandTime);
                            jsonArray.put(datos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    mensaje = mensaje + "En el partido 5 debe de seleccionar los equipos para en encuentro. \n";
                }

                //int partido6E1,parido6E2 = 0;
                ///******************************* para el partido 6 ******************************************
                if(nuevo11 != null || nuevo12 != null){
                    if(nuevo11.getIdteam() == nuevo12.getIdteam()){
                        mensaje = mensaje +"En el partido 6 no puede jugar contra el mismo \n";
                    }else{
                        JSONObject datos =  new JSONObject();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            datos.put("id","0");
                            datos.put("id_journeys",jorourneySeleccionada.getIdjornada());
                            datos.put("id_team_local",partido6E1);
                            datos.put("id_team_visiting",parido6E2);
                            datos.put("match_date",currentDateandTime);
                            jsonArray.put(datos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    mensaje = mensaje + "En el partido 6 debe de seleccionar los equipos para en encuentro. \n";
                }


                //int partido7E1,parido7E2 = 0;
                //********************************* para el partido 7 ************************************************
                if(nuevo13 != null || nuevo14 != null){
                    if(nuevo13.getIdteam() == nuevo14.getIdteam()){
                        mensaje = mensaje + "En el partido 7 no puede jugar contra el mismo \n";
                    }else{
                        JSONObject datos =  new JSONObject();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            datos.put("id","0");
                            datos.put("id_journeys",jorourneySeleccionada.getIdjornada());
                            datos.put("id_team_local",partido7E1);
                            datos.put("id_team_visiting",parido7E2);
                            datos.put("match_date",currentDateandTime);
                            jsonArray.put(datos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    mensaje = mensaje +"En el partido 7 debe de seleccionar los equipos para en encuentro. \n";
                }


                //int partido8E1,parido8E2 = 0;
                //***************************** pqrtido 8 ************************************************

                if(nuevo15 != null || nuevo16 != null){
                    if(nuevo15.getIdteam() == nuevo16.getIdteam()){
                        mensaje = mensaje + "En el partido 8 no puede jugar contra el mismo \n";
                    }else{
                        JSONObject datos =  new JSONObject();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            datos.put("id","0");
                            datos.put("id_journeys",jorourneySeleccionada.getIdjornada());
                            datos.put("id_team_local",partido8E1);
                            datos.put("id_team_visiting",parido8E2);
                            datos.put("match_date",currentDateandTime);
                            jsonArray.put(datos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    mensaje = mensaje + "En el partido 8 debe de seleccionar los equipos para en encuentro. \n";
                }




                // int partido9E1,parido9E2 = 0;
            // ************************************ pqrtido 9 ***************************************************
                if(nuevo17 != null || nuevo18 != null){
                    if(nuevo17.getIdteam() == nuevo18.getIdteam()){
                        mensaje = mensaje + "En el partido 9 no puede jugar contra el mismo \n";
                    }else{
                        JSONObject datos =  new JSONObject();

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            datos.put("id","0");
                            datos.put("id_journeys",jorourneySeleccionada.getIdjornada());
                            datos.put("id_team_local",partido9E1);
                            datos.put("id_team_visiting",parido9E2);
                            datos.put("match_date",currentDateandTime);
                            jsonArray.put(datos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    mensaje = mensaje + "En el partido 9 debe de seleccionar los equipos para en encuentro. \n";
                }



                // final
                if(jsonArray.length() == 9){

                    mPresenter.guardarPartidos(jsonArray);
                }else{
                    mensajeError(mensaje);
                }
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
    public void ExitoJornadas(List<jornadas> listJornadas) {
        spinJornadas.setAdapter(null);


        List<jornadas> infos =  new ArrayList<>();
        jornadas primero =  new jornadas(0,"Seleccionar jornadas",0,0,0,0,0);
        infos.add(primero);

        for(int  i = 0 ; i < listJornadas.size(); i++){
            infos.add(listJornadas.get(i));
        }

            adaptarJornadas =  new AdaptadorJornadas(getActivity(), infos);
            adaptarJornadas.notifyDataSetChanged();
            spinJornadas.setAdapter(adaptarJornadas);
    }

    @Override
    public void ErrorJorndas(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ExitoEquipos(List<Equipos> listEquipos) {

        Equipos primero =  new Equipos(0,"Seleccionar un equipo",0,"");
        global.add(primero);
        copiaGlobal.add(primero);

        for(int  i = 0; i < listEquipos.size(); i++){
            global.add(listEquipos.get(i));
            copiaGlobal.add(listEquipos.get(i));
        }


        adapEquipo1 = new AdaptadorEquipos(getActivity(), copiaGlobal);
        adapEquipo1.notifyDataSetChanged();

        spin1.setAdapter(adapEquipo1);
        spin2.setAdapter(adapEquipo1);
        spin3.setAdapter(adapEquipo1);
        spin4.setAdapter(adapEquipo1);
        spin5.setAdapter(adapEquipo1);
        spin6.setAdapter(adapEquipo1);
        spin7.setAdapter(adapEquipo1);
        spin8.setAdapter(adapEquipo1);
        spin9.setAdapter(adapEquipo1);
        spin10.setAdapter(adapEquipo1);
        spin11.setAdapter(adapEquipo1);
        spin12.setAdapter(adapEquipo1);
        spin13.setAdapter(adapEquipo1);
        spin14.setAdapter(adapEquipo1);
        spin15.setAdapter(adapEquipo1);
        spin16.setAdapter(adapEquipo1);
        spin17.setAdapter(adapEquipo1);
        spin18.setAdapter(adapEquipo1);


        spin1.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);
        spin3.setOnItemSelectedListener(this);
        spin4.setOnItemSelectedListener(this);
        spin5.setOnItemSelectedListener(this);
        spin6.setOnItemSelectedListener(this);
        spin7.setOnItemSelectedListener(this);
        spin8.setOnItemSelectedListener(this);
        spin9.setOnItemSelectedListener(this);
        spin10.setOnItemSelectedListener(this);
        spin11.setOnItemSelectedListener(this);
        spin12.setOnItemSelectedListener(this);
        spin13.setOnItemSelectedListener(this);
        spin14.setOnItemSelectedListener(this);
        spin15.setOnItemSelectedListener(this);
        spin16.setOnItemSelectedListener(this);
        spin17.setOnItemSelectedListener(this);
        spin18.setOnItemSelectedListener(this);

        spin1.setSelection(0);
        spin2.setSelection(0);
        spin3.setSelection(0);
        spin4.setSelection(0);
        spin5.setSelection(0);
        spin6.setSelection(0);
        spin7.setSelection(0);
        spin8.setSelection(0);
        spin9.setSelection(0);
        spin10.setSelection(0);
        spin11.setSelection(0);
        spin12.setSelection(0);
        spin13.setSelection(0);
        spin14.setSelection(0);
        spin15.setSelection(0);
        spin16.setSelection(0);
        spin17.setSelection(0);
        spin18.setSelection(0);

    }

    @Override
    public void ErroEquipos(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void mensajecargando() {
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
    public void cancelarmensajecargando() {
        if(ringProgressDialog != null){

            ringProgressDialog.dismiss();
        }
    }

    @Override
    public void ExitoCreacion() {
        mensajeError("Se agregaron los partidos a la jornada.");

        spin1.setSelection(0);
        spin2.setSelection(0);
        spin3.setSelection(0);
        spin4.setSelection(0);
        spin5.setSelection(0);
        spin6.setSelection(0);
        spin7.setSelection(0);
        spin8.setSelection(0);
        spin9.setSelection(0);
        spin10.setSelection(0);
        spin11.setSelection(0);
        spin12.setSelection(0);
        spin13.setSelection(0);
        spin14.setSelection(0);
        spin15.setSelection(0);
        spin16.setSelection(0);
        spin17.setSelection(0);
        spin18.setSelection(0);


         nuevo1 = null;
         nuevo2= null;
       nuevo3 = null;
       nuevo4 = null;
     nuevo5 = null;
     nuevo6 = null;
       nuevo7 = null ;
       nuevo8 = null;
         nuevo9=null;
         nuevo10 = null;
         nuevo11 = null;
         nuevo12 = null;
         nuevo13 = null;
         nuevo14 = null;
         nuevo15 = null;
         nuevo16 = null;
         nuevo17 = null;
         nuevo18 = null;

         mPresenter.obtenerJOrnadasActivas();
    }

    @Override
    public void ErrorCreacion(String mensaje) {
            mensajeError(mensaje);
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




    Equipos nuevoAnterior;
    Equipos nuevoAnterior2;
    Equipos nuevoAnterior3;
    Equipos nuevoAnterior4;
    Equipos nuevoAnterior5;
    Equipos nuevoAnterior6;
    Equipos nuevoAnterior7;
    Equipos nuevoAnterior8;
    Equipos nuevoAnterior9;
    Equipos nuevoAnterior10;
    Equipos nuevoAnterior11;
    Equipos nuevoAnterior12;
    Equipos nuevoAnterior13;
    Equipos nuevoAnterior14;
    Equipos nuevoAnterior15;
    Equipos nuevoAnterior16;
    Equipos nuevoAnterior17;
    Equipos nuevoAnterior18;


    List<seleccionados> listEspecial =  new ArrayList<>();

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinnerJorndas:
                if (position > 0 ){
                    jorourneySeleccionada  = (jornadas) spinJornadas.getAdapter().getItem(position);
                }else{
                    jorourneySeleccionada = null;
                }
                break;
            //patido 1
            case R.id.equipo1:
                if (position > 0 ){
                    Equipos nuevo   = (Equipos)spin1.getAdapter().getItem(position);
                    partido1E1      = nuevo.getIdteam();
                    nuevo1          = (Equipos)spin1.getAdapter().getItem(position);

                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (partido1E1 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 1){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,1,partido1E1);
                        listEspecial.add(n);


                        Log.d("EquipoSeleccionado","--->" + nuevo1.getNombre());
                    }else{
                        seleccionados n =  new seleccionados(0,1,partido1E1);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo1.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }

                        Log.d("EquipoSeleccionado","--->" + nuevo1.getNombre());
                    }
                }else{
                    partido1E1 = 0;
                    nuevo1     = null;
                }
                break;
            case R.id.equipo2:
                if (position > 0 ){
                        Equipos nue2 = (Equipos) spin2.getAdapter().getItem(position);
                        parido1E2 = nue2.getIdteam();
                        nuevo2 = (Equipos) spin2.getAdapter().getItem(position);


                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (parido1E2 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 2){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,2,parido1E2);
                        listEspecial.add(n);

                        Log.d("EquipoSeleccionado","--->" + nuevo2.getNombre());

                    }else{
                        seleccionados n =  new seleccionados(0,2,parido1E2);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo2.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }

                        Log.d("EquipoSeleccionado","--->" + nuevo2.getNombre());
                    }

                }else{
                    parido1E2 = 0;
                    nuevo2 = null;
                }
                break;

            //partido numero 2

            case R.id.equipo3:
                if (position > 0 ){

                    Equipos nuev3 = (Equipos) spin3.getAdapter().getItem(position);
                    partido2E1 = nuev3.getIdteam();
                    //nuevo3,nuevo4;
                    nuevo3 = nuev3;


                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (partido2E1 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 3){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,3,partido2E1);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,3,partido2E1);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo3.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }


                    }
                }else{

                    partido2E1 = 0;

                }
                break;
            case R.id.equipo4:
                if (position > 0 ){
                    Equipos nuev4 = (Equipos) spin4.getAdapter().getItem(position);
                    parido2E2 = nuev4.getIdteam();
                    nuevo4 = nuev4;



                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (parido2E2 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 4){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,4,parido2E2);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,4,parido2E2);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo4.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }


                    }

                }else{
                    parido2E2 = 0;
                    nuevo4 = null;
                }
                break;

            //partido numero 3
            case R.id.equipo5:
                if (position > 0 ){
                    Equipos nuev5 = (Equipos) spin5.getAdapter().getItem(position);

                    partido3E1 = nuev5.getIdteam();
                    //partido3E1,parido3E2
                    nuevo5 = nuev5;



                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (partido3E1 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 5){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,5,partido3E1);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,5,partido3E1);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo5.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }


                    }





                }else{
                    partido3E1 = 0;
                    nuevo5 = null;
                }
                break;
            case R.id.equipo6:
                if (position > 0 ){

                    Equipos nuev6 = (Equipos) spin6.getAdapter().getItem(position);
                    parido3E2 = nuev6.getIdteam();
                    nuevo6 = nuev6;

                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (parido3E2 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 6){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,6,parido3E2);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,6,parido3E2);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo6.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }

                }else{
                    parido3E2 = 0;
                    nuevo6 = null;
                }
                break;

            //partido numero 4
            case R.id.equipo7:
                if (position > 0 ){

                    Equipos nuev7 = (Equipos) spin7.getAdapter().getItem(position);
                    partido4E1 = nuev7.getIdteam();
                    //partido4E1,parido4E2
                    nuevo7 =nuev7;



                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (partido4E1 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 7){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,7,partido4E1);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,7,partido4E1);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo7.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }

                }else{
                    partido4E1 = 0;
                    nuevo7 = null;
                }
                break;
            case R.id.equipo8:
                if (position > 0 ){

                    Equipos nuevo81 = (Equipos) spin8.getAdapter().getItem(position);
                    parido4E2 = nuevo81.getIdteam();

                    nuevo8 = nuevo81;


                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (parido4E2 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 8){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,8,parido4E2);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,8,parido4E2);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo8.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }



                }else{
                    parido4E2 = 0;
                    nuevo8 = null;

                }
                break;

            //partido numero 5
            case R.id.equipo9:
                if (position > 0 ){
                    Equipos nuev8 = (Equipos) spin9.getAdapter().getItem(position);
                    partido5E1 = nuev8.getIdteam();
                    // partido5E1,parido5E2
                    nuevo9 = nuevo8;


                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (partido5E1 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 9){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,9,partido5E1);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,9,partido5E1);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo9.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }






                }else{
                    nuevo9 = null;
                    partido5E1 = 0;
                }
                break;
            case R.id.equipo10:
                if (position > 0 ){
                    Equipos nuev9 = (Equipos) spin10.getAdapter().getItem(position);
                    parido5E2 = nuev9.getIdteam();
                    nuevo10 = nuev9;




                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (parido5E2 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 10){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,10,parido5E2);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,10,parido5E2);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo10.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }






                }else{
                    parido5E2 = 0;
                    nuevo10= null;

                }
                break;

            //partido numero 6
            case R.id.equipo11:
                if (position > 0 ){
                    Equipos nuev11 = (Equipos) spin11.getAdapter().getItem(position);
                    partido6E1  = nuev11.getIdteam();
                    nuevo11 = nuev11;
                    // partido6E1,parido6E2




                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (partido6E1 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 11){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,11,partido6E1);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,11,partido6E1);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo11.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }

                }else{
                    partido6E1 = 0;
                    nuevo11 = null;
                }
                break;
            case R.id.equipo12:
                if (position > 0 ){
                    Equipos nuev12 = (Equipos) spin12.getAdapter().getItem(position);
                    parido6E2 = nuev12.getIdteam();


                    nuevo12 = nuev12;


                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (parido6E2 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 12){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,12,parido6E2);
                        listEspecial.add(n);



                    }else{
                        seleccionados n =  new seleccionados(0,12,parido6E2);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo12.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }



                }else{

                    nuevo12 = null;
                    parido6E2= 0;
                }
                break;

            //partido numero 7
            case R.id.equipo13:
                if (position > 0 ){
                    Equipos nuev13  = (Equipos) spin13.getAdapter().getItem(position);
                    partido7E1      = nuev13.getIdteam();
                    nuevo13         = (Equipos) spin13.getAdapter().getItem(position);



                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (partido7E1 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 13){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,13,partido7E1);
                        listEspecial.add(n);
                    }else{
                        seleccionados n =  new seleccionados(0,13,partido7E1);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo13.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }




                }else{
                    partido7E1 = 0;
                    nuevo13 = null;
                }
                break;
            case R.id.equipo14:
                if (position > 0 ){

                    Equipos nuev14 = (Equipos) spin14.getAdapter().getItem(position);
                    parido7E2 = nuev14.getIdteam();
                    Log.d("partido72","---->" + parido7E2);
                    nuevo14  = (Equipos) spin14.getAdapter().getItem(position);


                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (parido7E2 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 14){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,14,parido7E2);
                        listEspecial.add(n);
                    }else{
                        seleccionados n =  new seleccionados(0,14,parido7E2);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo14.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }










                }else{
                    nuevo14 = null;
                    parido7E2 = 0;
                }
                break;

            //partido numero 8
            case R.id.equipo15:
                if (position > 0 ){
                    Equipos nuev15 = (Equipos) spin15.getAdapter().getItem(position);
                    partido8E1 = nuev15.getIdteam();

                    nuevo15 = nuev15;
                    //partido8E1,parido8E2



                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (partido8E1 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 15){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,15,partido8E1);
                        listEspecial.add(n);
                    }else{
                        seleccionados n =  new seleccionados(0,15,partido8E1);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo15.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }


                }else{
                    nuevo15 = null;
                    partido8E1 = 0;
                }
                break;
            case R.id.equipo16:
                if (position > 0 ){

                    Equipos nuev16 = (Equipos) spin16.getAdapter().getItem(position);
                    parido8E2 = nuev16.getIdteam();

                    nuevo16 = nuev16;





                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (parido8E2 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 16){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,16,parido8E2);
                        listEspecial.add(n);
                    }else{
                        seleccionados n =  new seleccionados(0,16,parido8E2);
                        listEspecial.add(n);

                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (nuevo16.getIdteam() == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                    }




                }else{
                    nuevo16 = null;
                    parido8E2 =0;

                }
                break;

            //partido numero 9
            case R.id.equipo17:
                if (position > 0 ){
                    Equipos nuev17 = (Equipos) spin17.getAdapter().getItem(position);
                    partido9E1 = nuev17.getIdteam();

                    nuevo17 = nuev17;




                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (partido9E1 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 17){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,17,partido9E1);
                        listEspecial.add(n);
                    }else {
                        seleccionados n = new seleccionados(0, 17, partido9E1);
                        listEspecial.add(n);

                        for (int i = 0; i < copiaGlobal.size(); i++) {
                            if (nuevo17.getIdteam() == copiaGlobal.get(i).getIdteam()) {
                                copiaGlobal.remove(i);
                            }
                        }

                    }

                }else{

                    nuevo17 = null;
                    partido9E1 = 0;
                }
                break;
            case R.id.equipo18:
                if (position > 0 ){
                    Equipos nuev18 = (Equipos) spin18.getAdapter().getItem(position);
                    parido9E2 = nuev18.getIdteam();

                    nuevo18 = nuev18;





                    if (listEspecial.size() > 0){
                        for (int  i = 0; i < copiaGlobal.size(); i++){
                            if (parido9E2 == copiaGlobal.get(i).getIdteam()){
                                copiaGlobal.remove(i);
                            }
                        }
                        for (int i = 0; i < listEspecial.size(); i++){
                            if (listEspecial.get(i).getIdspin() == 18){
                                for (int b =0 ; b < global.size(); b++){
                                    if (listEspecial.get(i).getIdequipo() == global.get(b).getIdteam()){
                                        copiaGlobal.add(global.get(b));
                                    }
                                }
                                listEspecial.remove(i);
                                break;
                            }
                        }

                        seleccionados n =  new seleccionados(0,18,parido9E2);
                        listEspecial.add(n);
                    }else {
                        seleccionados n = new seleccionados(0, 18, parido9E2);
                        listEspecial.add(n);

                        for (int i = 0; i < copiaGlobal.size(); i++) {
                            if (nuevo18.getIdteam() == copiaGlobal.get(i).getIdteam()) {
                                copiaGlobal.remove(i);
                            }
                        }

                    }



                }else{
                    nuevo18 = null;
                    parido9E2 = 0;
                }
                break;
        }


    }


    public void eliminar(){


    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
