package kapadokia.nyandoro.traveadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kapadokia.nyandoro.traveadmin.utility.FirebaseUtils;

public class MainActivity extends AppCompatActivity {

    //firebase declarations
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRefference;

    //variables for the edit text
    EditText txtTitle, txtDescription, txtPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //basic inits
        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrie);
        txtDescription = findViewById(R.id.txtDescription);


        FirebaseUtils.openFbRefference("traveldeals");

        //firebase inits
        mFirebaseDatabase = FirebaseUtils.mFirebaseDatabase;
        mDatabaseRefference = FirebaseUtils.mDatabaseRefference;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Deals saved", Toast.LENGTH_LONG).show();
                clean();

             default:
                 return super.onOptionsItemSelected(item);
                 

        }

       
    }

    private void clean() {
        txtTitle.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
        txtTitle.requestFocus();
    }

    private void saveDeal() {

        // step 1. read the edit text and convert them to string
        String title = txtTitle.getText().toString();
        String price = txtPrice.getText().toString();
        String description  = txtDescription.getText().toString();

        TravelDeal deal = new TravelDeal(title, price,description,"");
        mDatabaseRefference.push().setValue(deal);
    }
}
