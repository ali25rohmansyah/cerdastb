package com.example.cerdastb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cerdastb.Model.MateriModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WebMainActivity extends AppCompatActivity {

    WebView webView;
    FirebaseDatabase database;
    DatabaseReference table_materi;
    TextView Judul;
    ImageButton backIMG;



    Activity activity ;
    private ProgressDialog progDailog;

    String materiID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_main);
        //get materiID from intent
        if(getIntent() != null){
            materiID = getIntent().getStringExtra("materiID");
        }



        database = FirebaseDatabase.getInstance();
        table_materi = database.getReference("Materi");
        backIMG = findViewById(R.id.backImage);

        activity = this;

        progDailog = ProgressDialog.show(activity, "Loading","Tunggu Sebentar...", true);
        progDailog.setCancelable(false);

        webView = findViewById(R.id.WebView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
        });

        table_materi.child(materiID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MateriModel materiModel = dataSnapshot.getValue(MateriModel.class);
                if (materiModel.getType().equals("web")){
                    webView.loadUrl(materiModel.getMateri().toString());
                }
                else if(materiModel.getType().equals("video")){
                    webView.loadData(loadLink(),"text/html", "utf-8");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private String loadLink() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        String weblink = "<iframe "
                +"width=\"100%\" height=\""+width+"\" "
                +"src=\"https://www.youtube.com/embed/5gQYzPlzVbw\" "
                +"frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; "
                +"picture-in-picture\" allowfullscreen=\"true\"></iframe>";

        return weblink;
    }
}
