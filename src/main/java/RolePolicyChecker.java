import javax.swing.JFileChooser;

public class RolePolicyChecker {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.showOpenDialog(null);
        String pathToJson = fileChooser.getSelectedFile().getAbsolutePath();
        JsonChecker jsonChecker = new JsonChecker(pathToJson);
        //jsonChecker.isJsonValid();
        if(!jsonChecker.resourcesContainsAsterisk()) {
            System.out.println("The JSON file contains a single asterisk in the resources");
        } else {
            System.out.println("The JSON file does not contain a single asterisk in the resources");
        }

    }
}