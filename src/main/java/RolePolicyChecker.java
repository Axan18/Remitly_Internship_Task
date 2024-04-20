public class RolePolicyChecker {
    public static void main(String[] args) {
        JsonChecker jsonChecker = new JsonChecker();
        jsonChecker.chooseJson();
        if(!jsonChecker.noAsteriskInResources()) {
            System.out.println("The JSON file contains a single asterisk in the resources");
        } else {
            System.out.println("The JSON file does not contain a single asterisk in the resources");
        }

    }
}