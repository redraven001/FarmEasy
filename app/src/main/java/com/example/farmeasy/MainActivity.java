package com.example.farmeasy;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
    private ImageView chat;
    private ImageView weather;
    private ImageView cropdis;
    private ImageView liverates;
    private ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chat = findViewById(R.id.chat);
        weather = findViewById(R.id.weather);
        cropdis = findViewById(R.id.crop_disease);
        liverates = findViewById(R.id.market_rates);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openchat();
            }
        });
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openweather();
            }
        });
        cropdis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencrop();
            }
        });
        liverates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlive();
            }
        });
//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openMenu();
//            }
//        });
    }

    private void openMenu() {
        Intent i=new Intent(this,menu.class);
        startActivity(i);
    }

    private void openlive() {
        Intent intent=new Intent(this,liverates.class);
        startActivity(intent);
    }

    private void opencrop() {
        Intent intent=new Intent(this,disease.class);
        startActivity(intent);
    }

    private void openweather() {
       Intent intent=new Intent(this,weather.class);
       startActivity(intent);
    }


    private void openchat() {
        Intent intent=new Intent(this,chatbot.class);
        startActivity(intent);
    }

    public void distData(View view) {
        Intent intent=new Intent(this,data.class);
        startActivity(intent);
    }
}