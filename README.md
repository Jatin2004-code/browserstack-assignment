# EL PAÍS Web Scraping Automation – BrowserStack Assignment

This project is a **Java Selenium automation script** that scrapes articles from the **EL PAÍS Opinion section**, translates the article titles from **Spanish to English**, extracts article content, downloads images, and performs **word frequency analysis**.

The project also demonstrates **cross-browser automation using BrowserStack** with parallel test execution.

---

# Project Features

* Opens the **EL PAÍS Opinion page**
* Extracts the **first 5 opinion articles**
* Retrieves the **original Spanish titles**
* Translates titles from **Spanish → English**
* Extracts the **first 3 paragraphs** from each article
* Downloads the **main article image**
* Performs **repeated word analysis** on translated titles
* Executes tests **across multiple browsers in parallel using BrowserStack**

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
├── images/                         # Downloaded article images
│
├── src/main/java/org/example
│       ├── Main.java               # Local Selenium automation
│       └── MainBrowserStack.java   # BrowserStack cross-browser automation
│
├── pom.xml                         # Maven dependencies
└── README.md                       # Project documentation
```

---

# BrowserStack Cross-Browser Testing

The automation is executed on **BrowserStack Automate** to validate cross-browser compatibility.

Browsers used:

* Chrome (Windows)
* Firefox (Windows)
* Edge (Windows)
* Chrome (macOS)
* Safari (macOS)

BrowserStack Build Link:

https://automate.browserstack.com/projects/Default+Project/builds/ElPais+Assignment/1

---

# How to Run the Project

### 1 Clone the repository

```
git clone https://github.com/YOUR_GITHUB_USERNAME/browserstack-assignment.git
```

### 2 Open the project

Open the project in **IntelliJ IDEA** or any Java IDE.

### 3 Install dependencies

Since this is a **Maven project**, dependencies will install automatically from `pom.xml`.

### 4 Run the program

Run the local automation:

```
Main.java
```

Run the BrowserStack automation:

```
MainBrowserStack.java
```

---

# Program Output

The program prints the following details:

* Original Spanish article title
* English translated title
* Article link
* Article content (first paragraphs)
* Image URL
* Downloaded image confirmation
* Repeated word analysis

Example output:

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
* Cross-browser testing using BrowserStack
* Parallel test execution

---

# Author

Jatin Kanojiya
