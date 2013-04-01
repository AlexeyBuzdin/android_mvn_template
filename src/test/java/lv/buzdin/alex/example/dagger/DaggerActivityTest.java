package lv.buzdin.alex.example.dagger;

import lv.buzdin.alex.example.MocksInjectingTestRunner;
import lv.buzdin.alex.example.R;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MocksInjectingTestRunner.class)
public class DaggerActivityTest {

    @Test
    public void shouldHaveHappySmiles() throws Exception {
        String appName = new DaggerActivity().getResources().getString(R.string.app_name);
        assertThat(appName, equalTo("android_mvn_template"));
    }
}