import java.util.*;

// The GroupMessagingSystem class manages messaging and user statistics in groups
public class GroupMessagingSystem {

    // Group names mapped to list of messages
    private Map<String, List<String>> groupMessages = new HashMap<>(); // Q.1 INITIALIZE 	           [01 M]

    // Group names mapped to set of users
    private Map<String, Set<User>> groupUsers = new HashMap<>(); // Q.2 INITIALIZE			     [01 M]

    // Q.3 WRITE CODE FOR THIS METHOD TO SEND A MESSAGE TO A GROUP 		     		       [10 M]
    public void sendMessage(String groupName, String message, User sender) {
        // Check if the group exists; if not, initialize it
        if (!groupMessages.containsKey(groupName)) {
            groupMessages.put(groupName, new ArrayList<String>());
        }
        if (!groupUsers.containsKey(groupName)) {
            groupUsers.put(groupName, new HashSet<>());
        }
        // Add the message to the group
        groupMessages.get(groupName).add(message);
        // Add the user to the group
        groupUsers.get(groupName).add(sender);
    }

    // Q.4 WRITE CODE FOR THIS METHOD TO FIND THE MOST ACTIVE USER IN A GROUP	     		       [20 M]
    public User findMostActiveUser(String groupName) {
        // Return null if the group doesn't exist
        if (!groupMessages.containsKey(groupName) || !groupUsers.containsKey(groupName)) {
            return null;
        }
        // Create a map to count messages for each user
        List<User> users = new ArrayList<>(groupUsers.get(groupName));
        List<String> messages = groupMessages.get(groupName);
        Map<User, Integer> counts = new HashMap<>();
        // Iterate through messages and count messages for each user
        for (String message : messages) {
            for (User user : users) {
                if (message.contains(user.getUserName()))
                    counts.put(user, counts.getOrDefault(user, 0) + 1);
            }
        }

        // Sort users based on message counts [call sortUsersByMessageCount()]
        sortUsersByMessageCount(users, counts);
        // Return the user with the highest message count
        return users.get(0);
    }

    // Q.5 WRITE CODE FOR THIS METHOD TO SORT USERS BY THE NUMBER OF MESSAGES THEY'VE SENT
    private void sortUsersByMessageCount(List<User> users,
                                         Map<User, Integer> criteriaMap) {
        // Get the total number of users in the list
        int totalUsers = users.size();
        // Outer loop: Traverse through the list of users
        // The loop will perform multiple passes to sort the users by message count
        for (int i = 0; i < totalUsers; i++) {
            // Inner loop: Compare adjacent users in the list
            int maxIndex = i;
            int maxCount = criteriaMap.get(users.get(i));
            for (int j = i; j < totalUsers; j++) {
                // Compare the message count of two adjacent users
                // The message count for each user is stored in the criteriaMap
                int countJ = criteriaMap.get(users.get(j));
                // If the current user has sent fewer messages than the next one
                if (maxCount < countJ) {
                    maxIndex = j;
                    maxCount = countJ;
                }
            }
            // swap their positions in the list to sort in descending order
            User temp = users.get(maxIndex);
            users.set(maxIndex, users.get(i));
            users.set(i, temp);
            System.out.println(criteriaMap.toString());
        }
    }

    // Q.6 WRITE CODE FOR THIS METHOD TO RETRIEVE USER STATISTICS USING THE UserStatistics INTERFACE
    // USE LAMBDA EXPRESSIONS TO IMPLEMENT THIS METHOD 				       		       [15 M]
    public User.UserStatistics getUserStatistics() {
        // Use lamda expressions to return an implementation of UserStatistics that
        // counts user messages
        User.UserStatistics userStatistics = (
                (user, groupName) -> {
            int userMessageCount = 0;
            List<String> messages = groupMessages.get(groupName);
            for(String message : messages) {
                if(message.contains(user.getUserName()))
                    userMessageCount++;
            }
            return userMessageCount;
        });
        return userStatistics;
    }

    // Q.7 WRITE CODE FOR THIS METHOD TO COMPUTE THE AVERAGE NUMBER OF MESSAGES PER USER IN A GROUP   [05 M]
    public double getAverageMessagesPerUser(String groupName) {
        // Return 0.0 if the group doesn't exist
        if(!groupMessages.containsKey(groupName) || !groupUsers.containsKey(groupName)) {
            return 0.0;
        }
        // Calculate total messages and total users in the group
        double totalMessages = groupMessages.get(groupName).size();
        double totalUsers = groupUsers.get(groupName).size();
        // Return the average number of messages per user
        return totalMessages / totalUsers;
    }

    // Q.8 WRITE CODE FOR THIS METHOD TO RETRIEVE THE MOST FREQUENT MESSAGE IN A GROUP 		       [07 M]
    public String getMostFrequentMessage(String groupName) {
        // Return null if the group doesn't exist
        if(!groupMessages.containsKey(groupName) || !groupUsers.containsKey(groupName)) {
            return null;
        }
        List<String> messages = groupMessages.get(groupName);
        // Count frequency of each message
        Map<String, Integer> counts = new HashMap<>();
        for (String message : messages) {
            counts.put(message, counts.getOrDefault(message, 0) + 1);
        }
        // Identify and return the most frequent message [Use sortMessages()]
        sortMessages(messages);
        return messages.get(messages.size() - 1);
    }

    // Q.9 WRITE CODE FOR THIS METHOD TO SORT MESSAGES BASED ON THEIR LENGTHS	 		       [03 M]
    private void sortMessages(List<String> messages) {
        // Use sort() method of the Collections interface with a lambda expression
        // to compare message lengths
        Collections.sort(messages, (m1, m2) -> m1.length() - m2.length());
    }

    // Inner class to represent a User
    public static class User {
        private final String userID;
        private final String userName;

        // Constructor to initialize User
        public User(String userID, String userName) {
            this.userID = userID;
            this.userName = userName;
        }

        public String getUserID() {
            return userID;
        }

        public String getUserName() {
            return userName;
        }

        // Q.10 OVERRIDE EQUALS METHOD TO COMPARE USERS BASED ON USERID 			       [03 M]
        public boolean equals(Object obj) { // Write code for this method }
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            User userObj = (User) obj;
            return userID.equals(userObj.getUserID());
        }

        public int hashCode() {
            return userID.hashCode();
        }

        public String toString () {
            return "User{" + "userID='" + userID + '\'' + ", userName='" + userName
                    + '\'' + '}'; // Return user details
        }


        // Interface to define a contract for retrieving user statistics
        public interface UserStatistics {
            int getUserMessageCount(User user, String groupName);
        }
    } // End of GroupMessagingSystem class

    // Main method to run the GroupMessagingSystem and test its functionality
    public static void main(String[] args) {
        // Create a new instance of the messaging system
        GroupMessagingSystem system = new GroupMessagingSystem();

        // Test sending messages
        system.sendMessage("GroupA", "Hello from Alpha", new User("U001", "Alpha"));
        system.sendMessage("GroupA", "Hello1 from Alpha", new User("U001", "Alpha"));
        system.sendMessage("GroupA", "Hello2 from Alpha", new User("U001", "Alpha"));
        system.sendMessage("GroupA", "Hello3 from Alpha", new User("U001", "Alpha"));
        system.sendMessage("GroupA", "Hi from Bravo", new User("U002", "Bravo"));
        system.sendMessage("GroupB", "Greetings from Charlie",
                new User("U003", "Charlie"));
        system.sendMessage("GroupB", "Greetings from Charlie",
                new User("U003", "Charlie"));
        system.sendMessage("GroupB", "Good morning from Delta",
                new User("U001", "Delta"));
        // Send more messages for further testing
        system.sendMessage("GroupA", "Another message from Alpha",
                new User("U001", "Alpha"));
        system.sendMessage("GroupA", "How are you, Bravo?",
                new User("U002", "Bravo"));
        system.sendMessage("GroupA", "How are you, Bravo?",
                new User("U002", "Bravo"));
        system.sendMessage("GroupA", "How are you, Bravo?",
                new User("U002", "Bravo"));
        system.sendMessage("GroupA", "How are you, Bravo? How are you, Bravo?",
                new User("U002", "Bravo"));
        system.sendMessage("GroupA", "How are you, Bravo? How are you, Bravo?",
                new User("U002", "Bravo"));

        // Find the most active user in GroupA
        User mostActiveUser = system.findMostActiveUser("GroupA");
        System.out.println("Most active user in GroupA: " +
                mostActiveUser.getUserName());

        // Compute the average number of messages per user in GroupA
        double avgMessages = system.getAverageMessagesPerUser("GroupA");
        System.out.println("Average messages per user in GroupA: " + avgMessages);

        // Retrieve the most frequent message in GroupA
        String mostFrequentMessage = system.getMostFrequentMessage("GroupA");
        System.out.println("Most frequent message in GroupA: " + mostFrequentMessage);
    }

}
