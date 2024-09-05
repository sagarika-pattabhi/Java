/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hangman1;

import java.awt.Color;
import static java.awt.Color.blue;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Set;
/**
 *
 * @author sagar
 */
public class Hangman1 {
 static List<String> phrases=new ArrayList<String>(); 
    static JFrame jFrame=new JFrame("1bg21ai092"); 
    static JLabel label=new JLabel(""); 
    static JLabel complementaryLabel=new JLabel(""); 
    static JTextField textArea=new JTextField("");
    static char userInput;
    static JButton button=new JButton("Enter");
    static boolean wordIsGuessed = false;
    static boolean buttonPressed=false;
         public static void main(String[] args) {
        jFrame.setResizable(true);
        jFrame.setLayout(null);
        jFrame.setSize(1400, 1000);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       label.setBounds(350, 450, 250, 150);
       complementaryLabel.setBounds(100, 500, 500, 100);
       complementaryLabel.setFont(new Font("timesnewroman", Font.BOLD, 30));
        textArea.setBounds(375, 600, 150, 25);
        textArea.setFocusable(true);
      button.setBounds(550, 600, 100, 25);
      ImageIcon icon = new ImageIcon("D:\\APACHE NET BEANS\\projects\\Hangman1\\image\\yes.png");

      JLabel labell = new JLabel(icon);
        labell.setBounds(10,10,1200,450);
        jFrame.setBackground(blue);
        jFrame.add(label);
        jFrame.add(complementaryLabel);
        jFrame.add(textArea);
        jFrame.add(button);
        jFrame.add(labell);
       
        String[] words = {"writer", "that", "program", "nervous","clarinet","bnmit","bottle","artificial"," intelligence","jump","run",""};

        int randomWordNumber = (int) (Math.random() * words.length);
        char[] enteredLetters=new char[words[randomWordNumber].length()];
        int triesCount = 0;

        button.addActionListener((ActionEvent e) -> {
            CheckString();
        });
       
        textArea.addActionListener(button.getActionListeners()[0]);
        do {
       
        switch (enterLetter(words[randomWordNumber], enteredLetters)) {
            case 0:
                triesCount++;
                break;
            case 1:
                triesCount++;
                break;
            case 2:
                break;
            case 3:
                wordIsGuessed = true;
                button.setVisible(false);
                textArea.setVisible(false);
                break;
        }
        } while (! wordIsGuessed);

        label.setText("\nThe word is " + words[randomWordNumber] +
            ". You missed " + (triesCount -findEmptyPosition(enteredLetters)) +
            " time(s).");
        
        complementaryLabel.setText("");
    }

    
    public static int enterLetter(String word, char[] enteredLetters)    {
        phrases.clear();
        phrases.add("Guess a letter !!   ");
       
        if (! printWord(word, enteredLetters))
            return 3;

       
        String sentence="";
        for (int i=0; i<phrases.size(); i++) {
            sentence+=phrases.get(i);
        }
       label.setText(sentence);
        int emptyPosition = findEmptyPosition(enteredLetters);

       if(buttonPressed) {
        if (inEnteredLetters(userInput, enteredLetters)) {
            complementaryLabel.setText(userInput+" is already in the word");
            buttonPressed=false;

            return 2;
        }
        else if (word.contains(String.valueOf(userInput))) {
            enteredLetters[emptyPosition] = userInput;
            complementaryLabel.setText("");
            complementaryLabel.setFont(new Font("timesnewroman", Font.BOLD, 30));
            buttonPressed=false;

            return 1;
        }
        else {
            complementaryLabel.setText(userInput + " is not present in the word");
            buttonPressed=false;

            return 0;
            }

       }
       return 2;

    }


    public static boolean printWord(String word, char[] enteredLetters) {
        boolean asteriskPrinted = false;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (inEnteredLetters(letter, enteredLetters)) {
                phrases.add(String.valueOf(letter));
            }
            else {
                phrases.add("-"); 
                asteriskPrinted = true;
            }
        }
        return asteriskPrinted;
    }
  
    public static void CheckString() {
           
            try {
             userInput=textArea.getText().charAt(0);
            }
            catch(Exception err) {
                return;
            }
             if(userInput!=' ') {
                 buttonPressed=true;
                
                textArea.setText(null);
             }
    }
    public static boolean inEnteredLetters(char letter, char[] enteredLetters) {
        return new String(enteredLetters).contains(String.valueOf(letter));
    }

    public static int findEmptyPosition(char[] enteredLetters) {
        int i = 0;

        for(int iterator=0; iterator<enteredLetters.length; iterator++) { 
            if(enteredLetters[iterator]=='\u0000') break;
            i++;
        }
        return i;
    }
}