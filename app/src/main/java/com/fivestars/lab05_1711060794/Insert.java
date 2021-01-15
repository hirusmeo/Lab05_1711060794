package com.fivestars.lab05_1711060794;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Insert extends AppCompatActivity {
    private EditText name, address, zipCode;
    private Spinner spnCountry;
    private RadioGroup rCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Button btnInsert = findViewById(R.id.btnInsert);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        zipCode = findViewById(R.id.zipCode);
        spnCountry = findViewById(R.id.spnCountry);
        rCountry = findViewById(R.id.rCountry);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {

                if (validate()) {
                    Helper country = new Helper();
                    country.setName(name.getText().toString());
                    country.setAddress(address.getText().toString());
                    country.setCountry(spnCountry.getSelectedItem().toString());
                    country.setZipCode(zipCode.getText().toString());

                    switch (rCountry.getCheckedRadioButtonId()) {
                        case R.id.rDomestic:
                            country.setArea("Domestic");
                            break;
                        case R.id.rForeign:
                            country.setArea("Foreign");
                            break;
                    }

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Country", country);
                    intent.putExtras(bundle);
                    setResult(1, intent);
                    finish();
                } else {
                    Toast.makeText(Insert.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validate() {
        return !name.getText().toString().matches("") &&
                !address.getText().toString().matches("") &&
                !zipCode.getText().toString().matches("");
    }
}