package com.maydana.roman.hoteleslapaz;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 11/11/2017.
 */

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    private Context context;
    private List<Hotel> dataset;
    private onHotelSelectedListener onHotelSelectedListener;

    public interface onHotelSelectedListener {
        void onHotelSelected(Hotel hotel);
        void onHotelLongSelected(String numHotel);
    }

    public HotelAdapter(Context context, HotelAdapter.onHotelSelectedListener onHotelSelectedListener) {
        this.context = context;
        this.onHotelSelectedListener = onHotelSelectedListener;
        dataset = new ArrayList<>();
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }


    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        Hotel hotel = dataset.get(position);
        holder.imageView.setImageResource(hotel.getFoto());
        holder.nombreTextView.setText(hotel.getNombre());

        holder.setOnHotelSelectedListener(hotel,onHotelSelectedListener);
        holder.setOnHotelLongSelectedListener(hotel.getNumero(),onHotelSelectedListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setData(List<Hotel> hotel){
        if (hotel == null){
            dataset = new ArrayList<>();
        }
        else{
            dataset = hotel;
        }
        notifyDataSetChanged();
    }


    @Override
    public void onViewAttachedToWindow(HotelViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCircularReveal(holder.itemView);
    }

    private void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int starRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, starRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        View carView;
        TextView nombreTextView;
        ImageView imageView;

        public HotelViewHolder(View itemView) {
            super(itemView);

            carView = itemView.findViewById(R.id.card_item);
            imageView = itemView.findViewById(R.id.imagen_item);
            nombreTextView = itemView.findViewById(R.id.nombre_textView_item);
        }

        public void setOnHotelSelectedListener(final Hotel hotel, final onHotelSelectedListener onHotelSelectedListener) {
            carView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onHotelSelectedListener.onHotelSelected(hotel);
                }
            });
        }

        public void setOnHotelLongSelectedListener(final String numHotel, final onHotelSelectedListener onHotelSelectedListener) {
            carView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onHotelSelectedListener.onHotelLongSelected(numHotel);
                    return true;
                }
            });

        }
    }
}
