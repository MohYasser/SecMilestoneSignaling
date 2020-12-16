package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopList extends AppCompatActivity {

    private static  final String BASE_URL_links = "http://192.168.43.121/android_login_api/GetLinks.php";
    private static  final String BASE_URL_shops = "http://192.168.43.121/android_login_api/GetShops.php";

    RecyclerView.Adapter mAdapter=null;

    private List<Link> links;
    private ArrayList<Integer> shop_ids;
    private ArrayList<Double> shop_prices;
    private ArrayList<String> shop_sp_offers;
    private List<Shop> shops;

    private String[] shop_distances;

    private List<Shop_enhanced> fds;

    private TextView mShopName, mPrice, mSpOffers, mDistance;
    private static final String TAG = RecyclerAdapter.class.getSimpleName();

    public ShopList() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        //catching intent
        Intent intent = getIntent();

        if (intent !=null){
            RecyclerView detailedView = findViewById(R.id.shops_recyclerView);
            detailedView.setHasFixedSize(true);
            detailedView.setLayoutManager(new LinearLayoutManager(this));

            getLinks();
            String id2=getIntent().getStringExtra("id");
            //getting product id from the last clicked product
            int product_id = 0;
            try {
                product_id= Integer.parseInt(id2);
            }catch(NumberFormatException e){

            }
            //getting all shops ids of this product
            for (Link i : links) {
                if(product_id==i.getProduct_id()){
                    shop_ids.add(i.getShop_id());
                    shop_prices.add(i.getPrice());
                    shop_sp_offers.add(i.getSp_offers());
                }
            }

            //getting all shop names and putting them in a normal list
            //we do this by connecting to the shop sql table and getting the names using the ids
            getShops();

            String[] shop_names = new String[shop_ids.size()];
            float[] shop_lat = new float[shop_ids.size()];
            float[] shop_long = new float[shop_ids.size()];
            int k=0;
            for(Integer j : shop_ids){
                int x = (int)j; //get x, which is the id number of shop j
                for (Shop i : shops) { //looping over all the shops we want to get:
                    if(x==i.getId()){ //the shop that has one of the ids in shop_ids and put that shop's name
                        Log.d(TAG, "333333333333333333333333333"+i.getShop_name());
                        shop_names[k] = i.getShop_name(); // in the shop_names array

                        shop_lat[k] = i.getLattitude(); // and put its lat in the shop_lat array
                        shop_long[k] = i.getLongitude(); // and put its long in the shop_long array
                        k++;
                        return;
                    }
                }
            }
            //now we have shop_names, lats, longs array of all the shop names
            // that have product with the old product id
            //now we want to view name, price, sp_offers and distance

            //calculating the distance from user to each shop:
            //getting the user's lat and long:
            float user_lat = (float)1.235;
            float user_long = (float)1.5335;

            float dis_lat;
            float dis_long;

            for(int m=0; m<k; m++){
                dis_lat = shop_lat[m]-user_lat;
                dis_long = shop_long[m]-user_long;
                String distance = "("+dis_lat + ", " +dis_long+")";
                shop_distances[m] = distance;
            }

           for(int i=0; i<shop_ids.size(); i++){
                Shop_enhanced x = new Shop_enhanced();
                x.setShop_name(shop_names[i]);
                x.setShop_distance(shop_distances[i]);
                x.setShop_price(shop_prices.get(i));
                x.setShop_sp_offers(shop_sp_offers.get(i));

                fds.add(x);
            }


            mAdapter = new DetailedAdapter(ShopList.this, fds);
           detailedView.setAdapter(mAdapter);


        }

    }


    private void getLinks(){
        new StringRequest(Request.Method.GET, BASE_URL_links,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);

                                int shop_id = Integer.parseInt(object.getString("shop_ID"));
                                int product_id = Integer.parseInt(object.getString("product_ID"));
                                double price = Double.parseDouble(object.getString("price"));
                                String sp_offers = object.getString("sp_offers");

                                Link link = new Link(shop_id, product_id, price, sp_offers);
                                links.add(link);
                            }

                        } catch (Exception e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShopList.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getShops (){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL_shops,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String shop_name = object.getString("shop_name");
                                float lattitude = Float.parseFloat(object.getString("lattitude"));
                                float longitude = Float.parseFloat(object.getString("longitude"));

                                Shop shop = new Shop(shop_name, lattitude, longitude);
                                shops.add(shop);
                            }

                        }catch (Exception e){

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ShopList.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
}