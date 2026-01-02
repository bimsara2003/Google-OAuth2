# Spring Boot Google OAuth2 Implementation Guide

This project demonstrates how to implement **Google Login (OAuth2)** using Spring Boot Security.

## 📋 Prerequisites
* Java 17 or higher
* Maven or Gradle
* A Google Cloud Account

---

## 🚀 Step 1: Google Cloud Console Setup

To get your Client ID and Secret, follow these steps:

1.  Go to the [Google Cloud Console](https://console.cloud.google.com/).
2.  Create a **New Project**.
3.  Navigate to **APIs & Services** > **OAuth consent screen**.
    * Select **External** (for testing) and click Create.
    * Fill in the App Name, User Support Email, and Developer Contact Info.
    * Click "Save and Continue" (You can skip Scopes and Test Users for now).
4.  Navigate to **Credentials** > **Create Credentials** > **OAuth Client ID**.
5.  Select **Web Application**.
6.  **Authorized Redirect URIs**: Add the default Spring Boot redirect URI:
    ```text
    http://localhost:8080/login/oauth2/code/google
    ```
7.  Click **Create**.
8.  **IMPORTANT:** Copy `Client ID` and `Client Secret`. **Do not share these!**

---

## 🛠 Step 2: Dependencies

Add the following dependency to your `pom.xml` (Maven):

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

