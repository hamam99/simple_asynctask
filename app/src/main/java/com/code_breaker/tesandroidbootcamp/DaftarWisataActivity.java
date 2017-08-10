package com.code_breaker.tesandroidbootcamp;

import android.animation.ObjectAnimator;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.code_breaker.tesandroidbootcamp.entity.Wisata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaftarWisataActivity extends AppCompatActivity {

    @BindView(R.id.wisata_list2)
    ListView listView;

    private Adapter adapter;
    private static String url = "http://www.erporate.com/bootcamp/jsonBootcamp.php";
    private ProgressDialog pDialog;
    private String TAG="TAG";
    private  ArrayList<Wisata> wisataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_wisata);
        ButterKnife.bind(this);

        wisataArrayList = new ArrayList<>();
        adapter = new Adapter(wisataArrayList, this);
        listView.setAdapter(adapter);

        new GetWisata().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Wisata d = wisataArrayList.get(i);

                Intent intent = new Intent(DaftarWisataActivity.this,WisataDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("nama",d.getNamaPariwisata());
                b.putString("alamat",d.getAlamatPariwisata());
                b.putString("deskripsi",d.getDetailPariwisata());
                b.putString("gambar",d.getGambarPariwisata());

                intent.putExtras(b);
                startActivity(intent);

            }
        });

    }

    private class GetWisata extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(DaftarWisataActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray wisatas = jsonObj.getJSONArray("data");

                    // looping through All Contacts
                    for (int i = 0; i < wisatas.length(); i++) {
                        JSONObject c = wisatas.getJSONObject(i);

                        String nama = c.getString("nama_pariwisata");
                        String alamat = c.getString("alamat_pariwisata");
                        String detail = c.getString("detail_pariwisata");
                        String gambar = c.getString("gambar_pariwisata");

                        Wisata d = new Wisata();
                        d.setGambarPariwisata(gambar);
                        d.setNamaPariwisata(nama);
                        d.setAlamatPariwisata(alamat);
                        d.setDetailPariwisata(detail);
                        wisataArrayList.add(d);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            adapter.notifyDataSetChanged();

        }

    }


}
