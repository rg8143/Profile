package com.example.profile;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerViewProfile;
    RecyclerView.LayoutManager layoutManagerProfile;
    RecyclerView.Adapter adapterProfile;

    private List<ProfileData> ProfileList;
    //Volley Request Queue (it will run automatically in background)
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewProfile = (RecyclerView) findViewById(R.id.recycler_view_profile);
        layoutManagerProfile = new LinearLayoutManager(getApplicationContext());
        recyclerViewProfile.setLayoutManager(layoutManagerProfile);


        //Initializing  profile list
        ProfileList = new ArrayList<>();

        //initializing  adapter
        adapterProfile = new RecyclerAdapter(ProfileList,this);
        recyclerViewProfile.setAdapter(adapterProfile);

        requestQueue = Volley.newRequestQueue(this);

        //Calling method to get data from the api
        getDataFromServer();

    }

    /**
     * A request dispatch queue with a thread pool of dispatchers.
     *
     * Calling { add(Request)} will enqueue the given Request for dispatch,
     * resolving from either cache or network on a worker thread, and then delivering
     * a parsed response on the main thread.
     */


//As This Is Done using volley so all the network request will be in a separate thread automatically running in background

    private void getDataFromServer() {
        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://test-api.nevaventures.com/",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        // Process the JSON
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("data");
                            parseData(array);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.d("in response",error.toString());

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }


    //This method will parse json data
    private void parseData(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {
            ProfileData profile = new ProfileData();
            JSONObject json;
            try {
                //Getting json
                json = array.getJSONObject(i);

                if(json.has("image")|| !json.isNull("image"))
                profile.setImageUrl(json.getString("image"));
                else
                    profile.setImageUrl("https://goo.gl/EyCMmB");
                if(json.has("id")|| !json.isNull("id"))
                profile.setId(json.getString("id"));
                else
                    profile.setId("0");
                if(json.has("name")|| !json.isNull("name"))
                profile.setName(json.getString("name"));
                else
                    profile.setName("Name");
                if(json.has("skills")|| !json.isNull("skills"))
                profile.setSkill(json.getString("skills"));
                else
                    profile.setSkill("Skills");
            } catch (JSONException e) {
                   Log.d("in parse data",e.toString());
                e.printStackTrace();
            }
            ProfileList.add(profile);
        }

        //Notifying the adapter that data has been added or changed
        adapterProfile.notifyDataSetChanged();
    }



}
