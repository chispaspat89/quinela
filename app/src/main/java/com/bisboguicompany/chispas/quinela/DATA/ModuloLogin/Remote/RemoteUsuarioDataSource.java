package com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Remote;


import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bisboguicompany.chispas.quinela.DATA.ConstanteServidor;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Models.Clientes;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Ganador;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.ResultJornada;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.bets;
import com.bisboguicompany.chispas.quinela.Models.jornadas;
import com.bisboguicompany.chispas.quinela.Utils.AppExecutors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

@Singleton
public class RemoteUsuarioDataSource implements UsuarioDataSource {

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    //private final static Map<String, Usuario> TASKS_SERVICE_DATA;
    //private final static Map<String, User> TASKS_SERVICE_DATA2;


    private final AppExecutors mAppExecutors;

    Handler myHandlerResumen;
    static boolean stop = false;
    static String pacienteId;


    @Inject
    public RemoteUsuarioDataSource(@NonNull AppExecutors executors) {

        mAppExecutors = executors;
    }




    @Override
    public void autentificacion(@NonNull String user, @NonNull String pass, @NonNull validacionCallBack callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        JSONObject perfil = new JSONObject();
        try {
            perfil.put("username",user);
            perfil.put("password",pass);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), perfil.toString());
        retrofit2.Call<ResponseBody> req = service.signIn(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);

                            try {

                                //Log.d("Response", "" + json.getString("Nombre"));
                                List<Usuario> data = new ArrayList<>();

                                if (json.has("name")) {


                                    String idpersona           = json.getString("id");
                                    String rol         = json.getString("id_roles");
                                    String nombre            = json.getString("name");
                                    String usuarios    = json.getString("username");
                                    String tele   = json.getString("phone");
                                    String correo          = json.getString("email");


                                    Usuario informacionUsuario =  new Usuario(0,
                                            nombre,
                                            "",
                                            "",
                                            usuarios,
                                            correo,
                                            rol,
                                            tele,
                                            pass,
                                            idpersona);

                                    //metodo para agregar el objeto del usuario que se ha autentificado
                                    data.add(informacionUsuario);

                                    //metodo para devolver el objeto del usuario autenticado
                                    callFinal.validacion(data);

                                } else {
                                    callFinal.Error(json.getString("Mensaje"));
                                }

                            } catch (JSONException e) {

                                callFinal.Error(json.getString("Mensaje"));
                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });

    }

    @Override
    public void guardarUsuario(Usuario objeto, @NonNull validacionCallBack callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        JSONObject perfil = new JSONObject();
        try {
            perfil.put("name",objeto.getNombre());
            perfil.put("id_roles",objeto.getTipoUsuario());
            perfil.put("username",objeto.getNombreUsuario());
            perfil.put("phone",objeto.getTelefono());
            perfil.put("email",objeto.getCorreo());
            perfil.put("password",objeto.getContrasenia());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), perfil.toString());
        retrofit2.Call<ResponseBody> req = service.registrarUsuarios(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);

                            try {

                                //Log.d("Response", "" + json.getString("Nombre"));
                                List<Usuario> data = new ArrayList<>();

                                if (json.has("name")) {


                                    String idpersona           = json.getString("id");
                                    String rol         = json.getString("id_roles");
                                    String nombre            = json.getString("name");
                                    String usuarios    = json.getString("username");
                                    String tele   = json.getString("phone");
                                    String correo          = json.getString("email");


                                    Usuario informacionUsuario =  new Usuario(0,
                                            nombre,
                                            "",
                                            "",
                                            usuarios,
                                            correo,
                                            rol,
                                            tele,
                                            objeto.getContrasenia(),
                                            idpersona);

                                    //metodo para agregar el objeto del usuario que se ha autentificado
                                    data.add(informacionUsuario);

                                    //metodo para devolver el objeto del usuario autenticado
                                    callFinal.validacion(data);

                                } else {
                                    callFinal.Error(json.getString("Mensaje"));
                                }

                            } catch (JSONException e) {

                                callFinal.Error(json.getString("Mensaje"));
                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void eliminarUsuario(String idUsuario, @NonNull validacioneliminado callFinal) {

    }

    @Override
    public void obtenerTodosUsuarios(@NonNull validacionCallBack callFinal) {

    }

    @Override
    public void guardarUsuarioServer(Usuario objeto, @NonNull validacionCallBack callFinal) {

    }

    @Override
    public void actualizarUsuarioServer(Usuario objeto, @NonNull validacionCallBack callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        JSONObject perfil = new JSONObject();
        try {
            perfil.put("name",objeto.getNombre());
            perfil.put("id_roles","3");
            perfil.put("username",objeto.getNombreUsuario());
            perfil.put("phone",objeto.getTelefono());
            perfil.put("email",objeto.getCorreo());
            perfil.put("password",objeto.getContrasenia());
            perfil.put("id",objeto.getUsuarioId());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), perfil.toString());
        retrofit2.Call<ResponseBody> req = service.actualizarUsuario(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);

                            try {

                                //Log.d("Response", "" + json.getString("Nombre"));
                                List<Usuario> data = new ArrayList<>();

                                if (json.has("name")) {


                                    String idpersona           = json.getString("id");
                                    String rol         = json.getString("id_roles");
                                    String nombre            = json.getString("name");
                                    String usuarios    = json.getString("username");
                                    String tele   = json.getString("phone");
                                    String correo          = json.getString("email");


                                    Usuario informacionUsuario =  new Usuario(0,
                                            nombre,
                                            "",
                                            "",
                                            usuarios,
                                            correo,
                                            rol,
                                            tele,
                                            objeto.getContrasenia(),
                                            idpersona);

                                    //metodo para agregar el objeto del usuario que se ha autentificado
                                    data.add(informacionUsuario);

                                    //metodo para devolver el objeto del usuario autenticado
                                    callFinal.validacion(data);

                                } else {
                                    callFinal.Error(json.getString("Mensaje"));
                                }

                            } catch (JSONException e) {

                                callFinal.Error(json.getString("Mensaje"));
                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });


    }

    @Override
    public void guardarVendedores(Usuario objeto, @NonNull validacionCallBack callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        JSONObject perfil = new JSONObject();
        try {
            perfil.put("name",objeto.getNombre());
            perfil.put("id_roles",objeto.getTipoUsuario());
            perfil.put("username",objeto.getNombreUsuario());
            perfil.put("phone",objeto.getTelefono());
            perfil.put("email",objeto.getCorreo());
            perfil.put("password",objeto.getContrasenia());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), perfil.toString());
        retrofit2.Call<ResponseBody> req = service.registrarUsuarios(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);

                            try {

                                //Log.d("Response", "" + json.getString("Nombre"));
                                List<Usuario> data = new ArrayList<>();

                                if (json.has("name")) {


                                    String idpersona           = json.getString("id");
                                    String rol         = json.getString("id_roles");
                                    String nombre            = json.getString("name");
                                    String usuarios    = json.getString("username");
                                    String tele   = json.getString("phone");
                                    String correo          = json.getString("email");


                                    Usuario informacionUsuario =  new Usuario(0,
                                            nombre,
                                            "",
                                            "",
                                            usuarios,
                                            correo,
                                            rol,
                                            tele,
                                            objeto.getContrasenia(),
                                            idpersona);

                                    //metodo para agregar el objeto del usuario que se ha autentificado
                                    data.add(informacionUsuario);

                                    //metodo para devolver el objeto del usuario autenticado
                                    callFinal.validacion(data);

                                } else {
                                    callFinal.Error(json.getString("Mensaje"));
                                }

                            } catch (JSONException e) {

                                callFinal.Error(json.getString("Mensaje"));
                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void obtenerListadoUsuarios(String idUsuario, @NonNull validacionCallBack callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        retrofit2.Call<ResponseBody> req = service.obtenerListadoUsuarios(idUsuario);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);


                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    List<Usuario> arrayApiarios =  new ArrayList<>();

                                    for (int i = 0; i < json.length(); i++){

                                        final String claveLocal = UUID.randomUUID().toString();
                                        JSONObject objetoApis =  new JSONObject(json.get(i).toString());



                                        String idpersona           = objetoApis.getString("id");
                                        String rol         = objetoApis.getString("id_roles");
                                        String nombre            = objetoApis.getString("name");
                                        String usuarios    = objetoApis.getString("username");
                                        String tele   = objetoApis.getString("phone");
                                        String correo          = objetoApis.getString("email");


                                        Usuario informacionUsuario =  new Usuario(0,
                                                nombre,
                                                "",
                                                "",
                                                usuarios,
                                                correo,
                                                rol,
                                                tele,
                                                "",
                                                idpersona);

                                        arrayApiarios.add(informacionUsuario);
                                    }



                                    callFinal.validacion(arrayApiarios);

                                }else{
                                    List<Usuario> arrayApis =  new ArrayList<>();
                                    callFinal.validacion(arrayApis);
                                }

                            }


                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void obtenerJornadas(@NonNull listadoJornadas callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        retrofit2.Call<ResponseBody> req = service.listadoJornadas();
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);


                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    List<jornadas> arrayJornadas =  new ArrayList<>();

                                    for (int i = 0; i < json.length(); i++){

                                        final String claveLocal = UUID.randomUUID().toString();
                                        JSONObject objetoApis =  new JSONObject(json.get(i).toString());



                                        int idj           = objetoApis.getInt("id");
                                        String namej      = objetoApis.getString("name");
                                        int  numberj      = objetoApis.getInt("number");
                                        int  puntosg      = objetoApis.getInt("point_grande");
                                        int  puntosc      = objetoApis.getInt("point_chivita");
                                        int  estat      = objetoApis.getInt("status");


                                        jornadas jour =  new jornadas(idj,namej,numberj,idj,puntosg,puntosc,estat);

                                        arrayJornadas.add(jour);
                                    }



                                   callFinal.Exito(arrayJornadas);

                                }else{
                                    List<jornadas> arrayApis =  new ArrayList<>();
                                   callFinal.Exito(arrayApis);
                                }

                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void obtenerEquipos(@NonNull listadoEqupos callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        retrofit2.Call<ResponseBody> req = service.listadoEquipos();
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);


                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    List<Equipos> arrayJornadas =  new ArrayList<>();

                                    for (int i = 0; i < json.length(); i++){


                                        JSONObject objetoApis =  new JSONObject(json.get(i).toString());

                                        int idj           = objetoApis.getInt("id");
                                        String namej      = objetoApis.getString("name");
                                        String logoj      = objetoApis.getString("logo");


                                        Equipos equip =  new Equipos(0,namej,idj,logoj);
                                        arrayJornadas.add(equip);
                                    }



                                    callFinal.Exito(arrayJornadas);

                                }else{
                                    List<Equipos> arrayApis =  new ArrayList<>();
                                    callFinal.Exito(arrayApis);
                                }

                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void generarCreditos(int chivita, int grande, int idUsuario, int idchivita, int idgrande, int idvendedor, String nombre, @NonNull validacioneliminado callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        JSONObject chivi = new JSONObject();
        JSONObject grand = new JSONObject();
        JSONArray credi = new JSONArray();
        try {
            chivi.put("id",idchivita);
            chivi.put("id_users",idUsuario);
            chivi.put("cant_big",0);
            chivi.put("cant_small",chivita);
            chivi.put("name","Chivita");
            chivi.put("type",1);
            chivi.put("id_vendedor",idvendedor);
            chivi.put("name_vendedor",nombre);

            grand.put("id",idgrande);
            grand.put("id_users",idUsuario);
            grand.put("cant_big",grande);
            grand.put("cant_small",0);
            grand.put("name","Grande");
            grand.put("type",2);
            chivi.put("id_vendedor",idvendedor);
            chivi.put("name_vendedor",nombre);

            credi.put(chivi);
            credi.put(grand);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), credi.toString());
        retrofit2.Call<ResponseBody> req = service.guardarCredito(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);

                            try {

                                if (json.getString("status").equals("success")) {

                                    callFinal.validacion("Se agregaron las oportunidades exitosamente.");
                                } else {
                                    callFinal.Error("Se produjo un error al guardar los créditos, intente más tarde.");
                                }

                            } catch (JSONException e) {

                                callFinal.Error(json.getString("Mensaje"));
                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }




    @Override
    public void listadoCreditosUsuario(String id, @NonNull creditos callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        retrofit2.Call<ResponseBody> req = service.listCreditos(id);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);
                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    int chivita = 0 ;
                                    int grande = 0;

                                    List<Creditos> credi =  new ArrayList<>();

                                    for (int i = 0; i < json.length(); i++){

                                        JSONObject objetoApis =  new JSONObject(json.get(i).toString());

                                        int idobjeto   = objetoApis.getInt("id");
                                        int cantchica  = objetoApis.getInt("cant_small");
                                        int cantgrande = objetoApis.getInt("cant_big");
                                        int idusu = objetoApis.getInt("id_users");
                                        String name = objetoApis.getString("name");
                                        int tipiti = objetoApis.getInt("type");


                                        Creditos cred =  new Creditos(0,
                                                idusu,
                                                cantgrande,
                                                cantchica,
                                                name,
                                                tipiti,
                                                idobjeto);


                                        credi.add(cred);
                                    }

                                    callFinal.Exito(credi);

                                }else{

                                    callFinal.Error("No cuenta con creditos");
                                }

                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void obtenerPartido(int idJornadas, @NonNull listPartidos callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        retrofit2.Call<ResponseBody> req = service.listPartidos(String.valueOf(idJornadas));
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);
                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    int chivita = 0 ;
                                    int grande = 0;

                                    List<Partidos> listadoPart =  new ArrayList<>();

                                    for (int i = 0; i < json.length(); i++){

                                        JSONObject objetoApis =  new JSONObject(json.get(i).toString());


                                        int idjornada   = objetoApis.getInt("id_journeys");
                                        int idlocal     = objetoApis.getInt("id_team_local");
                                        int idvisitante = objetoApis.getInt("id_team_visiting");
                                        String fecha    = objetoApis.getString("match_date");
                                        int status      = objetoApis.getInt("status");
                                        int idPart      = objetoApis.getInt("id");

                                        Partidos parti =  new Partidos(0,idjornada,idlocal,idvisitante,fecha,status,idPart);

                                        listadoPart.add(parti);
                                    }

                                    callFinal.Exito(listadoPart);

                                }else{

                                    callFinal.Error("Por el momento esta jornada no cuenta con partidos.");
                                }

                            }


                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void recuperarContrase(String usuario, @NonNull recuperar callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        JSONObject perfil = new JSONObject();
        try {
            perfil.put("username",usuario);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), perfil.toString());
        retrofit2.Call<ResponseBody> req = service.recuperar(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);

                            try {
                                if(json.getString("status").equals("200")){
                                    callFinal.Exito(json.getString("message"));
                                }else{
                                    callFinal.Error(json.getString("message"));
                                }
                            } catch (JSONException e) {
                                callFinal.Error(json.getString("Mensaje"));
                            }
                        }
                    } else {
                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });

    }

    @Override
    public void crearPartidos(JSONArray partidos, @NonNull crearPartidos callFinal) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        RequestBody body = RequestBody.create(MediaType.parse("application/json"), partidos.toString());
        retrofit2.Call<ResponseBody> req = service.crearPartidos(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();

                            if (json2 instanceof JSONObject) {

                                JSONObject Objetito =  new JSONObject(rawJson);
                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    int chivita = 0 ;
                                    int grande = 0;

                                    List<Partidos> listadoPart =  new ArrayList<>();

                                    for (int i = 0; i < json.length(); i++){

                                        JSONObject objetoApis =  new JSONObject(json.get(i).toString());


                                        int idjornada   = objetoApis.getInt("id_journeys");
                                        int idlocal     = objetoApis.getInt("id_team_local");
                                        int idvisitante = objetoApis.getInt("id_team_visiting");
                                        String fecha    = objetoApis.getString("match_date");
                                        int status      = objetoApis.getInt("status");
                                        int idPart      = objetoApis.getInt("id");

                                        Partidos parti =  new Partidos(0,idjornada,idlocal,idvisitante,fecha,status,idPart);

                                        listadoPart.add(parti);
                                    }

                                    callFinal.Exito(listadoPart);

                                }else{

                                    callFinal.Error("Por el momento esta jornada no cuenta con partidos.");
                                }

                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });



    }

    @Override
    public void obtenerJornadasConpartidos(@NonNull jornadasConPartidos callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        retrofit2.Call<ResponseBody> req = service.jornadasConPartidos();
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);
                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    List<jornadas> listado = new ArrayList<>();
                                    List<Partidos> partisList = new ArrayList<>();


                                    for(int i = 0; i < json.length(); i++){

                                        JSONObject objeto =  new JSONObject(json.get(i).toString());
                                        int idobjeto = objeto.getInt("id");
                                        String nom = objeto.getString("name");
                                        int objNumber = objeto.getInt("number");
                                        int puntosg = objeto.getInt("point_grande");
                                        int puntosc = objeto.getInt("point_chivita");
                                        int estat = objeto.getInt("status");

                                        jornadas nuevo = new jornadas(0,nom,objNumber,idobjeto,puntosg,puntosc,estat);

                                        listado.add(nuevo);


                                        JSONArray list = new JSONArray(objeto.getString("matches").toString());

                                        if(list.length() > 0){

                                            for(int j = 0 ; j < list.length(); j++){
                                                JSONObject partidos=  new JSONObject(list.get(j).toString());

                                                int idjornada   = partidos.getInt("id_journeys");
                                                int idlocal     = partidos.getInt("id_team_local");
                                                int idvisitante = partidos.getInt("id_team_visiting");
                                                String fecha    = partidos.getString("match_date");
                                                int status      = partidos.getInt("status");
                                                int idPart      = partidos.getInt("id");

                                                Partidos parti =  new Partidos(0,idjornada,idlocal,idvisitante,fecha,status,idPart);

                                                partisList.add(parti);
                                            }
                                        }
                                    }
                                    callFinal.Exito(partisList,listado);

                                }else{

                                    callFinal.Error("No cuenta con jornadas activas.");
                                }

                            }


                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void obtenerJornadasSinEquipos(@NonNull listadoJornadas callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        retrofit2.Call<ResponseBody> req = service.jornadasSinPartidos();
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);


                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    List<jornadas> arrayJornadas =  new ArrayList<>();

                                    for (int i = 0; i < json.length(); i++){

                                        final String claveLocal = UUID.randomUUID().toString();
                                        JSONObject objetoApis =  new JSONObject(json.get(i).toString());



                                        int idj           = objetoApis.getInt("id");
                                        String namej      = objetoApis.getString("name");
                                        int  numberj      = objetoApis.getInt("number");
                                        int  puntosg      = objetoApis.getInt("point_grande");
                                        int  puntosc      = objetoApis.getInt("point_chivita");
                                        int  estat      = objetoApis.getInt("status");


                                        jornadas jour =  new jornadas(idj,namej,numberj,idj,puntosg,puntosc,estat);

                                        arrayJornadas.add(jour);
                                    }



                                    callFinal.Exito(arrayJornadas);

                                }else{
                                    List<jornadas> arrayApis =  new ArrayList<>();
                                    callFinal.Exito(arrayApis);
                                }

                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void guardarResultado(JSONObject resultado, @NonNull recuperar callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        RequestBody body = RequestBody.create(MediaType.parse("application/json"), resultado.toString());
        retrofit2.Call<ResponseBody> req = service.agregarResultado(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);

                            try {

                                if (json.getString("status").equals("200")){
                                    callFinal.Exito(json.getString("message"));
                                }else{
                                    callFinal.Error("Se guardo el resultado del partido con éxito.");
                                }
                            } catch (JSONException e) {

                                callFinal.Error(json.getString("Mensaje"));
                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void obtenerResultados(String idJornadas, @NonNull ganadoresListado callFinal) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);

        retrofit2.Call<ResponseBody> req = service.obtenerGanadores(idJornadas);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);
                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {

                                JSONArray json  =  null;
                                json            =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    List<Ganador> listadoPart =  new ArrayList<>();

                                    JSONObject totalGeneral =  new JSONObject(json.get(0).toString());
                                    JSONObject chivi        =  new JSONObject(json.get(1).toString());
                                    JSONObject grand        =  new JSONObject(json.get(2).toString());

                                    Log.d("primero","---> " + totalGeneral.toString());
                                    Log.d("segundo","---> " + grand.toString());
                                    Log.d("tercero","---> " + chivi.toString());

                                    int general     =  totalGeneral.getInt("total");
                                    int chivita      =  chivi.getInt("total_chivita");
                                    int grande     = grand.getInt("total_grande");

                                    Ganador nuevo =  new Ganador(0,Integer.parseInt(idJornadas), general,grande,chivita);
                                    listadoPart.add(nuevo);

                                    callFinal.Exito(listadoPart);


                                }else{
                                    callFinal.Error("Por el momento esta jornada no tiene ganadores.");
                                }
                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void crearApuesta(JSONObject bet, @NonNull crearApuesta callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bet.toString());
        retrofit2.Call<ResponseBody> req = service.crearApuesta(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);

                            try {

                                if(json.getString("status").equals("200")){

                                    callFinal.Exito("Se creo exitosamente la apuesta.");
                                }else{
                                    callFinal.Error(json.getString("message"));
                                }

                            } catch (JSONException e) {

                                callFinal.Error(json.getString("Mensaje"));
                            }
                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });


    }

    @Override
    public void obtenerListadoClientes(String tipoUser, @NonNull listadoClientes callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);



        retrofit2.Call<ResponseBody> req = service.obtenerListadoUsuarios(tipoUser);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);


                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    List<Clientes> arrayApiarios =  new ArrayList<>();

                                    for (int i = 0; i < json.length(); i++){

                                        final String claveLocal = UUID.randomUUID().toString();
                                        JSONObject objetoApis =  new JSONObject(json.get(i).toString());



                                        String idpersona     = objetoApis.getString("id");
                                        String rol           = objetoApis.getString("id_roles");
                                        String nombre        = objetoApis.getString("name");
                                        String usuarios      = objetoApis.getString("username");
                                        String tele          = objetoApis.getString("phone");
                                        String correo        = objetoApis.getString("email");


                                        String infosCreditos = objetoApis.getString("credit");

                                        JSONArray list = new JSONArray(infosCreditos);

                                        List<Creditos> arrayCreditos =  new ArrayList<>();

                                        if(list.length() > 0){

                                            for (int a = 0 ; a < list.length(); a++){

                                                JSONObject objetoCredito =  new JSONObject(list.get(a).toString());


                                                int idCredito = objetoCredito.getInt("id");
                                                String nombreC = objetoCredito.getString("name");
                                                int tipoC = objetoCredito.getInt("type");
                                                int cant1 = objetoCredito.getInt("cant_big");
                                                int cant2 = objetoCredito.getInt("cant_small");

                                                Creditos credi1 =  new Creditos(0,
                                                        Integer.parseInt(idpersona),
                                                        cant1,
                                                        cant2,
                                                        nombreC,
                                                        tipoC,
                                                        idCredito);


                                                arrayCreditos.add(credi1);

                                            }




                                        }else{

                                            Creditos credi1 =  new Creditos(0,
                                                    Integer.parseInt(idpersona),
                                                    0,
                                                    0,
                                                    "Chivita",
                                                    1,
                                                    0);

                                            Creditos credi2 =  new Creditos(0,
                                                    Integer.parseInt(idpersona),
                                                    0,
                                                    0,
                                                    "Grande",
                                                    2,
                                                    0);

                                            arrayCreditos.add(credi1);
                                            arrayCreditos.add(credi2);
                                        }


                                        Clientes cli =  new Clientes(0,Integer.parseInt(rol),nombre,usuarios,tele,correo,Integer.parseInt(idpersona),arrayCreditos);
                                        arrayApiarios.add(cli);


                                    }

                                   callFinal.Exito(arrayApiarios);

                                }else{
                                    List<Clientes> arrayApis =  new ArrayList<>();
                                    callFinal.Exito(arrayApis);
                                }

                            }

                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void obtenerResultadosUsuario(String idUsuario, String idJornada, @NonNull listResultados callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        JSONObject perfil = new JSONObject();
        try {
            perfil.put("id_users", Integer.parseInt(idUsuario));
            perfil.put("id_journeys",Integer.parseInt(idJornada));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), perfil.toString());
        retrofit2.Call<ResponseBody> req = service.obtenerResultadosUsuario(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                try {

                    String codigoConexion = String.valueOf(response.code());

                    if (codigoConexion.equals("200")) {

                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            Object json2 = new JSONTokener(rawJson).nextValue();
                            if (json2 instanceof JSONObject) {
                                JSONObject Objetito =  new JSONObject(rawJson);


                                callFinal.Error(Objetito.getString("Mensaje"));

                            } else if (json2 instanceof JSONArray) {
                                JSONArray json =  null;
                                json =  new JSONArray(rawJson);

                                if (json.length() > 0){

                                    List<ResultJornada> arrayResult =  new ArrayList<>();
                                    List<bets>listbests = new ArrayList<>();
                                    ResultJornada restsu;
                                    for(int i = 0; i < json.length(); i++){

                                        JSONObject objetoJor =  new JSONObject(json.get(i).toString());

                                        String nombre   = objetoJor.getString("name");
                                        int numr        = objetoJor.getInt("number");
                                        int bestt       = objetoJor.getInt("betsTotal");
                                        String msj      = objetoJor.getString("resultFinal");

                                        String infos    = objetoJor.getString("bets");
                                        JSONArray BESTS = new JSONArray(infos);

                                        restsu = new ResultJornada(0,
                                                nombre,
                                                numr,
                                                BESTS.toString(),
                                                bestt,
                                                msj);

                                        arrayResult.add(restsu);



                                    }

                                    callFinal.Exito(arrayResult);
                                }else{
                                   callFinal.Error("La jornada no cuenta con apuestas realizadas por usted.");
                                }

                            }


                        }
                    } else {


                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }


    //metodo que se encarga de eliminar los vendedores
    @Override
    public void eliminarVendedores(String idUsuario, @NonNull eliminarVendedores callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);

        //RequestBody body = RequestBody.create(MediaType.parse("application/json"), bet.toString());
        retrofit2.Call<ResponseBody> req = service.eliminarVendedor(idUsuario);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String codigoConexion = String.valueOf(response.code());
                    if (codigoConexion.equals("200")) {
                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);
                            try {
                                if(json.getString("status").equals("success")){
                                    callFinal.Exito("Se eliminó con éxito al usuario.");
                                }else{
                                    callFinal.Error(json.getString("message"));
                                }
                            } catch (JSONException e) {

                                callFinal.Error(json.getString("message"));
                            }
                        }
                    } else {
                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void eliminarTablas(@NonNull eliminarTablas callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);

        //RequestBody body = RequestBody.create(MediaType.parse("application/json"), bet.toString());
        retrofit2.Call<ResponseBody> req = service.eliminarTablas();
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String codigoConexion = String.valueOf(response.code());
                    if (codigoConexion.equals("200")) {
                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);
                            try {
                                if(json.getString("status").equals("success")){
                                    callFinal.Exito(json.getString("message"));
                                }else{
                                    callFinal.Error(json.getString("message"));
                                }
                            } catch (JSONException e) {

                                callFinal.Error(json.getString("message"));
                            }
                        }
                    } else {
                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void generarReporteJornada(String idjornada, String tipo, @NonNull generarReporte callFinal) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);

        JSONObject perfil = new JSONObject();
        try {
            perfil.put("id_journeys",Integer.parseInt(idjornada));
            perfil.put("type",Integer.parseInt(tipo));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), perfil.toString());
        retrofit2.Call<ResponseBody> req = service.generarReporteJornada(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String codigoConexion = String.valueOf(response.code());
                    if (codigoConexion.equals("200")) {
                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);
                            try {
                                if(json.getString("status").equals("success")){
                                    callFinal.Exito(json.getString("message"));
                                }else{
                                    callFinal.Error(json.getString("message"));
                                }
                            } catch (JSONException e) {

                                callFinal.Error(json.getString("message"));
                            }
                        }
                    } else {
                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });



    }

    @Override
    public void guardarPuntos(String cantidad, String jornada, String cantiChivita, @NonNull guardarPuntos callFinal) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);

        JSONObject perfil = new JSONObject();
        try {
            perfil.put("id_journeys",Integer.parseInt(jornada));
            perfil.put("point",Integer.parseInt(cantidad));
            perfil.put("type",cantiChivita);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), perfil.toString());
        retrofit2.Call<ResponseBody> req = service.guardarPuntos(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String codigoConexion = String.valueOf(response.code());
                    if (codigoConexion.equals("200")) {
                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);
                            try {
                                if(json.getString("status").equals("success")){
                                    callFinal.Exito(json.getString("message"));
                                }else{
                                    callFinal.Error(json.getString("message"));
                                }
                            } catch (JSONException e) {

                                callFinal.Error(json.getString("message"));
                            }
                        }
                    } else {
                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }




    @Override
    public void cerrarJornada(String idjornada, @NonNull guardarPuntos callFinal) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        retrofit2.Call<ResponseBody> req = service.cerrar(String.valueOf(idjornada));
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String codigoConexion = String.valueOf(response.code());
                    if (codigoConexion.equals("200")) {
                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);
                            try {
                                if(json.getString("status").equals("success")){
                                    callFinal.Exito(json.getString("message"));
                                }else{
                                    callFinal.Error(json.getString("message"));
                                }
                            } catch (JSONException e) {

                                callFinal.Error(json.getString("message"));
                            }
                        }
                    } else {
                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }

    @Override
    public void crearNuevaJornada(String nombre, String numero, String descripcion, @NonNull guardarPuntos callFinal) {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        ServiciosUsuario service = new Retrofit.Builder().baseUrl(ConstanteServidor.server).client(client).build().create(ServiciosUsuario.class);


        JSONObject perfil = new JSONObject();
        try {
            perfil.put("name",nombre);
            perfil.put("number",Integer.parseInt(numero));
            perfil.put("descriptions",descripcion);
            perfil.put("status",true);
            perfil.put("point_chivita",0);
            perfil.put("point_grande",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestBody body = RequestBody.create(MediaType.parse("application/json"), perfil.toString());
        retrofit2.Call<ResponseBody> req = service.crearNuevaJornada(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String codigoConexion = String.valueOf(response.code());
                    if (codigoConexion.equals("200")) {
                        String rawJson = response.body().string();
                        if (rawJson != null) {
                            JSONObject json = null;
                            json = new JSONObject(rawJson);
                            try {
                                if(json.getString("status").equals("success")){
                                    callFinal.Exito(json.getString("message"));
                                }else{
                                    callFinal.Error(json.getString("message"));
                                }
                            } catch (JSONException e) {

                                callFinal.Error(json.getString("message"));
                            }
                        }
                    } else {
                        callFinal.Error("Error de conexion, verifique su conexión a internet.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    callFinal.Error(e.toString());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callFinal.Error("No cuenta con conexión a internet, verifique su conexión.");
            }
        });
    }


}
