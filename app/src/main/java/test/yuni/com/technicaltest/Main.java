package test.yuni.com.technicaltest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends AppCompatActivity {

    private DrawerLayout drawer;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        drawer = (DrawerLayout) findViewById(R.id.main);
        list = (ListView) findViewById(R.id.main_left_drawer);

        MenuAdapter adapter = new MenuAdapter(Main.this, R.layout.menu);
        adapter.add(new MenuItem("", "Users"));
        adapter.add(new MenuItem("", "Posts"));
        adapter.add(new MenuItem("", "Settings"));
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (index == 0) {
                    setTitle("Users");
                    transaction.replace(R.id.main_content, new Users());
                } else if (index == 1) {
                    setTitle("Posts");
                    transaction.replace(R.id.main_content, new Posts());
                } else if (index == 2) {
                    setTitle("Settings");
                    transaction.replace(R.id.main_content, new Settings());
                }
                transaction.commit();
                drawer.closeDrawer(Gravity.LEFT);
            }
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        setTitle("Users");
        transaction.replace(R.id.main_content, new Users());
        transaction.commit();
    }

    class MenuAdapter extends ArrayAdapter<MenuItem> {

        public MenuAdapter(Context context, int resource) {
            super(context, resource);
        }
    }

    class MenuItem {
        String icon;
        String name;
        String description;

        public MenuItem(String icon, String name) {
            this.icon = icon;
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
