package lv.buzdin.alex.example.roboguice;

import android.os.Bundle;
import android.widget.TextView;
import com.google.inject.Inject;
import lv.buzdin.alex.example.R;
import roboguice.activity.RoboActivity;

public class RoboGuiceActivity extends RoboActivity {

    int i;

    @Inject
    RoboGuiceStringProvider stringProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);



        i = 1;
        ((TextView) findViewById(R.id.textView)).setText(stringProvider.getString());
    }

}
