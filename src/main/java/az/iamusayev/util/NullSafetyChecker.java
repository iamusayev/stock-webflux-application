package az.iamusayev.util;

import az.iamusayev.exception.NullSafetyException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NullSafetyChecker {

    public void ensureNonNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                throw new NullSafetyException("Null value is not allowed");
            }
        }
    }
}