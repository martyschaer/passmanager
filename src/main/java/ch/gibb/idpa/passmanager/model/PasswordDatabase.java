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
package ch.gibb.idpa.passmanager.model;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jean-Rémy Buchs <jean-remy@gmx.ch>
 */
public class PasswordDatabase {

	public PasswordDatabase(File file) {
		// TODO: Implement logic
	}

	public void save(File file) {
		// TODO: Implement logic
	}

	public ObservableList<PasswordEntry> passwordsProperty() {
		return FXCollections.observableArrayList(); // TODO: Implement logic
	}
}
