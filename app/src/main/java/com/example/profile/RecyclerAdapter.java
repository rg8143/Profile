package com.example.profile;

/**
 * Created by Rahul on 09-03-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    //Imageloader to load image
    private ImageLoader imageLoader;
    private Context context;

    //List to store all profiles
    List<ProfileData> profileList;


    //Constructor of this class
    public RecyclerAdapter(List<ProfileData> profileList, Context context){
        super();
        //Getting all profiles
        this.profileList = profileList;
        this.context = context;
    }



    class ViewHolder extends RecyclerView.ViewHolder{



        //Views
        public NetworkImageView imageView;
        public TextView profileName;
        public TextView profileSkill;



        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(NetworkImageView)itemView.findViewById(R.id.profile_photo);
            profileName=(TextView)itemView.findViewById(R.id.profile_name);
            profileSkill=(TextView)itemView.findViewById(R.id.profile_skill);

        }

    }



    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.profile_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        //Getting the particular item from the list
        final ProfileData mProfileData =  profileList.get(position);

        //Loading image from url
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(mProfileData.getImageUrl(), ImageLoader.getImageListener(holder.imageView,R.drawable.ic_profile,R.drawable.ic_profile));

        //Showing data on the views
        holder.imageView.setImageUrl(mProfileData.getImageUrl(),imageLoader);
        holder.profileName.setText(mProfileData.getName());
        holder.profileSkill.setText(mProfileData.getSkill());



    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }
}
