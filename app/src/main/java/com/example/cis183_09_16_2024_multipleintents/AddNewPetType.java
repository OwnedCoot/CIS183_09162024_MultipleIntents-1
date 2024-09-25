package com.example.cis183_09_16_2024_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddNewPetType extends AppCompatActivity
{
    EditText et_j_addType_typeName;
    TextView tv_j_addType_error;
    Button btn_j_addType_addType;
    Button btn_j_addType_back;
    Intent addNewPet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_pet_type);

        et_j_addType_typeName = findViewById(R.id.et_v_addType_typeName);
        tv_j_addType_error = findViewById(R.id.tv_v_addType_error);
        btn_j_addType_addType = findViewById(R.id.btn_v_addType_addType);
        btn_j_addType_back = findViewById(R.id.btn_v_addType_back);

        addTypeButtonEventListener();
        backButtonEventListener();

        addNewPet = new Intent(AddNewPetType.this, AddNewPet.class);
    }

    private void addTypeButtonEventListener()
    {
        btn_j_addType_addType.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isError() != true)
                {
                    Pet.PetType.addPetType(et_j_addType_typeName.getText().toString());
                    Log.d("TYPE NAME:", et_j_addType_typeName.getText().toString());
                }
            }
        });
    }

    private void backButtonEventListener()
    {
        btn_j_addType_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(addNewPet);
            }
        });
    }

    private boolean isError()
    {
        if (et_j_addType_typeName.getText().toString().isEmpty())
        {
            tv_j_addType_error.setText("ERROR: Cannot Leave Text Field Blank");
            tv_j_addType_error.setVisibility(View.VISIBLE);
            return true;
        }
        else
        {
            for (int i = 0; i < Pet.PetType.getAllPetTypes().size(); i++)
            {
                if (et_j_addType_typeName.getText().toString().toLowerCase().equals(Pet.PetType.getPetAt(i).toLowerCase()))
                {
                    tv_j_addType_error.setText("ERROR: Type Already Exists");
                    tv_j_addType_error.setVisibility(View.VISIBLE);
                    return true;
                }
            }
        }
        tv_j_addType_error.setText("");
        tv_j_addType_error.setVisibility(View.INVISIBLE);
        return false;
    }

}