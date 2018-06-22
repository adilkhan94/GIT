package com.adilkhan.githubrepositorieslist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adilkhan.githubrepositorieslist.Adapter.RecyclerAdapter;
import com.adilkhan.githubrepositorieslist.Database.DatabaseOperation;
import com.adilkhan.githubrepositorieslist.Model.SingleRow;
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
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CheckInternet.CheckInternetListener{

    RecyclerView recyclerView;
    ArrayList<SingleRow> list;

    Toolbar toolbar;
    ProgressBar progressBar;
    ProgressDialog dialog;

    LinearLayoutManager layoutManager;
    SwipeRefreshLayout mSwipeRefreshLayout;

    int pageindex=1;
    String TAG="Adil";
    RecyclerAdapter adapter;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;

    private DatabaseOperation databaseOperation;
    ArrayList<SingleRow> lists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);

        toolbar=findViewById(R.id.toolbar);
        progressBar=findViewById(R.id.progress);

        mSwipeRefreshLayout=findViewById(R.id.swipetoRefresh);


        dialog=new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please wait...");
        dialog.show();

        databaseOperation = new DatabaseOperation(this);





        checkConnection();








        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                Toast.makeText(MainActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();

                checkConnection();

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });




        toolbar.setTitle("Jake's Git");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));




        list=new ArrayList<>();

        layoutManager= new LinearLayoutManager(MainActivity.this, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setHasFixedSize(true);





        adapter = new RecyclerAdapter(MainActivity.this, list);


        recyclerView.setAdapter(adapter);













        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {

                try{
                    if(dy > 0) //check for scroll down
                    {
                        visibleItemCount = layoutManager.getChildCount();
                        totalItemCount = layoutManager.getItemCount();
                        pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();



                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                             pageindex=pageindex+1;



                            if (pageindex==9)
                            {

                                    Toast.makeText(MainActivity.this, "No more data available", Toast.LENGTH_SHORT).show();

                                  pageindex=8;
                            }

                                else
                                {
                                    progressBar.setVisibility(View.VISIBLE);
                                    getDataFromVolley(pageindex);

                                }

                            }

                    }
                }
                catch (Exception e){
                   Log.d(TAG,e.getMessage());
                }

            }
        });







    }

    private void getDataFromVolley(final int pageindex) {

        String url = "https://api.github.com/users/JakeWharton/repos?page=" + pageindex + "&per_page=15";

             StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    dialog.dismiss();

                    progressBar.setVisibility(View.INVISIBLE);


                    fetchJsonData(response);




                }
            },
                    new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            progressBar.setVisibility(View.INVISIBLE);
                            Log.d(TAG, "msg " + error.getMessage());

                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);
        }


    private void fetchJsonData(String response) {


     databaseOperation.open();

        try {
            JSONArray array=new JSONArray(response);

            for(int i=0;i<array.length();i++)
            {

                SingleRow singleRow=new SingleRow();

                JSONObject object=array.getJSONObject(i);

                JSONObject imageObject= object.getJSONObject("owner");

                singleRow.setName(object.getString("name"));
                singleRow.setDescription(object.getString("description"));
                singleRow.setLanguage(object.getString("language"));
                singleRow.setOpen_issues(object.getString("open_issues"));
                singleRow.setWatchers(object.getString("watchers"));
                singleRow.setAvatar_url(imageObject.getString("avatar_url"));

                list.add(singleRow);

                databaseOperation.addSingleRow(singleRow);
               //


 }




            //



            adapter.notifyDataSetChanged();



            databaseOperation.close();





        }



        catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        if (isConnected)
        {

            getDataFromVolley(pageindex);





        } else {


            dialog.dismiss();

            databaseOperation.open();

            lists= databaseOperation.getAllEmployees();

            databaseOperation.close();

            if (lists.size() > 0) {


                adapter = new RecyclerAdapter(MainActivity.this, lists);
                recyclerView.setAdapter(adapter);

            }

         Toast.makeText(this, "No Internet "+"\n"+"swipe down", Toast.LENGTH_SHORT).show();

        }
    }
    public void checkConnection(){
        boolean isConnected=CheckInternet.isConnected();
        showSnack(isConnected);
    }



}
