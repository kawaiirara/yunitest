package test.yuni.com.technicaltest;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yuni on 10/3/17.
 */

public class API extends AsyncTask<Void, Void, JSONArray> {

    private Callback callback;

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public static void getUsers(Callback callback) {
        API api = new API();
        api.setCallback(callback);
        api.execute();
    }

    public static interface Callback {
        public void onFinish(JSONArray result);
    }


    @Override
    protected JSONArray doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://jsonplaceholder.typicode.com/users");

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            StringBuilder s = new StringBuilder();
            int l = 0;
            byte[] b = new byte[256];
            while ((l = in.read(b)) > 0) {
                s.append(new String(b, 0, l));
            }
            return new JSONArray(s.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        callback.onFinish(result);
    }


}
