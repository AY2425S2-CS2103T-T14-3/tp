# 📖 User Guide

## 📌 Table of Contents
1. [✨ Introduction](#introduction)
2. [👋 Quick Overview](#quick-overview)
3. [🛠 Installation](#installation)
4. [🚀 Getting Started](#getting-started)
5. [📚 Features](#features)
    - [➕ Add Contact](#feature-1-add-contact)
    - [❌ Delete Contact](#feature-2-delete-contact)
    - [❌❌ Mass Delete Contacts](#feature-3-mass-delete-contacts)
    - [📜 List Contact](#feature-4-list-contact)
    - [📝 Update/Edit Contact](#feature-5-updateedit-contact)
    - [🔎 Find Contact](#feature-6-find-contact)
    - [🎯 Filter Contact](#feature-7-filter-contact)
6. [🛠 Troubleshooting](#troubleshooting)
7. [🤔 FAQs](#faqs)
8. [📝 Command Summary](#command-summary)
9. [📧 Contact Support](#contact-support)
## Introduction
Welcome to **WhoDat**, the **fastest way** for NUS SoC teaching assistants to manage student contacts! 🎓
Designed for **fast typists**, WhoDat lets you **find students grouped by tags and classes effortlessly** with just a 
few keystrokes. 

Say goodbye to: \
❌ slow, cluttered spreadsheets that take too much procrastination to set up\
—now you can \
✅ **add and retrieve student details in seconds** 🚀

With a **minimalist graphic interface**, WhoDat ensures that your contact management is **efficient, distraction-free, 
and perfectly suited for busy student tutors like you**.

## Quick Overview
First time using **WhoDat**? Not sure where to start? Fret not, here's a quick overview of **WhoDat**.

* To start installing and running the app, refer to the [Getting Started](#getting-started) section.
* If you need a compilation of the available commands, check out our [Command Summary](#command-summary).
* Is there a pressing question on your mind? The [FAQ](#faqs) might have what you are looking for.

## Installation
Download ```whodat.jar``` from our latest github release: \
https://github.com/AY2425S2-CS2103T-T14-3/tp/releases/tag/v1.4 🔥

## Getting Started
1. Open up Terminal (if you're using Mac OS) and command shell if you're using Windows. Navigate to the folder where the jar file is using the following instruction: 
```shell
    cd ~/Downloads
```
2. Start running the application by copy-pasting in the following command: 
```shell
    java -jar whodat.jar
```
3. Your application window will pop up, and you can start managing contacts like a boss. 👨‍💻👩‍💻

---
## Features

### Feature 1: Add Contact
**🎯 Purpose:** Want to save student details without memorizing 100+ names? This feature lets you add a student's name, ID, NUSNET ID, and tutorial class. 📝

**🔤 Command Format:** \
Note: Bracketed fields "[ ]" are optional. 
```
add n/name i/ID e/NUSNET-ID c/class [t/tag1] [t/tag2]
```

**🖥 Example:**
```
add n/Lin Xinyi i/A0277024H e/E1136951 c/CS1101-03 t/NeedHelp
```

💡 **How it works:**
- **Name:** English alphabets only (no special characters or numbers) and we have made it **_case-insensitive_**, and it
will be automatically formatted to ```Title Case```. ✨
- **Student ID:** Starts and ends with a letter, with seven numbers in between (e.g., A1234567X).  🔐
- **NUSNET ID:** Always starts with ‘E’ followed by seven digits. Your email will be generated automatically (because we're nice like that). 📧
- **Class:** Simple as how you remember, formatted as _[course code]-[tutorial number]_ (e.g. CS1101-03 for CS1101, Tutorial 03). 😎
- **Tag:** Optional tag(s) to attach remarks about a student, e.g. NeedHelp. You may add more than one tags! 

🔎 **Duplicate Check:** If a student with the same ID or NUSNET ID exists, we will stop you right there! No cloning allowed. 🚫

❕**Case Sensitivity:** `ClassId`, `StudentId`, `NUSNETId` and `Tag` are **_case-sensitive_**, only `Name` is not. 

---
### Feature 2: Delete Contact
**🎯 Purpose:** Remove students who dropped out, switched classes, or never actually existed in your list. 🗑

**🔤 Command Format:**
```
delete ID
```

**🖥 Example:**
```
delete A0272111H
```
```
delete a0272111h
```

🛑 **What to expect:**
- Case-insensitive search 
- If the ID exists, it is now **gone forever** (unless you re-add it, of course). 🏃‍♂️💨
- If the ID **does not exist**, we will let you know—because deleting a ghost is not possible. 👻

---
### Feature 3: Mass Delete Contacts
**🎯 Purpose:** Remove multiple students at one go. 🗑

**🔤 Command Format:**
```
m_delete ID1, ID2, ...
```

**🖥 Example:**
```
m_delete A0272111H, A1234567G, A0123456F
```

🛑 **What to expect:**
- **No confirmation prompt** — deletions happen instantly in this version.
- At least one student ID must be supplied. If multiple student IDs are supplied, they must be **comma-separated**.
- Only **valid and existing** student IDs will be deleted, and they’ll appear under the **Deleted Students section**.
- All **invalid** student IDs (i.e. incorrect format) will be shown under the **Invalid Student IDs section**.
- All **valid but non-existent** student IDs will appear under the **Missing Student IDs section**.

🔎 **Duplicate Check:**
- **Duplicate valid IDs** will be removed before proceeding with mass-delete. The student ID will be displayed only once
  in the **Missing Student IDs section** or **Deleted Students section**.
- **Duplicate invalid IDs** are **not** filtered out — they will be listed as provided in the **Invalid Student IDs section**,
  but will not affect the operation.

💡 What Counts as a **Valid Student ID**?
- Begin with a capital **A**
- Follow with **7 digits**
- End with **any capital letter**
- E.g. ```A1234567G```

---
### Feature 4: List Contact
**🎯 Purpose:** Want a bird’s-eye view of all your students? Use this to list all stored contacts. 🦅

**🔤 Command Format:**
```
list
```

💡 **Bonus:** If you accidentally type something extra, not to worry—we will still show the list and gently correct you. 🤗

---
### Feature 5: Update/Edit Contact
**🎯 Purpose:** Mistakes are meant to be made. So are typos. No problem—this feature lets you update any information easily. 🔄

**🔤 Command Format:**
```
edit StudentID field/new_value
```

**🖥 Example:**
```
edit A0272222H n/Xinyi
```

🛠 **How to use:**
- Key in the student ID to locate the student whose info you want to change. 
- `/n` for name, `/i` for student ID, `/e` for NUSNET ID, `/c` for class.
- The new value **must** follow the same rules as adding a contact (we’re strict, but fair!). 👨‍⚖️


---
### Feature 6: Find Contact
**🎯 Purpose:** Need to find a student’s details fast? Search by name! 🔍

**🔤 Command Format:**
```
find name
```
```
find StudentId
```

**🖥 Example:**
```
find Jane Doe
```
```
find A1234567H
```

💡 **Cool Features:**
- Case-insensitive search (We do not judge your capitalization skills). 🔠
- Partial matches work—so even if you only remember "Jane," you are good to go. (but StudentId has to be the exact match)🧠

---
### Feature 7: Filter Contact
**🎯 Purpose:** Want to only see students from a specific tutorial class, or with a specific tag? You can use the filter command!

**🔤 Command Format:**
```
filter_c classId
```
```
filter_t tag
```

**🖥 Example:**
```
filter_c cs1231-05
```
```
filter_t NeedHelp
```

💡 **Keep in mind:**
- You can filter by class id or student tag, but not both! 🚦

---

## Troubleshooting
🔍 **Invalid input?** Follow the error messages—they exist for a reason! 😜

🔍 **Duplicate entry?** Each student ID and NUSNET ID must be unique, so check your list first. 📋

🔍 **Forgot the command?** Go to "Help" tab on the top left of the window, or simply type `help`, and 
there you will find everything you need to know. 😆

---
## FAQs
❓ **Q: How strict is the input format?**  
💡 **A:** Very strict! We want to keep data **clean and accurate**—no messy databases here. 🧼

❓ **Q: Can I update any field?**  
💡 **A:** Yes! Just use the correct update command, and remember to key in the student ID first, and you’re good to go. 🔄

❓ **Q: How do I add a tag to an existing student, if I forgot to add tag when keying in the information?**  
💡 **A:** Use the `edit` function [here](#feature-5-updateedit-contact), and change the `Tag` field just like any other!
If you want to add more than one tags, just use more than one `t/tag` fields!

**🖥 Example:**
```
edit A0272222H t/NeedHelp t/LateSubmission
```

---
## Command Summary

| Command         | Format, Examples                                                                                             |
|-----------------|--------------------------------------------------------------------------------------------------------------|
| **Help**        | `help`                                                                                                       |
| **Add**         | `add n/name i/studentId e/emailid c/classId` <br> e.g. `add n/Joshua Lai i/A1234567S e/E1234567 c/cs1231-05` |
| **Delete**      | `delete studentId` <br> e.g. `delete A1234567S`                                                              |
| **Mass Delete** | `m_delete studentId1, studentId2` <br> e.g. `m_delete A1234567S, A0123456B`                                  |
| **List**        | `list`                                                                                                       |
| **Find**        | `find name` or `find studentId` <br> e.g. `find Alex` or `find A1234567S`                                    |
| **Filter**      | `filter_c classId` or `filter_t tag` <br> e.g. `filter_c cs1231-05` or `filter_t sampletag`                  |
| **Clear**       | `clear`                                                                                                      |
| **Exit**        | `exit`                                                                                                       |

---
## Contact Support
Need help? Have feedback? Found a bug? 🐞 Drop us an email at [E1136951@u.nus.edu](mailto:support@example.com), and we’ll get back to you faster than you can type `add n/John Doe`! 💌
