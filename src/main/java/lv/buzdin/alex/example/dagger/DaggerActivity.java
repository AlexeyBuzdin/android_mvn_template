package lv.buzdin.alex.example.dagger;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Views;
import lv.buzdin.alex.example.R;

import javax.inject.Inject;

public class DaggerActivity extends DaggerBaseActivity {

    @Inject DaggerStringProvider stringProvider;
    @InjectView(R.id.textView) TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Views.inject(this);

        textView.setText(stringProvider.getString());
    }

}
