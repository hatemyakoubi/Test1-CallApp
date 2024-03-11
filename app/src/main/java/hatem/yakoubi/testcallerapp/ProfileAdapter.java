package hatem.yakoubi.testcallerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hatem.yakoubi.testcallerapp.DAO.ProfileDAO;
import hatem.yakoubi.testcallerapp.DAOImp.ProfileDAOImplementation;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<Profile> profileList;
    private Activity context;

    private static final int REQUEST_PHONE_CALL = 1;

    int selectedPosition = RecyclerView.NO_POSITION;

    public ProfileAdapter(List<Profile> profileList, Activity context) {
        this.profileList = profileList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_profile, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profile profile = profileList.get(position);
        holder.bind(profile,position);
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView emailTextView;
        private TextView phoneTextView;
        private TextView addressTextView;

        private ImageView callIcon;

        private ImageView editIcon;

        private ImageView deleteIcon;

        private ProfileDAO profileDAO;
        SearchView searchView;
        ProfileAdapter profileAdapter;
        RecyclerView recyclerView;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            callIcon = itemView.findViewById(R.id.ivcall_list);
            editIcon = itemView.findViewById(R.id.ivedit_list);
            deleteIcon = itemView.findViewById(R.id.ivdelete_list);
            searchView = itemView.findViewById(R.id.searchView);
           // profileDAO = new ProfileDAOImplementation(context);
           //recyclerView = itemView.findViewById(R.id.recyclerView);
            //profileList = profileDAO.getProfiles();
           // profileAdapter = new ProfileAdapter(profileList,context);
            //recyclerView.setAdapter(profileAdapter);

            callIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Profile profile = profileList.get(position);
                    String phone = profile.getPhone();
                    // add permission to call without passing by dial (phone keyboard)
                    if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(context, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                    } else {
                        String dial = "tel:" + phone;
                        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                    }
                }
            });

            editIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Profile profile = profileList.get(position);

                    // Create intent to start UpdateProfileActivity
                    Intent intent = new Intent(context, UpdateProfileActivity.class);

                    // Pass profile details as extras to the intent
                   /* intent.putExtra("PROFILE_ID", profile.getId());
                    intent.putExtra("PROFILE_NAME", profile.getName());
                    intent.putExtra("PROFILE_EMAIL", profile.getEmail());
                    intent.putExtra("PROFILE_PHONE", profile.getPhone());
                    intent.putExtra("PROFILE_ADDRESS", profile.getAddress());
                      */
                    intent.putExtra("PROFILE",profile);
                    // Start the activity
                    context.startActivity(intent);
                    context.finish();
                }
            });


            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Profile profile = profileList.get(position);
                    profileDAO = new ProfileDAOImplementation(context);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Delete Profile");
                    builder.setMessage("Are you sure you want to delete this item?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        profileDAO.deleteProfile(profile.getId());
                        profileList.remove(profile);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, profileList.size());
                        Toast.makeText(context, "Profile deleted", Toast.LENGTH_SHORT).show();
                    });
                    builder.setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    });
                    builder.show();
                }
            });



        }


        public void bind(Profile profile , int position) {
            nameTextView.setText(profile.getName());
            emailTextView.setText(profile.getEmail());
            phoneTextView.setText(profile.getPhone());
            addressTextView.setText(profile.getAddress());
        }
    }
}
