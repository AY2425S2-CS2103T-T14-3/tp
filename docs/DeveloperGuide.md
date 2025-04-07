---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# WhoDat Developer Guide

<!-- * Table of Contents -->
* [WhoDat Developer Guide](#whodat-developer-guide)
    * [**Acknowledgements**](#acknowledgements)
    * [**Setting up, getting started**](#setting-up-getting-started)
    * [**Design**](#design)
        * [Architecture](#architecture)
        * [UI component](#ui-component)
        * [Logic component](#logic-component)
        * [Model component](#model-component)
        * [Storage component](#storage-component)
        * [Common classes](#common-classes)
    * [**Implementation**](#implementation)
  * [**Documentation, logging, testing, configuration, dev-ops**](#documentation-logging-testing-configuration-dev-ops)
  * [**Appendix: Requirements**](#appendix-requirements)
  * [**Appendix: Instructions for manual testing**](#appendix-instructions-for-manual-testing)
<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* WhoDat is based on the AddressBook-Level3 project created by the SE-EDU initiative
* It incorporates the following third-party libraries: JavaFX, Jackson, JUnit5.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2425S2-CS2103T-T14-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2425S2-CS2103T-T14-3/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete studentId`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2425S2-CS2103T-T14-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/classId/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2425S2-CS2103T-T14-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete studentId")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete studentId` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `WhoDatParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `WhoDatParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `WhoDatParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2425S2-CS2103T-T14-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the classId book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `WhoDat`, which `Person` references. This allows `WhoDat` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2425S2-CS2103T-T14-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both classId book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `WhoDatStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.classId.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add a student contact

`WhoDatParser` creates `AddCommandParser` to parse user input string.

<puml src="diagrams/AddSequenceDiagram.puml" alt="AddSequenceDiagram" />

`WhoDatParser` receives the student's name, student id, email id, class id and tags from user input.

Then, `WhoDatParser` ensures that all fields match their respective validation regex. If the input is invalid, `WhoDatParser` throws a ParseException.
Otherwise, it creates a new instance of `AddCommand`.

Upon execution, `AddCommand` checks if a duplicate student contact exists. If there is no duplicate, `AddCommand` will add the student to the contact list.
 
`...` is used to represent a valid user input, as the full input is too long to be included in the diagram. A full example of a valid input is: add n/John i/A1234567X e/E1234567 c/CS210501

> **_NOTE:_** The sequence diagram shows a simplified execution of the AddCommand.

<br></br>

### Delete a student contact

`WhoDatParser` creates `DeleteCommandParser` to parse user input string.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="DeleteSequenceDiagram" />

`WhoDatParser` receives the student id from the user's input.
Then, `WhoDatParser` ensures that the student id matches the validation regex. If the input is invalid, `WhoDatParser` throws a ParseException.
Otherwise, it creates a new instance of `DeleteCommand`.

Upon execution, `DeleteCommand` deletes the student contact from the list.
> **_NOTE:_** The sequence diagram shows a simplified execution of the DeleteCommand.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* is a teaching assistant from NUS Computing
* has a need to manage a significant number of student contacts
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: WhoDat is a free desktop application that helps NUS teaching assistants manage student contacts faster than a typical mouse driven app.

---

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a/an …​ | I want to …​                                        | So that I can…​                                                                              |
|----------|------------|-----------------------------------------------------|----------------------------------------------------------------------------------------------|
| `* * *`  | SoC TA     | add a student's contact                             | store their contact details                                                                  |
| `* * *`  | SoC TA     | delete a student's contact                          | remove outdated or incorrect contacts                                                        |
| `* * *`  | SoC TA     | list all student contacts                           | view all my current students                                                                 |
| `* * *`  | SoC TA     | add tags to student contacts                        | include extra remarks about students                                                         |
| `* * *`  | SoC TA     | save my student contacts locally                    | avoid losing my data when I close the app                                                    |
| `* * *`  | SoC TA     | load my student contact details from a save file    | access my saved data when I open the app                                                     |
| `* * *`  | SoC TA     | clear my list of student contacts                   | create a new list for the next semester                                                      |
| `* *`    | SoC TA     | edit my students' contact details                   | update their information if there are changes                                                |
| `* *`    | SoC TA     | filter my student contacts by tutorial group        | quickly find students from any tutorial group                                                |
| `* *`    | SoC TA     | filter my student contacts by tag status            | quickly find specific students with special conditions                                       |
| `* *`    | SoC TA     | mark/unmark a student for requesting a consultation | track the consultation needs of the student                                                  |
| `* *`    | SoC TA     | search for a student by name                        | quickly locate specific student contacts                                                     |
| `* *`    | SoC TA     | search for a student by NUS ID                      | quickly locate specific student contacts                                                     |
| `* *`    | SoC TA     | delete multiple students at one go                  | de-clutter the contact list of outdated or incorrect contacts in as few commands as possible |
| `*`      | SoC TA     | archive old student contacts                        | contact ex-students from previous semesters                                                  |

---

### Use cases

(For all use cases below, the **System** is `WhoDat` and the **Actor** is the `user`, unless specified otherwise)

**Use case UC01: Add a new student contact**

**MSS**

1.  User requests to add student contact
2.  System adds the contact to the list
3.  System shows that user has been added

    Use case ends.

**Extensions**

* 1a. The student with the same StudentId or Email already exists in the list.

    * 1a1. System shows an error message.

  Use case ends.

* 1b. The user's input has missing or invalid fields.

    * 1b1. System shows an error message.

      Use case ends.

<br></br>

**Use case UC02: Delete a student contact**

**MSS**

1.  User requests to delete student contact
2.  System deletes the contact from the list
3.  System shows the contact has been deleted

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 1b. The student does not exist in the list.

    * 1b1. AddressBook shows an error message.

      Use case ends.

<br></br>

**Use case UC03: List all student contacts**

**MSS**

1. User requests to view the list of all student contacts.
2. System displays the list of student contacts.

   Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. System displays a relevant message.

  Use case ends.

* 1b. User enters an invalid command.
    * 1b1. System shows an error message.

  Use case ends.

<br></br>

**Use case UC04: Find a student contact**

**MSS**

1. User requests to find a student contact by name or student id.
2. System searches for matching contacts.
3. System displays the contact(s) that match the search criteria.

   Use case ends.

**Extensions**

* 1a. User enters an empty query.
    * 1a1. System shows an error message.

  Use case ends.

* 1b. User enters an invalid student id or name format.
    * 1b1. System shows an error message.

  Use case ends.

* 2a. No matching contacts are found.
    * 2a1. System shows a message stating that no contacts were found.

  Use case ends.

<br></br>

**Use case UC05: Edit a student contact**

**MSS**

1. User requests to edit a student contact.
2. System updates the contact's details.
3. System shows a success message.

   Use case ends.

**Extensions**

* 1a. The student id does not exist in the list.
    * 1a1. System shows an error message.

  Use case ends.

* 1b. The field to be edited is invalid or the value is incorrect.
    * 1b1. System shows an error message.

  Use case ends.

<br></br>

**Use case UC06: Filter contacts by class or tag**

**MSS**

1. User requests to filter student contacts based on class id or tag.
2. System filters the contacts based on the input filter criteria.
3. System displays the contacts that match the filter.

   Use case ends.

**Extensions**

* 1a. User enters an invalid filter or tag.
    * 1a1. System shows an error message.

  Use case ends.

* 2a. No matching contacts are found.
    * 2a1. System shows a relevant message.

  Use case ends.

<br></br>

**Use case UC07: Mass delete student contacts**

**MSS**

1. User requests to delete multiple student contacts by specifying multiple student ids.
2. System deletes the contacts from the list.
3. System displays completion message where the student ids of the students who were deleted successfully, not found or 
completely invalid.

   Use case ends.

**Extensions**


* 1a. No student ids are supplied.
    * 1a1. System shows an error message.
  Use case ends. 
  
* 3a. Invalid student ids were supplied in step 1.
    * 3a1. System appends a message to the completion message informing the user why some of the student ids were invalid.


<br></br>

**Use case UC08: Clear the contact list**

**MSS**

1. User requests to clear the contact list.
2. System deletes all contacts from the list.
3. System shows a success message.

   Use case ends.

**Extensions**

* 1a. The list is already empty.
    * 1a1. System shows an update message.

  Use case ends.

<br></br>

**Use case UC09: Exit the application**

**MSS**

1. User requests to exit the application.
2. System closes the application.

   Use case ends.

<br></br>

### Non-Functional Requirements

1.  **Platform Independent**: Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  **Scalability**: Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  **Typing Efficiency**: A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  **Portability**: The application should not **require an installer** and should be downloaded as a single JAR file.
5.  **Data storage**: Data should be stored in a **text file** rather than a Database Management System.
6.  **Data reliability**: After reopening the application, the loaded data should be **identical** to the previously saved data.
7.  **Stability**: The application should be able to **recover from errors gracefully** without crashing or losing data.

<br></br>

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **CLI**: A Command Line Interface which allows users to interact with the system via text
* **SoC TA**: A teaching assistant employed by the NUS School of Computing
* **Student**: A student who is taught by the SoC TA

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Run `java -jar whodat.jar` in a terminal. 
   
   2. Expected: Shows the GUI with a set of sample student contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by typing `java -jar whodat.jar` in a terminal.<br>
       Expected: The most recent window size and location is retained.

<br></br>

### Adding a person

1. Adding a valid contact

   1. Prerequisite: A duplicate contact cannot exist.
   
   2. Test case: `add n/James i/A0277024H e/E1136951 c/110103`<br>
      Expected: Contact is added with success status message.

2. Adding a duplicate contact

    1. Prerequisite: A duplicate contact exists.

    2. Test case: `add n/James i/A0277024H e/E1136951 c/110103`<br>
       Expected: Contact is not added to the list, an error message is shown.

3. Adding contact with invalid fields

    1. Omit a field like class id: `add add n/James i/A0277024H e/E1136951` <br>
    Expected: An error message showing the correct add format.<br>
   
   2. Other fields to omit include name, student id and email id.
       
<br></br>

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `delete AxxxxxxxX`, where x are numerical digits and X is a capitalised letter<br>
      Expected: If student id is valid, then contact is deleted from the list. Details of the deleted contact shown in the status message. 

   3. Test case: `delete A29`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete 1`, `/delete`<br>
      Expected: Similar to test case 2.

<br></br>

### Mass deleting people

1. Deleting more than one person

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    2. Test case: `m_delete `
       Expected: An error message detailing that at least one student id should be supplied.

    3. Test case: `m_delete AxxxxxxxX, AxxxxxxxX`, where x are numerical digits and X is a capitalised letter<br>
       Expected: If student ids are valid and existent, then the student ids are deleted from the list. If the student ids are 
       valid but non-existent or are invalid, they are not removed from the list. Student ids of the successfully deleted 
       students, missing students and invalid students are displayed.
   
    4. Test case: `m_delete AxxxxxxxX, AxxxxxxxX`, where x are numerical digits and X is a capitalised letter, and both student ids are the same.<br>
       Expected: If both the student ids are valid, the duplicate is removed and deletion happens only once. The message 
       displayed is similar to test case 3 but the duplicate, valid student id is displayed only once.
   
    5. Test case: `m_delete AxxxxxxxX AxxxxxxxX`, where x are numerical digits and X is a capitalised letter, and no comma-separation.<br>
       Expected: No deletion happens and the input student ids are displayed as invalid student ids.

    6. Test case: `m_delete A2910`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    7. Other incorrect delete commands to try: `/m_delete`, `m delete`<br>
       Expected: An error message warning about invalid command being used.

<br></br>

### List
1. List all student contacts

    1. Prerequisite: Ensure that the list is not empty.

    2. Test case: `list`<br>
       Expected: The list should display all contacts.

    3. Test case: `/list`<br>
       Expected: An error message.

<br></br>

### Editing a contact

1. Edit a student contact

    1. Prerequisite: The contact must already be in the list.

    2. Test case: `edit StudentId field/new_value`<br>
       Example: `edit A0272222H n/Xinyi`
       Expected: Contact is edited with success status message.

2. Edit a student contact using an invalid input

    1. Prerequisite: Input format is wrong.

    2. Test case: `edit StudentId field/wrong_value`<br>
       Example: `edit A0272222H e/Xinyi`
       Expected: Contact is not edited, an error message is shown.

<br></br>

### Finding a student contact
1. Find using name

    1. Prerequisites: The contact must already exist in the list. Let's use 'Jane' as an example.

    2. Test case: `find Jane`<br>
    Expected: All contacts with the name 'Jane' (case-insensitive) are displayed.

    3. Test case: `find jane`<br>
    Expected: All contacts with the name 'Jane' (case-insensitive) are displayed.

2. Find using student id

    1. Prerequisites: The contact must already exist in the list. Let's use 'A1234567B' as an example.

    2. Test case: `find A123456B`<br>
       Expected: The contact is displayed.

    3. Test case: `find a123456b`<br>
       Expected: The contact is displayed (find is case-insensitive).

3. Find with an empty input

    1. Test case: `find`<br>
       Expected: An error message will be shown which explains how to use the find command.

<br></br>

### Filtering a student contact
1. Filter by class id

    1. Prerequisite: There must be at least one contact with the chosen class id.

    2. Test case: `filter_c class id`<br>
       Example: `filter_c cs210501`<br>
       Expected: All contacts with the same class id (case-insensitive) are displayed.

    3. Test case: `filter_c CS210501`<br>
       Expected: All contacts with the same class id (case-insensitive) are displayed.

2. Find using tag

    1. Prerequisites: There must be at least one contact with the chosen tag.

   2. Test case: `filter_t tag`<br>
      Example: `filter_t NoSubmission`<br>
      Expected: All contacts with the same class id (case-insensitive) are displayed.

   3. Test case: `filter_t nosubmission`<br>
      Expected: All contacts with the same class id (case-insensitive) are displayed.

3. Filter with an empty input

    1. Test case: `filter_c`<br>
       Expected: An error message will be shown which explains how to use the find command.

<br></br>

### Clearing the student contact list
1. Clear the contact list

    1. Prerequisite: Ensure that the list is not empty.

    2. Test case: `clear`<br>
       Expected: A success message stating that WhoDat has been cleared.

<br></br>

### Saving data

1. Data is automatically saved when you exit the program

    1. Type `list` and ensure that the list is not empty.

    2. Test case: `exit` and open the WhoDat application again<br>
       Expected: The list of student contacts previously saved are displayed.

<br></br>

### Exit Application
1. Exit by clicking the close button at the top right

    1. Test case: Close the window by clicking on the Window's close button.<br>
       Expected: The window closes. <br></br>

2. Exit via exit command

    1. Test case: Type `exit` to close the window.<br>
       Expected: The window closes.
