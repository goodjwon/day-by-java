package dev.wonslab.practice.core.container;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MiniContainer {

    private final Map<Class<?>, Function<MiniContainer, ?>> factories = new HashMap<>();
    private final Map<Class<?>, Object> singletons = new HashMap<>();

    public <T> void register(Class<T> type, Function<MiniContainer, ? extends T> factory) {
        factories.put(type, factory);
    }

    public <T> T get(Class<T> type) {
        Object bean = singletons.get(type);
        if (bean == null) {
            Function<MiniContainer, ?> factory = factories.get(type);
            if (factory == null) {
                throw new IllegalStateException("등록되지 않은 타입입니다: " + type.getSimpleName());
            }
            bean = factory.apply(this);
            singletons.put(type, bean);
        }
        return type.cast(bean);
    }
}
