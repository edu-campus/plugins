package de.hems.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private final Map<Class<?>, List<RegisteredListener>> listeners = new HashMap<>();

    public void registerEvents(Listener listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(EventHandler.class)) continue;

            Class<?>[] params = method.getParameterTypes();
            if (params.length != 1 || !Event.class.isAssignableFrom(params[0])) continue;

            Class<?> eventType = params[0];
            method.setAccessible(true);

            listeners.computeIfAbsent(eventType, k -> new ArrayList<>())
                    .add(new RegisteredListener(listener, method));
        }
    }

    public void callEvent(Event event) {
        List<RegisteredListener> regs = listeners.getOrDefault(event.getClass(), new ArrayList<>());
        for (RegisteredListener reg : regs) {
            try {
                reg.method.invoke(reg.instance, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class RegisteredListener {
        final Listener instance;
        final Method method;

        RegisteredListener(Listener instance, Method method) {
            this.instance = instance;
            this.method = method;
        }
    }
}
