import java.util.*;

class User {
    String userId;
    String password;
    String name;

    public User(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public boolean login(String id, String pass) {
        return userId.equals(id) && password.equals(pass);
    }

    public void updateProfile(String newName, String newPass) {
        this.name = newName;
        this.password = newPass;
        System.out.println("✅ Profile updated successfully!");
    }
}

class Question {
    String question;
    String[] options;
    int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean askQuestion(Scanner sc) {
        System.out.println("\n" + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your answer: ");
        int ans = sc.nextInt();
        sc.nextLine(); // consume newline
        return ans == correctOption;
    }
}

public class OnlineExamination {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create a user
        User user = new User("user123", "1234", "Shraddha");

        // Login
        System.out.print("Enter User ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (!user.login(id, pass)) {
            System.out.println("❌ Invalid credentials. Exiting...");
            return;
        }
        System.out.println("✅ Login successful! Welcome " + user.name);

        // Main menu
        while (true) {
            System.out.println("\n----- ONLINE EXAM MENU -----");
            System.out.println("1. Update Profile & Password");
            System.out.println("2. Start Exam");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPass = sc.nextLine();
                    user.updateProfile(newName, newPass);
                    break;

                case 2:
                    // Create questions
                    Question[] questions = {
                        new Question("Which language is platform independent?",
                                new String[]{"C", "C++", "Java", "Python"}, 3),
                        new Question("Which keyword is used to inherit a class in Java?",
                                new String[]{"this", "super", "extends", "implements"}, 3),
                        new Question("Which data structure uses FIFO?",
                                new String[]{"Stack", "Queue", "Tree", "Graph"}, 2)
                    };

                    int score = 0;
                    long startTime = System.currentTimeMillis();
                    long endTime = startTime + 60 * 1000; // 1 minute timer

                    for (Question q : questions) {
                        if (System.currentTimeMillis() > endTime) {
                            System.out.println("\n⏰ Time's up! Auto-submitting exam...");
                            break;
                        }
                        if (q.askQuestion(sc)) {
                            score++;
                        }
                    }

                    System.out.println("\n✅ Exam finished! Your score: " + score + "/" + questions.length);
                    break;

                case 3:
                    System.out.println("👋 Logged out successfully. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}