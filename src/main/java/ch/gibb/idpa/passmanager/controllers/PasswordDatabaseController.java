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

import ch.gibb.idpa.passmanager.model.PasswordEntry;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		columnLabel.setCellValueFactory(cell -> cell.getValue().labelProperty());
		columnUsername.setCellValueFactory(cell -> cell.getValue().usernameProperty());
		columnDescription.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
	}
}
