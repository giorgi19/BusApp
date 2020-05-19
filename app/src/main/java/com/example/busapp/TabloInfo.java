package com.example.busapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TabloInfo extends AppCompatActivity {
    private TextView txt;
    TextView tv1;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablo_info);

        listView = (ListView)findViewById(R.id.TabloInfoListView);
        tv1=(TextView)findViewById(R.id.textView3);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            InputStream is = getAssets().open("TabloData.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("Route");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    arrayList.add(tv1.getText()+"TabloData : " + getValue("TabloData", element2)+"\n"
//                            +tv1.getText()+"HasBoard : " + getValue("HasBoard", element2)+"\n"
//                            +tv1.getText()+"Lat : " + getValue("Lat", element2)+"\n"
//                            +tv1.getText()+"Lon : " + getValue("Lon", element2)+"\n"
//                            +tv1.getText()+"Name : " + getValue("Name", element2)+"\n"
//                            +tv1.getText()+"Sequence : " + getValue("Sequence", element2)+"\n"
//                            +tv1.getText()+"StopId : " + getValue("StopId", element2)+"\n"
//                            +tv1.getText()+"Type : " + getValue("Type", element2)+"\n"
//                            +tv1.getText()+"Virtual : " + getValue("Virtual", element2)+"\n"
                    );
                }
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });
        } catch (Exception e) {e.printStackTrace();}
    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }}
