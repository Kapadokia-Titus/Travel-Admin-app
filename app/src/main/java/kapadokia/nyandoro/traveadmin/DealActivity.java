package kapadokia.nyandoro.traveadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kapadokia.nyandoro.traveadmin.utility.FirebaseUtils;

public class DealActivity extends AppCompatActivity {

    //firebase declarations
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRefference;

    //variables for the edit text
    EditText txtTitle, txtDescription, txtPrice;
   private TravelDeal deal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //basic inits
        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);

        Intent intent = getIntent();
        TravelDeal deal = (TravelDeal) intent.getSerializableExtra("Deal");
        if (deal==null){
            deal = new TravelDeal();
        }
        this.deal  = deal;
        txtTitle.setText(deal.getTitle());
        txtPrice.setText(deal.getPrice());
        txtDescription.setText(deal.getDescription());


        FirebaseUtils.openFbRefference("traveldeals", this);

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
                backToList();
                return true;
            case R.id.delete_menu:
                deleteDeal();
                Toast.makeText(this, "Deal Deleted", Toast.LENGTH_SHORT).show();
                backToList();
                return true;
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
         deal.setTitle(txtTitle.getText().toString());
         deal.setPrice(txtPrice.getText().toString());
         deal.setDescription(txtDescription.getText().toString());

         if (deal.getId()==null){
             mDatabaseRefference.push().setValue(deal);
         }else {
             mDatabaseRefference.child(deal.getId()).setValue(deal);
         }
    }

    private void deleteDeal(){
        if (deal.getId()==null){
            Toast.makeText(this, "please save deal before deleting ", Toast.LENGTH_SHORT).show();
            return;
        }else {
            mDatabaseRefference.child(deal.getId()).removeValue();
        }
    }

    private void backToList(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
