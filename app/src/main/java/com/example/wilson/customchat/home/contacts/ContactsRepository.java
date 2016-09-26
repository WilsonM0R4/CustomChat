package com.example.wilson.customchat.home.contacts;

import android.util.Log;

import com.example.wilson.customchat.User;
import com.example.wilson.customchat.domain.FirebaseHelper;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wmora on 9/6/16.
 */
public class ContactsRepository {

    private static final int SIGNAL_LIST = 0;
    private static final int SIGNAL_SEARCH = 1;
    private static final int SIGNAL_FOUND = 2;

    private FirebaseHelper helper;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    Map<String, Object> contactsMap;
    ArrayList<String> contacts;
    Map<String, Object> contactData;
    DatabaseReference contactsPath;
    DataSnapshot snapshot;
    ValueEventListener contactValueEventListen;
    ValueEventListener searchValueEventListener;
    ContactsController controller;
    String searchedUser;
    ArrayList<Contact> foundList;

    public ContactsRepository(ContactsController controller) {
        helper = FirebaseHelper.getInstance();
        databaseReference = helper.getDatabaseReference();
        Log.e("ContactsRepository", "databaseReference is :" + databaseReference);
        user = helper.getCurrentUserReference();
        Log.e("ContactsRepository", "user is:" + user);
        foundList = new ArrayList<>();
        contacts = new ArrayList<>();
        this.controller = controller;
    }

    protected void onDestroy() {
        if (contactsPath != null && contactValueEventListen != null) {
            contactsPath.removeEventListener(contactValueEventListen);
        } else {
            Log.d("ContactsController", "skipping destruction");
        }
    }

    protected void launchContactReading(){

        if (user != null) {
            contactsPath = databaseReference.child(User.USER_CONTACTS).child(User.formatEmail(user.getEmail()));
            contactsPath.addValueEventListener(valueEventListener(User.formatEmail(user.getEmail()), SIGNAL_LIST));
        }else{
            Log.e("ContactsRepository","cannot load data from database");
        }

    }

    protected ArrayList<String> getContacts() {
        ArrayList<String> result = new ArrayList<>();
        if(foundList!=null){
            //result = foundList;
            //Log.d("ContactsRepository","Se encontraron contactos, y son: "+result);
        }else{
            Log.d("ContactsRepository","No hay lista de contactos");
        }
        return result;
    }

    protected void addContact(Map<String, Object> contact) {
        //contactsPath.
    }

    protected void deleteContact(int contactIndex) {

    }

    void searchRegisteredUser(String user) {
        searchedUser = User.formatEmail(user);
        databaseReference.child(FirebaseHelper.USERS_PATH).addValueEventListener(valueEventListener(searchedUser, SIGNAL_SEARCH));
        Log.e("Repository", "received user for search is " + searchedUser);
    }

    private ValueEventListener valueEventListener(String user, int signalSearch) {

        final String usermail = user;
        final int signal = signalSearch;
        Log.d("ContactsRepository","Signal is "+signal);

        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                switch (signal) {
                    case SIGNAL_LIST:
                        Log.d("ContactsRepository","Signal is "+SIGNAL_LIST);
                        controller.loadContacts(listContacts(dataSnapshot));
                        break;
                    case SIGNAL_SEARCH:
                        Log.d("ContactsRepository","Signal is "+SIGNAL_SEARCH);
                        searchContact(dataSnapshot, usermail);
                        break;
                    case SIGNAL_FOUND:
                        Log.d("ContactsRepository","Signal is "+SIGNAL_FOUND);
                        getFoundUserData(dataSnapshot);
                        break;
                    default:
                        Log.e("ContactsRepository", "La señal recibida no corresponde a ninguna de las esperadas");
                        break;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ContactsRepository", "load canceled: " + databaseError.getMessage());
            }
        };
    }

    private ArrayList<Contact> listContacts(DataSnapshot dataSnapshot) {

        Map<String, String> resultList;

        if (dataSnapshot != null) {
            GenericTypeIndicator<Map<String, String>> indicator = new GenericTypeIndicator<Map<String, String>>() {};
            resultList = dataSnapshot.getValue(indicator);

            if (resultList != null) {

                for(int index=0;index<resultList.size();index++){
                    Contact contact = new Contact();
                    contact.setContactUsername(resultList.get(Contact.CONTACT_KEY_NAME));
                    contact.setContactAvailability(resultList.get(Contact.CONTACT_KEY_AVAILABILITY));
                    contact.setContactState(resultList.get(Contact.CONTACT_KEY_STATE));
                    //contact.setContactEmail();
                    foundList.add(index,contact);

                }

                Log.e("ContactsRepository", "tienes contactos");
            } else {
                Log.e("ContactsRepository", "No se han encontrado contactos");
            }
        } else {
            Log.e("ContactsRepository", "El usuario No ha sido encontrado");
        }

        return foundList;
    }

    private void searchContact(DataSnapshot dataSnapshot, String userToSearch) {

        //Map<String, String> resultList;

        if (dataSnapshot != null) {
            snapshot = dataSnapshot;
            if (dataSnapshot.toString().equals(User.USER_NO_CONTACTS)) {

                Log.e("ContactsRepository", "No se ha recibido informacion del servidor");
            } else {

                listContacts(dataSnapshot);

                if (foundList.contains(userToSearch)) {
                    databaseReference.child(User.EXTRA_DATA_KEY).child(userToSearch).addValueEventListener(valueEventListener(userToSearch, SIGNAL_FOUND));

                } else {
                    controller.onContactNotFound("el contacto que buscas no está registrado en CustomChat");
                }
                //
            }
            Log.e("ContactsRepository", "data is " + dataSnapshot.toString());
        }

    }

    private void getFoundUserData(DataSnapshot dataSnapshot) {

        Map<String, String> resultList;

        if (dataSnapshot != null) {

            GenericTypeIndicator<Map<String, String>> userData = new GenericTypeIndicator<Map<String, String>>() {};
            resultList = dataSnapshot.getValue(userData);

            if (resultList != null) {
                Log.d("ContactsRepository","contactData is: "+resultList);
                controller.onContactFound(resultList);
            } else {
                controller.onContactNotFound("la información del usuario que buscas no se ha encontrado");
            }

        }

    }

}
