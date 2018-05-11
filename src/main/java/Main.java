import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main( String[] args ) throws IOException {
        if (args.length == 0) {
            System.out.println("Please specify path to PDF file (and password if needed)");
            return;
        }
        String password = args.length > 1 ? args[1] : "";
        PDDocument document = PDDocument.load(new File(args[0]), password);
        if (!document.getCurrentAccessPermission().canExtractContent()) {
            System.out.println("Have no permissions to extract PDF's content");
            return;
        }
        Header structure = new Header(document);
        System.out.println(structure);
    }
}
