package com.example.pharmacymanagementsystem.controllers;

import com.example.pharmacymanagementsystem.Mapper.Supplier;
import com.example.pharmacymanagementsystem.Repository.SupplierRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierController  implements Initializable {

    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_address;
    @FXML
    private TextField txt_phoneNumber;
    @FXML
    private TextField txt_email;

    @FXML
    private TableColumn<Supplier, Void> col_actions;
    @FXML
    private TableView<Supplier> table_suppliers;
    @FXML
    private TableColumn<Supplier, Integer> col_id;
    @FXML
    private TableColumn<Supplier, String> col_name;
    @FXML
    private TableColumn<Supplier, String> col_address;
    @FXML
    private TableColumn<Supplier, String> col_phoneNumber;
    @FXML
    private TableColumn<Supplier, String> col_email;

    private SupplierRepository supplierRepository = new SupplierRepository();

    public SupplierController() throws SQLException {
    }

    @FXML
    void getSelected(MouseEvent event) {
        Supplier selectedSupplier = table_suppliers.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            txt_id.setText(String.valueOf(selectedSupplier.getId()));
            txt_name.setText(selectedSupplier.getName());
            txt_address.setText(selectedSupplier.getAddress());
            txt_phoneNumber.setText(selectedSupplier.getPhoneNumber());
            txt_email.setText(selectedSupplier.getEmail());
        }
    }

    @FXML
    void add(ActionEvent actionEvent) throws SQLException {
        Supplier supplier = new Supplier(
                Integer.parseInt(txt_id.getText()),
                txt_name.getText(),
                txt_address.getText(),
                txt_phoneNumber.getText(),
                txt_email.getText()
        );
        supplierRepository.insert(supplier);
        updateTable();
    }

    @FXML
    void update(ActionEvent actionEvent) throws SQLException {
        int id = Integer.parseInt(txt_id.getText());
        String name = txt_name.getText();
        String address = txt_address.getText();
        String phoneNumber = txt_phoneNumber.getText();
        String email = txt_email.getText();

        supplierRepository.update(id, name, address, phoneNumber, email);
        updateTable();
    }

    @FXML
    void delete(ActionEvent actionEvent) throws SQLException {
        supplierRepository.delete(Integer.parseInt(txt_id.getText()));
        updateTable();
    }

    private void updateTable() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        List<Supplier> supplierList = supplierRepository.read();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        suppliers.addAll(supplierList);
        table_suppliers.setItems(suppliers);
    }

    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierRepository.read();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Callback<TableColumn<Supplier, Void>, TableCell<Supplier, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Supplier, Void> call(final TableColumn<Supplier, Void> param) {
                final TableCell<Supplier, Void> cell = new TableCell<>() {

                    private final Button btnUpdate = new Button("Update");
                    private final Button btnDelete = new Button("Delete");

                    {
                        btnUpdate.setOnAction((ActionEvent event) -> {
                            Supplier supplier = getTableView().getItems().get(getIndex());
                            txt_id.setText(String.valueOf(supplier.getId()));
                            txt_name.setText(supplier.getName());
                            txt_address.setText(supplier.getAddress());
                            txt_phoneNumber.setText(supplier.getPhoneNumber());
                            txt_email.setText(supplier.getEmail());
                        });

                        btnDelete.setOnAction((ActionEvent event) -> {
                            Supplier supplier = getTableView().getItems().get(getIndex());
                            try {
                                supplierRepository.delete(supplier.getId());
                                updateTable();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox pane = new HBox(btnUpdate, btnDelete);
                            setGraphic(pane);
                        }
                    }
                };
                return cell;
            }
        };

        col_actions.setCellFactory(cellFactory);

        try {
            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}