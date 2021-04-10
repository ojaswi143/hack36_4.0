package com.rachit2525.jeevika;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Vector;

import static com.rachit2525.jeevika.PublishActivity.POST_MSG;
import static com.rachit2525.jeevika.PublishActivity.NO_OF_POSTS;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseFirestore db;
    //Vector<String> vect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        db = FirebaseFirestore.getInstance();
        //vect = new Vector<String>();



        ArrayList<PostItem> postList = new ArrayList<>();
        postList.add(new PostItem(R.drawable.ic_gola,"Hello"));
//        for(int i=1;i<=5;i++){
//            postList.add(new PostItem(R.drawable.ic_gola,"Hello"));
//
//        }

        db.collection("All Post").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                String det = "Hello \n";
                for(DocumentSnapshot snapshot : value){

                    String nos = snapshot.getString("post_data");
                    String pos = snapshot.getString("position");
                    //vect.add(nos);

                    Log.i("Abc",nos);
                    try{
                        det = det + nos + "\n";
                        //vect.add(nos);
                        postList.add(new PostItem(R.drawable.ic_gola,"Hello"));
                        Toast.makeText(getApplicationContext(), "Feed Updated Successfully!", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                    }
                }

                Toast.makeText(FeedActivity.this, det, Toast.LENGTH_LONG).show();
            }
        });

//        Log.i("Vect Size:",Integer.toString(vect.size()));
//        for(int i=vect.size()-1;i>0;i--){
//            Log.i("At:","hello");
//            postList.add(new PostItem(R.drawable.ic_gola,vect.get(i)));
//        }


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PostAdapter(postList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
