package kapadokia.nyandoro.traveadmin.utility;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kapadokia.nyandoro.traveadmin.TravelDeal;

public class FirebaseUtils {

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseRefference;
    public static FirebaseUtils firebaseUtils;
    public static ArrayList<TravelDeal> travelDeals;

    //to avoid this class being instantiated from outside of this class
    private FirebaseUtils(){}

    // create a generic static method that will open a reference of a child that it is passed a a child

    public static void openFbRefference(String ref){
        if (firebaseUtils == null){
            firebaseUtils = new FirebaseUtils();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            travelDeals = new ArrayList<>();

        }



        mDatabaseRefference = mFirebaseDatabase.getReference().child(ref);

    }

    public static void signIn(){


    }
//
//    public static void attatchListener(){
//        auth.addAuthStateListener(authStateListener);
//    }
//
//    public static void removeListener(){
//        auth.removeAuthStateListener(authStateListener);
//    }
}
