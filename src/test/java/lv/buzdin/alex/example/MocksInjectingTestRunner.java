package lv.buzdin.alex.example;

import com.google.inject.AbstractModule;
import com.google.inject.util.Modules;
import com.xtremelabs.robolectric.Robolectric;
import org.junit.runners.model.InitializationError;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import roboguice.RoboGuice;
import roboguice.test.RobolectricRoboTestRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MocksInjectingTestRunner extends RobolectricRoboTestRunner {

    public MocksInjectingTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    public void prepareTest(Object test) {
        super.prepareTest(test);
        MockitoAnnotations.initMocks(test);
        try {
            setupRoboguice(test, getListOfMocks(test));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to get instance of object", e);
        }
    }

    private List<Object> getListOfMocks(Object test) throws IllegalAccessException {
        final Field[] declaredFields = test.getClass().getDeclaredFields();
        List<Object> objects = new ArrayList<Object>();
        for (Field field : declaredFields) {
            if (field.getAnnotation(Mock.class) != null) {
                field.setAccessible(true);
                objects.add(field.get(test));
            }
        }
        return objects;
    }

    private void setupRoboguice(Object test, List<Object> objects) {
        RoboGuice.setBaseApplicationInjector(Robolectric.application, RoboGuice.DEFAULT_STAGE,
                Modules.override(RoboGuice.newDefaultRoboModule(Robolectric.application))
                        .with(new MyRoboModule(objects)));

        RoboGuice.injectMembers(Robolectric.application, test);
    }

    public static class MyRoboModule extends AbstractModule {

        private List<Object> mocksToInject;

        public MyRoboModule(List<Object> mocksToInject) {
            this.mocksToInject = mocksToInject;
        }

        @Override
        protected void configure() {
            for (final Object mock : mocksToInject) {
                Class clazz = mock.getClass();
                bind(clazz.getSuperclass()).toInstance(mock);
            }
        }
    }
}
