package com.abhi.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.abhi.bloodbank.Adapters.RequestAdapter;
import com.abhi.bloodbank.DataModels.RequestDataModel;
import com.abhi.bloodbank.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
       private RecyclerView recyclerView;
       private List<RequestDataModel>  requestDataModels ;
       private RequestAdapter requestAdapter;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView make_request_button=findViewById(R.id.make_request_button);
        make_request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MakeRequestActivity.class));
            }
        });
        requestDataModels=new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.search_button){
                        startActivity(new Intent(MainActivity.this,Seacrch_Activity.class));
                }
                return false;
            }
        });
        recyclerView =findViewById(R.id.recyclerView);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);
        requestAdapter =new RequestAdapter(requestDataModels,this);
        recyclerView.setAdapter(requestAdapter);
       populateHomePage();
    }

    private void populateHomePage(){
            RequestDataModel requestDataModel=new RequestDataModel("Message:- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. see more...","https://cdn.pixabay.com/photo/2022/11/26/08/39/winter-7617474_960_720.jpg");
            requestDataModels.add(requestDataModel);
            requestDataModels.add(requestDataModel);
            requestDataModels.add(requestDataModel);
            requestDataModels.add(requestDataModel);
            requestDataModels.add(requestDataModel);
            requestDataModels.add(requestDataModel);
            requestDataModels.add(requestDataModel);
            requestAdapter.notifyDataSetChanged();


    }

}