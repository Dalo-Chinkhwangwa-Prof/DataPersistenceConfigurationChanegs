package com.bb.datapersistenceconfigchanges.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.datapersistenceconfigchanges.R;
import com.bb.datapersistenceconfigchanges.adapter.NameAdapter;
import com.bb.datapersistenceconfigchanges.model.Name;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private TextView mainTextView;
    private String mainKey = "my.count.key";
    private SharedPreferences sharedPreferences;

    private NameAdapter nameAdapter;

    private EditText guestNameEditText;
    private ListView guestListView;
    private Button addGuestButton;

    private int guestCount = 0;
    private String guestKeyPrefix = "GUEST_";//GuestCount 0


    private List guestList = new ArrayList<String>();
    private List nameList = new ArrayList<Name>();

    private final String GUEST_COUNT_KEY = "guest.count.key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.bb.datapersistenceconfigchanges", Context.MODE_PRIVATE);

        guestNameEditText = findViewById(R.id.editText);
        guestListView = findViewById(R.id.guest_listview);
        addGuestButton = findViewById(R.id.add_guest_button);

        guestCount = sharedPreferences.getInt(GUEST_COUNT_KEY, 0);

        addGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guestName = guestNameEditText.getText().toString().trim();
                guestCount++;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(guestKeyPrefix + guestCount, guestName);
                editor.putInt(GUEST_COUNT_KEY, guestCount);
                editor.apply();

                readGuests();
                guestNameEditText.setText("");
            }
        });
//        mainTextView = findViewById(R.id.main_textview);
//        mainTextView.setText("Count: " + count);
//        mainTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addCount();
//            }
//        });

        readGuests();
    }

    private void readGuests() {
        guestCount = sharedPreferences.getInt(GUEST_COUNT_KEY, 0);
        guestList.clear();//To avoid adding the same values

        for (int i = 0; i < guestCount; i++) {
            String name = sharedPreferences.getString(guestKeyPrefix+(i + 1), "unknown");
            guestList.add(name);

            nameList.add(new Name("Mr.", name));

            Log.d("TAG_X", name);
        }

        updateGuestList();
    }

    private void updateGuestList() {

        nameAdapter = new NameAdapter(nameList);

//        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(
//                this,
//                R.layout.guest_item_layout,
//                R.id.name_textview,
//                guestList
//        );

        guestListView.setAdapter(nameAdapter);
//        guestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, guestList.get(position).toString(),Toast.LENGTH_LONG).show();
//            }
//        });

    }


    @Override
    protected void onResume() {
        super.onResume();
//        count = sharedPreferences.getInt(mainKey, 0);
//        mainTextView.setText("Count: " + count);
    }

//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        if(savedInstanceState.getInt(mainKey)  > 0 ){
//            count = savedInstanceState.getInt(mainKey);
//            mainTextView.setText("Count: " + count);
//        }
//    }

//    private void addCount() {
//        count++;
//        mainTextView.setText("Count: " + count);
//    }

//    @Override
////    public void onConfigurationChanged(@NonNull Configuration newConfig) {
////        super.onConfigurationChanged(newConfig);
////        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
////            Toast.makeText(this, "Changed to portrait", Toast.LENGTH_LONG).show();
////        else
////            Toast.makeText(this, "Changed to landscape", Toast.LENGTH_LONG).show();
////    }


    @Override
    protected void onStop() {
        super.onStop();
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(mainKey, count);
//        editor.apply();
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt(mainKey, count);
//    }
}
