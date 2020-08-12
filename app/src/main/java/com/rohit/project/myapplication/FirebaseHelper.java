package com.rohit.project.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {

    public FirebaseHelper() {
    }

    public boolean isExistingUser(FirebaseUser user){

        Log.d("MSG: ","inside helper method");
        final List <Residents> residentsList = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference("residents")
                .orderByChild("email")
                .equalTo(user.getEmail());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                residentsList.clear();
                for (DataSnapshot residentSnapshot : dataSnapshot.getChildren()){
                    Residents residents = residentSnapshot.getValue(Residents.class);
                    residentsList.add(residents);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("List size in helper: ",String.valueOf(residentsList.size()));
        if(residentsList.isEmpty()){
            Log.d("Msg: ","New User In helper");
            return false;
        }
        Log.d("Msg: ","Old user in helper");
        return true;
    }

}
