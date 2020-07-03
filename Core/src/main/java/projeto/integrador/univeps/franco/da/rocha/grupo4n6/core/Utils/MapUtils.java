package projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.Utils;

import java.util.Map;

public class MapUtils {
    public static boolean containsKeys(Map<? , ?> m, Object...keys) {
        for (Object key : keys) {
            if (!m.containsKey(keys))
                return false;
        }

        return true;
    }
}
