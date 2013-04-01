package lv.buzdin.alex.example.dagger;

import android.content.Context;
import lv.buzdin.alex.example.R;

import javax.inject.Inject;


public class DaggerStringProvider {

    Context context;

    @Inject
    public DaggerStringProvider(Context context) {
        this.context = context;
    }

    public String getString(){
        return context.getString(R.string.app_name);
    }
}
