package exceptions;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class ExceptionSubclassesSameCatch {

    public void m() throws IOException {

    }

    public static void main(String[] args) {
        try {
            new ExceptionSubclassesSameCatch().m();
//        } catch (AccessDeniedException | IOException e) { // WILL NOT COMPILE, cause AccessDeniedException is a subtype of IOException
//        }
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}