import java.util.*;

public class HospitalOPD {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OPDQueueManager m = new OPDQueueManager(20);

        System.out.println("=======================================");
        System.out.println(" HOSPITAL OPD TOKEN & QUEUE MANAGEMENT");
        System.out.println("=======================================");

        while (true) {

            System.out.println("\n1. Issue Token");
            System.out.println("2. Serve Next Patient");
            System.out.println("3. Mark Emergency");
            System.out.println("4. Preview Next Patient");
            System.out.println("5. Display All Queues");
            System.out.println("6. Display Regular Queue");
            System.out.println("7. Display Emergency Queue");
            System.out.println("8. Served Patients History");
            System.out.println("9. Search Patient");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 0) {
                System.out.println("Exiting. Goodbye!");
                break;
            }

            switch (ch) {

                case 1:
                    System.out.print("Patient name: ");
                    String name = sc.nextLine();

                    System.out.print("Issue / complaint: ");
                    String issue = sc.nextLine();

                    Patient p = m.issueToken(name, issue);

                    if (p != null)
                        System.out.println("Token issued -> " + p);
                    break;

                case 2:
                    p = m.serveNextPatient();

                    if (p == null)
                        System.out.println("No patients waiting.");
                    else
                        System.out.println("Now serving -> " + p);
                    break;

                case 3:
                    System.out.print("Enter token number: ");
                    int token = sc.nextInt();

                    if (m.markAsEmergency(token))
                        System.out.println("Token #" + token +
                                " moved to emergency queue.");
                    else
                        System.out.println("Token not found.");
                    break;

                case 4:
                    p = m.peekNextPatient();

                    if (p == null)
                        System.out.println("No patients waiting.");
                    else
                        System.out.println("Next up -> " + p);
                    break;

                case 5:
                    System.out.println("\n=== FULL DASHBOARD ===");
                    displayEmergency(m);
                    displayRegular(m);
                    System.out.println("Total waiting: " +
                            m.getTotalWaiting());
                    break;

                case 6:
                    displayRegular(m);
                    break;

                case 7:
                    displayEmergency(m);
                    break;

                case 8:
                    displayHistory(m);
                    break;

                case 9:
                    System.out.print("Enter token number: ");
                    token = sc.nextInt();

                    // Handled using try-catch with the custom exception
                    try {
                        p = m.searchPatient(token);
                        System.out.println("Found -> " + p);
                    } catch (PatientNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }

    static void displayRegular(OPDQueueManager m) {

        CircularQueue<Patient> q = m.regularQueue;

        System.out.println("\n--- Regular Queue (" +
                q.size + "/" + q.capacity + ") ---");

        if (q.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        for (int i = 0; i < q.size; i++)
            System.out.println((i + 1) + ". " + q.get(i));
    }

    static void displayEmergency(OPDQueueManager m) {

        System.out.println("\n--- Emergency Queue (" +
                m.emergencyQueue.size() + ") ---");

        if (m.emergencyQueue.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        PriorityQueue<Patient> copy =
                new PriorityQueue<>(m.emergencyQueue);

        int i = 1;

        while (!copy.isEmpty())
            System.out.println((i++) + ". " + copy.poll());
    }

    static void displayHistory(OPDQueueManager m) {

        System.out.println("\n--- Served Patients History ---");

        if (m.servedHistory.isEmpty()) {
            System.out.println("(none yet)");
            return;
        }

        int i = 1;

        for (Patient p : m.servedHistory)
            System.out.println((i++) + ". " + p);
    }
}


// Custom Exception

class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}


// OPD Queue Manager

class OPDQueueManager {

    CircularQueue<Patient> regularQueue;
    PriorityQueue<Patient> emergencyQueue;
    List<Patient> servedHistory;

    int nextToken = 1000;
    long arrival = 0;

    OPDQueueManager(int capacity) {
        regularQueue = new CircularQueue<>(capacity);
        emergencyQueue = new PriorityQueue<>();
        servedHistory = new ArrayList<>();
    }

    Patient issueToken(String name, String issue) {

        Patient p = new Patient(
                nextToken, name, issue, arrival, false);

        if (!regularQueue.enqueue(p)) {
            System.out.println("Regular queue is FULL.");
            return null;
        }

        nextToken++;
        arrival++;

        return p;
    }

    Patient serveNextPatient() {

        Patient p;

        if (!emergencyQueue.isEmpty())
            p = emergencyQueue.poll();
        else
            p = regularQueue.dequeue();

        if (p != null)
            servedHistory.add(p);

        return p;
    }

    Patient peekNextPatient() {

        if (!emergencyQueue.isEmpty())
            return emergencyQueue.peek();

        return regularQueue.peek();
    }

    boolean markAsEmergency(int token) {

        for (int i = 0; i < regularQueue.size; i++) {

            Patient p = regularQueue.get(i);

            if (p.tokenNumber == token) {

                regularQueue.remove(p);
                p.emergency = true;
                emergencyQueue.add(p);

                return true;
            }
        }

        return false;
    }

    // Java Stream API + Custom Exception
    Patient searchPatient(int token) {

        List<Patient> allPatients = new ArrayList<>();

        for (int i = 0; i < regularQueue.size; i++) {
            allPatients.add(regularQueue.get(i));
        }
        allPatients.addAll(emergencyQueue);
        allPatients.addAll(servedHistory);

        return allPatients.stream()
                .filter(p -> p.tokenNumber == token)
                .findFirst()
                .orElseThrow(() -> new PatientNotFoundException("Token #" + token + " not found."));
    }

    int getTotalWaiting() {
        return regularQueue.size + emergencyQueue.size();
    }
}


// Custom Circular Queue

class CircularQueue<T> {

    Object[] data;
    int capacity, front = 0, rear = -1, size = 0;

    CircularQueue(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }

    boolean enqueue(T item) {

        if (isFull())
            return false;

        rear = (rear + 1) % capacity;
        data[rear] = item;
        size++;

        return true;
    }

    @SuppressWarnings("unchecked")
    T dequeue() {

        if (isEmpty())
            return null;

        T item = (T) data[front];

        data[front] = null;
        front = (front + 1) % capacity;
        size--;

        return item;
    }

    @SuppressWarnings("unchecked")
    T peek() {

        if (isEmpty())
            return null;

        return (T) data[front];
    }

    @SuppressWarnings("unchecked")
    T get(int index) {

        return (T) data[(front + index) % capacity];
    }

    boolean remove(T item) {

        if (isEmpty())
            return false;

        Object[] temp = new Object[capacity];
        int count = 0;
        boolean found = false;

        for (int i = 0; i < size; i++) {

            Object x = data[(front + i) % capacity];

            if (!found && x.equals(item)) {
                found = true;
            } else {
                temp[count++] = x;
            }
        }

        if (!found)
            return false;

        data = temp;
        front = 0;
        rear = count - 1;
        size = count;

        return true;
    }
}


// Patient

class Patient implements Comparable<Patient> {

    int tokenNumber;
    String name, issue;
    long arrivalTime;
    boolean emergency;

    Patient(int token, String name, String issue,
            long arrival, boolean emergency) {

        tokenNumber = token;
        this.name = name;
        this.issue = issue;
        arrivalTime = arrival;
        this.emergency = emergency;
    }

    @Override
    public int compareTo(Patient p) {

        if (emergency != p.emergency)
            return emergency ? -1 : 1;

        return Long.compare(arrivalTime, p.arrivalTime);
    }

    @Override
    public String toString() {

        return (emergency ? "[EMERGENCY] " : "[Regular] ") +
                "Token #" + tokenNumber +
                " " + name +
                " Issue: " + issue;
    }
}