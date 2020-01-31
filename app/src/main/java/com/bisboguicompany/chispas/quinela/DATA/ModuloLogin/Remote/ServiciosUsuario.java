package com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Remote;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiciosUsuario {

    //@FormUrlEncoded
    /*@POST("api/login")
    Call<ResponseBody> signIn(
            @Field("Correo") String nombre,
            @Field("Contrasena") String password);*/
    @POST("api/login")
    Call<ResponseBody> signIn(@Body() RequestBody body );

    @POST("api/create_user")
    Call<ResponseBody> registrarUsuarios(@Body() RequestBody body );

    @POST("api/update_user")
    Call<ResponseBody> actualizarUsuario(@Body() RequestBody body );

    @POST("api/create_user")
    Call<ResponseBody> registrarVendedor(@Body() RequestBody body );

    @GET("/api/get_userByRol/{folio}")
    Call<ResponseBody> obtenerListadoUsuarios(@Path("folio") String folio );

    @GET("/api/get_journeys")
    Call<ResponseBody> listadoJornadas();


    @GET("/api/get_teams")
    Call<ResponseBody> listadoEquipos();

    @POST("api/create_credit")
    Call<ResponseBody> guardarCredito(@Body() RequestBody body );

    @GET("/api/get_creditByUserId/{folio}")
    Call<ResponseBody> listCreditos(@Path("folio") String folio );


    @GET("/api/get_matchs/{folio}")
    Call<ResponseBody> listPartidos(@Path("folio") String folio );

    @POST("api/recover_password")
    Call<ResponseBody> recuperar(@Body() RequestBody body );


    @POST("api/create_match")
    Call<ResponseBody> crearPartidos(@Body() RequestBody body );

    @GET("api/get_journey_yes_match")
    Call<ResponseBody> jornadasConPartidos();

    @GET("api/get_journey_no_match")
    Call<ResponseBody> jornadasSinPartidos();

    @POST("api/change_result")
    Call<ResponseBody> agregarResultado(@Body() RequestBody body );

    @GET("/api/get_byIdJourney/{folio}")
    Call<ResponseBody> obtenerGanadores(@Path("folio") String folio );

    @POST("api/create_bet")
    Call<ResponseBody> crearApuesta(@Body() RequestBody body );

    @POST("api/result")
    Call<ResponseBody> obtenerResultadosUsuario(@Body() RequestBody body );

    @GET("/api/delete_user/{folio}")
    Call<ResponseBody> eliminarVendedor(@Path("folio") String folio );


    @GET("/api/truncate_tables")
    Call<ResponseBody> eliminarTablas();


    @POST("api/report_by_jornada")
    Call<ResponseBody> generarReporteJornada(@Body() RequestBody body );


    @POST("api/change_point")
    Call<ResponseBody> guardarPuntos(@Body() RequestBody body );


    @GET("/api/change_status_journey/{folio}")
    Call<ResponseBody> cerrar(@Path("folio") String folio );



    @POST("api/create_journey")
    Call<ResponseBody> crearNuevaJornada(@Body() RequestBody body );
}
