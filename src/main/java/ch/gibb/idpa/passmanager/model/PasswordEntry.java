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

import com.migesok.jaxb.adapter.javatime.InstantXmlAdapter;
import java.time.Instant;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Jean-Rémy Buchs <jean-remy@gmx.ch>
 */
public class PasswordEntry implements Cloneable {

	private final Property<String> label = new SimpleStringProperty();
	private final Property<String> username = new SimpleStringProperty();
	private final Property<String> password = new SimpleStringProperty();
	private final Property<String> description = new SimpleStringProperty();
	private final Property<Instant> lastUpdate = new SimpleObjectProperty<>();

	public String getLabel() {
		return label.getValue();
	}

	public void setLabel(String label) {
		this.label.setValue(label);
	}

	public Property<String> labelProperty() {
		return label;
	}

	public String getUsername() {
		return username.getValue();
	}

	public void setUsername(String username) {
		this.username.setValue(username);
	}

	public Property<String> usernameProperty() {
		return username;
	}

	public String getPassword() {
		return password.getValue();
	}

	public void setPassword(String password) {
		this.password.setValue(password);
	}

	public Property<String> passwordProperty() {
		return password;
	}

	public String getDescription() {
		return description.getValue();
	}

	public void setDescription(String description) {
		this.description.setValue(description);
	}

	public Property<String> descriptionProperty() {
		return description;
	}

	@XmlJavaTypeAdapter(InstantXmlAdapter.class)
	public Instant getLastUpdate() {
		return lastUpdate.getValue();
	}

	public void setLastUpdate(Instant lastUpdate) {
		this.lastUpdate.setValue(lastUpdate);
	}

	public Property<Instant> lastUpdateProperty() {
		return lastUpdate;
	}

	@Override
	public PasswordEntry clone() {
		PasswordEntry copy = new PasswordEntry();

		copy.setLabel(getLabel());
		copy.setUsername(getUsername());
		copy.setPassword(getPassword());
		copy.setDescription(getDescription());
		copy.setLastUpdate(getLastUpdate());

		return copy;
	}
}
