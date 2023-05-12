import java.util.*;

// Define a Job class to represent a task to be executed
class Job {
    int id; // Job identifier
    int priority; // Job priority
    int duration; // Time required to complete the job
    int remaining; // Time remaining to complete the job

    // Constructor to initialize the job attributes
    public Job(int id, int priority, int duration) {
        this.id = id;
        this.priority = priority;
        this.duration = duration;
        this.remaining = duration;
    }

    // Comparator to sort jobs by priority
    public static Comparator<Job> priorityComparator = new Comparator<Job>() {
        @Override
        public int compare(Job j1, Job j2) {
            return j2.priority - j1.priority;
        }
    };

    // Comparator to sort jobs by remaining time
    public static Comparator<Job> remainingComparator = new Comparator<Job>() {
        @Override
        public int compare(Job j1, Job j2) {
            return j1.remaining - j2.remaining;
        }
    };

    // Override toString method to print the job identifier
    @Override
    public String toString() {
        return "Job " + id ;
    }
}

// Define a CPUScheduler class to schedule jobs using different policies
public class task4_CPUScheduler {
    Queue<Job> fcfsQueue; // Queue for FCFS policy
    PriorityQueue<Job> priorityQueue; // Queue for HPF policy
    PriorityQueue<Job> srtfQueue; // Queue for SRTF policy

    // Constructor to initialize the queues
    public task4_CPUScheduler() {
        fcfsQueue = new LinkedList<>();
        priorityQueue = new PriorityQueue<>(Job.priorityComparator);
        srtfQueue = new PriorityQueue<>(Job.remainingComparator);
    }

    // Method to add a job to all three queues
    public void addJob(Job job) {
        fcfsQueue.offer(job);
        priorityQueue.offer(job);
        srtfQueue.offer(job);
    }

    // Method to schedule jobs using a given policy
    public void schedule(String policy) {
        System.out.println("Scheduling with " + policy + " policy:");
        int time = 0;
        Queue<Job> queue;

        // Select the queue based on the policy
        switch (policy) {
            case "First Come First Served":
                queue = fcfsQueue;
                break;
            case "Highest priority":
                queue = priorityQueue;
                break;
            case "Shortest Remaining Time First":
                queue = srtfQueue;
                break;
            default:
                System.out.println("Invalid policy");
                return;
        }

        // Schedule the jobs in the selected queue
        while (!queue.isEmpty()) {
            Job current = queue.poll();
            System.out.println("System Time: " + time + ", running  " + current);
            current.remaining--;
            time++;

            // If the job has not finished and it's not FCFS policy, add it back to the queue
            if (current.remaining > 0 && !policy.equals("First Come First Served")) {
                queue.offer(current);
            }
        }

        // Print a message when all jobs are finished
        System.out.println("All jobs are finished.");
        System.out.println();
    }

    // Main method to create a scheduler object, add jobs, and schedule them
    public static void main(String[] args) {
        task4_CPUScheduler scheduler = new task4_CPUScheduler();


        Job job1 = new Job(1, 0, 10);
        Job job2 = new Job(2, 3, 5);
        Job job3 = new Job(3, 8, 7);
        Job job4 = new Job(4, 6, 4);

        scheduler.addJob(job1);
        scheduler.addJob(job2);
        scheduler.addJob(job3);
        scheduler.addJob(job4);


        scheduler.schedule("First Come First Served");
        scheduler.schedule("Highest priority");
        scheduler.schedule("Shortest Remaining Time First");
    }


}
