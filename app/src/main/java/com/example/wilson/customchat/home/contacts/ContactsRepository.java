package com.example.wilson.customchat.home.contacts;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wilson.customchat.User;
import com.example.wilson.customchat.domain.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private String searchKey;

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

            databaseReference.child(User.EXTRA_DATA_KEY).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d(TAG,"have received data");
                    controller.loadContacts(getContacts(dataSnapshot,stringContacts));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {
            Log.e(TAG, "didn't receive data");
        }

    }

    private ArrayList<Contact> getContacts(DataSnapshot dataSnapshot, ArrayList<String> contactsList){
        Map<String,Map<String,String>> data;
        ArrayList<Contact> dataToSet = new ArrayList<>();
        GenericTypeIndicator<Map<String,Map<String,String>>> contactsData = new GenericTypeIndicator<Map<String,Map<String,String>>>() {};

        data = dataSnapshot.getValue(contactsData);

        if(data!=null && !data.isEmpty()){
            Log.d(TAG,"data is "+data);
            Log.d(TAG,"contacts are "+contactsList);
            Log.d(TAG,"index for data is "+data.size());
            Map<String,String> temp;

            for(int c=0;c<contactsList.size(); c++){
                Contact contact = new Contact();

                Log.d(TAG,"index is "+c);
                temp = data.get(contactsList.get(c));

                if(temp!=null){
                    Log.d(TAG,"index for temp is "+temp.size());
                    contact.setContactEmail(contactsList.get(c));
                    contact.setContactUsername(temp.get(Contact.CONTACT_KEY_NAME));
                    contact.setContactState(temp.get(Contact.CONTACT_KEY_STATE));
                    contact.setContactAvailability(temp.get(Contact.CONTACT_KEY_AVAILABILITY));
                    contact.setContactProfileImagePath(temp.get(Contact.CONTACT_KEY_PROFILE_IMAGE));

                    Log.d(TAG,"received contact is "+contact.getContactUsername());
                    dataToSet.add(contact);
                }else{
                    Log.e(TAG,"cannot set the data");
                }

            }

        }else{
            Log.e(TAG,"received data is null or empty");
        }
        return dataToSet;
    }

    protected void searchContact(String contact) {
        Log.d(TAG,"received search is "+contact);
        this.searchKey = contact;
        databaseReference.child(User.EXTRA_DATA_KEY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,"datasnapshot for search is "+dataSnapshot);
                onReceivedSearchResults(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"process cancelled, reason:"+databaseError.getMessage()+"\n"+databaseError.getDetails());
            }
        });

    }

    private void onReceivedSearchResults(DataSnapshot dataSnapshot){
        Map<String,Map<String,String>> receivedData;
        GenericTypeIndicator<Map<String,Map<String,String>>> indicator = new GenericTypeIndicator<Map<String, Map<String, String>>>() {};

        receivedData = dataSnapshot.getValue(indicator);

        if(receivedData!=null && !receivedData.isEmpty() && searchKey!=null){
            Log.d(TAG,"found data is "+receivedData.get(searchKey));

            Map<String,String> data = receivedData.get(searchKey);

            if(data!= null && !data.isEmpty()){
                Contact contact = new Contact();
                contact.setContactEmail(searchKey);
                contact.setContactUsername(data.get(Contact.CONTACT_KEY_NAME));
                contact.setContactState(data.get(Contact.CONTACT_KEY_STATE));
                contact.setContactProfileImagePath(Contact.CONTACT_KEY_PROFILE_IMAGE);
                contact.setContactAvailability(Contact.CONTACT_KEY_AVAILABILITY);

                ArrayList<Contact> foundContact = new ArrayList<>();
                foundContact.add(contact);
                controller.onContactFound(foundContact);
            }else{
                Log.e(TAG,"data is corrupted, or is null");
                controller.onContactNotFound();
            }

        }else{
            Log.e(TAG,"couldn't load the data");
        }
    }

    protected void addContact(String email){

        Map<String,Object> contactData = new HashMap<>();

        contactData.put(User.createContactKey(email),email);

        databaseReference.child(FirebaseHelper.CONTACTS_PATH)
                .child(User.formatEmail(user.getEmail()))
                .updateChildren(contactData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete()){
                    if(task.isSuccessful()){
                        Log.d(TAG,"task completed successfully");
                    }else{
                        Log.e(TAG,"task isn't successful, reason: "+task.getResult());
                    }
                }else{
                    Log.e(TAG,"task is uncompleted, reason unknown");
                }
            }
        });
    }

    protected void deleteContact(String contactEmail){
        databaseReference.child(User.USER_CONTACTS).child(User.formatEmail(user.getEmail())).child(User.CONTACT_KEY+contactEmail).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    controller.onContactDeleted();
                } else {
                    controller.processFailure("cannot complete the task, unknown reason");
                }
            }
        });
    }

}
