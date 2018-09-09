package w3com.learn.snacksorder2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by USER01 on 4/2/2018.
 */

public class order extends android.support.v4.app.Fragment {
    TextView showData ;
    String s1="", s2="", s3="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Order");
        showData= view.findViewById(R.id.showData);
        fetchingData();
    }
    void  fetchingData(){
        String myUrl = "https://swarnabdcse.000webhostapp.com/menuToday.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                         s1 = jsonObject.getString("snack1")+"\n";
                         s2 = jsonObject.getString("snack2")+"\n";
                         s3 = jsonObject.getString("snack3")+"\n";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                showData.setText(s1+s2+s3);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log", error);
            }
        });
        w3com.learn.snacksorder2.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}