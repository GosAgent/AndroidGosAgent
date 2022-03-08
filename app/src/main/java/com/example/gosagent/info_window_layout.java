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
        viewPager2.setAdapter(adapterInfoWindow); ////

        setupIndicator();
    }

    private void setupAdapterInfoWind() {
        List<InformationPages> elements = new ArrayList<>();

        int lotName = 1;

        for (AllLotData lot : PluginCore.allLotData) {
            InformationPages item = new InformationPages();
            item.setTitle("ЛОТ " + lotName);

            int limit_information = 0;
            String description = "";
            for (String information : lot.lotConfigs) {
                if (limit_information != lot.lotConfigs.size() - 1) {
                    description += information + "\n\n";
                }
                limit_information++;
            }

            item.setDescription(description + "\n");
            item.setLink(lot.lotConfigs.get(lot.lotConfigs.size() - 1));

            lotName++;

            elements.add(item);
        }

        adapterInfoWindow = new AdapterInfoWindow(elements);
    }

    private void setupIndicator() {
        ImageView[] indicators = new ImageView[adapterInfoWindow.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i=0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            //indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
            //        R.drawable.ic_launcher_background));
            indicators[i].setLayoutParams(layoutParams);
            linearLayout.addView(indicators[i]);
        }
    }
    // если не планируется использовать закоментированный код, то лучше убрать
    @Override
    protected void onStart() {
        super.onStart();
        //System.out.print(new Object() {}.getClass().getEnclosingMethod().getName());
        //data.getData(); // типо тут мы получиаем данные
    }

    @Override
    protected void onStop() {
        super.onStop();
        //System.out.print(new Object() {}.getClass().getEnclosingMethod().getName());
        //data.setData(); // типо тут мы получиаем данные
    }



    /////  не обяз-но

    @Override
    protected void onResume() {
        super.onResume();
        //System.out.print(new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        //System.out.print(new Object() {}.getClass().getEnclosingMethod().getName());
        //
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //System.out.print(new Object() {}.getClass().getEnclosingMethod().getName());
    }
}