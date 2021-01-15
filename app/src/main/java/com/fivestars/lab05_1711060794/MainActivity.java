package com.fivestars.lab05_1711060794;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Helper> countryList = new ArrayList<>();
    ListView lAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lAddress = findViewById(R.id.lAddress);

        HelperAdapter adapter = new HelperAdapter();

        lAddress.setAdapter(adapter);

        lAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Helper country = countryList.get(position);

                Intent intent = new Intent(MainActivity.this, Edit.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CountryEdit", country);
                bundle.putInt("Position", position);
                intent.putExtras(bundle);
                startActivityForResult(intent, 2);
            }
        });
    }

    class HelperAdapter extends ArrayAdapter<Helper> {
        public HelperAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public HelperAdapter() {
            super(MainActivity.this, android.R.layout.simple_list_item_1, countryList);
        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, null);
            }

            Helper r = countryList.get(position);

            ((TextView) row.findViewById(R.id.title)).setText(r.getName());
            ((TextView) row.findViewById(R.id.content)).setText(r.getAddress() + " - " + r.getZipCode());
            ImageView icon = row.findViewById(R.id.icon);

            String type = r.getCountry();
            switch (type) {
                case "Viet Nam":
                    icon.setImageResource(R.drawable.vietnam);
                    break;
                case "United State":
                    icon.setImageResource(R.drawable.america);
                    break;
                case "Italy":
                    icon.setImageResource(R.drawable.italy);
                    break;
                case "United Kingdom":
                    icon.setImageResource(R.drawable.uk);
                    break;
            }

            return row;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.insert) {
            final int result = 1;
            startActivityForResult(new Intent(this, Insert.class), result);

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            Bundle bundle = data.getExtras();
            Helper country = (Helper) bundle.getSerializable("Country");
            countryList.add(country);
        } else {
            Bundle bundle = data.getExtras();
            boolean isEdit = bundle.getBoolean("isEdit");
            int position = bundle.getInt("Position");

            if (isEdit) {
                Helper country = (Helper) bundle.getSerializable("CountryEdit");
                countryList.get(position).setName(country.getName());
                countryList.get(position).setAddress(country.getAddress());
                countryList.get(position).setArea(country.getArea());
                countryList.get(position).setCountry(country.getCountry());
                countryList.get(position).setZipCode(country.getZipCode());
            }
        }

        lAddress.invalidateViews();
    }
}