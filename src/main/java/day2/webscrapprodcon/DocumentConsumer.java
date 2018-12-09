package day2.webscrapprodcon;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DocumentConsumer implements Runnable {

    BlockingQueue<Document> producedDocuments;
    Document doc;
    int done;

    public DocumentConsumer(BlockingQueue<Document> producedDocuments, int done) {
        this.producedDocuments = producedDocuments;
        this.done = done;
    }

    @Override
    public void run() {
        boolean moreDocumentsToConsume = true;
        Document doc;
        int totalDivs = 0;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DocumentConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (moreDocumentsToConsume) {
            try {
                doc = producedDocuments.poll(); //TODO: Change this to fetch a new document from the producedDocuments queue or wait if no one is ready
                //Hint: So far you have no way of knowing when all producers are done. For this part you can use a method that
                //WAIT, but only for max 10 seconds and if it times out take that as that all Producers are done
                if (done == 4 || doc == null) {
                    moreDocumentsToConsume = false;
                } else if (doc != null) {
                    String title = doc.title();
                    Elements divs = doc.select("div");
                    //TODO Update the totalDivs variable and print title and sum for this document
                    totalDivs += divs.size();
                    System.out.println("Titel: " + title);
                    System.out.println("Div's: " + divs.size());

                }

            } catch (Exception ex) {
                Logger.getLogger(DocumentConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Sum of all Divs:" + totalDivs);
    }

}
