package com.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Continents> {
    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<Continents> items){
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_quoc_gia, null);
        }
        Continents q = getItem(position);
        if (q!= null) {
            //Anh xa + Gan gia tri
            TextView tt1 = (TextView) v.findViewById(R.id.tVTen);
            tt1.setText(q.ten);
            TextView tt2 = (TextView) v.findViewById(R.id.tVmota);
            tt2.setText(q.mota);
            ImageView imgv = (ImageView)  v.findViewById(R.id.imgViewHinh);
            Picasso.get().load(q.hinhanh).into(imgv);
        }
        return v;
    }
}
