package com.example.pharmacymanagementsystem.controllers;

import com.example.pharmacymanagementsystem.Repository.DrugRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_save;

    @FXML
    private TextField txt_dosage;

    @FXML
    private TextField txt_generic_name;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_price;

    @FXML
    private TextField txt_quantity;

    @FXML
    private TextField txt_strength;

    @FXML
    private TextField txt_supplier_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            DrugRepository drugRepository = new DrugRepository();
            btn_save.setOnAction(e -> save(drugRepository, e));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @FXML
    void save (DrugRepository drugRepository, ActionEvent actionEvent)
    {
        try{
            drugRepository.insert(txt_name.getText(), txt_generic_name.getText(), txt_dosage.getText(),
                    txt_strength.getText(), Integer.parseInt(txt_supplier_id.getText()),
                    Integer.parseInt(txt_quantity.getText()), Integer.parseInt(txt_price.getText()));


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

   @FXML
    void reset() {
       TextField[] textFieldsToClear = {
               txt_name, txt_quantity, txt_dosage, txt_price, txt_strength, txt_generic_name, txt_supplier_id
       };


       for (TextField textField : textFieldsToClear) {
           textField.clear();
       }
   }
}
