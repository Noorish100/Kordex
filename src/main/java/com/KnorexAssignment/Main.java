package com.KnorexAssignment;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        VASTParser parser = new VASTParser();
        VASTRepository repository = new VASTRepository();

        try {
            // Example of reading from a file
//            String xmlContent = parser.readXmlFromFile("path/to/your/vast.xml");

            // Example of reading from a URL
             String xmlContent = parser.readXmlFromUrl("https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml");

            // Parsing the XML content into a VASTTag object
            VASTTag vastTag = parser.parseFromXml(xmlContent);

            // Convert to JSON and print it
            System.out.println("Parsed JSON:\n" + vastTag.toJson().toString(2));

            // Save the parsed data to MySQL
            repository.save(vastTag);

            // Retrieve and print the stored data
            VASTTag retrievedTag = repository.findById(vastTag.getId());
            if (retrievedTag != null) {
                System.out.println("Retrieved JSON from DB:\n" + retrievedTag.toJson().toString(2));
            } else {
                System.out.println("No VAST Tag found with ID: " + vastTag.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}