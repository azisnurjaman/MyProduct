package com.example.myproduct;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils2 {
    private static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public  static final String ITEMS_PATH = "pelanggans";

    public static DatabaseReference getRefrence(String path){
        return firebaseDatabase.getReference(path);
    }
}
