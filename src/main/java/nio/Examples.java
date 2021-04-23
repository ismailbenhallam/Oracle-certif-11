package nio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.time.Instant;
import java.util.Set;
import java.util.stream.IntStream;

public class Examples {
    public static final String SIBLING_FOLDER = "SQLI";
    public static final Path POM_FILE = Path.of(".", "pom.xml");
    public static final Path LINK = Path.of(".", "pom-(link).xml");

    public static void main(String[] args) throws IOException, InterruptedException {
        filesSystems();

        paths();

        files();

        readWriteWithFiles();

        http();
    }

    private static void http() throws IOException, InterruptedException {
        Path path = Path.of(".", "index");
        Files.createFile(path);
        URI uri = URI.create("https://openjdk.java.net");
        HttpResponse<Path> response = HttpClient.newHttpClient().send(HttpRequest.newBuilder(uri).build(), HttpResponse.BodyHandlers.ofFile(path));
    }

    private static void filesSystems() throws IOException {
        FileSystem fs = FileSystems.getDefault();
        System.out.println("Default file system: " + fs);

        System.out.println("\nFile stores:");
        fs.getFileStores().forEach(System.out::println);

        System.out.println("\nRoot directories:");
        fs.getRootDirectories().forEach(System.out::println);

        System.out.println("\nType of the root directory:");
        System.out.println(Files.getFileStore(fs.getRootDirectories().iterator().next()).type());
    }

    private static void paths() throws IOException {
        System.out.println("\nUser Home:");
        System.out.println(Path.of(System.getProperty("user.home")).toRealPath());

        System.out.println("\n. folder:");
        System.out.println(Path.of(".", "..", "Certif", "..", "Certif", "..", "Certif", "..", "Certif").toRealPath().toAbsolutePath());

        System.out.println("\nSibling dir:");
        System.out.println(Path.of(".").toRealPath().resolveSibling(SIBLING_FOLDER).toRealPath().toAbsolutePath());

        // Throws an exception !!
//        System.out.println(Path.of(".kndlknd").toRealPath());
    }

    private static void files() throws IOException {
        System.out.println("\nContent of this dir: ");
        Files.list(Path.of(".")).forEach(System.out::println);

        System.out.println("\nCreate a symbolic link for 'pom.xml'...");
        Files.createSymbolicLink(LINK, POM_FILE);

        System.out.println("\nDeleting the link...");
        Files.delete(LINK);

        System.out.println("\nWalking through the current directory:");
        Files.walk(Path.of("."))
//                .map(p -> p.toString()).filter(s -> s.endsWith(".txt"))
                .forEach(System.out::println);


        System.out.print("\nIs the '.' a directory: ");
        System.out.println(Files.isDirectory(Path.of(".")));

        System.out.print("\nIs the '.' hidden: ");
        System.out.println(Files.isHidden(Path.of(".")));


        ///////////////////// Attributes
        System.out.println("\npom.xml attributes:");
        var att = Files.readAttributes(POM_FILE, PosixFileAttributes.class);
        System.out.println("size: " + att.size());
        System.out.println("creationTime: " + att.creationTime());
        System.out.println("lastAccessTime: " + att.lastAccessTime());
        System.out.println("owner: " + att.owner());
        System.out.println("group: " + att.group());
        var permissions = att.permissions();
        System.out.println("Permissions: ");
        permissions.forEach(System.out::println);
        Files.setLastModifiedTime(POM_FILE, FileTime.from(Instant.now()));

        ///////////////////// Copy
        System.out.println("Copying 'pom.xml' to 'pom-copy.xml'...");
        var pomCopy = POM_FILE.resolveSibling("pom-copy.xml");
        Files.copy(POM_FILE, pomCopy, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(pomCopy);
    }

    private static void readWriteWithFiles() throws IOException {
        System.out.println("\nReading 'pom.xml' using the Files#newBufferedReader :");
        var bufferedReader = Files.newBufferedReader(POM_FILE);
        bufferedReader.lines().forEach(System.out::println);
        bufferedReader.close();

        System.out.println("\nReading 'pom.xml' using the Files#lines :");
        Files.lines(POM_FILE).forEach(System.out::println);

        System.out.println("\nWriting using the Files#writeString...");
        var tmp = Path.of(".", "tmp");
        Files.writeString(tmp, "Teeeeeest");
        Files.delete(tmp);

    }
}
