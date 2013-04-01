package lv.buzdin.alex.example.roboguice;

import android.os.Bundle;
import android.widget.TextView;
import com.google.inject.Inject;
import lv.buzdin.alex.example.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class RoboGuiceActivity extends RoboActivity {

    int i;

    @Inject RoboGuiceStringProvider stringProvider;
    @InjectView(R.id.textView) TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        i = 1;
        textView.setText(stringProvider.getString());
    }

}
