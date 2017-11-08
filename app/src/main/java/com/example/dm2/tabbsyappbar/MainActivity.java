package com.example.dm2.tabbsyappbar;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    private ListView l;
    private Menu menu;
    private int m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l= (ListView) findViewById(R.id.listaLlamadas);
        //Cogemos el menu por defecto que tenemos creado
        m=R.menu.menu_main;
        //Rellenamos la listas de llamadas
        final ArrayAdapter adapLlamadas= ArrayAdapter.createFromResource
                (this, R.array.llamadas, android.R.layout.simple_list_item_1);

        l.setAdapter(adapLlamadas);

        Resources res= getResources();
        TabHost tabs= (TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("llamadas");
        spec.setContent(R.id.llamadas);
        spec.setIndicator("Llamadas");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("chats");
        spec.setIndicator("Chats");
        spec.setContent(R.id.chat);
        tabs.addTab(spec);

        spec=tabs.newTabSpec("contactos");
        spec.setIndicator("Contactos");
        spec.setContent(R.id.contactos);
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //LLenamos el resto de lista de las pestañas
                if (tabId.equalsIgnoreCase("chats")){
                    l= (ListView) findViewById(R.id.listaChats);
                    ArrayAdapter adapLlamadas= ArrayAdapter.createFromResource
                            (MainActivity.this, R.array.chats, android.R.layout.simple_list_item_1);
                    l.setAdapter(adapLlamadas);

                    menu.clear();
                   m=R.menu.menu_chat;
                    onCreateOptionsMenu(menu);
                }

                if (tabId.equalsIgnoreCase("contactos")){
                    l= (ListView) findViewById(R.id.listaCobtactos);
                    ArrayAdapter adapLlamadas= ArrayAdapter.createFromResource
                            (MainActivity.this, R.array.contactos, android.R.layout.simple_list_item_1);
                    l.setAdapter(adapLlamadas);

                    menu.clear();
                    m=R.menu.menu_contactos;
                    onCreateOptionsMenu(menu);
                }

                if(tabId.equalsIgnoreCase("llamadas")){
                    menu.clear();
                    m=R.menu.menu_main;
                    onCreateOptionsMenu(menu);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Ponemos el nombe  y el menu de arriba
        this.menu=menu;
        setTitle("Whatsapp");
        getMenuInflater().inflate(m,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
