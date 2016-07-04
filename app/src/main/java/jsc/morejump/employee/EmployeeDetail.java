package jsc.morejump.employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EmployeeDetail extends AppCompatActivity  implements View.OnClickListener{
    TextView txtGender, txtBirthDay, txtName, txtBirthPlace;
    ImageView image;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        // connecting components to layout :))
        txtBirthDay = (TextView) findViewById(R.id.txtBirthDay);
        txtBirthPlace= (TextView) findViewById(R.id.txtBirthPlace);
        txtGender= (TextView) findViewById(R.id.txtGender);
        txtName = (TextView) findViewById(R.id.txtName);
        image = (ImageView) findViewById(R.id.imgProfile);
        btnBack = (Button) findViewById(R.id.btnBack);
        // assgining object
        image.setImageResource(R.drawable.profile);
        // get intent and package
        Intent intent = getIntent();
        Bundle bundle=intent.getBundleExtra("Mypackage");
        txtBirthDay.setText(bundle.getString("birth"));
        txtName.setText(bundle.getString("name"));
        txtGender.setText(bundle.getString("gender"));
        txtBirthPlace.setText(bundle.getString("place"));
        // listening event
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent myItent = new Intent(this, MainActivity.class);
        startActivity(myItent);
    }
}
