package ai.iston.common.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;

/**
 * @author pengmf
 * @since 2021/11/5
 */
public class BeanCopyUtil {


    public static <S, T> T copy(S s, T t) {
        BeanCopier.create(s.getClass(), t.getClass(), false).copy(s, t, null);

        return t;
    }


    public static <S, T> T copy(S s, Class<T> t) {
        T t1 = null;
        try {
            t1 = t.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        BeanCopier.create(s.getClass(), t, false).copy(s, t1, null);
        return t1;
    }

}
