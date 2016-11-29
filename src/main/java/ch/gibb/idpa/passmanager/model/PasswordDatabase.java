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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jean-Rémy Buchs <jean-remy@gmx.ch>
 */
public class PasswordDatabase {

	private final ObservableList<PasswordEntry> passwords = FXCollections.observableArrayList();

	public PasswordDatabase() {

	}

	public void load(File file) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			// TODO: Implement decryption logic
			passwords.setAll(JAXB.unmarshal(in, PasswordEntryList.class).getPasswords());
		} catch (FileNotFoundException ex) {
			throw new RuntimeException("Failed to load the password database.", ex);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(PasswordDatabase.class.getName()).log(Level.SEVERE, "Failed to close FileInputStream.", ex);
			}
		}
	}

	public void save(File file) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			// TODO: Implement encryption logic
			JAXB.marshal(new PasswordEntryList(passwords), out);
		} catch (FileNotFoundException ex) {
			throw new RuntimeException("Failed to save the password database.", ex);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(PasswordDatabase.class.getName()).log(Level.SEVERE, "Failed to close FileOutputStream.", ex);
			}
		}
	}

	public ObservableList<PasswordEntry> passwordsProperty() {
		return passwords;
	}

	public void clear() {
		passwords.clear();
		// TODO: Implement logic
	}

	@XmlRootElement(name = "passworddatabase")
	@XmlAccessorType(XmlAccessType.FIELD)
	private static class PasswordEntryList {

		@XmlElement(name = "password")
		private final List<PasswordEntry> passwords;

		public PasswordEntryList() {
			this(new ArrayList<>());
		}

		public PasswordEntryList(List<PasswordEntry> passwords) {
			this.passwords = passwords;
		}

		public List<PasswordEntry> getPasswords() {
			return passwords;
		}
	}
}
