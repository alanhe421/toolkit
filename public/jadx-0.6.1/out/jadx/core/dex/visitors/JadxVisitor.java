package jadx.core.dex.visitors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JadxVisitor {
    String desc() default "";

    String name();

    Class<? extends IDexTreeVisitor>[] runAfter() default {};

    Class<? extends IDexTreeVisitor>[] runBefore() default {};
}
