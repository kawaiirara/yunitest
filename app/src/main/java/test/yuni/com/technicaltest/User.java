package test.yuni.com.technicaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

/**
 * Created by yuni on 10/4/17.
 */

public class User extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        try {
            JSONObject item = new JSONObject(getIntent().getStringExtra("data"));
            ((TextView) findViewById(R.id.user_name)).setText("" + item.get("name"));
            ((TextView) findViewById(R.id.user_username)).setText("" + item.get("username"));
            ((TextView) findViewById(R.id.user_email)).setText("" + item.get("email"));
            ((TextView) findViewById(R.id.user_phone)).setText("" + item.get("phone"));
            JSONObject address = item.getJSONObject("address");
            ((TextView) findViewById(R.id.user_street)).setText("" + address.get("street"));
            ((TextView) findViewById(R.id.user_suite)).setText("" + address.get("suite"));
            ((TextView) findViewById(R.id.user_city)).setText("" + address.get("city"));
            ((TextView) findViewById(R.id.user_zip)).setText("" + address.get("zipcode"));
            JSONObject company = item.getJSONObject("company");
            ((TextView) findViewById(R.id.company_name)).setText("" + company.get("name"));
            ((TextView) findViewById(R.id.company_catchPhrase)).setText("" + company.get("catchPhrase"));
            ((TextView) findViewById(R.id.company_bs)).setText("" + company.get("bs"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.user_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            JSONObject item = new JSONObject(getIntent().getStringExtra("data"));
            JSONObject address = item.getJSONObject("address");
            JSONObject geo = address.getJSONObject("geo");
            LatLng location = new LatLng(geo.getDouble("lat"), geo.getDouble("lng"));
            googleMap.addMarker(new MarkerOptions().position(location));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
