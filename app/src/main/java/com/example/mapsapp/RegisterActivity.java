package com.example.mapsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

import com.example.mapsapp.personajes.Pokemon;
import com.example.mapsapp.services.PokemonService;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    static final int REQUEST_PICK_FROM_GALLERY = 2;

    private Button btnGallery;
    private Button btnEnviar;
    private Button btnCancelar;
    private ImageView imageView;
    public String fileBase64;
    private EditText etName;
    private EditText etType;
    private EditText etLatitude;
    private EditText etLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA}, 1000);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
        }

        btnGallery = findViewById(R.id.btnGallery);
        imageView = findViewById(R.id.imgCamera);
        etName = findViewById(R.id.etNombre);
        etType = findViewById(R.id.etTipo);
        etLatitude = findViewById(R.id.etLatitud);
        etLongitude = findViewById(R.id.etLongitud);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnCancelar = findViewById(R.id.btnCancelar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService service = retrofit.create(PokemonService.class);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryAddPic();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pokemon pokemon = new Pokemon();
                pokemon.setNombre(etName.getText().toString());
                pokemon.setTipo(etType.getText().toString());
                pokemon.setImagen(fileBase64);
                pokemon.setLatitude(Float.parseFloat(etLatitude.getText().toString()) * -1);
                pokemon.setLongitude(Float.parseFloat(etLongitude.getText().toString()) * -1);

                Call<Pokemon> call = service.create(pokemon);

                call.enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        Intent intent = new Intent(RegisterActivity.this, RecyclerViewActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                    }
                });
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void galleryAddPic() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, REQUEST_PICK_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_PICK_FROM_GALLERY && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            //Añadir a base64
            fileBase64 = gtFile(picturePath);

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
            bmp.compress(Bitmap.CompressFormat.PNG,100, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.NO_WRAP);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encodeString;
    }

}