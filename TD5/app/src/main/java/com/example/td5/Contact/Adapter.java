package com.example.td5.Contact;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td5.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {


    private final List<Contact> mContacts;

    public Adapter(List<Contact> mContacts) {
        this.mContacts = mContacts;
    }


    @NonNull
    @Override
    public com.example.td5.Contact.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contact,parent,false);
        return new com.example.td5.Contact.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.td5.Contact.ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        TextView firstNameTextView = holder.firstNameTextView;
        TextView lastNameTextView = holder.lastNameTextView;
        ImageView imageImageView = holder.imageImageView;

        firstNameTextView.setText(contact.getFirstName());
        lastNameTextView.setText(contact.getLastName());
        imageImageView.setImageURI(Uri.parse(contact.getImageUrl()));

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }


   /* public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView firstNameTextView  ;
        public TextView lastNameTextView;
        public ImageView imageImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lastNameTextView = itemView.findViewById(R.id.lastName);
            firstNameTextView = itemView.findViewById(R.id.firstName);
            imageImageView = itemView.findViewById(R.id.image);
        }
    }
*/

}
