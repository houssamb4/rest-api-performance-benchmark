package ma.variante.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.variante.dao.CategoryDAO;
import ma.variante.dao.ItemDAO;
import ma.variante.model.Category;
import ma.variante.model.Item;

import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {
    private final CategoryDAO dao = new CategoryDAO();
    private final ItemDAO itemDAO = new ItemDAO();

    @GET
    public Response getAll(@QueryParam("page") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("10") int size) {
        List<Category> content = dao.findPaged(page, size);
        long total = dao.countAll();
        return Response.ok(content)
                .header("X-Total-Count", total)
                .header("X-Page", page)
                .header("X-Size", size)
                .build();
    }

    @POST
    public Response addCategory(Category category) {
        dao.save(category);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Category c = dao.findById(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(c).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Category update) {
        Category updated = dao.update(id, update);
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

    @GET
    @Path("/{id}/items")
    public Response getItemsOfCategory(@PathParam("id") Long id) {
        Category c = dao.findById(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).build();
        List<Item> items = itemDAO.findByCategoryId(id);
        return Response.ok(items).build();
    }
}