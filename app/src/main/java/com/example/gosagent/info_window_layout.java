package com.example.gosagent;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.gosagent.ui.main.AdapterInfoWindow;
import com.example.gosagent.ui.main.InformationPages;

import java.util.ArrayList;
import java.util.List;

public class info_window_layout extends AppCompatActivity {

    private AdapterInfoWindow adapterInfoWindow;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info_window_layout);

        setupAdapterInfoWind();

        linearLayout = findViewById(R.id.layoutOnAction);

        ViewPager2 viewPager2 = findViewById(R.id.viewOnAction);
        viewPager2.setAdapter(adapterInfoWindow);

        setupIndicator();
    }

    private void setupAdapterInfoWind() {
        List<InformationPages> elements = new ArrayList<>();
        int nameLot = 1;
        int countLot = PluginCore.LotInformationData.size();

        for (LotInformationsData lot : PluginCore.LotInformationData) {
            InformationPages item = new InformationPages();

            item.setTextLot("Лот " + nameLot + "/" + countLot);
            item.setNameOfLot(lot.numberLot);
            item.setImage(PluginCore.getIcon(this));
            item.setAddresses(lot.locationLot);
            item.setPrice(lot.initialPriceLot);
            item.setLink(lot.linkLot);
            item.setSelectedLot(lot);

            StringBuilder description = new StringBuilder();
            for (String information : lot.lotConfigs) {
                description.append(information).append("\n\n");
            }

            item.setDescription(description.toString().trim());

            elements.add(item);
            nameLot++;
        }

        adapterInfoWindow = new AdapterInfoWindow(elements);
    }

    private void setupIndicator() {
        ImageView[] indicators = new ImageView[adapterInfoWindow.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int index = 0; index < indicators.length; index++) {
            indicators[index] = new ImageView(getApplicationContext());
            indicators[index].setLayoutParams(layoutParams);
            linearLayout.addView(indicators[index]);
        }
    }
    // как-то подозрительно
    @Override
    protected void onStart() { super.onStart(); }

    @Override
    protected void onStop() { super.onStop(); }

    @Override
    protected void onResume() { super.onResume(); }

    @Override
    protected void onPause() { super.onPause(); }

    @Override
    protected void onDestroy() { super.onDestroy(); }
}