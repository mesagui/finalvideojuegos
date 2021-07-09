package com.example.mapsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mapsapp.services.EntrenadorAPIService;

import java.io.ByteArrayOutputStream;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormCrearEntrenadorActivity extends AppCompatActivity {

    ImageView mPicture;
    Button btnCamera;
    Button btnGalery;
    Button btnSave;
    EditText txtNombre;
    EditText txtPueblo;
    String imgBase64;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_crear_entrenador);

        solicitarPermisos();


        mPicture = findViewById(R.id.imgEntrenador);

        btnCamera = findViewById(R.id.btnOpenCamera);
        btnGalery = findViewById(R.id.btnOpenGalery);
        btnSave = findViewById(R.id.btnGuardarEntrenador);

        txtNombre = findViewById(R.id.txtNameEntrenador);
        txtPueblo = findViewById(R.id.txtPuebloEntrenador);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EntrenadorAPIService entrenadorAPIService = retrofit.create(EntrenadorAPIService.class);

        //llamar a la camara
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                startActivityForResult( intent,101);
            }
        });


        //Llamar desde Ubicaion
        btnGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult( intent, 102);
            }
        });

        //Guardar
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = txtNombre.getText().toString();
                String pueblo = txtPueblo.getText().toString();



            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("Main_APP", "Resultado de INTENT");

      if (requestCode == 101 &&  resultCode == RESULT_OK){
            //setear desde la camara
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap)extras.get("data");

            //setear imagen
            mPicture.setImageBitmap(bitmap);
        }


        // CARGAR DESDE UBICACION
        if(requestCode == 102 && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            mPicture.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            //Añadir a base64
            imgBase64 = gtFile(picturePath);
            Log.i("MY_APP",imgBase64);
        }


    }


    public static String gtFile(String filePath){
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try{
            bmp = BitmapFactory.decodeFile(filePath);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG,102, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encodeString;
    }


    private void solicitarPermisos() {

        if (checkSelfPermission( Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{ Manifest.permission.CAMERA}, 10001);
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 10002);
        }
    }
}