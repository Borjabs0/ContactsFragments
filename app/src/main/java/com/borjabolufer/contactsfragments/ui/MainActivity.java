package com.borjabolufer.contactsfragments.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.borjabolufer.contactsfragments.R;
import com.borjabolufer.contactsfragments.inteficies.IClickListener;
import com.borjabolufer.contactsfragments.models.Contact;
import com.borjabolufer.contactsfragments.models.ContactParser;
import com.borjabolufer.contactsfragments.ui.fragments.FragmentDetail;
import com.borjabolufer.contactsfragments.ui.fragments.FragmentList;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IClickListener,
        FragmentList.IOnAttachListener, FragmentDetail.IOnAttachListener {

    private List<Contact> contacts;
    private final String CONTACTS_KEY = "CONTACTS";
    private final String SELECTED_CONTACT_KEY = "SELECTED_CONTACT";
    private FragmentManager fragmentManager;
    private FragmentDetail fragmentDetail;
    private boolean detail;
    private int selectedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            loadData();
        } else {
            contacts = (List<Contact>) savedInstanceState.getSerializable(CONTACTS_KEY);
            selectedContact = savedInstanceState.getInt(SELECTED_CONTACT_KEY);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detail = findViewById(R.id.fcvDetail) != null;
        if (detail) {
            fragmentDetail = (FragmentDetail) fragmentManager.findFragmentById(R.id.fcvDetail);
            if (!(fragmentManager.findFragmentById(R.id.fcvList) instanceof FragmentList)) {
                fragmentManager.popBackStack();
            }
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CONTACTS_KEY, (Serializable) contacts);
        outState.putInt(SELECTED_CONTACT_KEY, selectedContact);
    }

    private void loadData() {
        selectedContact = -1;
        contacts = ContactParser.parse(this);
        if(contacts != null){
            selectedContact = 0;
        }
    }

    @Override
    public void onClick(int position) {

            Contact contact = contacts.get(position);
            selectedContact = position;
            if (detail) {
                    fragmentDetail.setName(contact.getName());
                    fragmentDetail.setSurname(contact.getFullSurname());
            } else {
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .replace(R.id.fcvList, FragmentDetail.class, null)
                        .commit();
            }
    }

    @Override
    public String getName() {
        return selectedContact != -1 ? contacts.get(selectedContact).getName() : "";
    }

    @Override
    public String getfullSurname() {
        return selectedContact != -1 ? contacts.get(selectedContact).getFullSurname() : "";
    }

    @Override
    public String getAddres() {
        return selectedContact != -1 ? contacts.get(selectedContact).getAddress() : "";
    }

    @Override
    public String getCompany() {
        return selectedContact != -1 ? contacts.get(selectedContact).getCompany() : "";
    }

    @Override
    public String getBirth() {
        return selectedContact != -1 ? contacts.get(selectedContact).getBirth() : "";
    }

    @Override
    public String getPhoneOne() {
        return selectedContact != -1 ? contacts.get(selectedContact).getPhoneOne() : "";
    }

    @Override
    public String getPhoneTwo() {
        return selectedContact != -1 ? contacts.get(selectedContact).getPhoneTwo() : "";
    }

    @Override
    public String getEmail() {
        return selectedContact != -1 ? contacts.get(selectedContact).getEmail() : "";
    }

    @Override
    public List<Contact> getContacts() {
        return contacts;
    }
}