package sg.edu.rp.c346.id20046523.sa_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class ItemListActivity extends AppCompatActivity {

    TextView tvAppTitle;
    Spinner spnOpt, spnFilter;
    EditText etDate, etName;
    ListView lvProduct;
    Button btnAdd, btnEdit, btnRemove,btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        tvAppTitle = findViewById(R.id.appTitle);
        spnOpt = findViewById(R.id.spnOption);
        spnFilter = findViewById(R.id.spinnerFilter);
        etDate = findViewById(R.id.editTextDate);
        etName = findViewById(R.id.editTextName);
        lvProduct = findViewById(R.id.itemList);
        btnAdd = findViewById(R.id.addBtn);
        btnEdit = findViewById(R.id.modifyBtn);
        btnRemove = findViewById(R.id.removeBtn);
        btnFilter = findViewById(R.id.buttonSearch);

        ArrayList <String>  productList = new ArrayList<String>();
        //ArrayList <Product> productList = new ArrayList<Product>();

        productList.add("Vaccumm, 2022-03-25");
        productList.add("Washing Machine, 2022-06-02");
        productList.add("Speaker, 2022-11-30");
        productList.add("Fan, 2023-04-17");
        productList.add("Hair Dryer, 2021-12-23");

        Collections.sort(productList);

        ArrayList<String> newProList = new ArrayList<String>();
        for (int i=0; i<productList.size(); i++)
        {
            String line = productList.get(i);
            String proName= line.substring(0, line.indexOf(","));
            String expDate = line.substring(line.indexOf(",")+1);
            String proExp = "Expires " + expDate + " " + proName;
            newProList.add(proExp);
        }

        //array adapter for lv
        ArrayAdapter aaProd = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice, newProList);
        aaProd.notifyDataSetChanged();
        lvProduct.setAdapter(aaProd);


//        productList.add("Expires 2022-03-25 Vaccumm");
//        productList.add("Expires 2022-06-02 Washing Machine");
//        productList.add("Expires 2023-11-30 Speaker");
//        productList.add("Expires 2023-04-17 Fan");
//        productList.add("Expires 2021-12-23 Hair Dryer");

//        Product p1= new Product("2022-03-25","Vaccumm");
//        Product p2 = new Product("2022-06-02", "Washing Machine");
//        Product p3 = new Product("2023-11-30", "Speaker");
//
//        productList.add(p1);
//        productList.add(p2);
//        productList.add(p3);

//        ArrayList <String> proName = new ArrayList<String>();


        //array adapter for spinner filter
//        ArrayAdapter aaSearch = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,productList);
//        spnFilter.setAdapter(aaSearch);

//        Calendar cDate = Calendar.getInstance();
//        SimpleDateFormat cdMonth = new SimpleDateFormat("MM");
//        int month = Calendar.getInstance().MONTH;
//        if(spnFilter.getSelectedItemPosition() == 0)
//        {
//
//        }
//        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position)
//                {
//                    case 0:
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        spnOpt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        etDate.setHint("yyyy-mm-dd");
                        etName.setHint("Enter product name");
                        btnEdit.setEnabled(false);
                        btnRemove.setEnabled(false);
                        break;

                    case 1:
                        etDate.setHint("yyyy-mm-dd");
                        etName.setHint("Enter product name");
                        btnAdd.setEnabled(false);
                        btnEdit.setEnabled(true);
                        btnRemove.setEnabled(false);
                        break;

                    case 2:
                        etDate.setEnabled(false);
                        etName.setText("Select the items you wish to delete.");
                        btnAdd.setEnabled(false);
                        btnEdit.setEnabled(false);
                        btnRemove.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String name = etName.getText().toString();

                productList.add(name+", " + date);
                newProList.add("Expires" +" " + date + " " + name);
                Toast.makeText(ItemListActivity.this,name + " have been added", Toast.LENGTH_LONG).show();
                aaProd.notifyDataSetChanged();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray tf = lvProduct.getCheckedItemPositions();
                if(lvProduct.getCheckedItemCount() == 1)
                {
                    for(int i=0; i<lvProduct.getCount(); i++)
                    {
                        if(tf.get(i)) {
                            String date = etDate.getText().toString();
                            String name = etName.getText().toString();
                            newProList.set(i,"Expires " + " " + date + " " + name);
                        }
                        aaProd.notifyDataSetChanged();
                    }
                }

                else {
                    Toast.makeText(ItemListActivity.this,"You only can modify one product at a time.", Toast.LENGTH_LONG).show();
                }

                }


        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //SparseBooleanArray is to map int to boolean.
                SparseBooleanArray tf = lvProduct.getCheckedItemPositions();
                for(int i=0; i<lvProduct.getCount(); i++)
                {
                    if(tf.get(i) == true)
                    {
                        newProList.remove(i);
                    }
                    aaProd.notifyDataSetChanged();
                }

                //this is to uncheck all boxed in the listview.
                lvProduct.clearChoices();
                Toast.makeText(ItemListActivity.this,"The selected products have been removed.", Toast.LENGTH_LONG).show();
            }
        });


    }

}