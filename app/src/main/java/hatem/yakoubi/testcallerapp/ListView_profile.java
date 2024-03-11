package hatem.yakoubi.testcallerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import hatem.yakoubi.testcallerapp.DAO.ProfileDAO;
import hatem.yakoubi.testcallerapp.DAOImp.ProfileDAOImplementation;

public class ListView_profile extends AppCompatActivity {

    ProfileAdapter profileAdapter;
    List<Profile> profileList;
    ProfileDAO profileDAO;
    RecyclerView recyclerView;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_profile);
        profileDAO = new ProfileDAOImplementation(ListView_profile.this);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        profileList = profileDAO.getProfiles();
        profileAdapter = new ProfileAdapter(profileList,this);
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListView_profile.this));

                  searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filterData(newText);
                    return true;
                }
            });
    }

    public  void filterData(String txt){
            List<Profile> filterList = new ArrayList<>();
            for (Profile item:profileList){
                if (item.getName().toLowerCase().contains(txt.toLowerCase())){
                    filterList.add(item);
                }

            }
            profileAdapter = new ProfileAdapter(filterList,ListView_profile.this);
            recyclerView.setAdapter(profileAdapter);
        }

}