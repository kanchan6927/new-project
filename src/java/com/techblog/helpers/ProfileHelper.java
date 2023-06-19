/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techblog.helpers;

import java.io.*;

public class ProfileHelper {

    public static boolean deleteFile(String path) {

        boolean f = false;

        File file = new File(path);
        f = file.delete();

        return f;
    }

    public static boolean saveFile(InputStream is, String path) {

        boolean f = false;

        try {
            byte b[] = new byte[is.available()];
            is.read(b);

            FileOutputStream fos = new FileOutputStream(path);
            fos.write(b);
            fos.flush();
            fos.close();
            f = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return f;

    }

}
