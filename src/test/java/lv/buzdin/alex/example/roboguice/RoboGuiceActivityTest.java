package lv.buzdin.alex.example.roboguice;

import android.widget.TextView;
import com.google.inject.AbstractModule;
import com.google.inject.util.Modules;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import lv.buzdin.alex.example.R;
import lv.buzdin.alex.example.dagger.DaggerStringProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import roboguice.RoboGuice;

import javax.inject.Inject;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class RoboGuiceActivityTest extends AbstractModule {

    RoboGuiceActivity activity;

    @Mock
    RoboGuiceStringProvider stringProvider;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        RoboGuice.setBaseApplicationInjector(Robolectric.application, RoboGuice.DEFAULT_STAGE,
                Modules.override(RoboGuice.newDefaultRoboModule(Robolectric.application)).with(this));

        activity = new RoboGuiceActivity();
        activity.stringProvider = stringProvider;
    }

    @Test
    public void shouldHaveISet() throws Exception {
        activity.onCreate(null);
        assertThat(activity.getI(), equalTo(1));
    }


    @Test
    public void shouldSetTextViewValue() throws Exception {
        when(stringProvider.getString()).thenReturn("Injected");
        activity.onCreate(null);

        TextView textView = (TextView) activity.findViewById(R.id.textView);

        assertThat(textView.getText().toString(), equalTo("Injected"));
    }

    @Override
    protected void configure() {
        bind(RoboGuiceStringProvider.class).toInstance(stringProvider);
    }
}