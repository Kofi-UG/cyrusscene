package com.example.pharmacymanagementsystem.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pharmacymanagementsystem.Main;
import com.example.pharmacymanagementsystem.Mapper.Drug;
import com.example.pharmacymanagementsystem.Repository.DrugRepository;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DrugController implements Initializable {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private TableColumn<Drug, String> col_dosage;

    @FXML
    private TableColumn<Drug, String> col_generic_name;

    @FXML
    private TableColumn<Drug, String> col_name;

    @FXML
    private TableColumn<Drug, Integer> col_id;

    @FXML
    private TableColumn<Drug, String> col_strength;

    @FXML
    private TableColumn<Drug, String> col_supplier;

    @FXML
    private TableView<Drug> table_drugs;

    @FXML
    private TextField txt_dosage;

    @FXML
    private TextField txt_generic_name;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_search_box;

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_refresh;

    @FXML
    private TextField txt_strength;

    @FXML
    private TextField txt_supplier;
    int index = -1;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(getClass().getResourceAsStream("/images/refresh2.png"));
        ImageView imageView = new ImageView();

        btn_refresh.setGraphic(new ImageView(image));

        try{

            DrugRepository drugRepositoryIn = viewDrugs();
            btn_update.setOnAction(e -> update(drugRepositoryIn, e));
            btn_delete.setOnAction(e -> delete(drugRepositoryIn, e));
            btn_add.setOnAction(e -> add(e));
            btn_refresh.setOnAction(e -> refresh(drugRepositoryIn, e));
            btn_search.setOnAction(e -> search(drugRepositoryIn, e));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    private DrugRepository viewDrugs() throws Exception {
        DrugRepository drugRepository = new DrugRepository();
        updateTable(drugRepository);

        return drugRepository;

    }

    @FXML
    void getSelected (MouseEvent event){
        index = table_drugs.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }

        txt_id.setText(col_id.getCellData(index).toString());
        txt_name.setText(col_name.getCellData(index).toString());
        txt_generic_name.setText(col_generic_name.getCellData(index).toString());
        txt_dosage.setText(col_dosage.getCellData(index).toString());
        txt_strength.setText(col_strength.getCellData(index).toString());
        txt_supplier.setText(col_supplier.getCellData(index).toString());

    }

    @FXML
    private void openAddWindow(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pharmacymanagementsystem/add.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add New Drug"); // Set the title
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions (e.g., if the FXML file is not found)
        }
    }


    @FXML
    void update(DrugRepository drugRepository, ActionEvent actionEvent)
    {
        try{
            drugRepository.update(Integer.parseInt(txt_id.getText()), txt_name.getText(),txt_generic_name.getText(),
                    txt_dosage.getText(), txt_strength.getText(), Integer.parseInt(txt_supplier.getText()));
            updateTable(drugRepository);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @FXML
    void delete(DrugRepository drugRepository, ActionEvent actionEvent)
    {
        try{
            drugRepository.delete(Integer.parseInt(txt_id.getText()));
            updateTable(drugRepository);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void add(ActionEvent actionEvent)
    {
        try{
            openNewStage();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void openNewStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add drug");
        stage.show();
    }

    public void updateTable(DrugRepository drugRepository) throws Exception{
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_generic_name.setCellValueFactory(new PropertyValueFactory<>("genericName"));
        col_dosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        col_strength.setCellValueFactory(new PropertyValueFactory<>("strength"));
        col_supplier.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSupplier().getId() + ""));
        List<Drug> drugList = drugRepository.read();
        ObservableList<Drug> drugs = FXCollections.observableArrayList();
        drugs.addAll(drugList);
        table_drugs.setItems(drugs);
    }

    void refresh(DrugRepository drugRepository, ActionEvent actionEvent) {
        try{
            updateTable(drugRepository);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    void search(DrugRepository drugRepository, ActionEvent actionEvent) {
        if(!txt_search_box.getText().isEmpty()) {
            String searchTerm = txt_search_box.getText().trim().toLowerCase();
            ObservableList<Drug> filteredDrugs = FXCollections.observableArrayList();

            for (Drug drug : table_drugs.getItems()) {
                if (drug.getName().toLowerCase().contains(searchTerm)) {
                    filteredDrugs.add(drug);
                }
            }

            table_drugs.setItems(filteredDrugs);
        }
        else
            try {
                updateTable(drugRepository);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

}
