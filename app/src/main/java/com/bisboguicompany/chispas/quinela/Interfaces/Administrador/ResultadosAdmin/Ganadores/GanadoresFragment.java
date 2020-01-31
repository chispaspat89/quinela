package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.Ganadores;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorGanadores;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.AgregarResultados.AgregarResultadoContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminActivity;
import com.bisboguicompany.chispas.quinela.Models.Ganador;
import com.bisboguicompany.chispas.quinela.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GanadoresFragment extends DaggerFragment implements GanadoresContract.View  {

    @Inject
    GanadoresContract.Presenter mPresenter;

    ListView listadoGanadores;
    AdaptadorGanadores adaptadorGanador;
    ProgressDialog ringProgressDialog;
    int idjornada = 0;

    FloatingActionButton btnFloat;
    TextView txtgrande, txtxchivita, txtgeneral;
    Button btngrande,btnchivita;




    @Inject
    public GanadoresFragment() {
        // Required empty public constructor
    }
    public static GanadoresActivity mactivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {
            idjornada = 0;
        } else {
            idjornada = extras.getInt("jornada");
            mPresenter.obtenerResultados(idjornada);
        }

        Activity aa = getActivity();
        mactivity = (GanadoresActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Ganadores de la apuesta.");



        View v              = inflater.inflate(R.layout.fragment_ganadores, container, false);
        txtgeneral          = v.findViewById(R.id.txtGeneral);
        txtgrande           = v.findViewById(R.id.txtGrande);
        txtxchivita         = v.findViewById(R.id.txtChivita);
        btngrande           = v.findViewById(R.id.btnGenerarGrande);
        btnchivita          = v.findViewById(R.id.btnGenerarChivita);



        //metodo para generar el pdf de la grande
        btngrande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarTablas(String.valueOf(2),"Grande");
            }
        });


        //metodo para generar el pdg de la chivita
        btnchivita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarTablas(String.valueOf(1),"Chivita");
            }
        });



        return v;
    }
    public void limpiarTablas(String tipo,String nombre){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("¿Esta seguro en generar el reporte de ganadores de " + nombre + "?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mPresenter.generarDocumento(String.valueOf(idjornada), tipo);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
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
    public void ExitoResultados(List<Ganador> listadosGanadores) {

        txtgeneral.setText(String.valueOf(listadosGanadores.get(0).getTotalgenera()));
        txtgrande.setText(String.valueOf(listadosGanadores.get(0).getTotalgrande()));
        txtxchivita.setText( String.valueOf( listadosGanadores.get(0).getTotalchivita()));

    }

    @Override
    public void ErrorResultados(String mensaje) {
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
    public void cancelarmensaje() {
        if(ringProgressDialog != null){
            ringProgressDialog.dismiss();
        }
    }

    @Override
    public void ExitoGenerado(String mensaje) {

        Log.d("ExitoGenerado","----> " + mensaje);
        new DownloadFile().execute(mensaje);
        // mensajeError(mensaje);
    }

    @Override
    public void ErrorGenerado(String mensaje) {
        mensajeError(mensaje);
    }


    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Resultados");
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

    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(getActivity());
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {

                Log.d("HoliDescarga","--->"+f_url[0] );

                URL url = new URL("https://"+f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();


                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                //Extract file name from URL
                //fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());

                //Append timestamp to file name
                fileName = timestamp+"_"+String.valueOf(idjornada)+"_jornadad.pdf";

                //External directory path to save file
                folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GrandeMX";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                /*
                *  File myDir = new File(root + "/Apicultores/apiarios");
                    myDir.mkdirs();

                * */

                if (!directory.exists()) {
                    directory.mkdirs();
                    Log.d("creandoFolder","NoExiste");
                }else{
                    Log.d("creandoFolder","Exite");
                }

                File file = new File(folder, fileName);
                if (file.exists()) file.delete();
                try {
                    Log.d("CreandoFile","Creando el archivo");

                    // Output stream to write file
                    OutputStream output = new FileOutputStream(folder +"/"+fileName);

                    byte data[] = new byte[1024];

                    long total = 0;

                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        // After this onProgressUpdate will be called
                        publishProgress("" + (int) ((total * 100) / lengthOfFile));
                        // writing data to file
                        output.write(data, 0, count);
                    }

                    // flushing output
                    output.flush();

                    // closing streams
                    output.close();
                    input.close();
                } catch (Exception e) {

                    Log.d("ErrorCreando","----> " + e.toString());
                    e.printStackTrace();
                }

                return "Descargado en: " + folder + "/"+fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            //https://grandemxpost.000webhostapp.com/pdf/reportebyjornada.pdf
            return "Se produjo un error durante la obtención del archivo";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();

            // Display File path after downloading
            Toast.makeText(getActivity().getApplicationContext(),
                    message, Toast.LENGTH_LONG).show();
        }
    }
}
