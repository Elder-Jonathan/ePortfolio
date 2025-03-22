package edu.au.cpsc.module3;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Software Construction Fundamentals Module 3
 * @author Jonathan Elder
 * Airport.java
 */
public class Airport {

    private final String ident;
    private final String iataCode;
    private final String localCode;
    private final String type;
    private final String name;
    private final Integer elevationInFeet;
    private final String country;
    private final String region;
    private final String municipality;
    private final Double coordinatesLongitude;
    private final Double coordinatesLatitude;

    // Constructor for creating an Airport object.
    public Airport(String ident, String iataCode, String localCode, String type, String name,
                   Integer elevationInFeet, String country, String region, String municipality,
                   Double coordinatesLongitude, Double coordinatesLatitude) {
        this.ident = ident;
        this.iataCode = iataCode;
        this.localCode = localCode;
        this.type = type;
        this.name = name;
        this.elevationInFeet = elevationInFeet;
        this.country = country;
        this.region = region;
        this.municipality = municipality;
        this.coordinatesLongitude = coordinatesLongitude;
        this.coordinatesLatitude = coordinatesLatitude;
    }
    // Getter methods for all variables of the "Airport" class.
    public String getIdent() { // Getter method for "ident" variable for a given Airport object.
        return ident;
    }
    public String getIataCode() { // Getter method for "iataCode" variable for a given Airport object.
        return iataCode;
    }
    public String getLocalCode() { // Getter method for "localCode" variable for a given Airport object.
        return localCode;
    }
    public String getType() { // Getter method for "type" variable for a given Airport object.
        return type;
    }
    public String getName() { // Getter method for "name" variable for a given Airport object.
        return name;
    }
    public Integer getElevationInFeet() { // Getter method for "elevationInFeet" variable for a given Airport object.
        return elevationInFeet;
    }
    public String getCountry() { // Getter method for "country" variable for a given Airport object.
        return country;
    }
    public String getRegion() { // Getter method for "region" variable for a given Airport object.
        return region;
    }
    public String getMunicipality() { // Getter method for "municipality" variable for a given Airport object.
        return municipality;
    }
    public Double getCoordinatesLongitude() { // Getter method for longitude variable for a given Airport object.
        return coordinatesLongitude;
    }
    public Double getCoordinatesLatitude() { // Getter method for latitude variable for a given Airport object.
        return coordinatesLatitude;
    }

    /**
     * Reads in data from a csv resource stream, and creates "Airport" objects.
     * @return A List of all created Airport objects from the CSV file.
     * @throws IOException When an error reading in file or when the file is not found.
     */
    public static List<Airport> readAll() throws IOException {
        List<Airport> airports = new ArrayList<>();

        // Locates the airportcodes.csv in the "Resources" directory.
        InputStream is = Airport.class.getResourceAsStream("/edu/au/cpsc/module3/airportcodes.csv");
        if (is == null) {
            throw new IOException("Resource can not be located: airportcodes.csv");
        }

        try (
                InputStreamReader reader = new InputStreamReader(is);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) { // How to parse each column of the csv file.
            for (CSVRecord csvRecord : csvParser) {
                String ident = csvRecord.get("ident");
                String iataCode = csvRecord.get("iata_code");
                String localCode = csvRecord.get("local_code");
                String type = csvRecord.get("type");
                String name = csvRecord.get("name");
                Integer elevationInFeet = csvRecord.get("elevation_ft").isEmpty()
                        ? null
                        : Integer.parseInt(csvRecord.get("elevation_ft"));
                String country = csvRecord.get("iso_country");
                String region = csvRecord.get("iso_region");
                String municipality = csvRecord.get("municipality");

                // Uses regex for all delimiters to split coordinate values and trims all whitespace.
                String coordinatesStr = csvRecord.get("coordinates").trim();
                String[] coordinates = coordinatesStr.split(",\\s*");
                Double coordinatesLongitude = Double.parseDouble(coordinates[0]);
                Double coordinatesLatitude = Double.parseDouble(coordinates[1]);
                // For each row of the CSV file create a new airport object
                airports.add(new Airport(ident, iataCode, localCode, type, name, elevationInFeet,
                        country, region, municipality, coordinatesLongitude, coordinatesLatitude));
            }
        }

        return airports; // Returns all created airport objects from CSV file.
    }
}