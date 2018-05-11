import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

import java.util.ArrayList;
import java.util.List;

public class Header {
    List<Header> subheadings;
    String title;

    public Header(PDDocument document)
    {
        subheadings = new ArrayList<>();
        title = document.getDocumentInformation().getTitle();
        addSubheadings(document.getDocumentCatalog().getDocumentOutline().getFirstChild());
    }

    private Header(PDOutlineItem titleItem)
    {
        subheadings = new ArrayList<>();
        title = titleItem.getTitle();
        addSubheadings(titleItem.getFirstChild());
    }

    private void addSubheadings(PDOutlineItem subheading)
    {
        while (subheading != null) {
            subheadings.add(new Header(subheading));
            subheading = subheading.getNextSibling();
        }
    }

    public boolean isEmpty() {
        return subheadings.isEmpty();
    }

    @Override
    public String toString() {
        String sep = System.getProperty("line.separator");
        StringBuilder res = new StringBuilder();
        if (title != null) {
            res.append(title);
            res.append(System.getProperty("line.separator"));
        }
        for (Header subheading : subheadings)
            for (String subheadingSingleLine : subheading.toString().split(sep)) {
                if (title != null)
                    res.append("    ");
                res.append(subheadingSingleLine);
                res.append(sep);
            }
        if (res.length() > 0)
            res.setLength(res.length() - sep.length());
        return res.toString();
    }
}
