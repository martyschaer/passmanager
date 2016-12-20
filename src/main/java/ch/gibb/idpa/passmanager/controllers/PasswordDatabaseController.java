/*
 * Copyright (C) 2016 Gibb
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package ch.gibb.idpa.passmanager.controllers;

import ch.gibb.idpa.passmanager.model.PasswordDatabase;
import ch.gibb.idpa.passmanager.model.PasswordEntry;
import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 *
 * @author Jean-Rémy Buchs <jean-remy@gmx.ch>
 */
public class PasswordDatabaseController implements Initializable {

	@FXML
	private TableView<PasswordEntry> table;
	@FXML
	private TableColumn<PasswordEntry, String> columnLabel;
	@FXML
	private TableColumn<PasswordEntry, String> columnUsername;
	@FXML
	private TableColumn<PasswordEntry, String> columnDescription;

	private final PasswordDatabase database = new PasswordDatabase();

	public void openDatabase() {
		if (closeDatabase()) {
			try {
				showFileDialog(false).ifPresent(showKeyDialog(database::load));
			} catch (Exception ex) {
				showDialog(Bindings.format("Failed to open password database"), new ButtonType[]{ButtonType.OK}, grid -> {
					grid.addRow(0, new Label(ex.getLocalizedMessage()));
				}, button -> null);
			}
		}
	}

	public void saveDatabase() {
		showFileDialog(true).ifPresent(showKeyDialog(database::save));
	}

	public boolean closeDatabase() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Close Database");
		alert.setHeaderText("Close Database");
		alert.setContentText("Do you really want to close the database without saving?");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

		if (alert.showAndWait().filter(button -> button == ButtonType.YES).isPresent()) {
			database.clear();
			return true;
		}
		return false;
	}

	public void newPassword() {
		PasswordEntry entry = new PasswordEntry();
		entry.setLabel("Unnamed password");
		database.passwordsProperty().add(entry);
		table.getSelectionModel().select(entry);

		editPassword();
	}

	public void editPassword() {
		PasswordEntry rowData = table.getSelectionModel().getSelectedItem();

		showEditDialog(rowData).ifPresent(res -> {
			database.passwordsProperty().remove(rowData);
			database.passwordsProperty().add(res);
		});
	}

	public void deletePassword() {
		database.passwordsProperty().remove(table.getSelectionModel().getSelectedItem());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ChangeListener<Window> windowListener = (window, oldWindow, newWindow) -> {
			if (oldWindow != null) {
				oldWindow.setOnCloseRequest(null);
			}
			if (newWindow != null) {
				newWindow.setOnCloseRequest(event -> {
					if (!closeDatabase()) {
						event.consume();
					}
				});
			}
		};
		table.sceneProperty().addListener((scene, oldScene, newScene) -> {
			if (oldScene != null) {
				oldScene.windowProperty().removeListener(windowListener);
			}
			if (newScene != null) {
				newScene.windowProperty().addListener(windowListener);
			}
		});

		table.setItems(database.passwordsProperty());

		table.setRowFactory(table -> {
			TableRow<PasswordEntry> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					editPassword();
				}
			});
			return row;
		});

		columnLabel.setCellValueFactory(cell -> cell.getValue().labelProperty());
		columnUsername.setCellValueFactory(cell -> cell.getValue().usernameProperty());
		columnDescription.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
	}

	private Optional<PasswordEntry> showEditDialog(PasswordEntry original) {
		PasswordEntry copy = original.clone();

		Optional<PasswordEntry> ret = showDialog(Bindings.format("Edit Password \"%s\"", copy.labelProperty()), new ButtonType[]{ButtonType.APPLY, ButtonType.CANCEL}, grid -> {
			grid.addRow(0, new Label("Label: "), createTextField(copy.labelProperty()));
			grid.addRow(1, new Label("Username: "), createTextField(copy.usernameProperty()));
			grid.addRow(2, new Label("Password: "), createTextField(copy.passwordProperty()));
			grid.addRow(3, new Label("Description: "), createTextField(copy.descriptionProperty()));
		}, button -> button == ButtonType.APPLY ? copy : null);

		ret.ifPresent(entry -> entry.setLastUpdate(Instant.now()));
		return ret;
	}

	private TextField createTextField(Property<String> property) {
		TextField field = new TextField();
		field.textProperty().bindBidirectional(property);
		return field;
	}

	private PasswordField createPasswordField(Property<String> property) {
		PasswordField field = new PasswordField();
		field.textProperty().bindBidirectional(property);
		return field;
	}

	private Optional<File> showFileDialog(boolean save) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle((save ? "Save" : "Open") + " Password Database");
		chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Passmanager database", "*.pwdb"));
		return Optional.ofNullable(save ? chooser.showSaveDialog(null) : chooser.showOpenDialog(null));
	}

	private Consumer<? super File> showKeyDialog(Consumer<? super File> consumer) {
		return file -> {
			Property<String> key = new SimpleStringProperty();

			Optional<String> ret = showDialog(Bindings.format("Enter key for \"%s\"", file.getName()), new ButtonType[]{ButtonType.OK, ButtonType.CANCEL}, grid -> {
				grid.addRow(0, new Label("Key: "), createPasswordField(key));
			}, button -> button == ButtonType.OK ? key.getValue() : null);

			ret.ifPresent(database::setKey);
			ret.ifPresent(k -> consumer.accept(file));
		};
	}

	private <T> Optional<T> showDialog(StringExpression title, ButtonType[] buttons, Consumer<GridPane> dialogPopulator, Callback<ButtonType, T> resultConverter) {
		Dialog<T> dialog = new Dialog<>();

		dialog.titleProperty().bind(title);
		dialog.getDialogPane().getButtonTypes().addAll(buttons);
		dialog.setResultConverter(resultConverter);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		dialog.getDialogPane().setContent(grid);

		dialogPopulator.accept(grid);

		return dialog.showAndWait();
	}
}
