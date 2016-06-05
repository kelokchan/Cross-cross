package boochatech.cross_cross;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import boochatech.cross_cross.Model.Product;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SwipeableRecyclerViewAdapter sectionAdapter;

    ToDoListSection todaySection;
    ToDoListSection tomorrowSection;
    ToDoListSection sixJuneSection;
    ToDoListSection tenJuneSection;

    List<Product> todayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Create an instance of SectionedRecyclerViewAdapter
        sectionAdapter = new SwipeableRecyclerViewAdapter();


        todayList = new ArrayList<>();
        todayList.add(new Product("Flower for Babe", 5.0, "at Pretty Petals", 22.00, R.drawable.flower1, 0));

        // Instantiate section with tags
        todaySection = new ToDoListSection(this, ToDoListSection.TODAY, "Today", todayList);
        tomorrowSection = new ToDoListSection(this, ToDoListSection.TOMORROW, "Tomorrow", todayList);
        sixJuneSection = new ToDoListSection(this, ToDoListSection.SIX_JUNE, "6th June, Monday", todayList);
        tenJuneSection = new ToDoListSection(this, ToDoListSection.TEN_JUNE, "10th June, Friday", todayList);

        // Add your Sections
        sectionAdapter.addSection(todaySection);
        sectionAdapter.addSection(tomorrowSection);
        sectionAdapter.addSection(sixJuneSection);
        sectionAdapter.addSection(tenJuneSection);

        // Set up your RecyclerView with the SectionedRecyclerViewAdapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.todo_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sectionAdapter);


//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(sectionAdapter, recyclerView);
//        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(recyclerView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                todayList.add(0, new Product(result, 5.0, "movie", 22.00, R.drawable.movie_xmen, 0));
                sectionAdapter.notifyDataSetChanged();
            }
        }
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == MOLPayActivity.MOLPayXDK && resultCode == RESULT_OK){
//            Log.d(MOLPayActivity.MOLPAY, "MOLPay result = " + data.getStringExtra(MOLPayActivity.MOLPayTransactionResult));
//        }
//
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
