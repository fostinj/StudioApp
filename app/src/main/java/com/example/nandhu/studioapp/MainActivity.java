package com.example.nandhu.studioapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.aboutpage.AboutPage;

public class MainActivity extends AppCompatActivity {

    StudioList matches;
    RecyclerviewAdapter adapter;
    RecyclerView recyclerView;
    ImageView about;
    List<StudioList> list = new ArrayList<StudioList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        about=(ImageView) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,About.class));

            }
        });
        final ProgressDialog progress=new ProgressDialog(MainActivity.this);
        progress.setMessage("Loading Gallery");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://conserving-gravel.000webhostapp.com/misc/fostin/getAll.php",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONArray obj = new JSONArray(response);
                            /*Toast.makeText(MainActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();

                            JSONArray jsonArray = obj.getJSONArray("pdfs");*/

                            for (int i=obj.length()-1; i >=0; i--) {

                                JSONObject person = obj.getJSONObject(i);



                                matches=new StudioList(person.getString("caption"),"https://conserving-gravel.000webhostapp.com/misc/fostin/"+person.getString("path"));
                                list.add(matches);

                            }

                            adapter = new RecyclerviewAdapter(MainActivity.this,list,recyclerView);
                            final LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);
                            progress.dismiss();

                        } catch (JSONException e) {
                            progress.dismiss();
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.dismiss();
                        Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


        );

        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);


    }
}
