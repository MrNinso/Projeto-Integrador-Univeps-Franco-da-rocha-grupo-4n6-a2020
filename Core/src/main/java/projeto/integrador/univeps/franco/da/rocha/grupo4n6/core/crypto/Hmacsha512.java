package projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Hmacsha512 {

    private final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String HmacSHA512Hex(byte[] key, byte[] input) {
        byte[] b = HmacSHA512(key, input);
        return b != null ? bytesToHex(b) : "";
    }

    public static byte[] HmacSHA512(byte[] key, byte[] input) {
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(new SecretKeySpec(key, "HmacSHA512"));

            return mac.doFinal(input);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String assinar(String body, String email, String senha) {
        String key = Sha512.encrypt(String.format("%s%s", email, senha));
        return HmacSHA512Hex(key.getBytes(), body.getBytes());
    }

}
