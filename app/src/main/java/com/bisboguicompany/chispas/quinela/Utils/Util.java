package com.bisboguicompany.chispas.quinela.Utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bisboguicompany.chispas.quinela.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Util {


    //variables para las imagenes de carga
    private final int PICK_IMAGE_MULTIPLE = 1;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_PERFIL = 110;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private final int SELECT_PICTURE = 200;

    //uri para las carga de imagenes
    Uri imageUri;
    Uri imageUriPerfil;
    //espesifico para la carga de imagen de perfil
    public String imageUriPerfilString;
    //listado de imagenes que se obtienen del listado de urlImage
    private List<File> fileList = new ArrayList<>();
    //se utilizan ambos para comparar lo original con el temporal para que al final cuando se guarde se pueda ubicar que elimina y que se mantiene y que es nuevo
    public List<UrlImage> listOrign;
    public List<UrlImageTemp> listTemp;
    public ImageCompression imageCompression;

    //constructor
    public Util(Activity activity) {
        //verifica los permisos que ya cuente con ellos
        if (checkAndRequestPermissions(activity)) {
        }
        imageCompression = new ImageCompression(activity);
        listOrign = new ArrayList<>();
        listTemp = new ArrayList<>();
    }

    //carga el listado por medio de la estructura dura
    // para la imagen de perfil es ID_perfil_nombredelaimagen.jpeg
    ////////la palabra perfil es estatica para identificar
    // para las imagenes multiples es ID_estatuslogico_estatusfisico_tipo_privacidad_módulo_nombredelaimagen.jpeg
    ///////los estatus son 0 para eliminado y 1 para activo
    ///////el tipo es perfil, principal, secundario
    ///////la privacidad es publico ó privado
    public List<UrlImageTemp> loadImages(String path1, String id, String tipo) {
        //inicializa listado temporal
        listTemp = new ArrayList<>();
        try {
            //se crea el listado normal
            List<UrlImage> imageList = new ArrayList<>();


            //obtenemos el directorio
            File filepath = Environment.getExternalStorageDirectory();
            File dir = new File(filepath.getAbsolutePath() + "/" + path1);
            dir.mkdir();

            Log.d("pathczdafasda", filepath.getAbsolutePath() + "/" + path1);

            if (dir.isDirectory()) {
                //obtenemos todos los archivos
                String[] children = dir.list();
                //recorremos los listados de nombres
                for (int i = 0; i < children.length; i++) {

                    //cortamos por medio de _ para saber de que tamaño es para determinar su acción
                    String[] split3 = children[i].split("_");

                    //verificamos su largo
                    switch (split3.length) {
                        case 3:
                            //comprobamos que sea un perfil y que coincida su id para agregar a la lista
                            if (tipo.equals("perfil") && children[i].toLowerCase().contains("perfil")) {
                                if (id.equals(split3[0])) {
                                    //se llenan los temporales y originales
                                    UrlImage imageUrl1 = new UrlImage();
                                    UrlImageTemp imageUrl1T = new UrlImageTemp();
                                    imageUrl1.setImageUrl(dir + "/" + children[i]);
                                    imageUrl1T.setImageUrl(dir + "/" + children[i]);
                                    imageList.add(imageUrl1);
                                    listTemp.add(imageUrl1T);
                                    imageUriPerfilString = dir + "/" + children[i];

                                    Log.d("entro", "entro");
                                }
                            }
                            break;
                        case 6:
                            //buscar las imagenes multiples por tipo (principal o secundario)
                            if (id.equals(split3[0]) && split3[1].equals("1") && split3[2].equals("1") && split3[3].equals(tipo)) {
                                //se llenan los temporales y originales
                                UrlImage imageUrl2 = new UrlImage();
                                UrlImageTemp imageUrl2T = new UrlImageTemp();
                                imageUrl2.setImageUrl(dir + "/" + children[i]);
                                imageUrl2T.setImageUrl(dir + "/" + children[i]);
                                imageList.add(imageUrl2);
                                listTemp.add(imageUrl2T);
                            } else {
                                //buscar las imagenes multiples engloba todos los tipos (principal, secundario)
                                if (id.equals(split3[0]) && split3[1].equals("1") && split3[2].equals("1")) {
                                    //se llenan los temporales y originales
                                    UrlImage imageUrl2 = new UrlImage();
                                    UrlImageTemp imageUrl2T = new UrlImageTemp();
                                    imageUrl2.setImageUrl(dir + "/" + children[i]);
                                    imageUrl2T.setImageUrl(dir + "/" + children[i]);
                                    imageList.add(imageUrl2);
                                    listTemp.add(imageUrl2T);
                                }
                            }
                            break;
                        default:
                            Log.d("ImagenSinFormato ----->", children[i]);
                    }
                }

            }

            //se crea el listado originales
            listOrign = new ArrayList<>(imageList);

            //regresa el listado temporal
            return listTemp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //sirve para obtener la imagen de perfil se comunica con el loadimage
    public Uri obtenerImagePerfil(String path1, String id) {
        String perfilText = "";
        String tipo = "perfil";
        //metodo para que obtengas el url de la imagen de perfil por medio del id y el tipo perfil
        List<UrlImageTemp> imagePerfil = loadImages(path1, id, tipo);
        if (imagePerfil != null) {

            Log.d("diferente", "diferente");

            for (int i = imagePerfil.size() - 1; i >= 0; i--) {

                Log.d("imagen", "imagen" + imagePerfil.get(i).getImageUrl());
                perfilText = imagePerfil.get(i).getImageUrl();
            }
        }
        //se retorna la uri
        return Uri.parse(perfilText);
    }


    public Uri cargarImagenData(String path1, String id) {
        String perfilText = "";
        String tipo = "data";
        //metodo para que obtengas el url de la imagen de perfil por medio del id y el tipo perfil
        List<UrlImageTemp> imagePerfil = cargarDatasImagenes(path1, id, tipo);
        if (imagePerfil != null) {

            Log.d("diferente", "diferente");

            for (int i = imagePerfil.size() - 1; i >= 0; i--) {

                Log.d("imagen", "imagen" + imagePerfil.get(i).getImageUrl());
                perfilText = imagePerfil.get(i).getImageUrl();
            }
        }
        //se retorna la uri
        return Uri.parse(perfilText);
    }


    public List<UrlImageTemp> cargarDatasImagenes(String path1, String id, String tipo) {
        //inicializa listado temporal
        listTemp = new ArrayList<>();
        try {
            //se crea el listado normal
            List<UrlImage> imageList = new ArrayList<>();


            //obtenemos el directorio
            File filepath = Environment.getExternalStorageDirectory();
            File dir = new File(filepath.getAbsolutePath() + "/" + path1);
            dir.mkdir();


            if (dir.isDirectory()) {
                //obtenemos todos los archivos
                String[] children = dir.list();
                //recorremos los listados de nombres
                for (int i = 0; i < children.length; i++) {

                    //cortamos por medio de _ para saber de que tamaño es para determinar su acción
                    String[] split3 = children[i].split("_");

                    if (id.equals(children[i])) {
                        //se llenan los temporales y originales
                        UrlImage imageUrl1 = new UrlImage();
                        UrlImageTemp imageUrl1T = new UrlImageTemp();
                        imageUrl1.setImageUrl(dir + "/" + children[i]);
                        imageUrl1T.setImageUrl(dir + "/" + children[i]);
                        imageList.add(imageUrl1);
                        listTemp.add(imageUrl1T);
                        imageUriPerfilString = dir + "/" + children[i];

                        Log.d("entro", "entro");
                    }

                }

            }

            //se crea el listado originales
            listOrign = new ArrayList<>(imageList);

            //regresa el listado temporal
            return listTemp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //metodo para cargar las multiples imagenes
    public void cargaImageMultiple(final Activity activity) {
        //se carga el dialogo para escoger el tipo si es camara o galeria
        @SuppressLint("RestrictedApi") final android.support.v7.app.AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.myDialog));
        final android.view.View mView = activity.getLayoutInflater().inflate(R.layout.modal_cambiar_foto, null);

        Button mCamara = (Button) mView.findViewById(R.id.btnOpcionCamara);
        Button mGaleria = (Button) mView.findViewById(R.id.btnOpcionGaleria);
        Button mCancelar = (Button) mView.findViewById(R.id.btnCancelarCambioFoto);

        mBuilder.setView(mView);
        final android.support.v7.app.AlertDialog dialog = mBuilder.create();
        dialog.show();


        mGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //por medio de galeria se realiza unos permisos y definir que será de multiples imagenes
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    }
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

                    dialog.dismiss();
                } catch (Exception e) {
                    Toast.makeText(activity, "No hizo una selección.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //por medio de camara se crea un filepath para la ubicación de la foto esto se realiza para poder controlar la hubicación y calidad de la imagen
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath() + "/tempoWolf/");
                dir.mkdirs();


                try {

                    String NOMEDIA = ".nomedia";
                    String nomedia = Environment.getExternalStorageDirectory() + File.separator + "tempoWolf/.nomedia";
                    File nomediaFile = new File(nomedia);
                    if (!nomediaFile.exists()) {
                        nomediaFile.createNewFile();
                    }
                    File Folder = new File(Environment.getExternalStorageDirectory() + "/tempoWolf");
                    if (Folder.mkdir()) {
                        nomediaFile = new File(Environment.getExternalStorageDirectory() + "/tempoWolf/" + NOMEDIA);
                        if (!nomediaFile.exists()) {
                            nomediaFile.createNewFile();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                File file = new File(dir, "temp_" + timeStamp + ".jpeg");
                imageUri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".my.package.name.provider", file);


                Intent intent = (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
                        ? new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
                        : new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                } else {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                }
                imageUri = Uri.fromFile(file);
                activity.startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

                dialog.dismiss();
            }
        });

        mCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //insertar foto y devolver el listado de objeto temporal de URlImageTemp
    public List<UrlImageTemp> insertarFoto(Activity activity) {

        if (imageUri != null) {
            if (!imageUri.equals("")) {

                try {
                    //se obtiene la uri de la misma clase
                    String imageurlReal = getPath(activity, imageUri);
                    imageurlReal = imageCompression.compressImage(imageurlReal);
                    //se crea un objeto de tipo urlimagetemp
                    UrlImageTemp imageUrl1 = new UrlImageTemp();
                    imageUrl1.setImageUrl(imageurlReal);

                    //se agrega al listado de multiples imagenes
                    listTemp.add(imageUrl1);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(activity, "No seleccionó una imagen", Toast.LENGTH_SHORT).show();
            }

        }

        //se retorna el list de temp
        return listTemp;
    }

    //Sirve para inserar las multiples imagenes al listado temp
    public List<UrlImageTemp> insertarMultiple(Activity activity, Intent data) {
        try {

            //se crea un array de strings
            String[] imagesPath;

            //se obtiene del data el list de url
            ClipData mClipData = data.getClipData();

            if (mClipData == null) {
                //en caso de ser solo una foto se utiliza getdata no el getclipdata

                imagesPath = new String[1];

                imagesPath[0] = getPath(activity, data.getData());

            } else {

                //se recorre el listado y se agregan los url al liststring
                int pickedImageCount;

                imagesPath = new String[mClipData.getItemCount()];

                for (pickedImageCount = 0; pickedImageCount < mClipData.getItemCount();
                     pickedImageCount++) {
                    imagesPath[pickedImageCount] = getPath(activity, mClipData.getItemAt(pickedImageCount).getUri());

                }

            }

            //se recorre el listao de url
            for (int i = 0; i < imagesPath.length; i++) {
                try {
                    //se ubica su path real ya que al ser de tipo clpdata genera una path temporal
                    String imageurlReal = imagesPath[i];
                    imageurlReal = imageCompression.compressImage(imageurlReal);
                    //se convierte a uri y se agrega al listado de url
                    Uri imageUri = Uri.fromFile(new File(imageurlReal));
                    imageurlReal = getPath(activity, imageUri);

                    UrlImageTemp imageUrl1 = new UrlImageTemp();
                    imageUrl1.setImageUrl(imageurlReal);

                    listTemp.add(imageUrl1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            Toast.makeText(activity, "Se encontro un error en el proceso", Toast.LENGTH_SHORT).show();
        }

        return listTemp;
    }

    //verificación de permisos
    private boolean checkAndRequestPermissions(Activity activity) {

        //se crea los int de las permisos
        int locationPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionWriteStorage = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCamera = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        //se crea un list de permisos para generar una peticion de multiples permisos aqui se agregarian otro si fueran necesarios
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    //metodo para guardar las imagenes multiples
    public void guardarLocalMultiple(String path, Activity activity, int id) {
        //elimina lo que ya no existe
        //se recorreo el listado original
        for (UrlImage url : listOrign) {

            //boolean para determinar si eciste
            boolean existe = false;

            //se recorre el temporal para verificar su existencia
            for (UrlImageTemp urlTemp : listTemp) {
                if (urlTemp.getImageUrl().equals(url.getImageUrl())) {
                    existe = true;
                }
            }

            //si no existe se elimina
            if (!existe) {
                File dir = new File(path);

                if (dir.isDirectory()) {
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++) {

                        if ((path + children[i]).equals(url.getImageUrl())) {

                            try {
                                File file = new File(dir, children[i]);
                                file.delete();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }
            }
        }

        //se recorre el temporal y se compara con el original para determina si existe y así saber si es nuevo
        for (UrlImageTemp urlTemp : listTemp) {
            int conta = 0;
            boolean existe = false;
            for (UrlImage url : listOrign) {
                if (url.getImageUrl().equals(urlTemp.getImageUrl())) {
                    existe = true;
                }
            }

            if (!existe) {
                conta++;

                //en caso de ser nuevo se agrega
                guardaNuevosMultiple(path, activity, id, urlTemp, conta);
            }
        }


    }

    //metodo para agregar las imagenes nuevas en las multiples imagenes
    public void guardaNuevosMultiple(String path, Activity activity, int id, UrlImageTemp urlTemp, int conta) {


        //se obtiene la uri de la imagen
        Uri imageUri = Uri.fromFile(new File(urlTemp.getImageUrl()));
        String imageurlReal = getPath(activity, imageUri);

        //se nombra la nueva imagen actualmente todas son secundarias y publicas en caso de agregar a URLIMAGETEMP y URLIMAGE algun campo para alterar el nombte aqui se utiliza
        String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String fileOutput = path + id + "_1_1_secundario_publico_" + timeStamp + conta + ".jpeg";

        //se reinician los buffers
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //se crea el buffer de obtencion
            bis = new BufferedInputStream(new FileInputStream(imageurlReal));
            //se crea el buffer se salida
            bos = new BufferedOutputStream(new FileOutputStream(fileOutput, false));
            byte[] buf = new byte[1024];
            //se inserta copia la imagen
            bis.read(buf);
            do {
                bos.write(buf);
            } while (bis.read(buf) != -1);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //se detiene un segundo para que estas puedan guardarse ya que actuan en segundo plano y al ser un bucle estas no alcanzan el tiempo para escribirse no se copian las iamgenes
        SystemClock.sleep(1000);

    }


    //metodo para generar los multipartbody para el envio por retrofit
    public List<MultipartBody.Part> crearPartsMultiples() {
        //se crea el listado de files por medio del listado de uri
        crearListFile();
        //creamos una instancia de listado de multipartbody
        final List<MultipartBody.Part> parts = new ArrayList<>();
        if (fileList != null) {
            //recorremos el listado de fiels  para generar el listado de multipartsbody en caso de cambiar el nombre del servidor aqui se raliza
            for (File file1 : fileList) {
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file1);
                MultipartBody.Part body = MultipartBody.Part.createFormData("fotos", file1.getName(), reqFile);
                parts.add(body);
            }
        } else {
            //en caso de que sean nulos se iuntenta cargar nuevamente.
            crearListFile();
            for (File file1 : fileList) {
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file1);
                MultipartBody.Part body = MultipartBody.Part.createFormData("fotos", file1.getName(), reqFile);
                parts.add(body);
            }
        }

        return parts;
    }

    //metodo para crear el listado de fiels por medio del listado de uri del listado original
    public void crearListFile() {
        fileList = new ArrayList<>();
        for (UrlImage url : listOrign) {
            String selectedFilePath = url.getImageUrl();
            File file = new File(selectedFilePath);

            fileList.add(file);
        }
    }

    //Carga de imagen para perfil
    public void cargaImagePerfil(final Activity activity) {
        //se carga el dialogo para escoger el tipo si es camara o galeria
        @SuppressLint("RestrictedApi") final android.support.v7.app.AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.myDialog));
        final android.view.View mView = activity.getLayoutInflater().inflate(R.layout.modal_cambiar_foto, null);

        Button mCamara = (Button) mView.findViewById(R.id.btnOpcionCamara);
        Button mGaleria = (Button) mView.findViewById(R.id.btnOpcionGaleria);
        Button mCancelar = (Button) mView.findViewById(R.id.btnCancelarCambioFoto);

        mBuilder.setView(mView);
        final android.support.v7.app.AlertDialog dialog = mBuilder.create();
        dialog.show();


        mGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //en caso de que sea galeria se ejecuta la galeria de forma nativa esta solo selecciona una imagen
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    activity.startActivityForResult(intent.createChooser(intent, "Selecciona perfil de imagen"), SELECT_PICTURE);
                    dialog.dismiss();
                } catch (Exception e) {
                    Toast.makeText(activity, "No hizo una selección.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //por medio de camara se crea un filepath para la ubicación de la foto esto se realiza para poder controlar la hubicación y calidad de la imagen
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    File filepath = Environment.getExternalStorageDirectory();
                    File dir = new File(filepath.getAbsolutePath() + "/tempoWolf/");
                    dir.mkdirs();


                    try {

                        String NOMEDIA = ".nomedia";
                        String nomedia = Environment.getExternalStorageDirectory() + File.separator + "tempoWolf/.nomedia";
                        File nomediaFile = new File(nomedia);
                        if (!nomediaFile.exists()) {
                            nomediaFile.createNewFile();
                        }
                        File Folder = new File(Environment.getExternalStorageDirectory() + "/tempoWolf");
                        if (Folder.mkdir()) {
                            nomediaFile = new File(Environment.getExternalStorageDirectory() + "/tempoWolf/" + NOMEDIA);
                            if (!nomediaFile.exists()) {
                                nomediaFile.createNewFile();
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        // Permission is not granted
                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            // Show an explanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.
                        } else {
                            // No explanation needed; request the permission
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                                    CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_PERFIL);

                            // MY_PERMISSIONS_REQUEST_WRITE_SETTINGS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    } else {
                        // Permission has already been granted

                        Log.d("---->","---->bien");

                        File file = new File(dir, "temp_" + timeStamp + ".jpeg");
                        imageUriPerfilString = dir + "/temp_" + timeStamp + ".jpeg";
                        imageUriPerfil = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".my.package.name.provider", file);

                       /* Intent intent = (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                                ? new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
                                : new Intent(MediaStore.ACTION_IMAGE_CAPTURE);*/

                        Intent intent;
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        } else {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                        }


                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriPerfil);

                        } else {

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                        }

                        imageUriPerfil = Uri.fromFile(file);
                        activity.startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_PERFIL);
                    }


                   /* Intent intent = (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                            ? new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
                            : new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    Log.d("------A", "------>" + Build.VERSION.SDK_INT);
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriPerfil);
                        Log.d("holitas", "golitas if");
                    } else {
                        Log.d("holitas", "golitas else");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    }

                    if (checkPermission(activity)) {
                        Log.e("permission", "Permission already granted.");
                    } else {
                        requestPermission(activity);
                    }

                    imageUriPerfil = Uri.fromFile(file);
                    activity.startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_PERFIL);*/
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Error e) {
                    e.printStackTrace();
                }

                dialog.dismiss();

            }
        });

        mCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_PERFIL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private boolean checkPermission(Activity act) {
        int result = ContextCompat.checkSelfPermission(act, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    private static final int PERMISSION_REQUEST_CODE = 1;
    private void requestPermission(Activity act) {

        ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);

    }



    //se ingresa la uri que se tiene del perfil
    public String  incrustarImagenPerfil(ImageView imageView){
        String imageurlReal = imageCompression.compressImage(imageUriPerfilString);
        imageUriPerfilString = imageurlReal;
        imageUriPerfil = Uri.fromFile(new File(imageurlReal));
        imageView.setImageURI(imageUriPerfil);

        return imageUriPerfilString;
    }

    //metodo par guardar la imagen de perfil
    public void guardarLocalPerfil(String path, int id){
        File dir = new File(path);

        //se veririfica la encuenta el ditectio por medio del path de ahi se recorre la carpeta
        //y se determina si existe un archivo con el id y la palabra perfil y elimina todas las que coincidan
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {

                if((path  + children[i]).contains("perfil") && (path  + children[i]).contains(String.valueOf(id)) ) {

                    try {
                        File file = new File(dir, children[i]);
                        file.delete();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        //por ultimo guarda la nueva imagen
        guardaNuevoPerfil(path, id);
    }

    //metodo para guardar la imagen nueva de perfil
    public void guardaNuevoPerfil(String path, int id){

        //se crea el file nuevo por medio del path y un nombre nuevo, la palabra perfil y el id
        String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String fileOutput = path +id+ "_perfil_" + timeStamp + ".jpeg";

        //se reinician los buffers
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //se crea el buffer de la imagen por url
            bis = new BufferedInputStream(new FileInputStream(imageUriPerfilString));
            //se genera el nuevo buffer
            bos = new BufferedOutputStream(new FileOutputStream(fileOutput, false));
            byte[] buf = new byte[1024];
            //se crea imagen nueva
            bis.read(buf);
            do {
                bos.write(buf);
            } while (bis.read(buf) != -1);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //se detiene un segundo para que estas puedan guardarse ya que actuan en segundo plano y al ser un bucle estas no alcanzan el tiempo para escribirse no se copian las iamgenes
        SystemClock.sleep(1000);
    }

    //se crea el multipart de la imagen de perfil
    public List<MultipartBody.Part> crearPartsPerfil(){
        File file = new File(imageUriPerfilString);
        final List<MultipartBody.Part> parts = new ArrayList<>();

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("fotos", file.getName(), reqFile);
        parts.add(body);

        return parts;
    }



    ///////////////////metodos para las multiples imagenes path real/////////////////////////////////////////////

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @author paulburke
     */
    public static String getPath(final Context context, final Uri uri) {

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if ( DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };


                    Log.d("ver12345",contentUri.toString());

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {

                // Return the remote address
                if (isGooglePhotosUri(uri))
                    return uri.getLastPathSegment();

                Log.d("ver123", uri.toString());

                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////





}

