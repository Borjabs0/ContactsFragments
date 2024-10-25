package com.borjabolufer.contactsfragments.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.borjabolufer.contactsfragments.R;
import com.borjabolufer.contactsfragments.models.Contact;

import java.util.List;


public class FragmentDetail extends Fragment {
    public interface IOnAttachListener {
        String getName();
        String getfullSurname();
        String getAddres();
        String getCompany();
        String getBirth();
        String getPhoneOne();
        String getPhoneTwo();
        String getEmail();
    }

    private IOnAttachListener attachListener;
    private TextView tvName, tvSurname, tvAddres, tvCompany, tvBirthday, tvPhoneOne, tvPhoneTwo, tvEmail;
    private ImageView imageView;

    public FragmentDetail() {
        super(R.layout.fragment_detail);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.tvName);
        tvName.setText(attachListener.getName());

        tvSurname = view.findViewById(R.id.tvSurname);
        tvSurname.setText(attachListener.getfullSurname());

        tvAddres = view.findViewById(R.id.tvAddress);
        tvAddres.setText(attachListener.getAddres());

        tvCompany = view.findViewById(R.id.tvCompany);
        tvCompany.setText(attachListener.getCompany());

        tvBirthday = view.findViewById(R.id.tvBirthday);
        tvBirthday.setText(attachListener.getBirth());

        tvPhoneOne = view.findViewById(R.id.tvPhoneOne);
        tvPhoneOne.setText(attachListener.getPhoneOne());

        tvPhoneTwo = view.findViewById(R.id.tvPhoneTwo);
        tvPhoneTwo.setText(attachListener.getPhoneTwo());

        tvEmail = view.findViewById(R.id.tvEmail);
        tvEmail.setText(attachListener.getEmail());

        imageView = view.findViewById(R.id.ivContact);
        imageView.setImageResource(R.drawable.profile);
    }

    public void setName(String name) {
        tvName.setText(name);
    }

    public void setSurname(String surname) {
        tvSurname.setText(surname);
    }

    public void setAddres(String addres) {
        tvAddres.setText(addres);
    }

    public void setCompany(String company) {
        tvCompany.setText(company);
    }

    public void setBirthday(String birthday) {
        tvBirthday.setText(birthday);
    }

    public void setPhoneOne(String phoneOne) {
        tvPhoneOne.setText(phoneOne);
    }

    public void setPhoneTwo(String phoneTwo) {
        tvPhoneTwo.setText(phoneTwo);
    }

    public void setEmail(String email) {
        tvEmail.setText(email);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        attachListener = (IOnAttachListener) requireActivity();
    }
}
