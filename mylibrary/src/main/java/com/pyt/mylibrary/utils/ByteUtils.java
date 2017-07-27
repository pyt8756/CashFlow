package com.pyt.mylibrary.utils;


import com.pyt.mylibrary.utils.io.Charsets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import static com.pyt.mylibrary.utils.IOUtils.close;
import static com.pyt.mylibrary.utils.IOUtils.copy;


public class ByteUtils {

    private static final int EOF = -1;

    /**
     * byte[] 转为 对象
     */
    public static Object byteToObject(byte[] bytes) throws Exception {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return ois.readObject();
        } finally {
            if (ois != null) ois.close();
        }
    }

    /**
     * 对象 转为 byte[]
     */
    public static byte[] objectToByte(Object obj) throws Exception {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return bos.toByteArray();
        } finally {
            if (oos != null) oos.close();
        }
    }

    public static void byteToBit(byte[] bytes, StringBuilder sb) {
        for (int i = 0; i < Byte.SIZE * bytes.length; i++)
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
    }

    public static String byteToBit(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Byte.SIZE * bytes.length; i++)
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }

    public static byte[] getBytes(String src, Charset charSet) {
        return src.getBytes(charSet);
    }


    /**
     * 获取InputStream的内容，返回byte[]。
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    /**
     * 获取InputStream的内容，返回byte[]。
     */
    public static byte[] toByteArray(InputStream input, int size) throws IOException {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size);
        }
        if (size == 0) {
            return new byte[0];
        }
        byte[] data = new byte[size];
        int offset = 0;
        int readed;
        while (offset < size && (readed = input.read(data, offset, size - offset)) != EOF) {
            offset += readed;
        }
        if (offset != size) {
            throw new IOException("Unexpected readed size. current: " + offset + ", excepted: " + size);
        }
        return data;
    }

    /**
     * 获取Reader的内容，返回byte[]。
     */
    public static byte[] toByteArray(Reader input) throws IOException {
        return toByteArray(input, Charset.defaultCharset());
    }

    /**
     * 使用指定的字符编码获取Reader的内容，返回byte[]。
     */
    public static byte[] toByteArray(Reader input, Charset encoding) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output, encoding);
        return output.toByteArray();
    }

    /**
     * 使用指定的字符编码获取Reader的内容，返回byte[]。
     */
    public static byte[] toByteArray(Reader input, String encoding) throws IOException {
        return toByteArray(input, Charsets.toCharset(encoding));
    }

    /**
     * string 转 byte[]
     */
    @Deprecated
    public static byte[] toByteArray(String input) throws IOException {
        return input.getBytes();
    }

    /**
     * 获取 URI 的内容，返回byte[]。
     */
    public static byte[] toByteArray(URI uri) throws IOException {
        return toByteArray(uri.toURL());
    }

    /**
     * 获取 URL 的内容，返回byte[]。
     */
    public static byte[] toByteArray(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        try {
            return toByteArray(conn);
        } finally {
            close(conn);
        }
    }

    /**
     * 获取 URLConnection 的内容，返回byte[]。
     */
    public static byte[] toByteArray(URLConnection urlConn) throws IOException {
        InputStream inputStream = urlConn.getInputStream();
        try {
            return toByteArray(inputStream);
        } finally {
            inputStream.close();
        }
    }
}
