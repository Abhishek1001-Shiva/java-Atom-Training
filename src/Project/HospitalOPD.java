package Project;

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * HospitalOPD.java
 * ---------------------------------------------------------------------
 * Hospital OPD Token & Queue Management System — single-file edition.
 *
 * Everything required by the task lives in this one file, as separate
 * top-level-style classes (Java only allows one PUBLIC class per file,
 * so the supporting classes are package-private and simply stacked
 * below the public entry point):
 *
 *   1. Patient          - data model, Comparable for priority ordering
 *   2. CircularQueue<T>  - custom array-based circular queue, built from
 *                          scratch (NOT java.util.Queue), backs the
 *                          REGULAR (non-emergency) line. O(1) ops.
 *   3. OPDQueueManager   - orchestrates the regular CircularQueue and a
 *                          java.util.PriorityQueue<Patient> for
 *                          emergencies; emergencies are always served
 *                          before regular patients, FIFO among ties.
 *   4. HospitalOPD       - console menu (this is the public class /
 *                          the one with main()).
 *
 * Compile & run:
 *   javac HospitalOPD.java
 *   java HospitalOPD
 * ---------------------------------------------------------------------
 */
public class HospitalOPD {

    private static final int REGULAR_QUEUE_CAPACITY = 20;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OPDQueueManager manager = new OPDQueueManager(REGULAR_QUEUE_CAPACITY);

        System.out.println("=======================================");
        System.out.println(" HOSPITAL OPD TOKEN & QUEUE MANAGEMENT");
        System.out.println("=======================================");
        System.out.print("KINDLY ENTER");
        sc.nextLine(); // consume leftover newline

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    handleIssueToken(sc, manager);
                    break;
                case "2":
                    handleServeNext(manager);
                    break;
                case "3":
                    handleMarkEmergency(sc, manager);
                    break;
                case "4":
                    handlePeekNext(manager);
                    break;
                case "5":
                    displayAllQueues(manager);
                    break;
                case "6":
                    displayRegularQueue(manager);
                    break;
                case "7":
                    displayEmergencyQueue(manager);
                    break;
                case "8":
                    displayServedHistory(manager);
                    break;
                case "9":
                    handleSearch(sc, manager);
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 0-9.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("---------------------------------------");
        System.out.println("1. Issue Token"  );
        System.out.println("2. Serve Next Patient");
        System.out.println("3. Mark Emergency");
        System.out.println("4. Preview Next Patient");
        System.out.println("5. Display All Queues ");
        System.out.println("6. Display Regular Queue");
        System.out.println("7. Display Emergency Queue");
        System.out.println("8. Served Patients History");
        System.out.println("9. Search Patient");
        System.out.println("0. Exit");
        System.out.println("---------------------------------------");
    }

    private static void handleIssueToken(Scanner sc, OPDQueueManager manager) {
        System.out.print("Patient name: ");
        String name = sc.nextLine().trim();
        System.out.print("Issue / complaint: ");
        String issue = sc.nextLine().trim();

        Patient p = manager.issueToken(name, issue);
        if (p != null) {
            System.out.println("Token issued -> " + p);
            System.out.println("Current position in regular queue: " + manager.getRegularQueue().getSize());
        }
    }

    private static void handleServeNext(OPDQueueManager manager) {
        Patient next = manager.serveNextPatient();
        if (next == null) {
            System.out.println("No patients waiting. Queue is empty.");
        } else {
            System.out.println("Now serving -> " + next);
        }
    }

    private static void handleMarkEmergency(Scanner sc, OPDQueueManager manager) {
        System.out.print("Enter token number to mark as EMERGENCY: ");
        int token = readInt(sc);
        boolean ok = manager.markAsEmergency(token);
        if (ok) {
            System.out.println("Token #" + token + " moved to the emergency queue.");
        } else {
            System.out.println("Token #" + token + " not found in the regular queue " +
                    "(already served, already emergency, or invalid).");
        }
    }

    private static void handlePeekNext(OPDQueueManager manager) {
        Patient next = manager.peekNextPatient();
        System.out.println(next == null ? "No patients waiting." : "Next up -> " + next);
    }

    private static void displayAllQueues(OPDQueueManager manager) {
        System.out.println("=== FULL DASHBOARD ===");
        displayEmergencyQueue(manager);
        displayRegularQueue(manager);
        System.out.println("Total waiting: " + manager.getTotalWaiting());
    }

    private static void displayRegularQueue(OPDQueueManager manager) {
        CircularQueue<Patient> q = manager.getRegularQueue();
        System.out.println("--- Regular Queue (" + q.getSize() + "/" + q.getCapacity() + ") ---");
        if (q.isEmpty()) {
            System.out.println("  (empty)");
            return;
        }
        for (int i = 0; i < q.getSize(); i++) {
            System.out.println("  " + (i + 1) + ". " + q.get(i));
        }
    }

    private static void displayEmergencyQueue(OPDQueueManager manager) {
        PriorityQueue<Patient> eq = manager.getEmergencyQueue();
        System.out.println("--- Emergency Queue (" + eq.size() + ") ---");
        if (eq.isEmpty()) {
            System.out.println("  (empty)");
            return;
        }
        // Copy + poll so we display in true priority order without mutating the real queue
        PriorityQueue<Patient> copy = new PriorityQueue<>(eq);
        int rank = 1;
        while (!copy.isEmpty()) {
            System.out.println("  " + rank++ + ". " + copy.poll());
        }
    }

    private static void displayServedHistory(OPDQueueManager manager) {
        System.out.println("--- Served Patients History (" + manager.getServedHistory().size() + ") ---");
        if (manager.getServedHistory().isEmpty()) {
            System.out.println("  (none yet)");
            return;
        }
        int i = 1;
        for (Patient p : manager.getServedHistory()) {
            System.out.println("  " + (i++) + ". " + p);
        }
    }

    private static void handleSearch(Scanner sc, OPDQueueManager manager) {
        System.out.print("Enter token number to search: ");
        int token = readInt(sc);
        Patient p = manager.searchPatient(token);
        System.out.println(p == null ? "Token #" + token + " not found." : "Found -> " + p);
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine(); // consume newline
        return value;
    }

    private static void loadSampleData(OPDQueueManager manager) {
        manager.issueToken("Rajesh Kumar", "Fever");
        manager.issueToken("Priya Sharma", "Cough");
        manager.issueToken("Amit Patel", "Back Pain");
        manager.issueToken("Sunita Rao", "Headache");
        manager.markAsEmergency(1002); // Amit Patel jumps the queue
    }
}

/**
 * OPDQueueManager
 * ---------------------------------------------------------------------
 * Owns the two queues that make up the OPD line:
 *   - regularQueue   : CircularQueue<Patient>   (custom, array-based, O(1) ops)
 *   - emergencyQueue : PriorityQueue<Patient>   (java.util, ordered via Patient.compareTo)
 *
 * Rule enforced: whenever someone must be called next, the emergency
 * queue is drained first (in FIFO order among emergencies); only when it
 * is empty does the regular circular queue get served.
 */
class OPDQueueManager {

    private final CircularQueue<Patient> regularQueue;
    private final PriorityQueue<Patient> emergencyQueue;
    private final List<Patient> servedHistory;

    private int nextTokenNumber;
    private long arrivalCounter; // monotonically increasing "clock" for FIFO tie-breaks

    public OPDQueueManager(int regularQueueCapacity) {
        this.regularQueue = new CircularQueue<>(regularQueueCapacity);
        this.emergencyQueue = new PriorityQueue<>(); // uses Patient.compareTo()
        this.servedHistory = new ArrayList<>();
        this.nextTokenNumber = 1000;
        this.arrivalCounter = 0;
    }

    /** Issues a new token and enqueues the patient into the regular line. O(1). */
    public Patient issueToken(String name, String issue) {
        Patient patient = new Patient(nextTokenNumber++, name, issue, arrivalCounter++, false);
        boolean added = regularQueue.enqueue(patient);
        if (!added) {
            System.out.println("⚠️  Regular queue is FULL (capacity "
                    + regularQueue.getCapacity() + "). Cannot issue token right now.");
            nextTokenNumber--; // roll back the token, it was never actually queued
            arrivalCounter--;
            return null;
        }
        return patient;
    }

    /**
     * Serves (removes and returns) the next patient to be called.
     * Emergency queue always takes priority over the regular circular queue.
     * O(log n) worst case (emergency poll), O(1) for the regular path.
     */
    public Patient serveNextPatient() {
        Patient next;
        if (!emergencyQueue.isEmpty()) {
            next = emergencyQueue.poll();
        } else {
            next = regularQueue.dequeue();
        }
        if (next != null) {
            servedHistory.add(next);
        }
        return next;
    }

    /** Previews who will be served next without removing them. O(1). */
    public Patient peekNextPatient() {
        if (!emergencyQueue.isEmpty()) {
            return emergencyQueue.peek();
        }
        return regularQueue.peek();
    }

    /**
     * Finds a patient by token number in the regular queue and transfers
     * them into the emergency priority queue.
     * O(n) — scans the regular line, which is fine for an infrequent
     * administrative override rather than a routine queue operation.
     */
    public boolean markAsEmergency(int tokenNumber) {
        Patient target = null;
        for (int i = 0; i < regularQueue.getSize(); i++) {
            Patient p = regularQueue.get(i);
            if (p.getTokenNumber() == tokenNumber) {
                target = p;
                break;
            }
        }
        if (target == null) {
            return false; // not found in the regular line (already served, already emergency, or invalid token)
        }
        regularQueue.remove(target);
        target.setEmergency(true);
        emergencyQueue.offer(target);
        return true;
    }

    /** Searches all three pools (regular, emergency, served) for a token. O(n). */
    public Patient searchPatient(int tokenNumber) {
        for (int i = 0; i < regularQueue.getSize(); i++) {
            Patient p = regularQueue.get(i);
            if (p.getTokenNumber() == tokenNumber) return p;
        }
        for (Patient p : emergencyQueue) {
            if (p.getTokenNumber() == tokenNumber) return p;
        }
        for (Patient p : servedHistory) {
            if (p.getTokenNumber() == tokenNumber) return p;
        }
        return null;
    }

    public CircularQueue<Patient> getRegularQueue() {
        return regularQueue;
    }

    public PriorityQueue<Patient> getEmergencyQueue() {
        return emergencyQueue;
    }

    public List<Patient> getServedHistory() {
        return servedHistory;
    }

    public int getTotalWaiting() {
        return regularQueue.getSize() + emergencyQueue.size();
    }
}

/**
 * CircularQueue<T>
 * ---------------------------------------------------------------------
 * A generic, array-based circular queue implemented FROM SCRATCH
 * (no java.util.Queue / java.util.LinkedList involved).
 *
 * Design:
 *   - Fixed-capacity backing array.
 *   - front and rear indices wrap around using modulo arithmetic:
 *         nextIndex = (index + 1) % capacity
 *   - A `size` counter distinguishes "empty" from "full" so front == rear
 *     can mean either, without wasting a slot.
 *
 * Complexity (n = capacity):
 *   enqueue()  O(1)
 *   dequeue()  O(1)
 *   peek()     O(1)
 *   get(i)     O(1)
 *   toArray()  O(n)   (only used for display)
 *
 * This class backs the REGULAR (non-emergency) OPD line. Emergency
 * patients are handled separately by a java.util.PriorityQueue in
 * OPDQueueManager.
 */
class CircularQueue<T> {

    private final Object[] data;
    private final int capacity;
    private int front;   // index of the current front element
    private int rear;    // index of the last inserted element
    private int size;    // number of elements currently stored

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    /** Adds an element to the rear of the queue. O(1). */
    public boolean enqueue(T item) {
        if (isFull()) {
            return false; // caller decides how to handle a full queue
        }
        rear = (rear + 1) % capacity;   // wraparound
        data[rear] = item;
        size++;
        return true;
    }

    /** Removes and returns the front element. O(1). */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T item = (T) data[front];
        data[front] = null;             // avoid memory leak (loitering reference)
        front = (front + 1) % capacity; // wraparound
        size--;
        return item;
    }

    /** Returns the front element without removing it. O(1). */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) data[front];
    }

    /** Returns the i-th element in logical (front-to-rear) order. O(1). */
    @SuppressWarnings("unchecked")
    public T get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        int actualIndex = (front + i) % capacity;
        return (T) data[actualIndex];
    }

    /**
     * Removes the first element that matches the given item (by reference/
     * equals) and logically compacts the queue by rebuilding it. Used when
     * a regular patient is pulled out mid-queue to be marked as emergency.
     * O(n) — acceptable since this is a rare administrative action, not a
     * hot-path queue operation.
     */
    @SuppressWarnings("unchecked")
    public boolean remove(T item) {
        if (isEmpty()) return false;

        Object[] temp = new Object[size];
        boolean removed = false;
        int count = 0;

        for (int i = 0; i < size; i++) {
            int actualIndex = (front + i) % capacity;
            Object current = data[actualIndex];
            if (!removed && current.equals(item)) {
                removed = true; // skip this one — effectively removes it
                continue;
            }
            temp[count++] = current;
        }

        if (removed) {
            // Reset and re-enqueue remaining elements in order
            for (int i = 0; i < capacity; i++) data[i] = null;
            front = 0;
            rear = count - 1;
            size = count;
            for (int i = 0; i < count; i++) {
                data[i] = temp[i];
            }
        }
        return removed;
    }

    /** Returns all elements in front-to-rear logical order (for display). O(n). */
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] arrayType) {
        T[] result = Arrays.copyOf(arrayType, size);
        for (int i = 0; i < size; i++) {
            int actualIndex = (front + i) % capacity;
            result[i] = (T) data[actualIndex];
        }
        return result;
    }
}

/**
 * Patient
 * ---------------------------------------------------------------------
 * Data model for a patient in the OPD queue system.
 *
 * Implements Comparable so that Patient objects can be ordered directly
 * inside a java.util.PriorityQueue<Patient>:
 *   - Emergency patients always come before regular patients.
 *   - Within the same priority level, earlier arrivalTime (i.e. earlier
 *     token) wins -> FIFO order is preserved among equals.
 */
class Patient implements Comparable<Patient> {

    private final int tokenNumber;
    private final String name;
    private final String issue;
    private final long arrivalTime;   // logical arrival sequence number (acts as a timestamp)
    private boolean emergency;

    public Patient(int tokenNumber, String name, String issue, long arrivalTime, boolean emergency) {
        this.tokenNumber = tokenNumber;
        this.name = name;
        this.issue = issue;
        this.arrivalTime = arrivalTime;
        this.emergency = emergency;
    }

    public int getTokenNumber() {
        return tokenNumber;
    }

    public String getName() {
        return name;
    }

    public String getIssue() {
        return issue;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    /**
     * Ordering used by the emergency PriorityQueue:
     * emergency patients sort before non-emergency ones; ties broken by
     * arrival order (FIFO), so two emergencies are served in the order
     * they were marked/arrived.
     */
    @Override
    public int compareTo(Patient other) {
        if (this.emergency != other.emergency) {
            return this.emergency ? -1 : 1;   // emergency -> comes first
        }
        return Long.compare(this.arrivalTime, other.arrivalTime); // FIFO tie-break
    }

    @Override
    public String toString() {
        String tag = emergency ? "[EMERGENCY]" : "[Regular]  ";
        return String.format("%s Token #%-5d %-15s Issue: %-20s Arrived @ %d",
                tag, tokenNumber, name, issue, arrivalTime);
    }
}
