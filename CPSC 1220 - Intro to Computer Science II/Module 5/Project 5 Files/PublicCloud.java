/**
* Intro to Computer Science 1220 II - Project 4
* @author Jonathan Elder
* @date   9/15/2022
* Derived from the SharedCloud >> CloudStorage classes. Creates a PublicCloud storage object.
*/
public class PublicCloud extends SharedCloud {

    
    // Constant variable for the cost factor of PublicCloud class.
    public static final double COST_FACTOR = 2.0;
   
    /**
    * Constructor to initialize a PublicCloud object.
    * @param nameIn The user defined PublicCloud name.
    * @param baseStorageCostIn The user defined base storage cost of this PublicCloud object.
    * @param dataStoredIn The user defined current amount of data stored in this PublicCloud object.
    * @param dataLimitIn The user defined storage limit of this PublicCloud object.
    */
    public PublicCloud(String nameIn, double baseStorageCostIn,
        double dataStoredIn, double dataLimitIn) {
        super(nameIn, baseStorageCostIn, dataStoredIn, dataLimitIn);
    }

    /**
    * Getter of the constant cost factor variable for a PublicCloud object.
    * @return The cost factor constant for a PublicCloud object.
    */
    @Override
    public double getCostFactor() {
        return PublicCloud.COST_FACTOR;
    }

    /**
    * Method to calculate the monthly cost for a PublicCloud object.
    * @return The total monthly cost including overage fees of the PublicCloud object.
    */
    @Override // SharedCloud method version is being overridden.
    public double monthlyCost() {
        return baseStorageCost + dataOverage() * PublicCloud.COST_FACTOR;
    }
}