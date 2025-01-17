package com.example.qlthitracnghiem.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
  public static boolean checkPassword(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }

  public static String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }
}
