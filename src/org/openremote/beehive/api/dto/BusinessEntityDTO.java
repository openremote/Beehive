/* OpenRemote, the Home of the Digital Home.
* Copyright 2008-2010, OpenRemote Inc.
*
* See the contributors.txt file in the distribution for a
* full listing of individual contributors.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as
* published by the Free Software Foundation, either version 3 of the
* License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/
package org.openremote.beehive.api.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Business entity class for all DTO entities with the common property oid.
 * 
 * @author allen 2009-2-17
 * 
 */
public abstract class BusinessEntityDTO implements Serializable {

   private static final long serialVersionUID = -3871334485197341321L;
   private long id;

   @XmlElement(name = "id")
   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }
}