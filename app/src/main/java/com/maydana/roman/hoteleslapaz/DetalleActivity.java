package com.maydana.roman.hoteleslapaz;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView nombreTxt,direccionTxt,numeroTxt,detalleTxt;
    private RatingBar ratingBar;
    private String numeroTel,direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        init();

        Bundle bundle = getIntent().getExtras();
        Hotel hotel = (Hotel) bundle.getSerializable(getString(R.string.hotel));

        llenar(hotel);


    }

    private void llenar(Hotel hotel) {
        imageView.setImageResource(hotel.getFoto());
        nombreTxt.setText(hotel.getNombre());
        numeroTel = hotel.getNumero().toString();
        direccion = hotel.getDireccion();
        direccionTxt.setText(direccionTxt.getText()+" "+hotel.getDireccion());
        numeroTxt.setText(numeroTxt.getText()+" "+hotel.getNumero());
        detalleTxt.setText(hotel.getDetalle());
        ratingBar.setRating((float) hotel.getEstrellas());
    }

    private void init() {
        imageView = (ImageView)findViewById(R.id.imagen_detalle);
        nombreTxt = (TextView)findViewById(R.id.nombre_detalle);
        direccionTxt = (TextView)findViewById(R.id.direccion_detalle);
        numeroTxt = (TextView)findViewById(R.id.telefono_detalle);
        detalleTxt =(TextView)findViewById(R.id.detalleHotel_detalle);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar_detalle);
    }

    public void direccion(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q="+nombreTxt.getText().toString()+direccion));
        startActivity(intent);
    }

    public void telefono(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Uri numero = Uri.parse("tel:" + numeroTel);
            Intent intent = new Intent(Intent.ACTION_CALL, numero);
            startActivity(intent);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                Log.i(getString(R.string.mi), getString(R.string.vaya_a_configuraciones));

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 7);
            }
        }
    }


}
