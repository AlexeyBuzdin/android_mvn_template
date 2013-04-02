package lv.buzdin.alex.example.dagger;

import android.content.Context;
import android.location.LocationManager;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(
        entryPoints = {
                DaggerApplication.class,
                DaggerActivity.class
        }
)
public class DaggerModule {
    private final Context appContext;

    public DaggerModule(Context appContext) {
        this.appContext = appContext.getApplicationContext();
    }

    @Provides @Singleton
    Bus provideBus() {
        return new Bus();
    }

    @Provides Context provideContext() {
        return appContext;
    }

}
