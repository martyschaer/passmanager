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
 * Controller for the password database functionality.
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

	/**
	 * Close the current database, let the user choose another one and open it.
	 */
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

	/**
	 * Save the current database (let the user choose where).
	 */
	public void saveDatabase() {
		showFileDialog(true).ifPresent(showKeyDialog(database::save));
	}

	/**
	 * Close the current database after a confirmation.
	 *
	 * @return true if the current database was closed.
	 */
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

	/**
	 * Create a new empty password entry and let the user edit it.
	 */
	public void newPassword() {
		PasswordEntry entry = new PasswordEntry();
		entry.setLabel("Unnamed password");
		database.passwordsProperty().add(entry);
		table.getSelectionModel().select(entry);

		editPassword();
	}

	/**
	 * Let the user edit the currently selected password entry.
	 */
	public void editPassword() {
		PasswordEntry rowData = table.getSelectionModel().getSelectedItem();

		showEditDialog(rowData).ifPresent(res -> {
			database.passwordsProperty().remove(rowData);
			database.passwordsProperty().add(res);
		});
	}

	/**
	 * Delete the currently selected password entry.
	 */
	public void deletePassword() {
		database.passwordsProperty().remove(table.getSelectionModel().getSelectedItem());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Ask for confirmation before closing application
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

		// Prepare table rows.
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

		// Prepare table columns.
		columnLabel.setCellValueFactory(cell -> cell.getValue().labelProperty());
		columnUsername.setCellValueFactory(cell -> cell.getValue().usernameProperty());
		columnDescription.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
	}

	/**
	 * Show the edit dialog for a password entry.
	 *
	 * @param original The password entry that should be edited.
	 * @return The modified password entry or not present if the user has
	 * cancelled the edit. May be a clone of the original.
	 */
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

	/**
	 * Create a new text field for a property.
	 *
	 * @param property The property to bind to the text field.
	 * @return The new text field bound to the property.
	 */
	private TextField createTextField(Property<String> property) {
		TextField field = new TextField();
		field.textProperty().bindBidirectional(property);
		return field;
	}

	/**
	 * Create a new password field for a property.
	 *
	 * @param property The property to bind to the password field.
	 * @return The new password field bound to the property.
	 */
	private PasswordField createPasswordField(Property<String> property) {
		PasswordField field = new PasswordField();
		field.textProperty().bindBidirectional(property);
		return field;
	}

	/**
	 * Shows a file dialog to the user.
	 *
	 * @param save true if the dialog should be a save dialog, false if it
	 * should be an open dialog.
	 * @return The selected file or not present if the user has cancelled the
	 * selection.
	 */
	private Optional<File> showFileDialog(boolean save) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle((save ? "Save" : "Open") + " Password Database");
		chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Passmanager database", "*.pwdb"));
		return Optional.ofNullable(save ? chooser.showSaveDialog(null) : chooser.showOpenDialog(null));
	}

	/**
	 * Creates a consumer to show a dialog to the user where he enters the key
	 * of the password database.
	 *
	 * @param consumer Consumer to call if a key was entered.
	 * @return New consumer which should be called to show the dialog.
	 */
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

	/**
	 * Creates and shows a dialog.
	 *
	 * @param <T> The type of the result.
	 * @param title The expression for the title of the dialog.
	 * @param buttons An array with all buttons.
	 * @param dialogPopulator Consumer which adds the components to the dialog.
	 * @param resultConverter Converter to convert the input to the result.
	 * @return The result.
	 */
	private <T> Optional<T> showDialog(StringExpression title, ButtonType[] buttons, Consumer<GridPane> dialogPopulator, Callback<ButtonType, T> resultConverter) {
		// Generic stuff.
		Dialog<T> dialog = new Dialog<>();

		dialog.titleProperty().bind(title);
		dialog.getDialogPane().getButtonTypes().addAll(buttons);
		dialog.setResultConverter(resultConverter);

		// Content.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		dialog.getDialogPane().setContent(grid);

		dialogPopulator.accept(grid);

		// Show the dialog.
		return dialog.showAndWait();
	}
}
