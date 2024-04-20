import com.google.gson.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;


/**
 * This class checks if a JSON file is valid with AWS::IAM::Role Policy and if the resources contain a single asterisk
 * @author Bartosz Janusz
 */
public class JsonChecker {
    private JsonObject rootObject = null;
    /**
     * This method sets the rootObject to the given JSON file
     * @param pathToJson the path to the JSON file
     * @throws JsonParsingException if the file is not a valid JSON file
     */
    public void setJson(String pathToJson)
    {
        try {
            rootObject = JsonParser.parseReader(new FileReader(pathToJson)).getAsJsonObject();
        } catch (JsonParseException | FileNotFoundException e) {
            throw new JsonParsingException("The file is not a valid JSON file: " + e.getMessage(), e);
        }
    }
    /**
     * This method allows the user to choose a JSON file
     * @throws JsonParsingException if the file is not a valid JSON file
     */
    public void chooseJson()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.showOpenDialog(null);
        String pathToJson = fileChooser.getSelectedFile().getAbsolutePath();
        try {
            rootObject = JsonParser.parseReader(new FileReader(pathToJson)).getAsJsonObject();
        } catch (JsonParseException | FileNotFoundException e) {
            throw new JsonParsingException("The file is not a valid JSON file: " + e.getMessage(), e);
        }
    }
    /**
     * This method checks if the given object contains all the mandatory keys to be a valid AWS::IAM::Role Policy JSON file on current level
     * @param object the object to check
     * @param keys the set of mandatory keys
     * @throws IllegalArgumentException if the JSON file is missing a mandatory key or has a duplicate key
     */
    private void mandatoryKeyCheck(JsonObject object, Set<String> keys) {
        Set<Map.Entry<String, JsonElement>> entrySet = object.entrySet();
        for (String key : keys) {
            long count = entrySet.stream().filter(entry -> entry.getKey().equals(key)).count();
            if (count == 0) {
                throw new IllegalArgumentException("The JSON file is missing key: " + key);
            }
        }
    }
    /**
     * This method checks if the JSON file is valid with AWS::IAM::Role Policy
     * @return true if the JSON file is valid, false otherwise
     * @throws IllegalArgumentException if the JSON file is not valid
     */
    public Boolean isJsonValid() throws IllegalArgumentException
    {
        if( rootObject == null) throw new IllegalArgumentException("The json file is not set");
        Set<String> topLevelKeys = Set.of("PolicyName", "PolicyDocument"); //AWS::IAM::Role Policy JSON file must have these keys
        mandatoryKeyCheck(rootObject, topLevelKeys);

        Set<String> policyDocumentKeys = Set.of("Version", "Statement"); //PolicyDocument must have these keys
        JsonObject policyDocument = rootObject.getAsJsonObject("PolicyDocument");
        mandatoryKeyCheck(policyDocument, policyDocumentKeys);

        Set<String> statementMandatoryKeys = Set.of("Effect", "Action", "Resource"); //Statement must have these keys
        Set<String> statementOptionalKeys = Set.of("Effect", "Action", "Resource",  "Sid", "NotAction", "NotResource", "Principal", "NotPrincipal","Condition", "Version"); //Statement can have these keys

        JsonArray statement = policyDocument.getAsJsonArray("Statement");
        for (JsonElement statementElement : statement) { //for each statement in the statement array
            JsonObject statementObject = statementElement.getAsJsonObject();
            mandatoryKeyCheck(statementObject, statementMandatoryKeys);
            if(!statementOptionalKeys.containsAll(statementObject.keySet())) {
                throw new IllegalArgumentException("The JSON file is not a valid AWS::IAM::Role Policy JSON file");
            }
        }
        return true;
    }
    /**
     * This method checks if the resources contain a single asterisk and if the JSON file is valid with AWS::IAM::Role Policy
     * @return true if the resources contain a single asterisk, false otherwise
     * @see #isJsonValid()
     */
    public Boolean noAsteriskInResources()
    {
        try {
            if(!isJsonValid()) return true;
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
        JsonObject document = rootObject.getAsJsonObject("PolicyDocument");
        JsonArray statement = document.getAsJsonArray("Statement");

        for (JsonElement statementElement : statement) { //for each statement in the statement array
            JsonObject statementObject = statementElement.getAsJsonObject();
            JsonElement resourceElement = statementObject.get("Resource"); //get the resource element or null

            if (resourceElement != null && resourceElement.isJsonPrimitive()) { //if we have a resource element
                String resource = resourceElement.getAsString();
                if (resource.equals("*")) {
                    return false;
                }
            }
        }
        return true;
    }
}
