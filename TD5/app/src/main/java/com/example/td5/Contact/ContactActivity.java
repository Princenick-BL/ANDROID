package com.example.td5.Contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.td5.R;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    List<Contact> contacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        RecyclerView rvContacts = findViewById(R.id.rvContacts);

        contacts.add(new Contact("Jean1","PIERRE1","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean2","PIERRE2","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean3","PIERRE3","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean4","PIERRE4","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean5","PIERRE5","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean6","PIERRE6","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean7","PIERRE7","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean8","PIERRE8","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean9","PIERRE9","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean10","PIERRE10","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean11","PIERRE11","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean12","PIERRE12","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean13","PIERRE13","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean14","PIERRE14","https://picsum.photos/100/100"));
        contacts.add(new Contact("Jean15","PIERRE15","https://picsum.photos/100/100"));

        Adapter adapter = new Adapter(contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}