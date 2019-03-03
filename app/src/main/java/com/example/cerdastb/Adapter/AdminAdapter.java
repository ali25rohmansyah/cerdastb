package com.example.cerdastb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cerdastb.Common.Common;
import com.example.cerdastb.Model.Admin;
import com.example.cerdastb.Model.Users;
import com.example.cerdastb.R;
import com.example.cerdastb.UserDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import io.paperdb.Paper;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {
    Context context;
    ArrayList<Users> users;
    DatabaseReference reference;
    SimpleDateFormat dateFormatter;
    String minumObat;


    public AdminAdapter(Context context, ArrayList<Users> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        String User = Paper.book().read(Common.User_Key);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        final Calendar newCalendar = Calendar.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("User");

//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                Users user = dataSnapshot.child(User).getValue(Users.class);
//                String jkel = user.getJkel();
//                if(jkel.equals("Pria")){
//                    myViewHolder.Gambar.setImageResource(R.drawable.ic_male);
//                }
//                else{
//                    myViewHolder.Gambar.setImageResource(R.drawable.ic_female);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        if (users.get(i).getJkel().equals("Pria")){
            myViewHolder.Gambar.setImageResource(R.drawable.ic_male);
        }else{
            myViewHolder.Gambar.setImageResource(R.drawable.ic_female);
        }
        myViewHolder.Nama.setText(users.get(i).getNama());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(users.get(i).getMinumObat().equals(dateFormatter.format(newCalendar.getTime()))){
                    minumObat = "Sudah";
                }
                else{
                    minumObat = "Belum";
                }
                Intent intent = new Intent(context, UserDetail.class);
                intent.putExtra("Nama",users.get(i).getNama());
                intent.putExtra("Pretest",users.get(i).getPreTest());
                intent.putExtra("Posttest",users.get(i).getPostTest());
                intent.putExtra("jenisTb",users.get(i).getJenisTb());
                intent.putExtra("jkel",users.get(i).getJkel());
                intent.putExtra("kota",users.get(i).getKota());
                intent.putExtra("NoHandphone",users.get(i).getNoHandphone());
                intent.putExtra("tanggalLahir",users.get(i).getTanggalLahir());
                intent.putExtra("tanggalDiagnosa",users.get(i).getTanggalDiagnosa());
                intent.putExtra("statusObat",minumObat);
                intent.putExtra("kunjung","Belum");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Nama;
        ImageView Gambar;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Nama = (TextView)itemView.findViewById(R.id.nama_user);
            Gambar = (ImageView)itemView.findViewById(R.id.gambar_user);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}