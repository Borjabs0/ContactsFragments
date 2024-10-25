package com.borjabolufer.contactsfragments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.borjabolufer.contactsfragments.R;
import com.borjabolufer.contactsfragments.inteficies.IClickListener;
import com.borjabolufer.contactsfragments.models.Contact;

import java.util.List;

/**
 * El adaptador {@code ContactAdapter} es una clase que gestiona la visualización de una lista de contactos
 * dentro de un {@link RecyclerView}. Cada contacto es representado por una vista de elemento de lista que muestra
 * su nombre completo y número de teléfono.
 *
 * Este adaptador también maneja los eventos de selección de contactos a través de la interfaz {@link IClickListener}.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactRecycleView> {

    private final List<Contact> contacts;
    private IClickListener listener;
    private LayoutInflater inflater;

    /**
     * Constructor del adaptador que recibe una lista de contactos y un listener para manejar la interacción con ellos.
     *
     * @param contacts La lista de contactos que se mostrará en el RecyclerView.
     */
    public ContactAdapter(Context context, List<Contact> contacts, IClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.contacts = contacts;
        this.listener = listener;
    }

    /**
     * Crea una nueva vista de elemento de contacto cuando no hay suficientes vistas reutilizables disponibles.
     *
     * @param parent El ViewGroup al que la nueva vista se añadirá después de ser enlazada.
     * @param viewType El tipo de la nueva vista (no se utiliza en este caso específico).
     * @return Un nuevo ViewHolder que contiene la vista de contacto inflada.
     */
    @NonNull
    @Override
    public ContactRecycleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = inflater.inflate(R.layout.item_contact, parent, false);
        return new ContactRecycleView(layout, listener);
    }

    /**
     * Enlaza la información de un contacto a un ViewHolder existente para ser visualizada en la lista.
     *
     * @param holder El ViewHolder que contendrá los datos del contacto.
     * @param position La posición del contacto en la lista de contactos.
     */
    @Override
    public void onBindViewHolder(@NonNull ContactRecycleView holder, int position) {
        holder.bind(contacts.get(position), listener);
    }

    /**
     * Devuelve el número total de contactos en la lista.
     *
     * @return El tamaño de la lista de contactos.
     */
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    /**
     * La clase {@code ContactRecycleView} es un ViewHolder que contiene las vistas para mostrar la información
     * de un contacto individual en el RecyclerView.
     */
    public static class ContactRecycleView extends RecyclerView.ViewHolder {

        public TextView tvFullName, tvPhone;

        /**
         * Constructor del ViewHolder. Inicializa las vistas para mostrar el nombre completo y el teléfono del contacto.
         *
         * @param itemView La vista individual del contacto.
         * @param listener
         */
        public ContactRecycleView(@NonNull View itemView, IClickListener listener) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }

        /**
         * Vincula los datos de un contacto a las vistas del ViewHolder, y configura el listener para manejar
         * los clics en el contacto.
         *
         * @param contact El contacto cuyos datos se van a mostrar.
         * @param listener El listener que manejará los clics en el contacto.
         */
        public void bind(final Contact contact, final IClickListener listener) {
            // Construye el nombre completo del contacto y lo muestra en la TextView
            String fullName = contact.getName() + " " + contact.getFirstSurname() + " " + contact.getSecondSurname();
            tvFullName.setText(fullName);

            // Muestra el primer número de teléfono del contacto
            tvPhone.setText(contact.getPhoneOne());

            // Set the click listener for the itemView to trigger the listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}