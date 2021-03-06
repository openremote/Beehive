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
package org.openremote.beehive.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openremote.beehive.Constant;
import org.openremote.beehive.api.dto.TemplateDTO;
import org.openremote.beehive.api.service.TemplateService;
/**
 * Search Public Templates by keywords.
 * 
 * @author Javen
 *
 */
@Path("/templates")
public class PublicTemplatesRestService extends RESTBaseService {
   
  private static final Log logger = LogFactory.getLog(PublicTemplatesRestService.class);

   @Path("keywords/{keywords}/page/{page}")
   @GET
   @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
   public Response getTemplates(@PathParam("keywords") String keywords, @PathParam("page") int page,
         @HeaderParam(Constant.HTTP_AUTH_HEADER_NAME) String credentials) {
     
     logger.debug("search templates, keywords : " + keywords + ", page : " + page);
     logger.debug("Provided credentials : " + credentials);

      if (!authorize(credentials)) return unAuthorizedResponse();
      
      logger.debug("Authorization OK, getting resources");

      String newKeywords = keywords;
      if (keywords.equals(TemplateService.NO_KEYWORDS)) {
         newKeywords = "";
      }
      List<TemplateDTO> list = getTemplateService().loadPublicTemplatesByKeywordsAndPage(newKeywords, page);
      if (list != null) {
        logger.debug("Got a list of templates, building response");
         return buildResponse(new TemplateListing(list));
      }
      return resourceNotFoundResponse();
   }

   
   protected TemplateService getTemplateService() {
      return (TemplateService) getSpringContextInstance().getBean("templateService");
   }
   
}
