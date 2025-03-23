# 📖 User Guide

## 📌 Table of Contents
1. [✨ Introduction](#-introduction)
2. [🛠 Installation](#-installation)
3. [🚀 Getting Started](#-getting-started)
4. [📚 Features](#-features)
    - [➕ Add Contact](#-feature-1-add-contact)
    - [❌ Delete Contact](#-feature-2-delete-contact)
    - [📜 List Contact](#-feature-3-list-contact)
    - [📝 Update/Edit Contact](#-feature-4-updateedit-contact)
    - [🔎 Search for Contact](#-feature-5-search-for-contact)
    - [🎯 Filter Contact](#-feature-6-filter-contact)
5. [🛠 Troubleshooting](#-troubleshooting)
6. [🤔 FAQs](#-faqs)
7. [📧 Contact Support](#-contact-support)

## ✨ Introduction
Welcome to your new contact management system — **WhoDat**. This command-line based application aided with simple graphic 
interface is designed for teaching assistants like YOU!\
It will help keep your NUS students’ details organized so you don’t have to dig through Excel sheets at 3 AM. 🚀

## 🛠 Installation
1. **Download and install** the software like a pro. 💾
2. **Run the program** and feel the power. ⚡

## 🚀 Getting Started
1. Download ```whodat.jar``` from our github release: https://github.com/AY2425S2-CS2103T-T14-3/tp/releases/tag/v1.3 🔥
2. Open up Terminal (if you're using Mac OS) and command shell if you're using Windows. Navigate to the folder where the jar file is using the following instruction: 
```shell
    cd ~/Downloads
```
3. Start running the application by copy-pasting in the following command: 
```shell
    java -jar whodat.jar
```
4. Your application window will pop up, and you can start managing contacts like a boss. 👨‍💻👩‍💻

---
## 📚 Features

### ➕ Feature 1: Add Contact
**🎯 Purpose:** Want to save student details without memorizing 100+ names? This feature lets you add a student's name, ID, NUSNET ID, and tutorial class. 📝

**🔤 Command Format:**
```
add n/name i/ID e/NUSNET-ID c/class
```

**🖥 Example:**
```
add n/Lin Xinyi i/A0277024H e/E1136951 c/110103
```

💡 **How it works:**
- **Name:** English alphabets only (no special characters or numbers) and we have made it **_case-insensitive_**, and it
will be automatically formatted to ```Title Case```. ✨
- **Student ID:** Starts and ends with a letter, with seven numbers in between (e.g., A1234567X). Strict, but it keeps things neat. 🔐
- **NUSNET ID:** Always starts with ‘E’ followed by seven digits. Your email will be generated automatically (because we're nice like that). 📧
- **Class:** Just an integer, formatted as _[course code] + [tutorial number]_ (e.g. 110103 for CS2101, Tutorial 03). 😎

🛑 **Duplicate Check:** If a student with the same ID or NUSNET ID exists, we’ll stop you right there! No cloning allowed. 🚫

---
### ❌ Feature 2: Delete Contact
**🎯 Purpose:** Remove students who dropped out, switched classes, or never actually existed in your list. 🗑

**🔤 Command Format:**
```
delete ID
```

**🖥 Example:**
```
delete A0272111H
```

🛑 **What to expect:**
- If the ID exists, it’s **gone forever** (unless you re-add it, of course). 🏃‍♂️💨
- If the ID **doesn’t exist**, we’ll let you know—because deleting a ghost isn’t possible. 👻

---
### 📜 Feature 3: List Contact
**🎯 Purpose:** Want a bird’s-eye view of all your students? Use this to list all stored contacts. 🦅

**🔤 Command Format:**
```
list
```

💡 **Bonus:** If you accidentally type something extra, don’t worry—we’ll still show the list and gently correct you. 🤗

---
### 📝 Feature 4: Update/Edit Contact
**🎯 Purpose:** Mistakes are meant to be made. So are typos. No problem—this feature lets you update any information easily. 🔄

**🔤 Command Format:**
```
update StudentID field/new_value
```

**🖥 Example:**
```
update A0272222H n/Xinyi
```

🛠 **How to use:**
- Key in the student ID to locate the student whose info you want to change. 
- `/n` for name, `/i` for student ID, `/e` for NUSNET ID, `/c` for class.
- The new value **must** follow the same rules as adding a contact (we’re strict, but fair!). 👨‍⚖️


---
### 🔎 Feature 5: Search for Contact
**🎯 Purpose:** Need to find a student’s details fast? Search by name! 🔍

**🔤 Command Format:**
```
search name
```

**🖥 Example:**
```
search Jane Doe
```

💡 **Cool Features:**
- Case-insensitive search (We don’t judge your capitalization skills). 🔠
- Partial matches work—so even if you only remember "Jane," you’re good. 🧠

---
### 🎯 Feature 6: Filter Contact
**🎯 Purpose:** Want to see only students from a specific tutorial class? This feature helps you organize your list. 🎯

**🔤 Command Format:**
```
filter class
```

**🖥 Example:**
```
filter 14
```

💡 **Keep in mind:**
- The class **must** be a number. If not, we’ll kindly remind you. 🚦

---
## 🛠 Troubleshooting
🔍 **Invalid input?** Follow the error messages—they exist for a reason! 😜

🔍 **Duplicate entry?** Each student ID and NUSNET ID must be unique, so check your list first. 📋

🔍 **Forgot the command?** Type nonsense, and we’ll suggest the right one for you (we got your back). 😆

---
## 🤔 FAQs
❓ **Q: How strict is the input format?**  
💡 **A:** Very strict! We want to keep data **clean and accurate**—no messy databases here. 🧼

❓ **Q: Can I update any field?**  
💡 **A:** Yes! Just use the correct update command, and remember to key in the student ID first, and you’re good to go. 🔄

---
## 📧 Contact Support
Need help? Have feedback? Found a bug? 🐞 Drop us an email at [E1136951@u.nus.edu](mailto:support@example.com), and we’ll get back to you faster than you can type `add n/John Doe`! 💌
