package com.example.gosagent.ui.main;

import android.text.Html;
import android.widget.TextView;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.gosagent.R;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            if (mIndex.getValue() == 1) {
                //TextView t2 = (TextView) findViewById(R.id.section_label);

                return "Организатор торгов:\nКОМИТЕТ ПО УПРАВЛЕНИЮ МУНИЦИПАЛЬНЫМ ИМУЩЕСТВОМ ГОРОДА СТАВРОПОЛЯ\n\n" +
                        "Номер извещения\\Номер лота:\n141021/0074101/01 \\ Лот 1 \n\n" +
                        "Местоположение:\nСтавропольский край, Ставрополь г, \n2 Промышленная ул \n\n" +
                        "Площадь: 2861.0 м² \n\n" +
                        "Начальная цена: 1 080 000 руб.\n\n" +
                        "Ссылка: https://torgi.gov.ru/restricted/notification/notificationView.html?notificationId=55483028&lotId=55483033&prevPageN=7";
            }
            return "Организатор торгов:\nДЕПАРТАМЕНТ ИМУЩЕСТВЕННЫХ ОТНОШЕНИЙ АДМИНИСТРАЦИИ ГОРОДА ОМСКА\n\n" +
                "Номер извещения\\Номер лота:\n\"141021/0069468/01 \\" + "Лот 1 \n\n" +
                "Местоположение:\nОмская обл, Омск г, Маршала Жукова ул \n\n" +
                "Площадь:\n548.1 м² \n\n" +
                "Начальная цена:\n1 772 400 руб.\n\n" +
                "Ссылка: https://torgi.gov.ru/restricted/notification/notificationView.html?notificationId=55483028&lotId=55483031&prevPageN=0"; }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}