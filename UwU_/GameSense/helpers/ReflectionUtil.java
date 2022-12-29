package UwU_.GameSense.helpers;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static Field getField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }



}
