package com.example.farmeasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        List<Item> item=new ArrayList<>();
        item.add(new Item("Cotton","Chlorpyrifos and Thiamethoxam","Nitrogen-rich fertilizers","Drip irrigation or furrow irrigation",R.drawable.ctimg));
        item.add(new Item("Chilli","Chlorpyrifos and Carbofuran","Organic compost and NPK","Drip irrigation or sprinkler system",R.drawable.cimg));
        item.add(new Item("GroundNut","Imidacloprid and Thiamethoxam","Balanced NPK","Drip irrigation or furrow irrigation",R.drawable.gimg));
        item.add(new Item("Mango","Minimal Use ","Organic manure, NPK","Drip irrigation or Basin irrigation",R.drawable.ming));
        item.add(new Item("Maize","Chlorpyrifos and Thiamethoxam","Balanced NPK","Drip irrigation or furrow irrigation",R.drawable.mzimg));
        item.add(new Item("Onion"," Minimal Use","Organic manure, NPK","Drip irrigation or furrow irrigation",R.drawable.oimg));
        item.add(new Item("Paddy(Rice)","Imidacloprid and Cypermethrin","Organic manure (Farmyard manure)","Flood irrigation or submerged method",R.drawable.rimg));
        item.add(new Item("Palm Oil","Imidacloprid and Pyrethroids","NPK (higher potassium)","Drip irrigation or basin irrigation",R.drawable.pimg));
        item.add(new Item("SugarCane","Cypermethrin and Chlorpyrifos,","Organic manure, NPK","Drip irrigation or flood irrigation",R.drawable.sgimg));
        item.add(new Item("Tomato"," Carbofuran and Imidacloprid","NPK (higher potassium)","Drip irrigation or furrow irrigation",R.drawable.timg));
        item.add(new Item("Turmeric","Carbofuran and Thiamethoxam","Organic manure, NPK","Drip irrigation or furrow irrigation",R.drawable.trimg));
        item.add(new Item("SoyaBean","Imidacloprid and Thiamethoxam","Balanced NPK","Drip irrigation or furrow irrigation",R.drawable.simg));
        RecyclerView rcv=findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(new MyAdapter(getApplicationContext(),item));
    }
}

