/**
* Intro to Computer Science 1220 II - Project 4
* @author Jonathan Elder
* @date   9/16/2022
* Derived from SharedCloud >> CloudStorage. Creates a PersonalCloud storage object.
*/
public class PersonalCloud extends SharedCloud {

    // Constant variable for the cost factor of PersonalCloud class.
    public static final double COST_FACTOR = 3.0;
   
    /**
    * Constructor to initialize a PersonalCloud object.
    * @param nameIn The user defined PersonalCloud name.
    * @param baseStorageCostIn The user defined base storage cost of a PersonalCloud object.
    * @param dataStoredIn The user defined current amount of data stored for a PersonalCloud object.
    * @param dataLimitIn The user defined storage limit of the created PersonalCloud object.
    */
    public PersonalCloud(String nameIn, double baseStorageCostIn,
        double dataStoredIn, double dataLimitIn) {
        super(nameIn, baseStorageCostIn, dataStoredIn, dataLimitIn);
    }

    /**
    * Getter for the cost factor constant for a PersonalCloud object.
    * @return The cost factor constant for the PersonalCloud object.
    */
    @Override
    public double getCostFactor() {
        return PersonalCloud.COST_FACTOR;
    }

    /**
    * Method to calculate the monthly cost for a PersonalCloud object.
    * @return The total monthly cost including overage fees.
    */
    @Override // SharedCloud method version is being overridden.
    public double monthlyCost() {
        return baseStorageCost + dataOverage() * PersonalCloud.COST_FACTOR;
    }
}