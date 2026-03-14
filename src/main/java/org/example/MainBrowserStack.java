import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
public class MainBrowserStack {
    public static final String USERNAME = "jatinkanojiya_2e9ClH";
    public static final String ACCESS_KEY = "MJyKzxTeeFsa9ttPiu1u";


    public static String translate(String text) {

        try {

            String api = "https://api.mymemory.translated.net/get?q="
                    + URLEncoder.encode(text, StandardCharsets.UTF_8)
                    + "&langpair=es|en";

            URL url = new URL(api);

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(url.openStream()));

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

    public static void runTest(String browser, String os, String osVersion) {

        try {

            String hubURL = "https://" + USERNAME + ":" + ACCESS_KEY +
                    "@hub-cloud.browserstack.com/wd/hub";

            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("browserName", browser);
            caps.setCapability("browserVersion", "latest");

            Map<String, Object> options = new HashMap<>();

            options.put("os", os);
            options.put("osVersion", osVersion);
            options.put("buildName", "ElPais Assignment");
            options.put("sessionName", browser + " Test");

            caps.setCapability("bstack:options", options);

            WebDriver driver = new RemoteWebDriver(new URL(hubURL), caps);

            driver.get("https://elpais.com/opinion/");

            Thread.sleep(5000);

            List<String> englishTitles = new ArrayList<>();

            for (int i = 0; i < 5; i++) {


                List<WebElement> articles =
                        driver.findElements(By.cssSelector("article h2 a"));

                WebElement article = articles.get(i);

                String title = article.getText().trim();

                if(title.isEmpty()) continue;

                System.out.println("\nOriginal Title: " + title);

                String translated = translate(title);

                englishTitles.add(translated);

                System.out.println("English Title: " + translated);

                String link = article.getAttribute("href");

                driver.get(link);

                Thread.sleep(3000);

                List<WebElement> paragraphs =
                        driver.findElements(By.cssSelector("article p"));

                System.out.println("Article Content:");

                int count = 0;

                for (WebElement p : paragraphs) {

                    String text = p.getText().trim();

                    if (!text.isEmpty()) {

                        System.out.println(text);

                        count++;
                    }

                    if (count == 3)
                        break;
                }

                try {

                    WebElement img =
                            driver.findElement(By.cssSelector("figure img"));

                    String imgUrl = img.getAttribute("src");

                    InputStream in = new URL(imgUrl).openStream();

                    Path path = Paths.get("image_" + i + ".jpg");

                    Files.copy(in, path,
                            StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("Image downloaded");

                } catch (Exception e) {

                    System.out.println("No image found");
                }

                driver.navigate().back();

                Thread.sleep(3000);
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

                if (wordCount.get(w) > 2) {

                    System.out.println(w + " → " + wordCount.get(w));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(() ->
                runTest("Chrome", "Windows", "11"));

        Thread t2 = new Thread(() ->
                runTest("Firefox", "Windows", "11"));

        Thread t3 = new Thread(() ->
                runTest("Edge", "Windows", "11"));

        Thread t4 = new Thread(() ->
                runTest("Chrome", "OS X", "Ventura"));

        Thread t5 = new Thread(() ->
                runTest("Safari", "OS X", "Ventura"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }
}
