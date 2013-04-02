package lv.buzdin.alex.example.dagger;

import android.widget.TextView;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import lv.buzdin.alex.example.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module(
        includes = {DaggerModule.class },
        overrides = true,
        entryPoints = {DaggerActivityTest.class}
)
@RunWith(RobolectricTestRunner.class)
public class DaggerActivityTest {

    @Inject
    DaggerActivity activity;

    @Mock
    DaggerStringProvider stringProvider;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ObjectGraph.create(new DaggerModule(Robolectric.application), this).inject(this);
    }

    @Test
    public void shouldHaveAnAppName() throws Exception {
        String appName = activity.getResources().getString(R.string.app_name);
        assertThat(appName, equalTo("android_mvn_template"));
    }

    @Test
    public void shouldSetTextViewValue() throws Exception {
        when(stringProvider.getString()).thenReturn("Injected");

        activity.onCreate(null);
        TextView textView = (TextView) activity.findViewById(R.id.textView);

        assertThat(textView.getText().toString(), equalTo("Injected"));
    }

    @Provides
    DaggerStringProvider provideDaggerStringProvider(){
        return stringProvider;
    }

}