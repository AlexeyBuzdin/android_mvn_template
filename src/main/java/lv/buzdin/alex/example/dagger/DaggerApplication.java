package lv.buzdin.alex.example.dagger;

import android.app.Application;
import dagger.ObjectGraph;

public class DaggerApplication extends Application {

    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new DaggerModule(this));
    }

    public ObjectGraph objectGraph() {
        return objectGraph;
    }
}
