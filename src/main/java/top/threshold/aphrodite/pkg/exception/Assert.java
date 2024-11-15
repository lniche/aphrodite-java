package top.threshold.aphrodite.pkg.exception;

import java.util.Collection;
import java.util.Map;

public interface Assert {

    // Method to create a new exception, to be implemented by classes that implement Assert
    KtException newException(Object... args);

    // Method to create a new exception, to be implemented by classes that implement Assert
    KtException newException(Throwable t, Object... args);

    // Assert that the object is not null
    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException();
        }
    }

    // Assert that the object is not null with custom arguments
    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw newException(args);
        }
    }

    // Assert that the object is null
    default void assertIsNull(Object obj) {
        if (obj != null) {
            throw newException();
        }
    }

    // Assert that the object is null with custom arguments
    default void assertIsNull(Object obj, Object... args) {
        if (obj != null) {
            throw newException(args);
        }
    }

    // Assert that the string is not empty
    default void assertNotEmpty(String str) {
        if (str == null || str.trim().isEmpty()) {
            throw newException();
        }
    }

    // Assert that the string is not empty with custom arguments
    default void assertNotEmpty(String str, Object... args) {
        if (str == null || str.trim().isEmpty()) {
            throw newException(args);
        }
    }

    // Assert that the array is not empty
    default void assertNotEmpty(Object[] arrays) {
        if (arrays == null || arrays.length == 0) {
            throw newException();
        }
    }

    // Assert that the array is not empty with custom arguments
    default void assertNotEmpty(Object[] arrays, Object... args) {
        if (arrays == null || arrays.length == 0) {
            throw newException(args);
        }
    }

    // Assert that the collection is not empty
    default void assertNotEmpty(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            throw newException();
        }
    }

    // Assert that the collection is not empty with custom arguments
    default void assertNotEmpty(Collection<?> c, Object... args) {
        if (c == null || c.isEmpty()) {
            throw newException(args);
        }
    }

    // Assert that the map is not empty
    default void assertNotEmpty(Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            throw newException();
        }
    }

    // Assert that the map is not empty with custom arguments
    default void assertNotEmpty(Map<?, ?> map, Object... args) {
        if (map == null || map.isEmpty()) {
            throw newException(args);
        }
    }

    // Assert that the expression is false
    default void assertIsFalse(boolean expression) {
        if (expression) {
            throw newException();
        }
    }

    // Assert that the expression is false with custom arguments
    default void assertIsFalse(boolean expression, Object... args) {
        if (expression) {
            throw newException(args);
        }
    }

    // Assert that the expression is true
    default void assertIsTrue(boolean expression) {
        if (!expression) {
            throw newException();
        }
    }

    // Assert that the expression is true with custom arguments
    default void assertIsTrue(boolean expression, Object... args) {
        if (!expression) {
            throw newException(args);
        }
    }

    // Assert failure with no message
    default void assertFail() {
        throw newException();
    }

    // Assert failure with custom arguments
    default void assertFail(Object... args) {
        throw newException(args);
    }

    // Assert failure with a throwable
    default void assertFail(Throwable t) {
        throw newException(t);
    }

    // Assert failure with throwable and custom arguments
    default void assertFail(Throwable t, Object... args) {
        throw newException(t, args);
    }

    // Assert that two objects are equal
    default void assertEquals(Object o1, Object o2) {
        if (o1 == o2) {
            return;
        }
        if (o1 == null || !o1.equals(o2)) {
            throw newException();
        }
    }

    // Assert that two objects are equal with custom arguments
    default void assertEquals(Object o1, Object o2, Object... args) {
        if (o1 == o2) {
            return;
        }
        if (o1 == null || !o1.equals(o2)) {
            throw newException(args);
        }
    }
}
