package secure;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DB に保存するパスワードを SHA-256 の
 * メッセージ・ダイジェストとして保存
 * プレイン・テキストとメッセージ・ダイジェストの変換ユーティリティ
 *
 * @author Yoshio Terada
 * @source: https://github.com/yoshioterada/JDBC-Realm-Sample/blob/master/src/main/java/jp/co/oracle/jdbcrealm/SHADigestUtil/SHA256Encoder.java
 */
public class SHA256Encoder {
    // SHA-256 ハッシュとして保存

    static public String encodePassword(String origPassword){
        String returnValue = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(origPassword.getBytes());
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                String tmp = Integer.toHexString(digest[i] & 0xff);
                if (tmp.length() == 1) {
                    builder.append('0').append(tmp);
                } else {
                    builder.append(tmp);
                }
            }
            returnValue = builder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SHA256Encoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnValue;
    }
}