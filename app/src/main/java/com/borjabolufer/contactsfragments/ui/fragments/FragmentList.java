package com.borjabolufer.contactsfragments.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabolufer.contactsfragments.R;
import com.borjabolufer.contactsfragments.inteficies.IClickListener;
import com.borjabolufer.contactsfragments.models.Contact;
import com.borjabolufer.contactsfragments.adapter.ContactAdapter;

import java.util.List;

public class FragmentList extends Fragment {
    public interface IOnAttachListener {
        List<Contact> getContacts();
    }
    private IClickListener clickListener;
    private List<Contact> contacts;

    public FragmentList() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvList = view.findViewById(R.id.rvList);
        ContactAdapter adapter = new ContactAdapter(requireContext(),contacts, clickListener);
        adapter.setListener(clickListener);
        rvList.setAdapter(adapter);
        rvList.hasFixedSize();
        rvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener onAttachListener = (IOnAttachListener) requireActivity();
        contacts = onAttachListener.getContacts();
        clickListener = (IClickListener) requireActivity();
        }
    }