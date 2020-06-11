package kapadokia.nyandoro.traveadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import kapadokia.nyandoro.traveadmin.utility.FirebaseUtils;

public class ListActivity extends AppCompatActivity {

    private ArrayList<TravelDeal> deals;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //inits
        deals = new ArrayList<>();

        textView = findViewById(R.id.tvDeals);


        FirebaseUtils.openFbRefference("traveldeals");

        //firebase inits
        firebaseDatabase = FirebaseUtils.mFirebaseDatabase;
        databaseReference = FirebaseUtils.mDatabaseRefference;
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                   TravelDeal td = dataSnapshot.getValue(TravelDeal.class);
                   textView.setText(textView.getText()+ "\n" +td.getTitle());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);

    }
}
