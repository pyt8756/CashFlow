package com.pyt.mylibrary.utils;


import com.pyt.mylibrary.utils.io.Charsets;
import com.pyt.mylibrary.utils.io.stream.StringBuilderWriter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IOUtils {

    private static final int EOF = -1;
    private static final String LINE_SEPARATOR;

    static {
        StringBuilderWriter buf = new StringBuilderWriter(4);
        PrintWriter out = new PrintWriter(buf);
        out.println();
        LINE_SEPARATOR = buf.toString();
        out.close();
    }

    /**
     * 默认缓冲区大小
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * skip 方法的默认缓冲区大小。
     */
    private static final int SKIP_BUFFER_SIZE = 2048;

    private static char[] SKIP_CHAR_BUFFER;
    private static byte[] SKIP_BYTE_BUFFER;

    public IOUtils() {
        super();
    }

    //-----------------------------------------------------------------------

    /**
     * 关闭 URLConnection.
     */
    public static void close(URLConnection conn) {
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection) conn).disconnect();
        }
    }

    /**
     * 关闭 Closeable
     */
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    /**
     * 关闭 Socket
     */
    public static void close(Socket sock) {
        if (sock != null) {
            try {
                sock.close();
            } catch (IOException ioe) {
                // ignored
            }
        }
    }

    /**
     * 关闭 Selector
     */
    public static void close(Selector selector) {
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException ioe) {
                // ignored
            }
        }
    }

    /**
     * 关闭 ServerSocket
     */
    public static void close(ServerSocket sock) {
        if (sock != null) {
            try {
                sock.close();
            } catch (IOException ioe) {
                // ignored
            }
        }
    }

    /**
     * 获取BufferedReader
     */
    public static BufferedReader toBufferedReader(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    /**
     * 获取 InputStream 的内容，返回char[]。
     */
    public static char[] toCharArray(InputStream is) throws IOException {
        return toCharArray(is, Charset.defaultCharset());
    }

    /**
     * 获取 InputStream 的内容，返回char[]。
     */
    public static char[] toCharArray(InputStream is, Charset encoding)
            throws IOException {
        CharArrayWriter output = new CharArrayWriter();
        copy(is, output, encoding);
        return output.toCharArray();
    }

    /**
     * 获取 InputStream 的内容，返回char[]。
     */
    public static char[] toCharArray(InputStream is, String encoding) throws IOException {
        return toCharArray(is, Charsets.toCharset(encoding));
    }

    /**
     * 获取 Reader 的内容，返回char[]。
     */
    public static char[] toCharArray(Reader input) throws IOException {
        CharArrayWriter sw = new CharArrayWriter();
        copy(input, sw);
        return sw.toCharArray();
    }

    /**
     * 读取 InputStream 到 List<String>, 每行一个条目
     */
    public static List<String> readLines(InputStream input) throws IOException {
        return readLines(input, Charset.defaultCharset());
    }

    /**
     * 读取 InputStream 到 List<String>, 每行一个条目
     */
    public static List<String> readLines(InputStream input, Charset encoding) throws IOException {
        InputStreamReader reader = new InputStreamReader(input, Charsets.toCharset(encoding));
        return readLines(reader);
    }

    /**
     * 读取 InputStream 到 List<String>, 每行一个条目
     */
    public static List<String> readLines(InputStream input, String encoding) throws IOException {
        return readLines(input, Charsets.toCharset(encoding));
    }

    /**
     * 读取 Reader 到 List<String>, 每行一个条目
     */
    public static List<String> readLines(Reader input) throws IOException {
        BufferedReader reader = toBufferedReader(input);
        List<String> list = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            list.add(line);
            line = reader.readLine();
        }
        return list;
    }

    /**
     * 将 CharSequence 转换为 InputStream
     */
    public static InputStream toInputStream(CharSequence input) {
        return toInputStream(input, Charset.defaultCharset());
    }

    /**
     * 将 CharSequence 转换为 InputStream
     */
    public static InputStream toInputStream(CharSequence input, Charset encoding) {
        return toInputStream(input.toString(), encoding);
    }

    /**
     * 将 CharSequence 转换为 InputStream
     */
    public static InputStream toInputStream(CharSequence input, String encoding) throws IOException {
        return toInputStream(input, Charsets.toCharset(encoding));
    }

    /**
     * 将 String 转换为 InputStream
     */
    public static InputStream toInputStream(String input) {
        return toInputStream(input, Charset.defaultCharset());
    }

    /**
     * 将 String 转换为 InputStream
     */
    public static InputStream toInputStream(String input, Charset encoding) {
        return new ByteArrayInputStream(ByteUtils.getBytes(input, Charsets.toCharset(encoding)));
    }

    /**
     * 将 String 转换为 InputStream
     */
    public static InputStream toInputStream(String input, String encoding) throws IOException {
        byte[] bytes = ByteUtils.getBytes(input, Charsets.toCharset(encoding));
        return new ByteArrayInputStream(bytes);
    }

    /**
     * 将 byte[] 写入 OutputStream
     */
    public static void write(byte[] data, OutputStream output)
            throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    /**
     * 将 byte[] 写入 Writer
     */
    public static void write(byte[] data, Writer output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    /**
     * 将 byte[] 写入 Writer
     */
    public static void write(byte[] data, Writer output, Charset encoding) throws IOException {
        if (data != null) {
            output.write(new String(data, Charsets.toCharset(encoding)));
        }
    }

    /**
     * 将 byte[] 写入 Writer
     */
    public static void write(byte[] data, Writer output, String encoding) throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    /**
     * 将 char[] 写入 Writer
     */
    public static void write(char[] data, Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    /**
     * 将 char[] 写入 OutputStream
     */
    public static void write(char[] data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    /**
     * 将 char[] 写入 OutputStream
     */
    public static void write(char[] data, OutputStream output, Charset encoding) throws IOException {
        if (data != null) {
            output.write(ByteUtils.getBytes(new String(data), Charsets.toCharset(encoding)));
        }
    }

    /**
     * 将 char[] 写入 OutputStream
     */
    public static void write(char[] data, OutputStream output, String encoding) throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    /**
     * 将 CharSequence 写入 Writer
     */
    public static void write(CharSequence data, Writer output) throws IOException {
        if (data != null) {
            write(data.toString(), output);
        }
    }

    /**
     * 将 CharSequence 写入 OutputStream
     */
    public static void write(CharSequence data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    /**
     * 将 CharSequence 写入 OutputStream
     */
    public static void write(CharSequence data, OutputStream output, Charset encoding) throws IOException {
        if (data != null) {
            write(data.toString(), output, encoding);
        }
    }

    /**
     * 将 CharSequence 写入 OutputStream
     */
    public static void write(CharSequence data, OutputStream output, String encoding) throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    /**
     * 将 String 写入 Writer
     */
    public static void write(String data, Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    /**
     * 将 String 写入 OutputStream
     */
    public static void write(String data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    /**
     * 将 String 写入 OutputStream
     */
    public static void write(String data, OutputStream output, Charset encoding) throws IOException {
        if (data != null) {
            output.write(ByteUtils.getBytes(data, Charsets.toCharset(encoding)));
        }
    }

    /**
     * 将 String 写入 OutputStream
     */
    public static void write(String data, OutputStream output, String encoding) throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    /**
     * 将 StringBuffer 写入 Writer
     */
    @Deprecated
    public static void write(StringBuffer data, Writer output)
            throws IOException {
        if (data != null) {
            output.write(data.toString());
        }
    }

    /**
     * 将 StringBuffer 写入 OutputStream。
     */
    @Deprecated
    public static void write(StringBuffer data, OutputStream output) throws IOException {
        write(data, output, (String) null);
    }

    /**
     * 将 StringBuffer 写入 OutputStream。
     */
    @Deprecated
    public static void write(StringBuffer data, OutputStream output, String encoding) throws IOException {
        if (data != null) {
            output.write(ByteUtils.getBytes(data.toString(), Charsets.toCharset(encoding)));
        }
    }

    /**
     * 写入字符串集合到 OutputStream，指定行结束符
     */
    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output) throws IOException {
        writeLines(lines, lineEnding, output, Charset.defaultCharset());
    }

    /**
     * 写入字符串集合到 OutputStream，指定行结束符
     */
    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset encoding)
            throws IOException {
        if (lines == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = LINE_SEPARATOR;
        }
        Charset cs = Charsets.toCharset(encoding);
        for (Object line : lines) {
            if (line != null) {
                output.write(ByteUtils.getBytes(line.toString(), cs));
            }
            output.write(ByteUtils.getBytes(lineEnding, cs));
        }
    }

    /**
     * 写入字符串集合到 OutputStream，指定行结束符
     */
    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output,
                                  String encoding) throws IOException {
        writeLines(lines, lineEnding, output, Charsets.toCharset(encoding));
    }

    /**
     * 写入字符串集合到 Writer，指定行结束符
     */
    public static void writeLines(Collection<?> lines, String lineEnding, Writer writer) throws IOException {
        if (lines == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = LINE_SEPARATOR;
        }
        for (Object line : lines) {
            if (line != null) {
                writer.write(line.toString());
            }
            writer.write(lineEnding);
        }
    }

    /**
     * 将从 InputStream 复制到 OutputStream
     */
    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * 将大型（超过2GB）InputStream 的字节复制到 OutputStream
     */
    public static long copyLarge(InputStream input, OutputStream output) throws IOException {
        return copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 将大型（超过2GB）InputStream 的字节复制到 OutputStream
     */
    public static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
        long count = 0;
        int n = 0;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    /**
     * 将大型（超过2GB）InputStream 的字节复制到 OutputStream，可选地跳过输入字节
     */
    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length) throws IOException {
        return copyLarge(input, output, inputOffset, length, new byte[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 将大型（超过2GB）InputStream 的字节复制到 OutputStream，可选地跳过输入字节
     */
    public static long copyLarge(InputStream input, OutputStream output, final long inputOffset,
                                 final long length, byte[] buffer) throws IOException {
        if (inputOffset > 0) {
            skipFully(input, inputOffset);
        }
        if (length == 0) {
            return 0;
        }
        final int bufferLength = buffer.length;
        int bytesToRead = bufferLength;
        if (length > 0 && length < bufferLength) {
            bytesToRead = (int) length;
        }
        int read;
        long totalRead = 0;
        while (bytesToRead > 0 && EOF != (read = input.read(buffer, 0, bytesToRead))) {
            output.write(buffer, 0, read);
            totalRead += read;
            if (length > 0) {
                bytesToRead = (int) Math.min(length - totalRead, bufferLength);
            }
        }
        return totalRead;
    }

    /**
     * 从 InputStream 复制到 Writer
     */
    public static void copy(InputStream input, Writer output) throws IOException {
        copy(input, output, Charset.defaultCharset());
    }

    /**
     * 从 InputStream 复制到 Writer
     */
    public static void copy(InputStream input, Writer output, Charset encoding) throws IOException {
        InputStreamReader in = new InputStreamReader(input, Charsets.toCharset(encoding));
        copy(in, output);
    }

    /**
     * 从 InputStream 复制到 Writer
     */
    public static void copy(InputStream input, Writer output, String encoding) throws IOException {
        copy(input, output, Charsets.toCharset(encoding));
    }

    /**
     * 从 Reader 复制到 Writer
     */
    public static int copy(Reader input, Writer output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * 将大型（超过2GB）Reader 复制到 Writer
     */
    public static long copyLarge(Reader input, Writer output) throws IOException {
        return copyLarge(input, output, new char[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 将大型（超过2GB）Reader 复制到 Writer
     */
    public static long copyLarge(Reader input, Writer output, char[] buffer) throws IOException {
        long count = 0;
        int n = 0;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    /**
     * 将大型（超过2GB）Reader 复制到 Writer，可选地跳过输入字节
     */
    public static long copyLarge(Reader input, Writer output, final long inputOffset, final long length) throws IOException {
        return copyLarge(input, output, inputOffset, length, new char[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 将大型（超过2GB）Reader 复制到 Writer，可选地跳过输入字节
     */
    public static long copyLarge(Reader input, Writer output, final long inputOffset, final long length, char[] buffer)
            throws IOException {
        if (inputOffset > 0) {
            skipFully(input, inputOffset);
        }
        if (length == 0) {
            return 0;
        }
        int bytesToRead = buffer.length;
        if (length > 0 && length < buffer.length) {
            bytesToRead = (int) length;
        }
        int read;
        long totalRead = 0;
        while (bytesToRead > 0 && EOF != (read = input.read(buffer, 0, bytesToRead))) {
            output.write(buffer, 0, read);
            totalRead += read;
            if (length > 0) { // only adjust length if not reading to the end
                // Note the cast must work because buffer.length is an integer
                bytesToRead = (int) Math.min(length - totalRead, buffer.length);
            }
        }
        return totalRead;
    }

    /**
     * 从 Reader 复制到 OutputStream
     */
    public static void copy(Reader input, OutputStream output) throws IOException {
        copy(input, output, Charset.defaultCharset());
    }

    /**
     * 从 Reader 复制到 OutputStream
     */
    public static void copy(Reader input, OutputStream output, Charset encoding) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output, Charsets.toCharset(encoding));
        copy(input, out);
        out.flush();
    }

    /**
     * 从 Reader 复制到 OutputStream
     */
    public static void copy(Reader input, OutputStream output, String encoding) throws IOException {
        copy(input, output, Charsets.toCharset(encoding));
    }

    /**
     * 比较两个 InputStream 的内容，确定它们是否相等
     */
    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
        if (!(input1 instanceof BufferedInputStream)) {
            input1 = new BufferedInputStream(input1);
        }
        if (!(input2 instanceof BufferedInputStream)) {
            input2 = new BufferedInputStream(input2);
        }
        int ch = input1.read();
        while (EOF != ch) {
            int ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
            ch = input1.read();
        }
        int ch2 = input2.read();
        return ch2 == EOF;
    }

    /**
     * 比较两个 Reader 的内容，确定它们是否相等
     */
    public static boolean contentEquals(Reader input1, Reader input2) throws IOException {
        input1 = toBufferedReader(input1);
        input2 = toBufferedReader(input2);
        int ch = input1.read();
        while (EOF != ch) {
            int ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
            ch = input1.read();
        }
        int ch2 = input2.read();
        return ch2 == EOF;
    }

    /**
     * 比较两个 Reader 的内容，确定它们是否相等，忽略EOL字符
     */
    public static boolean contentEqualsIgnoreEOL(Reader input1, Reader input2) throws IOException {
        BufferedReader br1 = toBufferedReader(input1);
        BufferedReader br2 = toBufferedReader(input2);

        String line1 = br1.readLine();
        String line2 = br2.readLine();
        while (line1 != null && line2 != null && line1.equals(line2)) {
            line1 = br1.readLine();
            line2 = br2.readLine();
        }
        return line1 == null ? line2 == null : line1.equals(line2);
    }

    /**
     * 从 InputStream 流跳过字节。
     */
    public static long skip(InputStream input, long toSkip) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        if (SKIP_BYTE_BUFFER == null) {
            SKIP_BYTE_BUFFER = new byte[SKIP_BUFFER_SIZE];
        }
        long remain = toSkip;
        while (remain > 0) {
            long n = input.read(SKIP_BYTE_BUFFER, 0, (int) Math.min(remain, SKIP_BUFFER_SIZE));
            if (n < 0) { // EOF
                break;
            }
            remain -= n;
        }
        return toSkip - remain;
    }

    /**
     * 从 Reader 流跳过字节。
     */
    public static long skip(Reader input, long toSkip) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        if (SKIP_CHAR_BUFFER == null) {
            SKIP_CHAR_BUFFER = new char[SKIP_BUFFER_SIZE];
        }
        long remain = toSkip;
        while (remain > 0) {
            long n = input.read(SKIP_CHAR_BUFFER, 0, (int) Math.min(remain, SKIP_BUFFER_SIZE));
            if (n < 0) { // EOF
                break;
            }
            remain -= n;
        }
        return toSkip - remain;
    }

    /**
     * 从 InputStream 流跳过字节。
     */
    public static void skipFully(InputStream input, long toSkip) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        long skipped = skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }

    /**
     * 从 Reader 流跳过字节。
     */
    public static void skipFully(Reader input, long toSkip) throws IOException {
        long skipped = skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Chars to skip: " + toSkip + " actual: " + skipped);
        }
    }


    /**
     * 读取 Reader 到 char[]
     */
    public static int read(Reader input, char[] buffer, int offset, int length) throws IOException {
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        int remaining = length;
        while (remaining > 0) {
            int location = length - remaining;
            int count = input.read(buffer, offset + location, remaining);
            if (EOF == count) { // EOF
                break;
            }
            remaining -= count;
        }
        return length - remaining;
    }

    /**
     * 读取 Reader 到 char[]
     */
    public static int read(Reader input, char[] buffer) throws IOException {
        return read(input, buffer, 0, buffer.length);
    }

    /**
     * 读取 InputStream 到 byte[]
     */
    public static int read(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        int remaining = length;
        while (remaining > 0) {
            int location = length - remaining;
            int count = input.read(buffer, offset + location, remaining);
            if (EOF == count) { // EOF
                break;
            }
            remaining -= count;
        }
        return length - remaining;
    }

    /**
     * 读取 InputStream 到 byte[]
     */
    public static int read(InputStream input, byte[] buffer) throws IOException {
        return read(input, buffer, 0, buffer.length);
    }

    /**
     * 读取 Reader 到 char[]
     */
    public static void readFully(Reader input, char[] buffer, int offset, int length) throws IOException {
        int actual = read(input, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    /**
     * 读取 Reader 到 char[]
     */
    public static void readFully(Reader input, char[] buffer) throws IOException {
        readFully(input, buffer, 0, buffer.length);
    }

    /**
     * 读取 InputStream 到 byte[]
     */
    public static void readFully(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        int actual = read(input, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    /**
     * 读取 InputStream 到 byte[]
     */
    public static void readFully(InputStream input, byte[] buffer) throws IOException {
        readFully(input, buffer, 0, buffer.length);
    }

}
