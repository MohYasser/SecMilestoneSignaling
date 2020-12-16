package com.example.shoppingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends AppCompatActivity {

  //  private Toolbar mToolbar;
//    private ActionBar mActionBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Product> products;
    private static final String TAG = RecyclerAdapter.class.getSimpleName();

    private static  final String BASE_URL = "http://192.168.43.121/android_login_api/GetProducts.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        products = new ArrayList<>();

        getProducts();

        mAdapter = new RecyclerAdapter(ProductList.this, products);
        recyclerView.setAdapter(mAdapter);
    }

    private void getProducts (){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){
                                Log.d(TAG, "88888888888888888888888888888888888888 " +response);

                                JSONObject object = array.getJSONObject(i);

                                String product_name = object.getString("product_name");
                                String description = object.getString("description");
                                String image_url = object.getString("image_url");

                                Product product = new Product(product_name, description, image_url);
                                products.add(product);
                            }

                        }catch (Exception e){
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });


        Volley.newRequestQueue(this).add(stringRequest);
    }

}
