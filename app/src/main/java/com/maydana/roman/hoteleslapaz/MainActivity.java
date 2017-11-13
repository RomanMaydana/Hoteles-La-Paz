package com.maydana.roman.hoteleslapaz;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements HotelAdapter.onHotelSelectedListener {
    private RecyclerView mRecycleView;
    private HotelAdapter mHotelAdapter;
    private List<Hotel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = (RecyclerView) findViewById(R.id.recycle_main);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setHasFixedSize(true);


        mHotelAdapter = new HotelAdapter(this, this);
        llenarDatos();
        mRecycleView.setAdapter(mHotelAdapter);


    }

    private void llenarDatos() {
        list = new ArrayList<>();
        list.add(new Hotel(getString(R.string.hotel_presidente), getString(R.string.direc_presidente), 4.1, getString(R.string.num_presidente), getString(R.string.detalleHotelPresidente), R.drawable.hotel_presidente));
        list.add(new Hotel(getString(R.string.hotel_casa_grande_suites), getString(R.string.direc_casa_grande_suites), 4.5, getString(R.string.num_casa_grande_suites), getString(R.string.detalleCasaGrandeSuites), R.drawable.hotel_casa_grande_suites));
        list.add(new Hotel(getString(R.string.hotel_caminoCasaReal), getString(R.string.direc_caminoCasaReal), 4.4, getString(R.string.num_caminoCasaReal), getString(R.string.detalle_caminoCasaReal), R.drawable.hotel_camino_casa_real));
        list.add(new Hotel(getString(R.string.hotel_pirwaHostel), getString(R.string.direc_pirwHostel), 4.3, getString(R.string.num_pirwa), getString(R.string.detalle_hotelPirwa), R.drawable.hotel_pirwa_hostels));
        list.add(new Hotel(getString(R.string.hotel_calacoto),getString(R.string.direc_hotel_Calacoto),4.0,getString(R.string.num_hotelCalacoto),getString(R.string.detalle_hotelCalacoto),R.drawable.hotel_calacoto));
        list.add(new Hotel(getString(R.string.hotel_gloria),getString(R.string.direc_hotelGloria),3.7,getString(R.string.num_hotelGloria),getString(R.string.detalle_hotelGloria),R.drawable.hotel_gloria));
        list.add(new Hotel(getString(R.string.hotel_europa),getString(R.string.direc_hotelEuropa),4.3,getString(R.string.num_hotelEuropa),getString(R.string.detalle_hotelEuropa),R.drawable.hotel_europa));
        list.add(new Hotel(getString(R.string.hotel_rey),getString(R.string.dire_rey),4.0,getString(R.string.num_rey),getString(R.string.detalle_rey),R.drawable.hotel_rey));
        list.add(new Hotel(getString(R.string.hotel_panamerican),getString(R.string.direc_panamerican),3.8,getString(R.string.num_panamerican),getString(R.string.detalle_panamerican),R.drawable.hotel_panamerican));
        mHotelAdapter.setData(list);
    }

    @Override
    public void onHotelSelected(Hotel hotel) {
        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        intent.putExtra(getString(R.string.hotel), hotel);
        startActivity(intent);
    }

    @Override
    public void onHotelLongSelected(String numHotel) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Uri numero = Uri.parse("tel:" + numHotel);
            Intent intent = new Intent(Intent.ACTION_CALL, numero);
            startActivity(intent);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                Log.i("MIAPP", "Vaya a confirguraciones para autorizar llamada");

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 7);
            }
        }
    }

}
