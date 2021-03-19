package javafx.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.Avatar;
import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Global Variables
    ObservableList<String> characters = FXCollections.observableArrayList("Main", "Shimano", "Iolo",
            "Mariah", "Geoffrey", "Jaana", "Julia", "Dupre", "Katrina", "Sentri", "Gwenno", "Johne", "Gorn", "Maxwell",
            "Toshi", "Sadju");

    String filePath = "";

    //GUI components
    @FXML
    private ChoiceBox characterList;

    @FXML
    private TextField filePathField;

    @FXML
    private Button fileButton;

    @FXML
    private Button maxButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField strengthField;

    @FXML
    private TextField intelliField;

    @FXML
    private TextField dexField;

    @FXML
    private TextField hpField;

    @FXML
    private TextField skullField;

    @FXML
    private TextField expField;

    @FXML
    private TextField goldField;

    @FXML
    private TextField keysField;

    @FXML
    private TextField maxHPField;

    @FXML
    private TextField gemsField;

    @FXML
    private TextField badgeField;

    @FXML
    private TextField carpetsField;

    @FXML
    private TextField axesField;

    //GUI button actions
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        characterList.setValue("Main");
        characterList.setItems(characters);
        resetValues();
    }

    @FXML
    private void selectFile(ActionEvent event){

        FileChooser file = new FileChooser();
        file.setTitle("Open SAVED.GAM File");
        File selected = file.showOpenDialog(null);

        if(selected != null) {
            filePath = selected.getAbsolutePath();
        } else {
            System.out.println("No file selected.");
        }

        filePathField.setText(filePath);
    }

    @FXML
    private void updateCharacter(MouseEvent event) throws IOException {
        // validate inputs first
        boolean valid = validateInputs();

        if(valid){
            // Assign values and execute algorithm
            String name = (String) characterList.getValue();

            String strengthString = strengthField.getText();
            int strength = Integer.parseInt(strengthString);

            String intelliString = intelliField.getText();
            int intelli = Integer.parseInt(intelliString);

            String dexString = dexField.getText();
            int dex = Integer.parseInt(dexString);

            String hpString = hpField.getText();
            int hp = Integer.parseInt(hpString);

            String skullString = skullField.getText();
            int skull = Integer.parseInt(skullString);

            String expString = expField.getText();
            int exp = Integer.parseInt(expString);

            String goldString = goldField.getText();
            int gold = Integer.parseInt(goldString);

            String keyString = keysField.getText();
            int keys = Integer.parseInt(keyString);

            String maxHPString = maxHPField.getText();
            int maxhp = Integer.parseInt(maxHPString);

            String gemString = gemsField.getText();
            int gems = Integer.parseInt(gemString);

            String badgeString = badgeField.getText();
            int badge = Integer.parseInt(badgeString);

            String carpetString = carpetsField.getText();
            int carpets = Integer.parseInt(carpetString);

            String axeString = axesField.getText();
            int axes = Integer.parseInt(axeString);

            Avatar character = new Avatar(name, strength, intelli, dex, hp, skull, gold, exp, keys, maxhp, gems, badge, carpets, axes);

            // Setup confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Modifying your stats requires you to restart the game.");
            alert.setContentText("Are you sure you want to continue?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                modifyFile(character);

                // Confirm changes.
                Alert complete = new Alert(Alert.AlertType.INFORMATION);
                complete.setHeaderText("Changes Complete");
                complete.setContentText("Modifications have been made. Restart the game.");
                complete.showAndWait();
            }
        }
    }

    @FXML
    private void maxButtonClicked(MouseEvent event){
        strengthField.setText("99");
        intelliField.setText("99");
        dexField.setText("99");
        hpField.setText("999");
        skullField.setText("100");
        expField.setText("9999");
        goldField.setText("9999");
        keysField.setText("100");
        maxHPField.setText("999");
        gemsField.setText("100");
        badgeField.setText("1");
        carpetsField.setText("2");
        axesField.setText("10");
    }

    @FXML
    private void clearButtonClicked(MouseEvent event){
        resetValues();
    }

    //Modify text fields to only accept numbers
    @FXML
    private void strNumOnly(KeyEvent event){
        numOnly(strengthField);
    }

    @FXML
    private void intelliNumOnly(KeyEvent event){
        numOnly(intelliField);
    }

    @FXML
    private void dexNumOnly(KeyEvent event){
        numOnly(dexField);
    }

    @FXML
    private void currHPNumOnly(KeyEvent event){
        numOnly(hpField);
    }

    @FXML
    private void skullNumOnly(KeyEvent event){
        numOnly(skullField);
    }

    @FXML
    private void expNumOnly(KeyEvent event){
        numOnly(expField);
    }

    @FXML
    private void goldNumOnly(KeyEvent event){
        numOnly(goldField);
    }

    @FXML
    private void keysNumOnly(KeyEvent event){
        numOnly(keysField);
    }

    @FXML
    private void maxHPNumOnly(KeyEvent event){
        numOnly(maxHPField);
    }

    @FXML
    private void gemsNumOnly(KeyEvent event){
        numOnly(gemsField);
    }

    @FXML
    private void badgeNumOnly(KeyEvent event){
        numOnly(badgeField);
    }

    @FXML
    private void carpetNumOnly(KeyEvent event){
        numOnly(carpetsField);
    }

    @FXML
    private void axesNumOnly(KeyEvent event){
        numOnly(axesField);
    }

    // function modifies the .GAM file hex
    private void modifyFile(Avatar c) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");

        String name = c.getName();
        int strength = c.getStrength();
        int intelli = c.getIntelli();
        int dex = c.getDex();
        int hp = c.getHP();
        int skull = c.getSkull();
        int gold = c.getGold();
        int exp = c.getExp();
        int keys = c.getKeys();
        int maxhp = c.getMaxHP();
        int gems = c.getGems();
        int badge = c.getBadge();
        int carpets = c.getCarpets();
        int axes = c.getAxes();

        // initial position for character attributes
        // need to increment xCharacter position
        int strPos = 14;
        int intelPos = 16;
        int dexPos = 15;
        int hpPos = 18;
        int maxHPPos = 20;
        int expPos = 22;

        // character position in arraylist
        int pos = (characters.indexOf(name)) * 32;

        // fixed position of other attributes
        int goldPos = 516;
        int keysPos = 518;
        int skullPos = 523;
        int gemsPos = 519;
        int badgePos = 536;
        int carpetPos = 522;
        int axesPos = 576;

        // change stats
        // change strength
        byte strByte = (byte) strength;
        raf.seek(pos + strPos);
        raf.write(strByte);

        // change intelligence
        byte intelliByte = (byte) intelli;
        raf.seek(intelPos + pos);
        raf.write(intelliByte);

        // change dex
        byte dexByte = (byte) dex;
        raf.seek(dexPos + pos);
        raf.write(dexByte);

        // change currentHP
        byte[] hpByte = intTo2Byte(hp);
        raf.seek(hpPos + pos);
        raf.write(hpByte);

        // change maxHP
        byte[] maxHPByte = intTo2Byte(maxhp);
        raf.seek(maxHPPos + pos);
        raf.write(hpByte);

        // change expPoints
        byte[] expBytes = intTo2Byte(exp);
        raf.seek(expPos + pos);
        raf.write(expBytes);

        // modify global values
        // change gold
        byte[] goldBytes = intTo2Byte(gold);
        raf.seek(goldPos);
        raf.write(goldBytes);

        // change keys
        byte keyBytes = (byte) keys;
        raf.seek(keysPos);
        raf.write(keyBytes);

        // change skull
        byte skullBytes = (byte) skull;
        raf.seek(skullPos);
        raf.write(skullBytes);

        // change gems
        byte gemBytes = (byte) gems;
        raf.seek(gemsPos);
        raf.write(gemBytes);

        // change black badge
        byte badgeByte = (byte) badge;
        raf.seek(badgePos);
        raf.write(badgeByte);

        // change magic carpets
        byte carpetsByte = (byte) carpets;
        raf.seek(carpetPos);
        raf.write(carpetsByte);

        // change magic axes
        byte axesByte = (byte) axes;
        raf.seek(axesPos);
        raf.write(axesByte);

        // marking my territory
        String signature = "pwned by johnnyappleseed";
        byte[] bytes = signature.getBytes();
        raf.seek(864);
        raf.write(bytes);

    }

    // function validates the users entries, throws error message if not valid
    public boolean validateInputs(){
        String strengthString = strengthField.getText();
        int strength = Integer.parseInt(strengthString);

        String intelliString = intelliField.getText();
        int intelli = Integer.parseInt(intelliString);

        String dexString = dexField.getText();
        int dex = Integer.parseInt(dexString);

        String hpString = hpField.getText();
        int hp = Integer.parseInt(hpString);

        String skullString = skullField.getText();
        int skull = Integer.parseInt(skullString);

        String goldString = goldField.getText();
        int gold = Integer.parseInt(goldString);

        String expString = expField.getText();
        int exp = Integer.parseInt(expString);

        String keyString = keysField.getText();
        int keys = Integer.parseInt(keyString);

        String maxHPString = maxHPField.getText();
        int maxhp = Integer.parseInt(maxHPString);

        String gemString = gemsField.getText();
        int gems = Integer.parseInt(gemString);

        String badgeString = badgeField.getText();
        int badge = Integer.parseInt(badgeString);

        String carpetString = carpetsField.getText();
        int carpets = Integer.parseInt(carpetString);

        String axeString = axesField.getText();
        int axes = Integer.parseInt(axeString);

        if((strength <=99) && (intelli <= 99) && (dex <= 99) && (gold <= 9999) && (hp <=999)
                && (skull <= 100) &&  (exp <= 9999) && (keys <=100) && (maxhp <= 999)
                && (gems <= 100) && (badge <=1)
                && (carpets <= 2) && (axes <= 10)){
            if(filePath.endsWith(".GAM")){
                return true;
            } else {
                // Show error message
                String errorMsg = "Invalid file type. Please select .GAM file.";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(errorMsg);
                alert.showAndWait();
                return false;
            }

        } else {
            // Show error message
            String errorMsg = "Exceeded max values. Please change your stats.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(errorMsg);
            alert.showAndWait();
            return false;
        }
    }

    // function resets the values back to 0
    public void resetValues() {
        strengthField.setText("0");
        intelliField.setText("0");
        dexField.setText("0");
        hpField.setText("0");
        skullField.setText("0");
        expField.setText("0");
        goldField.setText("0");
        keysField.setText("0");
        maxHPField.setText("0");
        gemsField.setText("0");
        badgeField.setText("0");
        carpetsField.setText("0");
        axesField.setText("0");
    }

    // function converts 4 bytes to length of 2 bytes
    public static byte[] intTo2Byte(int integer){
        byte[] bytes = new byte[2];
        for (int i = 0; i < 2; i++) {
            bytes[i] = (byte)(integer >>> (i * 8));
        }
        return bytes;
    }

    // function allows only numeric values to be input
    public void numOnly(TextField textField){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
