package com.user.frenzi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frenzi.R;
import com.user.frenzi.Responce.ResponceFetchRecentAddressList;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecentAddressList extends RecyclerView.Adapter<AdapterRecentAddressList.MyViewHolder>{

    private static final String TAG = "AdapterResidentsFamilyL";
    private Context mContext;
    private final AdapterRecentAddressList.OnItemClickListener listener;
    private List<ResponceFetchRecentAddressList.Response> AssignmentList;
    private static final String USER_PREF = "USER_PREF";
    private static final String SHOP_NAME = "SHOP_NAME";
    private static final String SHOP_ADDRESS= "SHOP_ADDRESS";
    private static final String ORDER_STATUS= "ORDER_STATUS";
    private static final String ORDER_TOTAL_PRIZE= "ORDER_TOTAL_PRIZE";
    private static final String ORDER_ID= "ORDER_ID";
    private static final String ORDER_PLACED_ID= "ORDER_PLACED_ID";

    //FAMILY MEMEBER DETAIL DB
    private static final String SHOP_BANNER = "SHOP_BANNER";


    private Fragment callingFragment;



    public interface OnItemClickListener {
        void onItemClick(ResponceFetchRecentAddressList.Response item);

    }

    public AdapterRecentAddressList(Context mContext, List<ResponceFetchRecentAddressList.Response> AssignmentList,  AdapterRecentAddressList.OnItemClickListener listener) {
        this.AssignmentList = AssignmentList;
        this.listener = listener;
        this.mContext=mContext;



    }

    @NonNull
    @Override
    public AdapterRecentAddressList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recent_address, parent, false);
        return new AdapterRecentAddressList.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecentAddressList.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.txt_address.setText(AssignmentList.get(position).getAddress());
//        holder.txt_date.setText(AssignmentList.get(position).getD());

        holder.full_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(AssignmentList.get(position));
            }
        });

        switch (AssignmentList.get(position).getAddressStatus()) {
            case "Home":
                holder.iv_address.setImageResource(R.drawable.home_address);
                break;
            case "Work":
                holder.iv_address.setImageResource(R.drawable.work_address);
                break;
            case "Other":
                holder.iv_address.setImageResource(R.drawable.other_address);
                break;
            default:
                holder.iv_address.setImageResource(R.drawable.history_gray);
                break;
        }


    }



    public void filterList(ArrayList<ResponceFetchRecentAddressList.Response> filterdNames) {
//        Log.e(TAG, "ADAPTER -- filterList: " + filterdNames );
        this.AssignmentList = filterdNames;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return AssignmentList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_address,txt_address_2;
        RelativeLayout full_layout;
        ImageView iv_address;


        public MyViewHolder(View view) {
            super(view);

            txt_address = view.findViewById(R.id.txt_address);
            txt_address_2=view.findViewById(R.id.txt_address_2);
            full_layout=view.findViewById(R.id.full_layout);
            iv_address=view.findViewById(R.id.iv_address);




        }


    }
}


