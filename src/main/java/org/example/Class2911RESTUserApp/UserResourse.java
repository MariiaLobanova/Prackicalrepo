package org.example.Class2911RESTUserApp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserResourse {

        UserDao userDao = new UserDao();

        /*@GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<User> getUsers() {
            return userDao.getAllUsers();
        }

        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public User getUser(@PathParam("id") int id) {
            return userDao.getUser(id);
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void addUser(User user) {
            userDao.addUser(user);
        }

        @PUT
        @Path("/{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public void updateUser(@PathParam("id") int id, User user) {
            user.setId(id);
            userDao.updateUser(user);
        }

        @DELETE
        @Path("/{id}")
        public void deleteUser(@PathParam("id") int id) {
            userDao.deleteUser(id);
        }
    }*/
}
