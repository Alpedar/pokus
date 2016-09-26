package weapons;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin
 */
public class ReflectUtil {

    public static class FieldAccess {

        private Object obj;
        private Field field;

        public FieldAccess(Object obj, Field field) {
            this.obj = obj;
            this.field = field;
        }

        public String getName() {
            return field.getName();
        }

        public Object getValue() {
            field.setAccessible(true);
            try {
                return field.get(obj);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } finally {
                field.setAccessible(false);
            }

        }
    }

    public static List<FieldAccess> fieldsAsList(Object o) {
        List<FieldAccess> lst = new ArrayList<>();
        fieldsAsList(o, o.getClass(), lst);
        return lst;
    }

    public static void fieldsAsList(Object o, Class clazz, List<FieldAccess> accumulateInto) {
        if (clazz.equals(Object.class)) {
            return;
        }
        Field[] dFields = clazz.getDeclaredFields();
        for (Field f : dFields) {
            accumulateInto.add(new FieldAccess(o, f));
        }
        fieldsAsList(o, clazz.getSuperclass(), accumulateInto);
    }

    public static class ObjectAccess {

        private final String name;
        private final List<FieldAccess> fields;

        public ObjectAccess(Object obj) {
            Class<? extends Object> clazz = obj.getClass();
            name = clazz.getSimpleName();
            fields = fieldsAsList(obj);
        }

        public String getName() {
            return name;
        }

        public List<FieldAccess> getFields() {
            return fields;
        }

    }
}
