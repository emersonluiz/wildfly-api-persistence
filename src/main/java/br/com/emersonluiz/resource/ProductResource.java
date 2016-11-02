package br.com.emersonluiz.resource;
import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.emersonluiz.dao.ProductDAO;
import br.com.emersonluiz.model.Product;

@Path("/products")
@Stateless
public class ProductResource {

    @Inject
    ProductDAO productDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Product product) {
        productDAO.create(product);
        return Response.created(URI.create("/products/" + product.getId())).entity(product).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{id:\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") int id, Product product) {
        if (productDAO.findOne(id) == null) {
            Response.status(Status.NOT_FOUND).build();
        }
        product.setId(id);
        productDAO.update(product);
    }

    @DELETE
    @Path("{id:\\d+}")
    public void delete(@PathParam("id") int id) {
        Product product = productDAO.findOne(id);
        if (product == null) {
            Response.status(Status.NOT_FOUND).build();
        }
        productDAO.delete(product);
    }

    @GET
    @Path("{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOne(@PathParam("id") int id) {
        Product product = productDAO.findOne(id);
        if (product == null) {
            Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(product).build();
        
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> findAll() {
        return productDAO.findAll();
    }
}
