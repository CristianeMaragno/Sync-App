    package com.example.user.sync;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {


        private DrawerLayout dl;
        private ActionBarDrawerToggle abdt;

        private Button botaoSaudade;
        private Button botaoConversar;
        private Button botaoPreciso;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ///////////////////////DrawerButtom////////////////////////////////////////////

            dl = (DrawerLayout) findViewById(R.id.dl);
            abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
            abdt.setDrawerIndicatorEnabled(true);

            dl.addDrawerListener(abdt);
            abdt.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

            nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    int id = item.getItemId();

                    if (id == R.id.calendarioId){
                        Toast.makeText(MainActivity.this, "Calendário", Toast.LENGTH_SHORT).show();
                    }else if (id == R.id.diarioId){
                        Toast.makeText(MainActivity.this, "Diario", Toast.LENGTH_SHORT).show();
                    }else if (id == R.id.areadrId){
                        Toast.makeText(MainActivity.this, "Area da DR", Toast.LENGTH_SHORT).show();
                    }else if (id == R.id.galeriaId){
                        Toast.makeText(MainActivity.this, "Galeria de Fotos", Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
            });
            ///////////////////////DrawerButtom////////////////////////////////////////////

            ///////////////////////Botoes Tela////////////////////////////////////////////

            botaoSaudade = (Button) findViewById(R.id.botaoSaudadeId);
            botaoConversar = (Button) findViewById(R.id.botaoConversarId);
            botaoPreciso = (Button) findViewById(R.id.botaoPrecisoId);

            botaoSaudade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Botão Saudade clicado", Toast.LENGTH_SHORT).show();
                }
            });

            botaoConversar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Botão Conversar clicado", Toast.LENGTH_SHORT).show();
                }
            });

            botaoPreciso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Botão Preciso clicado", Toast.LENGTH_SHORT).show();
                }
            });

            ///////////////////////Botoes Tela////////////////////////////////////////////
        }





        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
        }
    }