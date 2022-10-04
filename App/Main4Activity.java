package com.example.hunger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Main4Activity extends AppCompatActivity {

    Button update_btn , statistics_btn;

    TextView welcome;

    TextInputLayout meal, veg;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //String uri = "mongodb://pranjal:litchi09@mycluster-shard-00-00.raql5.mongodb.net:27017,mycluster-shard-00-01.raql5.mongodb.net:27017,mycluster-shard-00-02.raql5.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=atlas-nmn4xg-shard-0&authSource=admin&retryWrites=true&w=majority";
    //MongoClient mongoClient = MongoClients.create(uri) ;

    //MongoClient mongoClient = MongoClients.create(uri) ;
       // MongoDatabase database = mongoClient.getDatabase("hunger");
        //MongoCollection<Document> collection = database.getCollection("restaurants");
        //Document doc = collection.find(eq("title", "Back to the Future")).first();
        //System.out.println(doc.toJson());



    //MongoDatabase database = mongoClient.getDatabase("hunger");

    // Retrieving a collection
    //MongoCollection<Document> collection = database.getCollection("restaurants");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String userName = intent.getStringExtra("userName");

        welcome = findViewById(R.id.welcome);
        welcome.setText("Welcome back "+ name + " ! ");

        statistics_btn = findViewById(R.id.statistics_btn);

        statistics_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this, Main5Activity.class);
                startActivity(intent);
            }
        });

        meal = findViewById(R.id.meal);
        veg = findViewById(R.id.veg);

        update_btn = findViewById(R.id.update_btn);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String mealStatus = meal.getEditText().getText().toString();
                String vegStatus = veg.getEditText().getText().toString();

                //collection.updateOne(Filters.eq("Name", "Indian Adda Restaurant"), Updates.set("Availability", mealStatus));

                //Document query = new Document().append("Name",  "Indian Adda Restaurant");
                ///Bson updates = Updates.combine(
                        //Updates.set("Availability", mealStatus));
                        //Updates.addToSet("genres", "Sports"),
                        //Updates.currentTimestamp("lastUpdated"));
                //UpdateOptions options = new UpdateOptions().upsert(true);
                //try {
                    //UpdateResult result = collection.updateOne(query, updates, options);
                    //System.out.println("Modified document count: " + result.getModifiedCount());
                    //System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
                //} //catch (MongoException me) {
                  //  System.err.println("Unable to update due to an error: " + me);
                //}

                UserHelperClass2 helperClass2 = new UserHelperClass2(mealStatus, vegStatus);

                //String key = reference.push().getKey();

                //reference.child(userName).push().setValue(helperClass2);

                reference.child(userName).child("Meal").setValue(helperClass2);


                //collection.updateOne(Filters.eq("Name", "Indian Adda Restaurant"), Updates.set("Availability", mealStatus));


            }
        });

    }
}
