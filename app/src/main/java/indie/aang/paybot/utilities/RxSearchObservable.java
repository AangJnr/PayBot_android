package indie.aang.paybot.utilities;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxSearchObservable {

    public static Observable<String> fromView(SearchView searchView, View close) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                if(close.getVisibility() != View.VISIBLE)
                    close.setVisibility(View.VISIBLE);

                subject.onNext(text);
                return true;
            }
        });

        return subject;
    }

    public static Observable<String> fromView(SearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                subject.onNext(text);
                return true;
            }
        });

        return subject;
    }



    public static Observable<String> fromView(EditText editText, View close) {
        final PublishSubject<String> subject = PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                AppLogger.e("RxSEARCH >>> BEFORE TEXT CHANGED " +  s);

                if(close.getVisibility() != View.VISIBLE)
                close.setVisibility(View.VISIBLE);

                subject.onNext(s.toString());



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AppLogger.e("RxSEARCH >>> ON TEXT CHANGED " +  s);

                subject.onComplete();


            }

            @Override
            public void afterTextChanged(Editable s) {
                AppLogger.e("RxSEARCH >>> AFTER TEXT CHANGED " +  s);

            }
        });

        return subject;
    }



}