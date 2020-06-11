package kapadokia.nyandoro.traveadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import kapadokia.nyandoro.traveadmin.adapter.DealAdapter;
import kapadokia.nyandoro.traveadmin.utility.FirebaseUtils;

public class ListActivity extends AppCompatActivity {

    private ArrayList<TravelDeal> deals;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //inits
        deals = new ArrayList<>();

        recyclerView = findViewById(R.id.deals_recycler);
        final DealAdapter dealAdapter = new DealAdapter();
        recyclerView.setAdapter(dealAdapter);

        LinearLayoutManager  manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(manager);

    }
}
