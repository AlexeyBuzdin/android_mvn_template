package lv.buzdin.alex.example.roboguice;

import android.content.Context;
import com.google.inject.Inject;
import lv.buzdin.alex.example.R;
import lv.buzdin.alex.example.dagger.DaggerActivity;

public class RoboGuiceStringProvider {

    Context context;

    @Inject
    public RoboGuiceStringProvider(DaggerActivity context) {
        this.context = context;
    }

    public String getString(){
        return context.getString(R.string.app_name) + ((RoboGuiceActivity)context).i;
    }
}
