/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roguelikeengine.stat;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Greg
 */
public class EquationStat implements Stat {
    
    private StatContainer container;    
    private int baseScore, curScore;
    private String equation;

    public EquationStat(String string) {
        equation = string;
    }
    
    /* This class is intended for stats that have complicated equations behind 
    them, like hitpoints. The equation will be performed in linear order, not 
    the proper order of operations. Stats should be bouned by @ on either side. 
    There doesn't strictly need to be a space between stats and operators, but 
    there probably should be, for easier reading by humans.
    */
    
    private int compute(String equation) throws ParseException, NoSuchStatException {
        int cur = 0, next = 0;
        char operator = '#', curChar;
        for (int i = 0; i < equation.length(); i++) {
            curChar = equation.charAt(i);
            switch (curChar) {
                case '@': 
                    String stat = "";
                    while (equation.charAt(i+1) != '@') {
                        stat += equation.charAt(i+1);
                        i++;
                    }
                    i++;
                    next = (int) container.getScore(stat);
                    break;
                    
                case '(':
                    int numParens = 0;
                    int start = i;
                    parenLoop:
                    for (; i < equation.length() ; i++) {
                        curChar = equation.charAt(i);
                        switch (curChar) {
                            case '(': numParens++;
                            case ')': if (numParens > 0) numParens--;
                            else {
                                next = compute(equation.substring(start, i));
                                break parenLoop;
                            }
                            default: break;
                        }
                    }
                    
                case ' ': 
                case '\n':
                case '\r': 
                    break;
                    
                case '+':
                case '-':
                case '*':
                case '/':
                    switch(operator) {
                        case '#': cur = next;
                        case '+': cur += next;
                        case '-': cur -= next;
                        case '*': cur *= next;
                        case '/': cur /= next;
                    }
                    operator = curChar;
                    next = 0;
                    break;
                    
                case '0':
                    next *= 10;
                    break;
                case '1':
                    next *= 10;
                    next += 1;
                    break;
                case '2':
                    next *= 10;
                    next += 2;
                    break;
                case '3':
                    next *= 10;
                    next += 3;
                    break;
                case '4':
                    next *= 10;
                    next += 4;
                    break;
                case '5':
                    next *= 10;
                    next += 5;
                    break;
                case '6':
                    next *= 10;
                    next += 6;
                    break;
                case '7':
                    next *= 10;
                    next += 7;
                    break;
                case '8':
                    next *= 10;
                    next += 8;
                    break;
                case '9':
                    next *= 10;
                    next += 9;
                    break;
            }
        }
        
        return cur;
    }

    @Override
    public int getScore() {
        return curScore;
    }

    @Override
    public void setContainer(StatContainer i) {
        this.container = i;
    }

    @Override
    public void refactor() throws NoSuchStatException {
        try {
            baseScore = compute(equation);
            curScore = baseScore;
        } catch (ParseException ex) {
            Logger.getLogger(EquationStat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modify(float change) {
        curScore += change;
    }

    @Override
    public void modifyBase(float change) {
        baseScore += change;
    }

    @Override
    public Stat copy() {
        return new EquationStat(equation);
    }
    
}
