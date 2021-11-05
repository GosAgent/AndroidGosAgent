package com.example.gosagent.ui.main;

// layout
// name off classes
// search
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gosagent.R;

import java.util.List;

public class AdapterInfoWindow extends RecyclerView.Adapter<AdapterInfoWindow.PageHolder>{

    private List<InformationPages> informationOnPageList;

    public AdapterInfoWindow(List<InformationPages> informationOnPageList) {
        this.informationOnPageList = informationOnPageList;
    }

    @NonNull
    @Override
    public PageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.info_window_layout,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PageHolder holder, int position) {
        holder.setOnPageInformation(informationOnPageList.get(position));
    }

    @Override
    public int getItemCount() {
        return informationOnPageList.size();
    }

    class PageHolder extends RecyclerView.ViewHolder {

        private ImageView imageView; /// ну это если мы картинку добавили бы какую-нибудь
        private TextView textTitle;
        private TextView textDescription;
        private TextView textLink;

        PageHolder(@NonNull View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.image);
            textTitle = itemView.findViewById(R.id.textTitle);  // add
            textDescription = itemView.findViewById(R.id.textDescription);  // add
            textLink = itemView.findViewById(R.id.textLink);

        }

        void setOnPageInformation(InformationPages information) {
            textTitle.setText(information.getTitle());
            textDescription.setText(information.getDescription());
            textLink.setText(information.getLink());
        }
    }
}
