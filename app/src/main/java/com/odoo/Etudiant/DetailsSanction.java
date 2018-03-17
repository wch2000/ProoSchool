package com.odoo.Etudiant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.odoo.R;

public class DetailsSanction extends AppCompatActivity {
TextView name_student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_sanction);
        name_student=(TextView)findViewById(R.id.student_name);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String city = intent.getStringExtra("city");
        name_student.setText(id);

    }
}
