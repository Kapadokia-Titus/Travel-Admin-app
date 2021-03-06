package kapadokia.nyandoro.traveadmin.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import kapadokia.nyandoro.traveadmin.DealActivity;
import kapadokia.nyandoro.traveadmin.R;
import kapadokia.nyandoro.traveadmin.TravelDeal;
import kapadokia.nyandoro.traveadmin.utility.FirebaseUtils;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<TravelDeal> deals;
    private ChildEventListener childEventListener;

    public DealAdapter(){

        //firebase inits
        firebaseDatabase = FirebaseUtils.mFirebaseDatabase;
        databaseReference = FirebaseUtils.mDatabaseRefference;
        deals = FirebaseUtils.travelDeals;

        if (childEventListener !=null){

            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user != null){
                        TravelDeal td = dataSnapshot.getValue(TravelDeal.class);
                        Log.d("deal", td.getTitle());
                        td.setId(dataSnapshot.getKey());
                        deals.add(td);
                        notifyItemInserted(deals.size()-1);
                    }else {
                        return;
                    }



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

        }else {
            return;
        }

    }
    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_row,parent, false);
        return new DealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        TravelDeal deal = deals.get(position);
        holder.bind(deal) ;
    }

    @Override
    public int getItemCount() {
        if (deals !=null){
            return deals.size();
        }
        return 0;
    }

    public class DealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         TextView tvTitle,tvPrice, tvDescription;
         ImageView imvDeal;
        public DealViewHolder(@NonNull View itemView) {
            super(itemView);

//            imvDeal = itemView.findViewById(R.id.imageDeal);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvDescription = itemView.findViewById(R.id.tv_description);
            itemView.setOnClickListener(this);

        }

        public void bind(TravelDeal travelDeal){
            tvTitle.setText(travelDeal.getTitle());
            tvPrice.setText(travelDeal.getPrice());
            tvDescription.setText(travelDeal.getDescription());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.v("position", String.valueOf(position));
            TravelDeal selectedDeal = deals.get(position);
            Intent intent = new Intent(view.getContext(), DealActivity.class);
            intent.putExtra("Deal", selectedDeal);
            view.getContext().startActivity(intent);

        }
    }
}
