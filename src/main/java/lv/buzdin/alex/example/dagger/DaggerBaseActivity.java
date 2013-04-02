package lv.buzdin.alex.example.dagger;

import android.app.Activity;
import android.os.Bundle;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public class DaggerBaseActivity extends Activity {

    @Inject
    Bus bus;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);

        // Android constructs Activity instances so we must find the ObjectGraph
        // instance and inject this.
        DaggerApplication.inject(this);
    }

    @Override protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }
}
