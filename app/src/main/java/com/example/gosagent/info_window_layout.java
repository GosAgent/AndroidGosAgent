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

        setupAdapterInfoWind(); //

        linearLayout = findViewById(R.id.layoutOnAction);

        ViewPager2 viewPager2 = findViewById(R.id.viewOnAction);
        viewPager2.setAdapter(adapterInfoWindow); ////

        setupIndicator();

    }

    private void setupAdapterInfoWind() {
        List<InformationPages> elements = new ArrayList<>();

        InformationPages item = new InformationPages();
        // item.setImage();  // тут же можно было бы туда картиночку добавить
        item.setTitle("ЛОТ 1");   /// mText
        item.setDescription(
        "Организатор торгов:\nКОМИТЕТ ПО УПРАВЛЕНИЮ МУНИЦИПАЛЬНЫМ ИМУЩЕСТВОМ ГОРОДА СТАВРОПОЛЯ\n\n" +
                "Номер извещения\\" +
                "Номер лота:\n141021/0074101/01 \\ " +
                "Лот 1 \n\n" +
                "Местоположение:\n" +
                "Ставропольский край, Ставрополь г, \n2 Промышленная ул \n\n" +
                "Площадь: 2861.0 м² \n\n" +
                "Начальная цена: 1 080 000 руб.\n\n");
        item.setLink("Ссылка: https://torgi.gov.ru/restricted/notification/notificationView.html?notificationId=55483028&lotId=55483033&prevPageN=7");

        InformationPages item1 = new InformationPages();
        // item.setImage();  // тут же можно было бы туда картиночку добавить
        item1.setTitle("ЛОТ 2");   /// mText
        item1.setDescription("много-много текста!!");   /// mText

        elements.add(item);
        elements.add(item1);
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