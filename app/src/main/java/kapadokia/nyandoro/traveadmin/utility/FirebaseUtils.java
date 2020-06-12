package kapadokia.nyandoro.traveadmin.utility;

import android.app.Activity;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> cant figure out the bug

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
    public static FirebaseAuth  mFirebaseAuth;
    public static FirebaseAuth.AuthStateListener mAuthStateListener;
    public static ArrayList<TravelDeal> travelDeals;
<<<<<<< HEAD
=======
    private static final int RC_SIGN_IN =123;
    private static Activity caller;
>>>>>>> cant figure out the bug

    //to avoid this class being instantiated from outside of this class
    private FirebaseUtils(){}

    // create a generic static method that will open a reference of a child that it is passed a a child

    public static void openFbRefference(String ref, final Activity callerActivity){
        if (firebaseUtils == null){
            firebaseUtils = new FirebaseUtils();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
<<<<<<< HEAD
            travelDeals = new ArrayList<>();
=======

            mFirebaseAuth = FirebaseAuth.getInstance();
            caller = callerActivity;
            mAuthStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUtils.signIn();
                    Toast.makeText(callerActivity, "Welcome back", Toast.LENGTH_SHORT).show();
>>>>>>> cant figure out the bug

                }
            };
        }



        mDatabaseRefference = mFirebaseDatabase.getReference().child(ref);

    }

<<<<<<< HEAD
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
=======

    private static void signIn(){

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());




// Create and launch sign-in intent
        caller.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }
    public static void attachListener(){
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    public static void detachListener(){
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
>>>>>>> cant figure out the bug
}
