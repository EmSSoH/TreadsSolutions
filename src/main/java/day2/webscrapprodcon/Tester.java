
package day2.webscrapprodcon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jsoup.nodes.Document;

public class Tester {
  
  public static void main(String[] args) throws InterruptedException {
      
      int done = 0;

    //The list of URL's that must be processed. This is Q1 in the exercise figure
    ArrayBlockingQueue<String> urls = new ArrayBlockingQueue(40);
    
    urls.add("http://www.fck.dk");
    urls.add("http://www.google.com");
    urls.add("http://politiken.dk");
    urls.add("https://www.youtube.com/");
    urls.add("https://www.twitch.tv/");
    urls.add("https://docs.oracle.com/javase/8/docs/api/?java/util/concurrent/BlockingQueue.html");
    urls.add("https://gw2efficiency.com/");
    urls.add("https://poe.ninja/challengehc/currency");
    urls.add("http://poe.trade/");
    urls.add("https://www.facebook.com/");
    urls.add("https://github.com/");
    urls.add("https://www.reddit.com/");
    //TODO: Add some more URL's of your own choice

    //Holds the Documents produced by the producers. This is Q2 in the exercise figure
    ArrayBlockingQueue<Document> producedDocuments = new ArrayBlockingQueue(10);
   
    ExecutorService es = Executors.newCachedThreadPool();
    //Create and start the four Producers (P1-P4)
    es.execute(new DocumentProducer(urls, producedDocuments, done)); 
    es.execute(new DocumentProducer(urls, producedDocuments, done)); 
    es.execute(new DocumentProducer(urls, producedDocuments, done)); 
    es.execute(new DocumentProducer(urls, producedDocuments, done)); 
    
    //Create and start the single Consumer Thead (P1)
    es.execute(new DocumentConsumer(producedDocuments, done)); 
    
    es.shutdown();
    es.awaitTermination(5,TimeUnit.SECONDS);
    System.out.println("Closing Down");
    
  }
}
