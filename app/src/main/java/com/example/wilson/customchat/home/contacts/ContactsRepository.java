package com.example.wilson.customchat.home.contacts;

import android.os.AsyncTask;
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
    private static final int SIGNAL_DATA = 1;
    private static final int SIGNAL_SEARCH = 2;
    private static final int SIGNAL_FOUND = 3;
    private static final String TAG = "ContactsRepository";

    private FirebaseHelper helper;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    private Map<String, String> contactsMap;
    private ArrayList<Contact> contacts;
    private ArrayList<String> stringContacts;
    private ValueEventListener contactValueEventListen;
    private ContactsController controller;
    private ArrayList<Contact> foundList;

    DatabaseReference contactsPath;

    public ContactsRepository(ContactsController controller) {
        helper = FirebaseHelper.getInstance();
        databaseReference = helper.getDatabaseReference();
        Log.e("ContactsRepository", "databaseReference is :" + databaseReference);
        user = helper.getCurrentUserReference();
        Log.e("ContactsRepository", "user is:" + user);
        foundList = new ArrayList<>();
        contacts = new ArrayList<>();
        contactsMap = new HashMap<>();
        this.controller = controller;
    }

    protected void onDestroy() {
        if (contactsPath != null && contactValueEventListen != null) {
            contactsPath.removeEventListener(contactValueEventListen);
        } else {
            Log.d("ContactsController", "skipping destruction");
        }
    }

    protected void launchListeners() {
        if (user != null) {
            Log.d(TAG, "current user is " + user.getEmail());
            databaseReference.child(User.USER_CONTACTS).child(User.formatEmail(user.getEmail())).addValueEventListener(listener(SIGNAL_LIST));
        } else {
            Log.e(TAG, "Unable to load data from server");
        }
    }

    private ValueEventListener listener(final int signal) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                switch (signal) {
                    case SIGNAL_LIST:
                        listContacts(dataSnapshot);
                        Log.d(TAG,"signal: "+SIGNAL_LIST);
                        break;
                    case SIGNAL_DATA:
                        //getContact(dataSnapshot);
                        Log.d(TAG,"signal: "+SIGNAL_DATA);
                        break;
                    case SIGNAL_SEARCH:
                        Log.d(TAG,"signal: "+SIGNAL_SEARCH);
                        break;
                    case SIGNAL_FOUND:
                        Log.d(TAG,"signal: "+SIGNAL_FOUND);
                        break;
                    default:
                        Log.e(TAG, "received signals doesn't match with any expected");
                        break;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (databaseError != null) {
                    Log.e(TAG, "Process cancelled, reason: " + databaseError.getMessage() + "\ndetails: " + databaseError.getDetails());
                } else {
                    Log.e(TAG, "Cannot load the cancel reason");
                }
            }
        };
    }

    /**
     * Proper methods
     **/
    private void listContacts(DataSnapshot dataSnapshot) {

        GenericTypeIndicator<Map<String, String>> map = new GenericTypeIndicator<Map<String, String>>() {
        };
        contactsMap = dataSnapshot.getValue(map);

        if (contactsMap != null) {
            Log.d(TAG, "received data, it is: " + contactsMap);
            stringContacts = new ArrayList<>();
            stringContacts.addAll(contactsMap.values());

            new AsyncRepoContact(contactsMap,controller).execute();
            /*for (int count = 0; count < contactsMap.size(); count++) {
                databaseReference.child(User.EXTRA_DATA_KEY).child(User.formatEmail(stringContacts.get(count))).addValueEventListener(listener(SIGNAL_DATA));
                Log.d(TAG, "in a loop!");
            }*/

            //Log.d(TAG, "sending contacts list: "+contacts);
            //controller.loadContacts(contacts);


        } else {
            Log.e(TAG, "didn't receive data");
        }

    }

    /*private void getContact(DataSnapshot dataSnapshot) {

        Contact contact = new Contact();

        GenericTypeIndicator<Map<String, String>> typeIndicator = new GenericTypeIndicator<Map<String, String>>() {
        };
        Map<String, String> userMap = dataSnapshot.getValue(typeIndicator);

        if (userMap != null && !userMap.isEmpty()) {
            contact.setContactUsername(userMap.get(Contact.CONTACT_KEY_NAME));
            contact.setContactAvailability(userMap.get(Contact.CONTACT_KEY_AVAILABILITY));
            contact.setContactState(userMap.get(Contact.CONTACT_KEY_STATE));
            contact.setContactProfileImagePath(userMap.get(Contact.CONTACT_KEY_PROFILE_IMAGE));

            contacts.add(contact);
            Log.d(TAG,"contact "+contact.getContactUsername()+" added");
        } else {
            Log.e(TAG, "Cannot load the contact data");
        }

    }*/

    private class AsyncRepoContact extends AsyncTask<Void,Void,ArrayList<Contact>>{

        private Map<String, String> contacts;
        private ContactsController controller;
        private ValueEventListener listener;
        private ArrayList<Contact> contactsList;

        public AsyncRepoContact(Map<String, String> contacts, ContactsController controller){
            this.contacts = contacts;
            this.controller = controller;
        }

        @Override
        public void onPreExecute(){
            contactsList = new ArrayList<>();
            listener = listener();
        }

        @Override
        protected ArrayList<Contact> doInBackground(Void... params) {

            for (int count = 0; count < contacts.size(); count++) {
                databaseReference.child(User.EXTRA_DATA_KEY).child(User.formatEmail(stringContacts.get(count))).addValueEventListener(listener);
                Log.d(TAG, "in a loop!");
            }
            Log.d("ContactsRepository","populated data is: "+contactsList);
            return contactsList;
        }

        private ValueEventListener listener(){
            return new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    getContact(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("ContactsRepository","have an error during process: "+databaseError.getMessage()+" \ncause: "+databaseError.getDetails());
                }
            };
        }

        private void getContact(DataSnapshot dataSnapshot) {

            Contact contact = new Contact();

            GenericTypeIndicator<Map<String, String>> typeIndicator = new GenericTypeIndicator<Map<String, String>>() {
            };
            Map<String, String> userMap = dataSnapshot.getValue(typeIndicator);

            if (userMap != null && !userMap.isEmpty()) {
                contact.setContactUsername(userMap.get(Contact.CONTACT_KEY_NAME));
                contact.setContactAvailability(userMap.get(Contact.CONTACT_KEY_AVAILABILITY));
                contact.setContactState(userMap.get(Contact.CONTACT_KEY_STATE));
                contact.setContactProfileImagePath(userMap.get(Contact.CONTACT_KEY_PROFILE_IMAGE));

                contactsList.add(contact);
                Log.d(TAG,"contact "+contact.getContactUsername()+" added");
            } else {
                Log.e(TAG, "Cannot load the contact data");
            }

        }
        @Override
        public void onPostExecute(ArrayList<Contact> contactList){
            if(contactList!=null && !contactList.isEmpty()){
                controller.loadContacts(contactList);
                Log.e(TAG,"process ended");
            }else{
                Log.e(TAG,"have some trouble with server data");
            }
        }





    }

}
