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
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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

	private static String TRANSFORMATION = "AES/CBC/PKCS5Padding";

	private final ObservableList<PasswordEntry> passwords = FXCollections.observableArrayList();
	private final Property<String> key = new SimpleStringProperty();

	public PasswordDatabase() {

	}

	public void load(File file) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			CipherInputStream cin = new CipherInputStream(in, getCipher(true));
			passwords.setAll(JAXB.unmarshal(cin, PasswordEntryList.class).getPasswords());
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
			CipherOutputStream cout = new CipherOutputStream(out, getCipher(false));
			JAXB.marshal(new PasswordEntryList(passwords), cout);
			cout.close();
		} catch (IOException ex) {
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

	public String getKey() {
		return key.getValue();
	}

	public void setKey(String val) {
		key.setValue(val);
	}

	public Property<String> keyProperty() {
		return key;
	}

	public void clear() {
		passwords.clear();
	}

	private Cipher getCipher(boolean decrypt) {
		try {
			byte[] hash = MessageDigest.getInstance("SHA-512").digest(key.getValue().getBytes(StandardCharsets.UTF_8));
			byte[] key = new byte[256 / 8];
			System.arraycopy(hash, 0, key, 0, key.length);
			byte[] iv = new byte[16];
			System.arraycopy(hash, key.length, iv, 0, iv.length);
			Cipher c = Cipher.getInstance(TRANSFORMATION);
			c.init(decrypt ? Cipher.DECRYPT_MODE : Cipher.ENCRYPT_MODE, new SecretKeySpec(key, TRANSFORMATION.split("/")[0]), new IvParameterSpec(iv));
			return c;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException ex) {
			throw new RuntimeException("Failed to create cypher and / or key for transformation " + TRANSFORMATION, ex);
		}
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
