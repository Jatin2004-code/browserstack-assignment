import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class Main {


    public static String translate(String text) {
        try {

            String api =
                    "https://api.mymemory.translated.net/get?q="
                            + URLEncoder.encode(text, "UTF-8")
                            + "&langpair=es|en";

            URL url = new URL(api);

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(url.openStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String res = response.toString();

            int start = res.indexOf("translatedText\":\"") + 17;
            int end = res.indexOf("\"", start);

            return res.substring(start, end);

        } catch (Exception e) {
            return text;
        }
    }

    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://elpais.com/opinion/");

        Thread.sleep(4000);


        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        Thread.sleep(3000);

        List<WebElement> articleLinks =
                driver.findElements(By.cssSelector("article h2 a"));

        List<String> titles = new ArrayList<>();
        List<String> links = new ArrayList<>();

        for (WebElement a : articleLinks) {

            String title = a.getText().trim();
            String link = a.getAttribute("href");

            if (!title.isEmpty()) {

                titles.add(title);
                links.add(link);

                if (titles.size() == 5)
                    break;
            }
        }

        List<String> englishTitles = new ArrayList<>();

        for (int i = 0; i < titles.size(); i++) {

            String title = titles.get(i);

            System.out.println("\nOriginal Title: " + title);

            String translated = translate(title);

            englishTitles.add(translated);

            System.out.println("English Title: " + translated);
            System.out.println("Link: " + links.get(i));

            driver.get(links.get(i));

            Thread.sleep(3000);

            System.out.println("Article Content:");

            List<WebElement> paragraphs =
                    driver.findElements(By.cssSelector("article p"));

            int count = 0;

            for (WebElement p : paragraphs) {

                String text = p.getText().trim();

                if (!text.isEmpty()) {

                    System.out.println(text);
                    count++;

                    if (count == 3)
                        break;
                }
            }

            try {

                WebElement img =
                        driver.findElement(By.cssSelector("figure img"));

                String imgUrl = img.getAttribute("src");

                System.out.println("Image URL: " + imgUrl);

                InputStream in = new URL(imgUrl).openStream();

                Path path = Paths.get("image_" + i + ".jpg");

                Files.copy(in, path,
                        StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image downloaded: " + path);

            } catch (Exception e) {

                System.out.println("No image found");
            }

            System.out.println("--------------------------------");
        }

        driver.quit();


        Map<String, Integer> wordCount = new HashMap<>();

        for (String title : englishTitles) {

            String clean =
                    title.toLowerCase().replaceAll("[^a-z ]", "");

            String[] words = clean.split(" ");

            for (String w : words) {

                if (w.length() > 2) {

                    wordCount.put(
                            w,
                            wordCount.getOrDefault(w, 0) + 1);
                }
            }
        }

        System.out.println("\nRepeated Words:");

        for (String w : wordCount.keySet()) {

            if (wordCount.get(w) > 1) {

                System.out.println(w + " → " + wordCount.get(w));
            }
        }
    }
}