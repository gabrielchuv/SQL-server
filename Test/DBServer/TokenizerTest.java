package DBServer;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    /*
    @Test
    void testRemoveBlanksSpaces() {
        ArrayList<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("==");expectedOutput.add("15");expectedOutput.add(";");


        String testCommand = "SELECT * FROM elections WHERE age == 15 ;";
        //String testCommand = "a; b; c; d";
        Tokenizer tokenizer = new Tokenizer();
        //testOutput = tokenizer.removeBlankSpaces(testCommand);

        //System.out.println(expectedOutput);
        //System.out.println(Arrays.toString(tokenizer.removeBlankSpaces(testCommand)));

        assertArrayEquals(tokenizer.removeBlankSpaces(testCommand), expectedOutput.toArray());
    }*/

    @Test
    void testSplitWithDelimiters() {
        ArrayList<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("==");expectedOutput.add("15");expectedOutput.add(";");

        String testCommand = "SELECT * FROM elections WHERE age == 15 ;";

        Tokenizer tokenizer = new Tokenizer(testCommand);
        System.out.println(expectedOutput);
        System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());
       // System.out.println(tokenizer.getTokens());


        /* reset */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("==");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections; WHERE age == 15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());

        /* reset - string literals 'Mikey Mouse'*/
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("!=");expectedOutput.add("'");expectedOutput.add("Mickey Mouse");expectedOutput.add("'");
        expectedOutput.add(";");
        /* reset */

        testCommand = " SELECT*FROM elections WHERE age!='Mickey Mouse';  ";

        tokenizer = new Tokenizer(testCommand);
        System.out.println(expectedOutput);
        System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("==");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections ; WHERE age == 15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());



        /* reset - MANY SPACES */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("==");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections   ; WHERE age == 15;";

        tokenizer = new Tokenizer(testCommand);
        System.out.println(expectedOutput);
        System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - "==" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("==");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections   ; WHERE age== 15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());

        /* reset - ">" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add(">");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections   ; WHERE age> 15;";

        tokenizer = new Tokenizer(testCommand);
        System.out.println(expectedOutput);
        System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - ">" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add(">");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections   ; WHERE age   >   15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - ">" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add(">");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections   ; WHERE age   >15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());

        /* reset - "<" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("<");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections   ; WHERE age<15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());

        /* reset - "<=" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("<=");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections; WHERE age<=15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - ">=" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add(">=");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections   ; WHERE age  >=  15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());



        /* reset - "!=" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("!=");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = "SELECT * ;FROM elections   ; WHERE age!=15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - "," */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(",");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(",");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("!=");expectedOutput.add("15");expectedOutput.add(",");
        /* reset */

        testCommand = "SELECT * ,FROM elections , WHERE age!=15,";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - "empty space at the start of string" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("!=");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = " SELECT * ;FROM elections ; WHERE age!=15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());



        /* reset - "empty space at the start and end of string" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");expectedOutput.add(";");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("!=");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = " SELECT * ;FROM elections ; WHERE age!=15;  ";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - "*" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("!=");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = " SELECT *FROM elections ; WHERE age!=15;  ";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - "*" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add(";");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("!=");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = " SELECT*FROM elections ; WHERE age!=15;  ";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - "*" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add("WHERE");expectedOutput.add("(");
        expectedOutput.add("age");expectedOutput.add("!=");expectedOutput.add("15");expectedOutput.add(")");
        expectedOutput.add("and");expectedOutput.add("(");expectedOutput.add("age");expectedOutput.add("==");expectedOutput.add("12");
        expectedOutput.add(")");expectedOutput.add(";");
        /* reset */

        testCommand = " SELECT*FROM elections WHERE (age!=15) and (age == 12);";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - "!=" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add("WHERE");
        expectedOutput.add("age");expectedOutput.add("!=");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = " SELECT*FROM elections WHERE age!=15;";

        tokenizer = new Tokenizer(testCommand);
        //System.out.println(expectedOutput);
        //System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - "=" */
        expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add("WHERE");
        expectedOutput.add("age");expectedOutput.add("=");expectedOutput.add("15");expectedOutput.add(";");
        /* reset */

        testCommand = " SELECT*FROM elections WHERE age=15;";

        tokenizer = new Tokenizer(testCommand);
        System.out.println(expectedOutput);
        System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());


        /* reset - string literals 'Mikey Mouse'*/
      /*  expectedOutput.clear();
        expectedOutput.add("SELECT");expectedOutput.add("*");
        expectedOutput.add("FROM");
        expectedOutput.add("elections");expectedOutput.add("WHERE");expectedOutput.add("age");
        expectedOutput.add("!=");expectedOutput.add("'");expectedOutput.add("Mickey Mouse");expectedOutput.add("'");
        expectedOutput.add(";");
        /* reset */

        /*testCommand = " SELECT*FROM elections WHERE age!='Mickey Mouse';  ";

        tokenizer = new Tokenizer(testCommand);
        System.out.println(expectedOutput);
        System.out.println(tokenizer.getTokens());
        assertArrayEquals(expectedOutput.toArray(), tokenizer.getTokens().toArray());*/



    }

}