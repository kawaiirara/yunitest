package test.yuni.com.technicaltest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by yuni on 10/3/17.
 */

public class Users extends Fragment {
    UsersAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.users, null);
        ListView list = view.findViewById(R.id.users_list);
        adapter = new UsersAdapter(getActivity(), R.layout.users_item);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent intent = new Intent(getActivity(), User.class);
                intent.putExtra("data", adapter.getItem(index).toString());
                startActivity(intent);
            }
        });

        loadUsers();
        return view;
    }

    private void loadUsers() {
        API.getUsers(new API.Callback() {
            @Override
            public void onFinish(JSONArray result) {
                for (int i = 0; i < result.length(); i++) {
                    try {
                        adapter.add(result.getJSONObject(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    class UsersAdapter extends ArrayAdapter<JSONObject> {

        public UsersAdapter(Context context, int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = getActivity().getLayoutInflater().inflate(R.layout.users_item, null);
            try {
                JSONObject item = getItem(position);
                ((TextView) v.findViewById(R.id.users_item_name)).setText("" + item.get("name"));
                ((TextView) v.findViewById(R.id.users_item_username)).setText("" + item.get("username"));
                ((TextView) v.findViewById(R.id.users_item_email)).setText("" + item.get("email"));
                ((TextView) v.findViewById(R.id.users_item_phone)).setText("" + item.get("phone"));
            } catch (Exception e) {

            }
            return v;
        }
    }
}
