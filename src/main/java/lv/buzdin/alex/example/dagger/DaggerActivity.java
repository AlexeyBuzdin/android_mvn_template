package lv.buzdin.alex.example.dagger;

import android.os.Bundle;
import android.widget.TextView;
import lv.buzdin.alex.example.R;

import javax.inject.Inject;

public class DaggerActivity extends DaggerBaseActivity {

    @Inject
    DaggerStringProvider stringProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((TextView) findViewById(R.id.textView)).setText(stringProvider.getString());
    }

}
