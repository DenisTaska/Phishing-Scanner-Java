package Main;


import AppWindows.MainApplicationWindow;

import javax.swing.text.BadLocationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        //Create main HashMap storing the risk point and secondary HashMaps
        Map<Integer, HashMap<String, Integer>> phishing_words = new HashMap<>();
        //Create secondary HashMaps storing the word and its occurrences
        Map<String, Integer> occurrences = new HashMap<>();
        int risk_points = 0;

        //Words with 1 risk points
        HashMap<String, Integer> level_1_risk = new HashMap<>();
        level_1_risk.put("Delivery", 0);
        level_1_risk.put("Public", 0);
        level_1_risk.put("Important", 0);
        level_1_risk.put("Security System", 0);
        level_1_risk.put("Your Password Expires in Less Than 24 Hours", 0);
        level_1_risk.put("Official Data Breach Notification", 0);

        //Words with 2 risk points
        HashMap<String, Integer> level_2_risk = new HashMap<>();
        level_2_risk.put("Vulnerability", 0);
        level_2_risk.put("Financial", 0);
        level_2_risk.put("Attacker", 0);
        level_2_risk.put("Fedex", 0);
        level_2_risk.put("Calculations", 0);
        level_2_risk.put("Important", 0);
        level_2_risk.put("Action required", 0);
        level_2_risk.put("Irregular Activity", 0);
        level_2_risk.put("Please read the sent files from Human Resources", 0);
        level_2_risk.put("Change of Password Required Immediately", 0);
        level_2_risk.put("To perform the verification", 0);



        //Words with 3 risk points
        HashMap<String, Integer> level_3_risk = new HashMap<>();
        level_3_risk.put("Postal", 0);
        level_3_risk.put("Label", 0);
        level_3_risk.put("Fedex", 0);
        level_3_risk.put("Document", 0);
        level_3_risk.put("Invoice", 0);
        level_3_risk.put("Immediately", 0);
        level_3_risk.put("Restrict Access", 0);
        level_3_risk.put("Login Credentials", 0);
        level_3_risk.put("Verification required", 0);
        level_3_risk.put("Update your Healthcare Info", 0);
        level_3_risk.put("Email Account Updates ", 0);
        level_3_risk.put("A Delivery Attempt was made ", 0);
        level_3_risk.put("Quick company survey", 0);

        phishing_words.put(1, level_1_risk);
        phishing_words.put(2, level_2_risk);
        phishing_words.put(3, level_3_risk);

        try {

            File myObj = new File("MyPhishingEmail.txt");
            Scanner sc = new Scanner(myObj);
            while (sc.hasNext()) {
                String data = sc.nextLine();
                //Tokenize each word and phrase found in the text file
                String[] words = data.split("\\\\s*");

                //loop every element inside hashmap
                for (HashMap.Entry<Integer, HashMap<String, Integer>> entry : phishing_words.entrySet()) {
                    //get the hashmaps that are inside each hashmap
                    for (String token : entry.getValue().keySet()) {
                        //loop every word in text file
                        for (String word : words) {
                            //compare the data of the file with the value of the elements inside the secondary HashMaps
                            if (word.toLowerCase().contains(token.toLowerCase())) {

                                risk_points = risk_points + entry.getKey();

                                //If token doesn't exist in occurrences, set value to 0 (default value)
                                occurrences.put(token, occurrences.getOrDefault(token, 0) + 1);
                            }
                        }
                    }
                }
            }
            sc.close();

            //Get final results on Terminal
            System.out.println(printResults(phishing_words, occurrences, risk_points));

            //Initialize main app window
            MainApplicationWindow application_window = new MainApplicationWindow();


            //Fill the TextArea2 with the final results
            application_window.print_text_field_results(printResults(phishing_words, occurrences, risk_points));


            //Highlight level 1 risk with Green
            for (String key : level_1_risk.keySet()) {
                application_window.findWordRisk1(key.toLowerCase());
            }

            //Highlight level 2 risk with Yellow
            for (String key : level_2_risk.keySet()) {
                application_window.findWordRisk2(key.toLowerCase());
            }

            //Highlight level 3 risk with Red
            for (String key : level_3_risk.keySet()) {
                application_window.findWordRisk3(key.toLowerCase());
            }


        } catch (FileNotFoundException | BadLocationException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Print results string.
     *
     * @param my_phishing_words the phishing words
     * @param my_occurrences    the occurrences
     * @param my_risk_points    the risk points
     * @return the string
     */
    public static String printResults(Map<Integer, HashMap<String, Integer>> my_phishing_words, Map<String, Integer> my_occurrences, int my_risk_points) {
        return printOccurrences(my_occurrences) + printListOfPhishingWords(my_phishing_words) + printRiskPercentage(my_risk_points);
    }

    /**
     * Print occurrences string.
     *
     * @param my_occurrences the occurrences
     * @return the string
     */
    public static String printOccurrences(Map<String, Integer> my_occurrences) {
        String a = ("List of Occurrences:" + "\n\n");
        String b = "";
        for (String key : my_occurrences.keySet()) {
            b = b.concat((key + " => " + my_occurrences.get(key))) + "\n";
        }
        return a + b + "\n";
    }

    /**
     * Print list of phishing words string.
     *
     * @param my_phishing_words the phishing words
     * @return the string
     */
    public static String printListOfPhishingWords(Map<Integer, HashMap<String, Integer>> my_phishing_words) {

        String c = ("\n" + "List of risk values related to words:" + "\n\n");
        String d = "";
        for (HashMap.Entry<Integer, HashMap<String, Integer>> entry : my_phishing_words.entrySet()) {
            for (String token : entry.getValue().keySet()) {
                d = d.concat(entry.getKey() + " = " + token + "\n");
            }
        }
        return c + d + "\n";
    }

    /**
     * Print risk percentage string.
     *
     * @param my_risk_points the risk points
     * @return the string
     */
    public static String printRiskPercentage(int my_risk_points) {

        String risk_level;

        if (my_risk_points < 3) {
            risk_level = "Low";
        } else if (my_risk_points < 7) {
            risk_level = "Medium";
        } else if (my_risk_points < 10) {
            risk_level = "High";
        } else
            risk_level = "Very High";

        return ("\n" + "Total risk points : " + my_risk_points + "\n" + "Total risk level: " + risk_level);
    }
}
