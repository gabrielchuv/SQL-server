package DBServer;

import DBExceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testCommandType() throws InvalidTokenException {
        String testCommand = "SELECT name FROM elections;";
        String output = "Parsed OK";
        Parser parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        //System.out.println("SEPARATOR!");
        testCommand = "SELECT * FROM marks;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

       // System.out.println("SEPARATOR!");
        testCommand = "SELECT * FROM marks WHERE name != 'Dave';";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        testCommand = "SELECT * FROM marks WHERE pass == true;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT * FROM marks WHERE name == 'Clive';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = "SELECT id FROM movies WHERE name == 'Mickey Blue Eyes';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT id FROM movies WHERE name == 'About a Boy';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = "SELECT id FROM movies WHERE name == 'Sense and Sensibility';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT id FROM actors WHERE name == 'Hugh Grant';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT id FROM actors WHERE name == 'Toni Collette';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT id FROM actors WHERE name == 'James Caan';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT id FROM actors WHERE name == 'Emma Thompson';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT * FROM actors WHERE name == 'Hugh Grant';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT * FROM actors WHERE awards < 5;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = "SELECT * FROM actors WHERE awards >= 10;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = "SELECT * FROM actors WHERE awards >= 10.19;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT * FROM actors WHERE (awards > 5) AND (nationality == 'British');";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT * FROM actors WHERE name LIKE 'an';";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "SELECT * FROM actors WHERE (awards > 5) AND ((nationality == 'British') OR (nationality == 'Australian'));";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "USE elections;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "CREATE DATABASE elections;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = "CREATE TABLE elections;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = "CREATE TABLE marks (name, mark, pass);";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        testCommand = "CREATE TABLE actors (name, nationality, awards);";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "CREATE TABLE roles (name, movieId, actorId);";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        testCommand = "DROP DATABASE elections;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "DROP TABLE elections;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        testCommand = "ALTER TABLE elections ADD marks;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);



        testCommand = "ALTER TABLE actors ADD age;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);



        testCommand = "INSERT INTO elections VALUES ('gabo');";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        testCommand = "INSERT INTO elections VALUES ('gabo',23);";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "INSERT INTO elections VALUES ('gabo',23,   44.44,  false);";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "INSERT INTO marks VALUES ('Steve', 65, true);";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        testCommand = "INSERT INTO actors VALUES ('Emma Thompson', 'British', 10);";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "INSERT INTO movies VALUES ('Sense and Sensibility', 'Period Drama');";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        testCommand = "INSERT INTO roles VALUES ('Elinor', 3, 4);";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        /* UPDATE  */

        testCommand = "UPDATE elections SET mark=15 WHERE age!= 12;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "UPDATE elections SET mark=15, gabo = 22 WHERE age!= 12;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "UPDATE actors SET age = 45 WHERE name == 'Hugh Grant';";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        /* DELETE */

        testCommand = "DELETE FROM cast WHERE age == 14;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "DELETE FROM marks WHERE name == 'Dave';";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "DELETE FROM actors WHERE name == 'Hugh Grant';";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        /* JOIN */

        testCommand = "JOIN elections AND cast ON id AND castName;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "JOIN actors AND roles ON id AND actorId;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        testCommand = "JOIN movies AND roles ON id AND movieId;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

    }

    @Test
    void testAttributeList() throws InvalidTokenException {
        String testCommand = "SELECT name,age,colour FROM elections;";
        String output = "Parsed OK";
        Parser parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);

        testCommand = "SELECT name, age, colour FROM elections;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "SELECT name , age , colour FROM elections;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


        testCommand = "SELECT name , a3e , colour FROM elections;";
        parser = new Parser(testCommand);
        //parser.command();
        assertEquals(parser.command(), output);


    }


    @Test
    void testExceptionsAttributeList()  {

        /* Wrong "age" */
       /* String testCommand = "SELECT name , a!e , colour FROM elections;";
        Exception exception = assertThrows(NonAlphaNumericException.class, () -> {
            Parser parser = new Parser(testCommand);
            parser.command();
            //assertEquals(parser.command(), output);
        });
        String expectedMessage = "Non alpha-numeric token:";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* Wrong: "elections" */
        /*String testCommand2 = "SELECT name , age , colour FROM ele?ctions;";
        exception = assertThrows(NonAlphaNumericException.class, () -> {
            Parser parser = new Parser(testCommand2);
            parser.command();
            //assertEquals(parser.command(), output);
        });
        expectedMessage = "Non alpha-numeric token:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));*/

        /* No "FROM" */
        String testCommand3 = "SELECT name , age , colour ele?ctions;";
        Exception exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand3);
            parser.command();
            //assertEquals(parser.command(), output);
        });
        String expectedMessage = "Invalid keyword:";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* No "select" */
        String testCommand4 = " name , age , colour ele?ctions;";
        exception = assertThrows(InvalidCommandException.class, () -> {
            Parser parser = new Parser(testCommand4);
            parser.command();
            //assertEquals(parser.command(), output);
        });
        expectedMessage = "Invalid command:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Missing comma */
        String testCommand5 = " SELECT name , age  colour ele?ctions;";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand5);
            parser.command();
            //assertEquals(parser.command(), output);
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testValue() throws InvalidTokenException {
        /* Testing string literal */
        String testCommand = " SELECT name , age, colour FROM elections WHERE age=='age';";
        Tokenizer tokenizer = new Tokenizer(testCommand);
        System.out.println(tokenizer.getTokens());
        Parser parser = new Parser(testCommand);
        String output = "Parsed OK";
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE age== 'age' ;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = " SELECT name , age, colour FROM elections WHERE age=='age' ;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE age==  'ag!-e ' ;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE age==  TRUE;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE age==  FALSE;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE age==  24;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE age==  24.55;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);
    }


    @Test
    void testOperator() throws InvalidTokenException {
        String testCommand = " SELECT name , age, colour FROM elections WHERE age>'age';";
        Parser parser = new Parser(testCommand);
        String output = "Parsed OK";
        assertEquals(parser.command(), output);

        testCommand = " SELECT name , age, colour FROM elections WHERE age<  24.55;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = " SELECT name , age, colour FROM elections WHERE age <=  24.55;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE age >=24.55;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE age   !=    24.55;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = " SELECT name , age, colour FROM elections WHERE age LIKE   24.55;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);
    }

    @Test
    void testCondition() throws InvalidTokenException {
        String testCommand = " SELECT name , age, colour FROM elections WHERE (age== 'age') AND (name >= 22.34);";
        Parser parser = new Parser(testCommand);
        String output = "Parsed OK";
        assertEquals(parser.command(), output);

        testCommand = " SELECT name , age, colour FROM elections WHERE ( age LIKE   24.55) AND (name!='hola'   );";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

        testCommand = " SELECT name , age, colour FROM elections WHERE age LIKE   24.55;";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE (age LIKE   24.55) OR (name==23);";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE (age !=   24.55) OR (name==23) OR (colour>= 23);";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE (age LIKE 24.55) AND (name==23) AND (colour!= 'sup');";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);


        testCommand = " SELECT name , age, colour FROM elections WHERE (age LIKE   24.55) OR (name==23) AND (col == 55.55);";
        parser = new Parser(testCommand);
        assertEquals(parser.command(), output);

    }


    @Test
    void testExceptionsValue() {

        /* String - Missing apostrophe */
        String testCommand = " SELECT name , age , colour FROM elections WHERE age == age';";
        Exception exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand);
            parser.command();
        });
        String expectedMessage = "Invalid value literal:";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* String - Missing apostrophe */
        String testCommand2 = " SELECT name , age , colour FROM elections WHERE age == 'age;";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand2);
            parser.command();
        });
        //expectedMessage = "Invalid value literal:";
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));



        /* String - using tab */
       /* String testCommand3 = " SELECT name , age , colour FROM elections WHERE age == 'a   e';";
        Tokenizer tokenizer = new Tokenizer(testCommand3);
        System.out.println(tokenizer.getTokens());
        exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand3);
            parser.command();
        });
        expectedMessage = "Invalid value literal:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

*/

        /* String - single quote in the middle of stringliteral */
       /* String testCommand4 = " SELECT name , age , colour FROM elections WHERE age == 'a''e';";
        exception = assertThrows(InvalidDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand4);
            parser.command();
        });
        expectedMessage = "Invalid delimiter:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));*/


        /* Float - nothing behind the point */
        String testCommand5 = " SELECT name , age , colour FROM elections WHERE age == .23;";
        exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand5);
            parser.command();
        });
        expectedMessage = "Invalid value literal:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Float - two points */
        String testCommand6 = " SELECT name , age , colour FROM elections WHERE age == 23.2.3;";
        exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand6);
            parser.command();
        });
        expectedMessage = "Invalid value literal:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));



        /* Int - chars in the middle */
        String testCommand7 = " SELECT name , age , colour FROM elections WHERE age == 23a3;";
        exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand7);
            parser.command();
        });
        expectedMessage = "Invalid value literal:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));



        /* Int - chars in the middle */
        String testCommand8 = " SELECT name , age , colour FROM elections WHERE age == 23.ga;";
        exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand8);
            parser.command();
        });
        expectedMessage = "Invalid value literal:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void testExceptionsOperators() {
        String testCommand = " SELECT name , age , colour FROM elections WHERE age =! age';";
        Exception exception = assertThrows(InvalidOperatorException.class, () -> {
            Parser parser = new Parser(testCommand);
            parser.command();
        });
        String expectedMessage = "Invalid operator:";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        String testCommand2 = " SELECT name , age , colour FROM elections WHERE age === 23;";
        exception = assertThrows(InvalidOperatorException.class, () -> {
            Parser parser = new Parser(testCommand2);
            parser.command();
        });
        expectedMessage = "Invalid operator:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        String testCommand3 = " SELECT name , age , colour FROM elections WHERE age =!= 23;";
        exception = assertThrows(InvalidOperatorException.class, () -> {
            Parser parser = new Parser(testCommand3);
            parser.command();
        });
        expectedMessage = "Invalid operator:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        String testCommand4 = " SELECT name , age , colour FROM elections WHERE age >== 23;";
        exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand4);
            parser.command();
        });
        expectedMessage = "Invalid value literal:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testExceptionCondition() {
        /* WRONGLY SPELLED AND */
        String testCommand = " SELECT name , age , colour FROM elections WHERE age != 'age' AN name == 23;";
        Exception exception = assertThrows(InvalidDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand);
            parser.command();
        });
        String expectedMessage = "Invalid delimiter:";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* WRONGLY SPELLED OR */
        String testCommand2 = " SELECT name , age , colour FROM elections WHERE age != 'age' O name == 23;";
        exception = assertThrows(InvalidDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand2);
            parser.command();
        });
        expectedMessage = "Invalid delimiter:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* MISSING "AND" OR "OR" */
        String testCommand3 = " SELECT name , age , colour FROM elections WHERE age != 'age' name == 23;";
        exception = assertThrows(InvalidDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand3);
            parser.command();
        });
        expectedMessage = "Invalid delimiter:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* MISSING BRACKET */
        String testCommand4 = " SELECT name , age , colour FROM elections WHERE (age != 'age') AND (name == 23;";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand4);
            parser.command();
        });
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* MISSING BRACKET */
        String testCommand5 = " SELECT name , age , colour FROM elections WHERE (age != 'age') AND name == 23);";
        exception = assertThrows(InvalidDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand5);
            parser.command();
        });
        expectedMessage = "Invalid delimiter:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* MISSING BRACKET */
        String testCommand6 = " SELECT name , age , colour FROM elections WHERE age != 'age') AND (name == 23);";
        exception = assertThrows(InvalidDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand6);
            parser.command();
        });
        expectedMessage = "Invalid delimiter:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCommandTypeExceptions() {
        String testCommand = "SELECT * FROM actors";
        Exception exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand);
            parser.command();
        });
        String expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* rogue parenthesis */
        String testCommand2 = "SELECT * FROM actors);";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand2);
            parser.command();
        });
        expectedMessage = "Invalid keyword";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* MISSING APOSTROPHE */
        String testCommand3 = "SELECT * FROM actors WHERE name == 'Hugh Grant;";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand3);
            parser.command();
        });
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* MISSING COMMA */
        String testCommand4 = "SELECT name age FROM actors;";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand4);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* MISSING "WHERE" KEYWORD */
        String testCommand5 = "SELECT * FROM actors awards > 10;";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand5);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* CREATE */

        /* non alphanumeric database name */
        String testCommand6 = "CREATE DATABASE name!";
        exception = assertThrows(NonAlphaNumericException.class, () -> {
            Parser parser = new Parser(testCommand6);
            parser.command();
        });
        expectedMessage = "Non alpha-numeric token:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* missing opening bracket */
        String testCommand7 = "CREATE TABLE name, age, respect);";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand7);
            parser.command();
        });
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* missing closing bracket */

        System.out.println();
        System.out.println();
        System.out.println();
        String testCommand8 = "CREATE TABLE marks (name, age, respect;";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand8);
            parser.command();
        });
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Wrong keyword - not DATABASE nor TABLE */
        String testCommand9 = "CREATE TAB (name, age, respect);";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand9);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* ALTER */

        /* Wrong table name */
        String testCommand10 = "ALTER table el!ections ADD marks";
        exception = assertThrows(NonAlphaNumericException.class, () -> {
            Parser parser = new Parser(testCommand10);
            parser.command();
        });
        expectedMessage = "Non alpha-numeric token:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* Wrong ALTERATION TYPE */
        String testCommand11 = "ALTER table elections ADDD marks";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand11);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* INSERT */

        /* MISSING "INTO" */
        String testCommand12 = "INSERT elections VALUES ('gabo',23,   44.44,  false);";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand12);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* MISSING "VALUES" */
        String testCommand13 = "INSERT INTO elections ('gabo',23,   44.44,  false);";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand13);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* MISSING "(" */
        String testCommand14 = "INSERT INTO elections VALUES 'gabo',23,   44.44,  false);";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand14);
            parser.command();
        });
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* MISSING ")" */
        String testCommand15 = "INSERT INTO elections VALUES ('gabo',23,   44.44,  false;";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand15);
            parser.command();
        });
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Wrong int value */
        String testCommand16 = "INSERT INTO elections VALUES ('gabo',23a,   44.44,  false);";
        exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand16);
            parser.command();
        });
        expectedMessage = "Invalid value literal:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Wrong float value */
        String testCommand17 = "INSERT INTO elections VALUES ('gabo',23a,   44.4.4,  false);";
        exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand17);
            parser.command();
        });
        expectedMessage = "Invalid value literal:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));



        /* Wrong boolean value */
        String testCommand18 = "INSERT INTO elections VALUES ('gabo',23a,   44.44,  fals);";
        exception = assertThrows(InvalidValueLiteral.class, () -> {
            Parser parser = new Parser(testCommand18);
            parser.command();
        });
        expectedMessage = "Invalid value literal:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));



        /* Missing apostrophe */
        String testCommand19 = "INSERT INTO elections VALUES ('gabo,23a,   44.44,  false);";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand19);
            parser.command();
        });
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* UPDATE */

        /* Missing semi colon at the end */
        String testCommand20 = "UPDATE elections SET mark=15 WHERE age!= 12";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand20);
            parser.command();
        });
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Missing "Update" */
        String testCommand21 = "elections SET mark=15 WHERE age!= 12";
        exception = assertThrows(InvalidCommandException.class, () -> {
            Parser parser = new Parser(testCommand21);
            parser.command();
        });
        expectedMessage = "Invalid command:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Missing "SET" */
        String testCommand22 = "UPDATE elections  mark=15 WHERE age!= 12";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand22);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Missing "WHERE" */
        String testCommand23 = "UPDATE elections SET mark=15 age!= 12";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand23);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Missing "=" in nameValuePair" */
        String testCommand24 = "UPDATE elections SET mark 15 WHERE age!= 12";
        exception = assertThrows(InvalidOperatorException.class, () -> {
            Parser parser = new Parser(testCommand24);
            parser.command();
        });
        expectedMessage = "Invalid operator:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Missing comma in NameValueList */
        String testCommand25 = "UPDATE elections SET mark=15,gabo=true,alex='al' ask=12 WHERE age!= 12";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand25);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* DELETE */

        /* Missing "FROM" */
        String testCommand26 = "DELETE ACTORS where age == 12;";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand26);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Missing "WHERE" */
        String testCommand27 = "DELETE from ACTORS age == 12;";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand27);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

        /* Missing semi colon at the end" */
        String testCommand28 = "DELETE FROM ACTORS where age == 12";
        exception = assertThrows(MissingDelimiterException.class, () -> {
            Parser parser = new Parser(testCommand28);
            parser.command();
        });
        expectedMessage = "Are you missing an apostrophe, parenthesis or semicolon?";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* JOIN  */


        /* Missing first AND */
        String testCommand29 = "JOIN ask tom ON id AND castName;";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand29);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Missing ON */
        String testCommand30 = "JOIN ask AND tom  id AND castName;";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand30);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));


        /* Missing second AND */
        String testCommand31 = "JOIN ask AND tom  ON id  castName;";
        exception = assertThrows(InvalidKeywordException.class, () -> {
            Parser parser = new Parser(testCommand31);
            parser.command();
        });
        expectedMessage = "Invalid keyword:";
        actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));

    }

}


