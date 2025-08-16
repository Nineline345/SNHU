public class Monkey extends RescueAnimal {
    /**
     * @Author Hunter Goodwin
     * @Version 1.1
     * @Since 02/16/2025
     */

    // Instance variables for Monkey
    private String tailLength;
    private String height;
    private String bodyLength;
    private String species;

    // Monkey Constructor
    public Monkey(String name, String gender, String age,
            String weight, String acquisitionDate, String acquisitionCountry,
            String trainingStatus, boolean reserved, String inServiceCountry,
            String tailLength, String height, String bodyLength, String species) {
        super(name, "Monkey", gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, reserved,
                inServiceCountry);
        setTailLength(tailLength);
        setHeight(height);
        setBodyLength(bodyLength);
        setSpecies(species);
    }

    // Accessor and mutator
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getTailLength() {
        return tailLength;
    }

    public void setTailLength(String tailLength) {
        this.tailLength = tailLength;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(String bodyLength) {
        this.bodyLength = bodyLength;
    }
}