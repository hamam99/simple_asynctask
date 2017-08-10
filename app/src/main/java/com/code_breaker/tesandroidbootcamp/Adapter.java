package com.code_breaker.tesandroidbootcamp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.code_breaker.tesandroidbootcamp.entity.Wisata;

import java.util.List;

/**
 * Created by code_breaker on 8/10/17.
 */

    public class Adapter extends ArrayAdapter<Wisata> {

        private List<Wisata> itemList;
        private Context context;

        public Adapter(List<Wisata> itemList, Context ctx) {
            super(ctx, android.R.layout.simple_list_item_1, itemList);
            this.itemList = itemList;
            this.context = ctx;
        }

        public int getCount() {
            if (itemList != null)
                return itemList.size();
            return 0;
        }

        public Wisata getItem(int position) {
            if (itemList != null)
                return itemList.get(position);
            return null;
        }



        public long getItemId(int position) {
            if (itemList != null)
                return itemList.get(position).hashCode();
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.wisata, null);
            }

            Wisata c = itemList.get(position);
            TextView text = (TextView) v.findViewById(R.id.wisata_nama);
            text.setText(c.getNamaPariwisata());

/*
            ImageView gambar = v.findViewById(R.id.wisata_icon);
            String url = c.getGambarPariwisata();
            Picasso.with(context).load(url).into(gambar);
*/
            return v;

        }

        public List<Wisata> getItemList() {
            return itemList;
        }

        public void setItemList(List<Wisata> itemList) {
            this.itemList = itemList;
        }
    }
