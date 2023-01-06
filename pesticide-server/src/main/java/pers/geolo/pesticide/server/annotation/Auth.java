package pers.geolo.pesticide.server.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    AuthType[] value() default AuthType.ALL;
}
