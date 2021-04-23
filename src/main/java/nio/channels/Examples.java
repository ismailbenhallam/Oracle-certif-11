package nio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Examples {
    private static final String FILE_NAME = "file_channel_test.txt";
    private static final Path PATH = Path.of(FILE_NAME);
    private static final String TEXT = "We're trying to write a file using the new Channel and Buffer APIs\n";

    public static void main(String[] args) {
        writeFile();
        readFile();
    }


    private static void writeFile() {
        try (FileChannel fileChannel = FileChannel.open(PATH, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
//            CharBuffer charBuffer = CharBuffer.wrap(TEXT);
//            ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
            ByteBuffer byteBuffer = ByteBuffer.wrap(TEXT.getBytes());
            fileChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() {
        try (FileChannel fileChannel = FileChannel.open(PATH, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
//                while (buffer.hasRemaining())
//                    System.out.print(((char) buffer.get()));
                System.out.println("Read data: " + new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
