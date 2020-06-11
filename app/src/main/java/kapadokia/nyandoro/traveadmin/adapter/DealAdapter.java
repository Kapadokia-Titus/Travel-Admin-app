package kapadokia.nyandoro.traveadmin.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import kapadokia.nyandoro.traveadmin.R;
import kapadokia.nyandoro.traveadmin.TravelDeal;
import kapadokia.nyandoro.traveadmin.utility.FirebaseUtils;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<TravelDeal> deals;
    private ChildEventListener childEventListener;

    public DealAdapter(){

        FirebaseUtils.openFbRefference("traveldeals");

        //firebase inits
        firebaseDatabase = FirebaseUtils.mFirebaseDatabase;
        databaseReference = FirebaseUtils.mDatabaseRefference;

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                TravelDeal td = dataSnapshot.getValue(TravelDeal.class);
                

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
    }
    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DealViewHolder extends RecyclerView.ViewHolder{

         TextView tvTitle;
        public DealViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        public void bind(TravelDeal travelDeal){
            tvTitle.setText(travelDeal.getTitle());
        }
    }
}
