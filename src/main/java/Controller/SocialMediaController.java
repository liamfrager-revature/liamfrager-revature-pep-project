package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // REGISTER
        app.get("/register", this::getRegister);
        app.post("/register", this::postRegister);
        // LOGIN
        app.get("/login", this::getLogin);
        app.post("/login", this::postLogin);
        // MESSAGES
        app.get("/messages", this::getAllMessages);
        app.get("/messages/:id", this::getMessageByID);
        app.post("/messages", this::postMessage);
        app.patch("/messages/:id", this::patchMessageByID);
        app.delete("/messages/:id", this::deleteMessageByID);
        app.get("/accounts/:account_id/messages.", this::getAllMessagesByUser);
        

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getRegister(Context context) {
        context.json("getRegister");
    }
    private void postRegister(Context context) {
        context.json("postRegister");
    }
    private void getLogin(Context context) {
        context.json("getLogin");
    }
    private void postLogin(Context context) {
        context.json("postLogin");
    }
    private void getAllMessages(Context context) {
        context.json("getAllMessages");
    }
    private void getMessageByID(Context context) {
        context.json("getMessageByID");
    }
    private void postMessage(Context context) {
        context.json("postMessage");
    }
    private void patchMessageByID(Context context) {
        context.json("patchMessageByID");
    }
    private void deleteMessageByID(Context context) {
        context.json("deleteMessageByID");
    }
    private void getAllMessagesByUser(Context context) {
        context.json("getAllMessagesByUser");
    }


}