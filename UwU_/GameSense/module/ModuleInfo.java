package UwU_.GameSense.module;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    public String name();
    public Category type();
}
