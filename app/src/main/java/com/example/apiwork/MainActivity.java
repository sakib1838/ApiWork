package com.example.apiwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.apiwork.Backend.Users;

import java.util.ArrayList;

import com.example.apiwork.Backend.DataHold;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

 //   private String TAG = MainActivity.class.getSimpleName();
 //   private ListView lv;
 // ArrayList<HashMap<String, String>> contactList;

    ArrayList<Users> usersArrayList=new ArrayList<Users>();
    Button btn,btn2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= findViewById(R.id.getvalue);
        btn2=findViewById(R.id.grid);
        //contactList = new ArrayList<>();
       // lv = (ListView) findViewById(R.id.list);

       // new GetContacts().execute();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ApiValueListActivity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new getData().execute();
            }
        });

    }

//    private class GetContacts extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            HttpHandler sh = new HttpHandler();
//            // Making a request to url and getting response
//            String url = "http://sajidur.com/BloodApp/getdonorlist.php";
//            String jsonStr = sh.makeServiceCall(url);
//            Log.e(TAG, "Response from url: " + jsonStr);
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);
//
//                    // Getting JSON Array node
//                    JSONArray contacts = jsonObj.getJSONArray("Donor_Data");
//
//                    // looping through All Contacts
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//                        //String id = c.getString("id");
//                        String name = c.getString("Name");
//                        String email = c.getString("Email");
//                        String bloodGroup = c.getString("BloodGroup");
//                        String phone = c.getString("Phone");
//
//                        // Phone node is JSON Object
//
//
//                        // tmp hash map for single contact
//                        HashMap<String, String> contact = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        //contact.put("id", id);
//                        contact.put("Name", name);
//                        contact.put("Email", email);
//                        contact.put("BloodGroup", bloodGroup);
//                        contact.put("Phone",phone);
//
//
//                        // adding contact to contact list
//                        contactList.add(contact);
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//                }
//
//            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList,
//                    R.layout.list_item, new String[]{ "Email","Phone"},
//                    new int[]{R.id.email, R.id.mobile});
//            lv.setAdapter(adapter);
//        }
//    }


    class getData extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            getVolley();
            return null;
        }

        private void getVolley(){
          //  String URLline="http://sajidur.com/BloodApp/getdonorlist.php";
            String URLline="http://msrkmstest-001-site1.gtempurl.com/Api/Lab";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    parseData(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("error"+error.toString());
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);
        }

        public void parseData(String response){
            System.out.println("Response"+response);
            try{
                //JSONObject jsonObject=new JSONObject(response);
                //JSONArray dataArray = jsonObject.getJSONArray("Donor_Data");
                JSONArray dataArray = new JSONArray(response);

                for(int i=0;i<dataArray.length();i++){

                    JSONObject dataobj=dataArray.getJSONObject(i);
                    Users users = new Users();
                    users.setName(dataobj.getString("labName"));
                    users.setPhone(dataobj.getString("id"));
                    System.out.println("testuser"+users.getName());
                    usersArrayList.add(users);
                }
                if(!(DataHold.usersArrayList==null)){
                    DataHold.usersArrayList.clear();
                }
                DataHold.usersArrayList=usersArrayList;
                System.out.println("test"+usersArrayList.size());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        @Override
        protected void onPostExecute(String s) {
            Intent intent = new Intent(MainActivity.this,Loading.class);
            startActivity(intent);
        }
    }



}
