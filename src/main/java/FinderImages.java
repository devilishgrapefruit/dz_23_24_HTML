import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;
import java.util.List;

public class FinderImages {
    public void Finding() throws IOException {
        Document doc = Jsoup.connect("https://www.mirea.ru").get();
        int k = 0;
        Elements link = doc.select("img");
        List<String> absHref = link.eachAttr("abs:src");
        for (String src : absHref) {
            if(k<absHref.size()-1)
                downloadImage(src);
            k++;
        }

        File folder = new File("D:\\Images_23_24");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }

    }
    private static void downloadImage(String strImageURL){ 

        String strImageName = strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );
        try {

            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();
            byte[] buffer = new byte[4096];
            int n = -1;
            OutputStream os =  new FileOutputStream( "D:\\Images_23_24" + "/" + strImageName );

            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}