package com.fivestars.lab05_1711060794;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Edit extends AppCompatActivity {
    private EditText nameEdit, addressEdit, zipCodeEdit;
    private Spinner spnCountryEdit;
    private RadioGroup rCountryEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button btnEdit = findViewById(R.id.btnEdit);
        nameEdit = findViewById(R.id.nameEdit);
        addressEdit = findViewById(R.id.addressEdit);
        zipCodeEdit = findViewById(R.id.zipCodeEdit);
        spnCountryEdit = findViewById(R.id.spnCountryEdit);
        rCountryEdit = findViewById(R.id.rCountryEdit);

        Bundle bundle = getIntent().getExtras();
        Helper country = (Helper) bundle.getSerializable("CountryEdit");
        int position = bundle.getInt("Position");

        nameEdit.setText(country.getName());
        addressEdit.setText(country.getAddress());
        zipCodeEdit.setText(country.getZipCode());

        if (country.getArea().equals("Foreign"))
            rCountryEdit.check(R.id.rForeignEdit);
        else
            rCountryEdit.check(R.id.rDomesticEdit);

        int pos = ((ArrayAdapter<String>) spnCountryEdit.getAdapter()).getPosition(country.getCountry());
        spnCountryEdit.setSelection(pos);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper country = new Helper();
                country.setName(nameEdit.getText().toString());
                country.setAddress(addressEdit.getText().toString());
                country.setCountry(spnCountryEdit.getSelectedItem().toString());
                country.setZipCode(zipCodeEdit.getText().toString());

                switch (rCountryEdit.getCheckedRadioButtonId()) {
                    case R.id.rDomesticEdit:
                        country.setArea("Domestic");
                        break;
                    case R.id.rForeignEdit:
                        country.setArea("Foreign");
                        break;
                }

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("CountryEdit", country);
                bundle.putInt("Position", position);
                bundle.putBoolean("isEdit", true);
                intent.putExtras(bundle);
                setResult(2, intent);
                finish();
            }
        });
    }
}