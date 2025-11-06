package ma.variante.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.variante.dao.ItemDAO;
import ma.variante.model.Category;
import ma.variante.model.Item;

import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {
    private final ItemDAO dao = new ItemDAO();

    @GET
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("10") int size,
                           @QueryParam("categoryId") Long categoryId) {
        if (categoryId != null) {
            List<Item> items = dao.findByCategoryId(categoryId);
            return Response.ok(items).build();
        }
        List<Item> content = dao.findPaged(page, size);
        long total = dao.countAll();
        return Response.ok(content)
                .header("X-Total-Count", total)
                .header("X-Page", page)
                .header("X-Size", size)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Item item = dao.findById(id);
        if (item == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(item).build();
    }

    @POST
    public Response create(Item item) {
        if (item.getCategory() != null && item.getCategory().getId() == null) {
            // normalize: ignore incomplete category payloads
            item.setCategory((Category) null);
        }
        dao.save(item);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Item update) {
        Item updated = dao.update(id, update);
        if (updated == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = dao.delete(id);
        if (!deleted) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}




