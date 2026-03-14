# BrowserStack Web Scraping Assignment

This project is a **Java Selenium automation program** that scrapes articles from the **EL PAÍS Opinion section**, translates the article titles from **Spanish to English**, extracts content, downloads images, and performs **word frequency analysis**.

The project also demonstrates **cross-browser automation using BrowserStack** with parallel execution.

---

# Project Features

* Opens the **EL PAÍS Opinion page**
* Scrapes the **first 5 opinion articles**
* Extracts the **original Spanish titles**
* Translates titles from **Spanish → English**
* Extracts the **first 3 paragraphs** of each article
* Downloads the **main article image**
* Performs **repeated word analysis** on translated titles
* Supports **cross-browser testing using BrowserStack**
* Executes tests **in parallel across multiple browsers**

---

# Technologies Used

* **Java**
* **Selenium WebDriver**
* **BrowserStack Automate**
* **Maven**
* **MyMemory Translation API**

---

# Project Structure

```
browserstack-assignment
│
├── images/                      # Downloaded article images
│
├── src/main/java/org/example
│       └── Main.java            # Main automation script
│
├── pom.xml                      # Maven dependencies
└── README.md                    # Project documentation
```

---

# BrowserStack Cross-Browser Testing

The project also runs on **BrowserStack Automate** to demonstrate cross-browser compatibility.

Browsers used in parallel execution:

* Chrome on Windows
* Firefox on Windows
* Edge on Windows
* Chrome on macOS
* Safari on macOS

BrowserStack Build Link:

https://automate.browserstack.com/projects/Default+Project/builds/ElPais+Assignment/1

---

# How to Run the Project

### 1 Clone the repository

```
git clone https://github.com/YOUR_USERNAME/browserstack-assignment.git
```

### 2 Open the project

Open the project in **IntelliJ IDEA** or any Java IDE.

### 3 Install dependencies

Since this is a **Maven project**, dependencies will install automatically from `pom.xml`.

### 4 Run the program

Run:

```
Main.java
```

---

# Program Output

The program prints the following information in the console:

* Original Spanish article title
* English translated title
* Article link
* First paragraphs from the article
* Image URL
* Downloaded image confirmation
* Repeated word analysis

Example Output:

```
Original Title: Apertura forzada en Cuba
English Title: Forced opening in Cuba

Article Content:
El horizonte democrático y el respeto a la soberanía...

Image downloaded: image_0.jpg

Repeated Words:
opening → 2
cuba → 2
```

---

# Assignment Objectives Achieved

* Web scraping using Selenium
* API integration for translation
* Image downloading
* Text processing and word analysis
* Cross-browser automation using BrowserStack
* Parallel test execution

---

# Author

Jatin Kanojiya
