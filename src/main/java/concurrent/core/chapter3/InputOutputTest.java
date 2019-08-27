package concurrent.core.chapter3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 3.1.12 通过管道进行线程间通信:字节流
 */
public class InputOutputTest {

    public void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("begin write");
            for (int i = 0; i < 20; i++) {
                String outData = "" + (i + 1);
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println("\nend write");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMethod(PipedInputStream input) {
        System.out.println("begin read");
        byte[] byteArr = new byte[20];
        try {
            int readLength = input.read(byteArr);
            while (readLength != -1) {
                String newData = new String(byteArr, 0, readLength);
                System.out.print(newData);
                readLength = input.read(byteArr);
            }
            System.out.println("\nend read");
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        InputOutputTest test = new InputOutputTest();
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();

        try {
            //inputStream.connect(outputStream);
            outputStream.connect(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread readThread = new Thread(() -> {
            test.readMethod(inputStream);
        });

        Thread writeThread = new Thread(() -> {
            test.writeMethod(outputStream);
        });

        readThread.start();
        writeThread.start();

    }


}
