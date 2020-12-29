package com.example.x_etc_25_32;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_25_32.fragment.Fragment_clwz;
import com.example.x_etc_25_32.fragment.Fragment_dtcx;
import com.example.x_etc_25_32.fragment.Fragment_gjcx;
import com.example.x_etc_25_32.fragment.Fragment_lkcx;
import com.example.x_etc_25_32.fragment.Fragment_shzs;
import com.example.x_etc_25_32.fragment.Fragment_sjfx;
import com.example.x_etc_25_32.fragment.Fragment_sshj;
import com.example.x_etc_25_32.fragment.Fragment_yjfk;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dra;
    private ImageView caidan;
    private TextView title;
    private NavigationView nav;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        title.setText("智能交通");

        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dra.openDrawer(GravityCompat.START);
                nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.lkcx:
                                Fragment1(new Fragment_lkcx());
                                break;
                            case R.id.sjfx:
                                Fragment1(new Fragment_sjfx());
                                break;
                            case R.id.shzs:
                                Fragment1(new Fragment_shzs());
                                break;
                            case R.id.gjcx:
                                Fragment1(new Fragment_gjcx());
                                break;
                            case R.id.sshj:
                                Fragment1(new Fragment_sshj());
                                break;
                            case R.id.clwz:
                                Fragment1(new Fragment_clwz());
                                break;
                            case R.id.yjfk:
                                Fragment1(new Fragment_yjfk());
                                break;
                            case R.id.dtcx:
                                Fragment1(new Fragment_dtcx());
                                break;
                        }
                        dra.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
            }
        });



    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void initView() {
        dra = findViewById(R.id.dra);
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        nav = findViewById(R.id.nav);
    }
}