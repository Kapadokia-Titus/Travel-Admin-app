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
    public static FirebaseAuth auth;
    public static FirebaseAuth.AuthStateListener authStateListener;
    public static ArrayList<TravelDeal> travelDeals;
    private static Activity caller;
    private static final int RC_SIGN_IN =123;

    //to avoid this class being instantiated from outside of this class
    private FirebaseUtils(){}

    // create a generic static method that will open a reference of a child that it is passed a a child

    public static void openFbRefference(String ref, final Activity callerActivity){
        if (firebaseUtils == null){
            firebaseUtils = new FirebaseUtils();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            auth = FirebaseAuth.getInstance();

            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                    caller = callerActivity;

                }
            };
        }


        travelDeals = new ArrayList<>();
        mDatabaseRefference = mFirebaseDatabase.getReference().child(ref);

    }

    public static void signIn(){

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent

        caller.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    public static void attatchListener(){
        auth.addAuthStateListener(authStateListener);
    }

    public static void removeListener(){
        auth.removeAuthStateListener(authStateListener);
    }
}
