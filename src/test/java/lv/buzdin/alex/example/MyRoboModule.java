package lv.buzdin.alex.example;

import com.google.inject.AbstractModule;

import java.util.List;

public class MyRoboModule extends AbstractModule {

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
