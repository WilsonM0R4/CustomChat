package com.example.wilson.customchat.home.contacts;

import android.app.Application;
import android.util.Log;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.domain.FirebaseHelper;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * Created by wmora on 9/6/16.
 */
public class ContactsRepository {

    private FirebaseHelper helper;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    Map<String, Object> contactsMap;
    ArrayList<String> contacts;
    Map<String, Object> contactData;
    DatabaseReference contactsPath;
    ValueEventListener listener;
    DataSnapshot snapshot;
    ContactsController controller;
    String searchedUser;

    public ContactsRepository(ContactsController controller){
        helper = FirebaseHelper.getInstance();
        databaseReference = helper.getDatabaseReference();
        user = helper.getCurrentUserReference();
        listener = valueEventListener();
        contactsPath = databaseReference.child(User.USER_CONTACTS).child(User.formatEmail(user.getEmail()));
        contactsPath.addValueEventListener(listener);
        contacts = new ArrayList<>();
        this.controller = controller;
    }

    protected void onDestroy(){
        if(contactsPath!=null && listener !=null){
            contactsPath.removeEventListener(listener);
        }
    }

    protected ArrayList<String> getContacts(){
        return contacts;
    }

    protected void addContact(Map<String, Object> contact){
                                                                                                                                                                                                   //contactsPath.
     }

    //not used for now
    protected void updateContact(){

    }

    protected void deleteContact(int contactIndex){

    }

    void searchRegisteredUser(String user){
        searchedUser = User.formatEmail(user);
        databaseReference.child(FirebaseHelper.USERS_PATH).child(searchedUser).addValueEventListener(listener);
    }

    void getContactData(String user){
        databaseReference.child(User.EXTRA_DATA_KEY).child(searchedUser).addValueEventListener(contactListener());

    }

    private ValueEventListener valueEventListener(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot!=null){
                    snapshot = dataSnapshot;
                    if(dataSnapshot.toString()==User.USER_NO_CONTACTS){
                        controller.onContactNotFound("el contacto que buscas no está registrado en CustomChat");
                        Log.e("ContactsRepository","01:el contacto que buscas no está registrado en CustomChat");
                    }else{
                        getContactData(searchedUser);
                    }
                    Log.e("ContactsRepository","data is "+dataSnapshot.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ContactsRepository","load canceled: "+databaseError.getMessage());
            }
        };
    }

    private ValueEventListener contactListener(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    GenericTypeIndicator<Map<String,Object>> indicator = new GenericTypeIndicator<Map<String, Object>>() {};
                    contactData = dataSnapshot.getValue(indicator);
                    controller.onContactFound(contactData);
                }else{
                    controller.onContactNotFound("el contacto que buscas no está registrado en CustomChat");
                    Log.e("ContactsRepository","02:el contacto que buscas no está registrado en CustomChat");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
