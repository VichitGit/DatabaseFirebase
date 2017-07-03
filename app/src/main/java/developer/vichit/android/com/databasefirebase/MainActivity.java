package developer.vichit.android.com.databasefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import developer.vichit.android.com.databasefirebase.authentication.signup.SinginActivity;

import static developer.vichit.android.com.databasefirebase.R.id.lvArticle;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView lvArticles;
    ArrayList<String> listArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvArticles = (ListView) findViewById(lvArticle);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("articles");
        //auto generate key
//        String id = databaseReference.push().getKey();
//        databaseReference.child(id).setValue(new Articles(id, "davy"));

        listArticles = new ArrayList<>();


        /*
        Loading data from firebase database, Get all data
         */
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.e("ppppp", snapshot.toString()); // Get all data
                    Articles articles = snapshot.getValue(Articles.class); //get data that want
                    Log.e("ppppp", articles.getTitle());

                    listArticles.add(articles.getTitle());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_list_item_1, listArticles);
                    lvArticles.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.auth_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                FirebaseAuth.getInstance().signOut();
            }
            startActivity(new Intent(this, SinginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
