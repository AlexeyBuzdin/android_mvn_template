package lv.buzdin.alex.example.dagger;

import android.widget.TextView;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import lv.buzdin.alex.example.MocksInjectingTestRunner;
import lv.buzdin.alex.example.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class DaggerActivityTest {

    @Inject
    DaggerActivity activity;

    @Before
    public void setUp() throws Exception {
        ObjectGraph.create(new DaggerModule(Robolectric.application), new ServiceTestModule()).inject(this);
    }

    @Test
    public void shouldHaveAnAppName() throws Exception {
        String appName = activity.getResources().getString(R.string.app_name);
        assertThat(appName, equalTo("android_mvn_template"));
    }

    @Test
    public void shouldInjectStringProvider() throws Exception {
        when(activity.stringProvider.getString()).thenReturn("Injected");

        shadowOf(activity).callOnCreate(null);
        TextView textView = (TextView) activity.findViewById(R.id.textView);

        assertThat(textView.getText().toString(), equalTo("Injected"));
    }

    @Module(
            includes = {DaggerModule.class },
            overrides = true,
            entryPoints = {DaggerActivity.class, DaggerActivityTest.class}
    )
    static class ServiceTestModule {

        @Provides
        DaggerStringProvider provideDaggerStringProvider(){
            return mock(DaggerStringProvider.class);
        }
    }

}