package sam.oldsport;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CrawlerDriver {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "D:\\New folder (4)\\data";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(0);

                /*
                 * Instantiate the controller for this crawl.
                 */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

                /*
                 * For each crawl, you need to add some seed urls. These are the first
                 * URLs that are fetched and then the crawler starts following links
                 * which are found in these pages
                 */
        String monthFormat = "http://www.itunescharts.net/us/charts/songs/2014/%02d/%02d";
        for(int i =1; i <=4; i++) {
            for(int j = 1; j <= 30; j++) {
                controller.addSeed(String.format(monthFormat, i, j));
//                System.out.println(String.format(monthFormat, i, j));
            }
        }

                /*
                 * Start the crawl. This is a blocking operation, meaning that your code
                 * will reach the line after this only when crawling is finished.
                 */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}
