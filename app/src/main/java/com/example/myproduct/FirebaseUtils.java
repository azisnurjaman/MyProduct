package com.example.myproduct;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    private static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public  static final String ITEMS_PATH = "items";

    public static DatabaseReference getRefrence(String path){
        return firebaseDatabase.getReference(path);
    }
}
