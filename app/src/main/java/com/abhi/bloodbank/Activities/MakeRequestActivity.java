package com.abhi.bloodbank.Activities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.bloodbank.R;
import com.bumptech.glide.Glide;

public class MakeRequestActivity extends AppCompatActivity {

    EditText messageText;
    TextView chooseImageText;
    ImageView postImage;
    Button submit_button;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);

        messageText = findViewById(R.id.message);
        chooseImageText = findViewById(R.id.choose_text);
        postImage = findViewById(R.id.post_image);
        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid())
                {
                    //code to upload this post...
                    uploadRequest(messageText.getText().toString());
                }
            }
        });
        chooseImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to pick a image..

                    permission();
            }
        });
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
    }

    private void permission()
    {
        if (PermissionChecker.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE)
                != PermissionChecker.PERMISSION_GRANTED) {
            //asking for permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, 401);
            }
        } else {
            //permission is already there
            pickImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 401) {
            if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                //permission was granted
                pickImage();
            } else {
                //permission not granted
                showMessage("Permission Declined");
            }
        }
    }

    private void uploadRequest(String message)
    {
        //code to uploaAD the message

    }


    private void uploadImage()
    {
        //code ton upload..
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode==RESULT_OK){
            if(data!=null)
            {
             imageUri= data.getData();
                Glide.with(getApplicationContext()).load(imageUri).into(postImage);            }
        }
    }

    private boolean isValid()
    {
        if (messageText.getText().toString().isEmpty()) {
            showMessage("Message shouldn't be empty");
            return false;
        }else if(imageUri==null){
            showMessage("Pick Image");
            return false;
        }
        return true;
    }
    private void showMessage(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
