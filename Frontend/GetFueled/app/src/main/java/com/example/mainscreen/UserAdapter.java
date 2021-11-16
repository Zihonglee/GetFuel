package com.example.mainscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
{
    private LayoutInflater inflater;
    private List<User> users;
    public static String UserName;
    private String ID;
    private String Role;


    public UserAdapter(Context ctx, List<User> restaurants)
    {
        this.inflater = LayoutInflater.from(ctx);
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.user_layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position)
    {
        holder.userName.setText(users.get(position).getUserName());
        holder.userId.setText(users.get(position).getId());
        holder.userRole.setText(users.get(position).getRole());
    }

    @Override
    public int getItemCount()
    {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        TextView userName;
        TextView userId;
        TextView userRole;


        public UserViewHolder(View itemView)
        {
            super(itemView);

            userName = itemView.findViewById(R.id.udb_username);
            userId = itemView.findViewById(R.id.udb_userid);
            userRole = itemView.findViewById(R.id.udb_userrole);
        }

    }
}
