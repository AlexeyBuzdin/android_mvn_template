package lv.buzdin.alex.example.roboguice;

import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import lv.buzdin.alex.example.MocksInjectingTestRunner;
import lv.buzdin.alex.example.R;
import lv.buzdin.alex.example.dagger.DaggerActivity;
import lv.buzdin.alex.example.dagger.DaggerStringProvider;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import roboguice.test.RobolectricRoboTestRunner;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
@RunWith(RobolectricTestRunner.class)
public class RoboGuiceActivityTest {

    @InjectMocks
    RoboGuiceActivity activity;

    @Test
    public void shouldHaveISet() throws Exception {
        activity.onCreate(null);
        assertThat(activity.getI(), equalTo(1));
    }


    @Test
    public void shouldInjectStringProvider() throws Exception {
        when(activity.stringProvider.getString()).thenReturn("Injected");
        shadowOf(activity).callOnCreate(null);

        TextView textView = (TextView) activity.findViewById(R.id.textView);

        assertThat(textView.getText().toString(), equalTo("Injected"));
    }
}