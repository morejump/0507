package jsc.morejump.employee;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Date date;
    Calendar calendar;
    Button btnSave;
    EditText edtName;
    TextView edtBithDay;
    RadioButton rdoGay, rdoMale, rdoFemale;
    RadioGroup radioGroup;
    private int mYear, mMonth, mDay;
    Spinner spinner;
    ArrayList<String> lstData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // connecting components to layout---------
        rdoFemale = (RadioButton) findViewById(R.id.rdoFemale);
        rdoGay = (RadioButton) findViewById(R.id.rdoGay);
        rdoMale = (RadioButton) findViewById(R.id.rdoMale);
        radioGroup = (RadioGroup) findViewById(R.id.Gender);
        rdoGay.setChecked(true);
        edtName = (EditText) findViewById(R.id.edtName);
        edtBithDay = (TextView) findViewById(R.id.edtBirthDay);
        btnSave = (Button) findViewById(R.id.btnSave);
        spinner = (Spinner) findViewById(R.id.BirthPlace);
        //------------------------------------
        // list string
        lstData = new ArrayList<>();
        lstData.add("Hà Nội");
        lstData.add("Hồ Chí Minh");
        lstData.add("Bắc Ninh");
        lstData.add("Đà Nẵng");
        // initializing adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, lstData);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(arrayAdapter);
        // listening event -----------
        btnSave.setOnClickListener(this);
        edtBithDay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edtBirthDay:
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                edtBithDay.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btnSave:
                String name = edtName.getText().toString();
                String birthDay = edtBithDay.getText().toString();
                String gender="lalala";
                String place= (String) spinner.getSelectedItem();
                if (name != null && !name.isEmpty()) {
                    int idRdo = radioGroup.getCheckedRadioButtonId();
                    switch (idRdo) {
                        case R.id.rdoFemale:
                            gender= rdoFemale.getText().toString();
                            break;
                        case R.id.rdoGay:
                            gender= rdoGay.getText().toString();
                            break;
                        case R.id.rdoMale:
                            gender= rdoMale.getText().toString();
                            break;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("name",name);
                    bundle.putString("birth",birthDay);
                    bundle.putString("gender",gender);
                    bundle.putString("place",place);
                    Intent intent = new Intent(this, EmployeeDetail.class);
                    intent.putExtra("Mypackage",bundle);
                    // switching activity to get result
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Enter your name, plz!!!", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }

    }
}
