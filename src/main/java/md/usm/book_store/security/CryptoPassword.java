package md.usm.book_store.security;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class CryptoPassword {
    public static String encryptPassword(String inputPassword) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.encryptPassword(inputPassword);
    }

    public static boolean checkPassword(String inputPassword, String encryptedStoredPassword) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.checkPassword(inputPassword, encryptedStoredPassword);
    }
}
