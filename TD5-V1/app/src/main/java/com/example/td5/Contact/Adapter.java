package com.example.td5.Contact;

import static android.content.ContentValues.TAG;
import static android.os.FileUtils.copy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td5.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
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
        new DownLoadImageTask(imageImageView).execute(contact.getImageUrl());


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
    private class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String... urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try {
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            } catch (Exception e) { // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
