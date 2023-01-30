package com.example.demo.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MyZip {
    private void zip(String zipFileName, File intputFile) {
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
            zip(out, intputFile, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void zip(ZipOutputStream out, File f, String base) {
        try {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                if (base.length() != 0) {
                    out.putNextEntry(new ZipEntry(base + "/"));
                }
                for (int i = 0; i < files.length; i++) {
                    zip(out, files[i], base + files[i]);
                }
            } else {
                out.putNextEntry(new ZipEntry(base));
                FileInputStream in = new FileInputStream(f);
                int b;
                System.out.println(base);
                while ((b = in.read()) != -1) {
                    out.write(b);
                }
                in.close();
            }
        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) throws Exception{
        MyZip myZip = new MyZip();
        myZip.zip("d:/hello.zip", new File("d:/hello"));
        System.out.println("压缩完成");
    }
}
