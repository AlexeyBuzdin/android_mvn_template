package lv.buzdin.alex.example.dagger;

import android.app.Application;
import dagger.ObjectGraph;

public class DaggerApplication extends Application {

    private static ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new DaggerModule(this));
    }

    public static <T> void inject(T instance) {
        if(objectGraph != null) objectGraph.inject(instance);
    }
}
