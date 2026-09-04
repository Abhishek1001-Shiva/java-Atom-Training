HospitalOPD-Project 
HOSPITAL OPD TOKEN & QUEUE MANAGEMENT SYSTEM

1. PROJECT TITLE

Hospital OPD Token & Queue Management System Using Custom Circular Queue

Subject: Data Structures & Algorithms
Project Type: Console-Based Java Application
Technology: Java
Main DSA Concepts: Custom Circular Queue, Priority Queue, FIFO, Priority Handling


---

2. GIVEN PROBLEM STATEMENT

In a hospital Out-Patient Department (OPD), patients arrive at a counter and receive tokens for consultation. Normally, patients are served according to their arrival order using the FIFO (First-In-First-Out) principle.

However, when a doctor identifies a patient as requiring immediate attention, the patient can be marked as emergency. The emergency patient is removed from the regular queue and moved into a priority queue so that the patient can be served before regular patients.

The system is designed to:

Issue a unique token number to every patient.

Store regular patients in a custom circular array-based queue.

Maintain regular patients according to FIFO order.

Allow a patient to be marked as emergency using the token number.

Move emergency patients from the regular queue to the emergency priority queue.

Serve emergency patients before regular patients.

Maintain arrival order among patients having the same priority.

Display the current regular queue.

Display the emergency queue.

Preview the next patient.

Display served-patient history.

Search for a patient using the token number.

Display the total number of waiting patients.


The program uses a circular queue with a capacity of 20 patients, while emergency patients are maintained using Java's PriorityQueue.


---

3. OBJECTIVES

The main objectives of this project are:

1. To implement a custom circular queue from scratch using an array.


2. To understand the FIFO principle in queue data structures.


3. To manage regular OPD patients efficiently.


4. To implement circular movement using modulo arithmetic.


5. To avoid unnecessary shifting during normal enqueue and dequeue operations.


6. To use a PriorityQueue for emergency patients.


7. To give emergency patients priority over regular patients.


8. To maintain arrival order among patients having the same priority.


9. To generate patient token numbers automatically starting from 1000.


10. To provide a menu-driven console interface.


11. To allow searching for patients using their token numbers.


12. To maintain a history of served patients.


13. To display the current status of regular and emergency queues.




---

4. DSA CONCEPTS USED

4.1 Queue

A queue is a linear data structure that follows the FIFO (First-In-First-Out) principle.

In this project, regular patients are inserted at the rear and removed from the front.

Example:

Patient 1000 → Patient 1001 → Patient 1002
     ↓
Served first

Therefore:

1000 → 1001 → 1002

is the normal serving order.


---

4.2 Custom Circular Queue

The regular patient queue is implemented using the custom class:

CircularQueue<Patient>

The queue uses an array:

Object[] data;

and maintains:

int capacity;
int front;
int rear;
int size;

The constructor is:

CircularQueue(int capacity)

In the main program, the queue is created with:

OPDQueueManager m = new OPDQueueManager(20);

Therefore, the regular queue has a capacity of 20 patients.


---

4.3 Circular Queue Working

The circular queue uses modulo arithmetic:

rear = (rear + 1) % capacity;

This allows the rear position to return to index 0 after reaching the last array position.

For example, with a capacity of 5:

Index:
0   1   2   3   4
                ↓
                0

The movement is:

0 → 1 → 2 → 3 → 4 → 0 → 1 ...

This allows previously used positions to be reused.


---

4.4 Priority Queue

The emergency queue is implemented using Java's:

PriorityQueue<Patient> emergencyQueue;

It is initialized as:

emergencyQueue = new PriorityQueue<>();

Emergency patients are served before regular patients.

The Patient class implements:

Comparable<Patient>

and defines the priority using the compareTo() method.


---

4.5 Comparable

The Patient class contains:

class Patient implements Comparable<Patient>

The comparison logic is:

if (emergency != p.emergency)
    return emergency ? -1 : 1;

return Long.compare(arrivalTime, p.arrivalTime);

This means:

If one patient is emergency and the other is regular, the emergency patient comes first.

If both have the same emergency status, their arrivalTime is compared.

The patient with the smaller arrival time comes first.


Therefore, the priority is:

Emergency Patient
       ↓
Earlier Arrival
       ↓
Regular Patient


---

4.6 Array

The custom circular queue uses:

Object[] data;

The array stores Patient objects.

The queue does not use java.util.Queue for regular patients. Instead, the regular queue is created using:

regularQueue = new CircularQueue<>(capacity);

This satisfies the requirement of implementing the regular queue from scratch.


---

4.7 ArrayList

The program uses:

List<Patient> servedHistory;

and initializes it using:

servedHistory = new ArrayList<>();

This stores patients after they have been served.


---

5. ALGORITHM / LOGIC

Algorithm 1: Issue Token

Step 1: Ask the user to enter the patient's name.

Step 2: Ask the user to enter the issue/complaint.

Step 3: Create a Patient object using the next token number.

Step 4: The first token is 1000.

Step 5: Set the patient's emergency status to false.

Step 6: Insert the patient into the regular circular queue.

Step 7: If the queue is full, display:

Regular queue is FULL.

Step 8: If insertion is successful, increase the token number and arrival counter.

Step 9: Display the issued token.

The relevant logic is:

Patient p = new Patient(
        nextToken, name, issue, arrival, false);

if (!regularQueue.enqueue(p)) {
    System.out.println("Regular queue is FULL.");
    return null;
}

nextToken++;
arrival++;

return p;


---

6. ALGORITHM FOR SERVING NEXT PATIENT

Step 1: Check whether the emergency queue is empty.

Step 2: If the emergency queue is not empty, remove the patient using:

emergencyQueue.poll();

Step 3: Otherwise, remove the patient from the regular circular queue using:

regularQueue.dequeue();

Step 4: If a patient exists, add the patient to:

servedHistory

Step 5: Return the served patient.

The main logic is:

if (!emergencyQueue.isEmpty())
    p = emergencyQueue.poll();
else
    p = regularQueue.dequeue();

if (p != null)
    servedHistory.add(p);

Therefore:

Emergency Queue available?
        |
       YES
        ↓
Emergency Patient served
        |
       NO
        ↓
Regular Queue Patient served


---

7. ALGORITHM FOR MARKING EMERGENCY

Step 1: Ask the user for the token number.

Step 2: Search through the regular circular queue.

Step 3: Compare each patient's token number with the entered token.

Step 4: If the token is found, remove the patient from the regular queue.

Step 5: Change:

p.emergency = true;

Step 6: Add the patient to:

emergencyQueue

Step 7: Display that the token was moved to the emergency queue.

If the token is not found:

Token not found.

The code performs:

regularQueue.remove(p);
p.emergency = true;
emergencyQueue.add(p);


---

8. ALGORITHM FOR PREVIEW NEXT PATIENT

The program uses the peekNextPatient() method.

Step 1: Check the emergency queue.

Step 2: If an emergency patient exists, return:

emergencyQueue.peek();

Step 3: Otherwise return:

regularQueue.peek();

The patient is only viewed and is not removed from the queue.


---

9. ALGORITHM FOR SEARCH PATIENT

The program searches for a patient in three locations.

Step 1

Search the regular queue:

regularQueue.get(i)

Step 2

If not found, search the emergency queue.

Step 3

If still not found, search:

servedHistory

Step 4

If no matching token exists:

Token not found.

Otherwise:

Found -> Patient Details


---

10. IMPLEMENTATION

The complete program is divided into four logical components/classes:

1. HospitalOPD

Responsible for:

Main method.

Scanner input.

Menu display.

User interaction.

Display functions.


2. OPDQueueManager

Responsible for:

Issuing tokens.

Serving patients.

Marking emergencies.

Searching patients.

Previewing the next patient.

Maintaining queues and served history.


3. CircularQueue<T>

Responsible for:

Custom array-based queue.

Enqueue.

Dequeue.

Peek.

Get.

Remove.

Empty/full checking.


4. Patient

Responsible for:

Patient details.

Token number.

Name.

Issue.

Arrival time.

Emergency status.

Priority comparison.

Patient display format.



---

11. PATIENT DATA

Each Patient object contains:

int tokenNumber;
String name, issue;
long arrivalTime;
boolean emergency;

Therefore, each patient stores:

Field	Purpose

tokenNumber	Unique patient token
name	Patient name
issue	Complaint/problem
arrivalTime	Arrival sequence
emergency	Emergency status


The token number starts at:

1000

and increases after every successfully issued token.


---

12. CIRCULAR QUEUE IMPLEMENTATION

Enqueue

The enqueue operation first checks whether the queue is full.

if (isFull())
    return false;

Then:

rear = (rear + 1) % capacity;
data[rear] = item;
size++;

Therefore, the item is placed at the rear of the queue.


---

Dequeue

The dequeue operation first checks whether the queue is empty.

if (isEmpty())
    return null;

The front item is then retrieved:

T item = (T) data[front];

The position is cleared:

data[front] = null;

Then the front moves circularly:

front = (front + 1) % capacity;

Finally:

size--;


---

Peek

The peek() method returns the front patient without removing them.

return (T) data[front];


---

Get

The get(int index) method accesses a logical queue position using:

data[(front + index) % capacity]

This allows the program to access patients according to their logical queue position even when the physical array has wrapped around.


---

13. REMOVE OPERATION

The custom queue also contains:

boolean remove(T item)

This is required when a regular patient is marked as emergency.

The method:

1. Creates a temporary array.


2. Traverses the current queue.


3. Skips the patient being removed.


4. Copies the remaining patients.


5. Replaces the original data array.


6. Resets front, rear, and size.



This allows a patient to be removed from the regular queue before being transferred to the emergency queue.


---

14. SYSTEM FLOW

START
                  |
                  ↓
          Display Main Menu
                  |
                  ↓
             Select Option
                  |
       ┌──────────┼──────────┐
       ↓          ↓          ↓
 Issue Token  Emergency   Serve Next
       |       Handling       |
       ↓          ↓          ↓
Regular Queue → Emergency → Served
       |          Queue       |
       |                       ↓
       └──────────────→ Served History
                  |
                  ↓
             Continue Menu
                  |
                  ↓
                 EXIT


---

15. SAMPLE WORKING EXAMPLE

Step 1: Issue first patient

Input:

Patient name: Rajesh Kumar
Issue / complaint: Fever

Output:

Token issued -> [Regular] Token #1000 Rajesh Kumar Issue: Fever


---

Step 2: Issue second patient

Input:

Patient name: Priya Sharma
Issue / complaint: Cough

Output:

Token issued -> [Regular] Token #1001 Priya Sharma Issue: Cough


---

Step 3: Issue third patient

Input:

Patient name: Amit Patel
Issue / complaint: Back Pain

Output:

Token issued -> [Regular] Token #1002 Amit Patel Issue: Back Pain

The regular queue is now:

1000 → 1001 → 1002


---

16. MARKING AN EMERGENCY PATIENT

Suppose token 1002 needs immediate attention.

Input:

Enter token number: 1002

Output:

Token #1002 moved to emergency queue.

The queues become:

Regular Queue:

1. [Regular] Token #1000 Rajesh Kumar Issue: Fever
2. [Regular] Token #1001 Priya Sharma Issue: Cough

Emergency queue:

1. [EMERGENCY] Token #1002 Amit Patel Issue: Back Pain


---

17. PREVIEW NEXT PATIENT

When the user selects:

4. Preview Next Patient

the program checks the emergency queue first.

Output:

Next up -> [EMERGENCY] Token #1002 Amit Patel Issue: Back Pain

Therefore, the emergency patient gets priority.


---

18. SERVE NEXT PATIENT

When the user selects:

2. Serve Next Patient

the emergency patient is served first.

Output:

Now serving -> [EMERGENCY] Token #1002 Amit Patel Issue: Back Pain

The remaining regular queue is:

1000 → 1001

The next two patients will then be served in FIFO order.


---

19. DISPLAY ALL QUEUES

When option 5 is selected, the program displays:

=== FULL DASHBOARD ===

Then it displays:

--- Emergency Queue ---

followed by:

--- Regular Queue (size/capacity) ---

Finally, it displays:

Total waiting: ...

The regular queue size is displayed using:

q.size + "/" + q.capacity


---

20. SERVED PATIENT HISTORY

Whenever a patient is successfully served, the patient is added to:

servedHistory

The history can be displayed using:

8. Served Patients History

Example:

--- Served Patients History ---
1. [EMERGENCY] Token #1002 Amit Patel Issue: Back Pain
2. [Regular] Token #1000 Rajesh Kumar Issue: Fever


---

21. SEARCH PATIENT

The user can select:

9. Search Patient

and enter a token number.

For example:

Enter token number: 1001

Output:

Found -> [Regular] Token #1001 Priya Sharma Issue: Cough

If the token does not exist:

Token not found.

The search checks:

Regular Queue
      ↓
Emergency Queue
      ↓
Served History


---

22. TEST CASES

Test Case 1: Issue Token

Input:

Name: Rajesh Kumar
Issue: Fever

Expected Output:

Token issued -> [Regular] Token #1000 Rajesh Kumar Issue: Fever

Result: PASS


---

Test Case 2: Multiple Patients

Input:

Issue 1000
Issue 1001
Issue 1002

Expected Queue:

1000 → 1001 → 1002

Result: PASS


---

Test Case 3: FIFO Serving

Input:

Serve
Serve
Serve

Expected Order:

1000 → 1001 → 1002

Result: PASS


---

Test Case 4: Mark Emergency

Input:

Patients: 1000, 1001, 1002
Emergency Token: 1002

Expected:

Regular Queue:
1000 → 1001

Emergency Queue:
1002

Result: PASS


---

Test Case 5: Emergency Serving

Input:

1000 → 1001 → 1002
Mark 1002 Emergency
Serve

Expected:

1002 served first

Result: PASS


---

Test Case 6: Preview Next Patient

If emergency patient 1002 exists:

Next up -> [EMERGENCY] Token #1002 ...

Result: PASS


---

Test Case 7: Empty Queue

If there are no waiting patients and the user selects:

2. Serve Next Patient

Expected:

No patients waiting.

Result: PASS


---

Test Case 8: Invalid Token

If the user enters a token that does not exist:

Enter token number: 9999

Expected:

Token not found.

Result: PASS


---

Test Case 9: Full Regular Queue

The regular queue has a capacity of 20.

When it becomes full, the program displays:

Regular queue is FULL.

Result: PASS


---

23. COMPLEXITY ANALYSIS

Circular Queue

Operation	Time Complexity

enqueue()	O(1)
dequeue()	O(1)
peek()	O(1)
get()	O(1)
isEmpty()	O(1)
isFull()	O(1)
remove()	O(n)


The normal circular queue operations use direct array access and modulo arithmetic.


---

Priority Queue

Operation	Time Complexity

add()	O(log n)
poll()	O(log n)
peek()	O(1)



---

OPD Queue Manager

Operation	Time Complexity

issueToken()	O(1)
serveNextPatient()	O(log n) when emergency queue is used
peekNextPatient()	O(1)
markAsEmergency()	O(n)
searchPatient()	O(n)
getTotalWaiting()	O(1)


The markAsEmergency() operation requires searching through the regular queue, so its complexity is O(n).


---

24. ADVANTAGES

1. Simple and easy-to-use console interface.


2. Automatic token generation.


3. Custom circular queue implementation.


4. Efficient normal enqueue and dequeue operations.


5. Emergency patients receive priority.


6. Regular patients maintain FIFO order.


7. Served patients are stored in history.


8. Patients can be searched by token.


9. Queue status can be viewed at any time.


10. The project demonstrates practical DSA implementation in Java.




---

25. LIMITATIONS

The current implementation has the following limitations:

1. The regular queue has a fixed capacity of 20.


2. The application is console-based.


3. Patient information is not stored permanently after the program exits.


4. Only one regular queue and one emergency queue are maintained.


5. There is no graphical user interface.


6. There is no database connectivity.


7. There is no appointment scheduling system.


8. The program does not contain multiple doctor/counter management.



These limitations are based specifically on the code provided.


---

26. FUTURE ENHANCEMENTS

The system can be improved by adding:

Database storage for patient records.

GUI-based hospital dashboard.

Multiple doctors and counters.

Appointment scheduling.

Patient waiting-time calculation.

Different emergency priority levels.

Automatic display boards.

SMS/notification functionality.

Daily OPD reports.

Patient statistics.



---

27. OUTPUT / SCREENSHOTS

For your project report, take screenshots of these actual program screens:

Screenshot 1 — Main Menu

=======================================
 HOSPITAL OPD TOKEN & QUEUE MANAGEMENT
=======================================

1. Issue Token
2. Serve Next Patient
3. Mark Emergency
4. Preview Next Patient
5. Display All Queues
6. Display Regular Queue
7. Display Emergency Queue
8. Served Patients History
9. Search Patient
0. Exit

Screenshot 2 — Issue Token

Show:

Patient name: Rajesh Kumar
Issue / complaint: Fever

Token issued -> [Regular] Token #1000 Rajesh Kumar Issue: Fever

Screenshot 3 — Regular Queue

Show multiple patients:

--- Regular Queue (3/20) ---
1. [Regular] Token #1000 Rajesh Kumar Issue: Fever
2. [Regular] Token #1001 Priya Sharma Issue: Cough
3. [Regular] Token #1002 Amit Patel Issue: Back Pain

Screenshot 4 — Mark Emergency

Enter token number: 1002
Token #1002 moved to emergency queue.

Screenshot 5 — Emergency Queue

--- Emergency Queue (1) ---
1. [EMERGENCY] Token #1002 Amit Patel Issue: Back Pain

Screenshot 6 — Preview Next Patient

Next up -> [EMERGENCY] Token #1002 Amit Patel Issue: Back Pain

Screenshot 7 — Serve Patient

Now serving -> [EMERGENCY] Token #1002 Amit Patel Issue: Back Pain

Screenshot 8 — History

--- Served Patients History ---
1. [EMERGENCY] Token #1002 Amit Patel Issue: Back Pain

Screenshot 9 — Search

Enter token number: 1001
Found -> [Regular] Token #1001 Priya Sharma Issue: Cough


---

28. CONCLUSION

The Hospital OPD Token & Queue Management System is a console-based Java application developed to demonstrate the practical use of Data Structures and Algorithms in a hospital OPD environment.

The project implements a custom circular array-based queue for regular patients. The circular queue uses front, rear, size, and modulo arithmetic to efficiently manage the queue without shifting elements during normal enqueue and dequeue operations.

A Java PriorityQueue is used for emergency patients. When a regular patient is marked as emergency, the patient is removed from the circular queue, their emergency status is changed to true, and they are inserted into the emergency queue.

When the next patient is served, the program first checks the emergency queue. If an emergency patient exists, that patient is served first. Otherwise, the front patient of the regular circular queue is served.

The system also provides token generation starting from 1000, patient searching, next-patient preview, regular queue display, emergency queue display, total waiting count, and served-patient history.

Thus, the project demonstrates the practical implementation of Circular Queue, Priority Queue, FIFO, Comparable, arrays, ArrayList, searching, priority handling, and object-oriented programming in Java.

Overall, the project successfully models a real-world OPD queue system while demonstrating how different data structures can be combined to solve a practical problem efficiently.


---

29. SOURCE CODE STRUCTURE

Your submitted code can be represented in the report as:

HospitalOPD
     |
     ↓
OPDQueueManager
     |
     ├───────────────┐
     ↓               ↓
CircularQueue    PriorityQueue
     |               |
     ↓               ↓
Regular          Emergency
Patients         Patients
     \               /
      \             /
       ↓           ↓
        Serve Next
            |
            ↓
     Served History

The four logical classes in your supplied code are HospitalOPD, OPDQueueManager, CircularQueue<T>, and Patient, with OPDQueueManager connecting the two queue structures.
