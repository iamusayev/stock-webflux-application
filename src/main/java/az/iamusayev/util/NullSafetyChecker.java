package az.iamusayev.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NullSafetyChecker {


      public void ensureNonNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                throw new NullPointerException("One or more objects are null");
            }
        }
    }
}