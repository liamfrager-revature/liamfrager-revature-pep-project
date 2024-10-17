package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Exceptions.AccountExceptions.*;
import Service.MessageService;
import Exceptions.MessageExceptions.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;
    ObjectMapper om;

    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
        this.om = new ObjectMapper();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // REGISTER
        app.post("/register", this::Register);
        // LOGIN
        app.post("/login", this::Login);
        // MESSAGES
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{id}", this::getMessageByID);
        app.post("/messages", this::postMessage);
        app.patch("/messages/{id}", this::patchMessageByID);
        app.delete("/messages/{id}", this::deleteMessageByID);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountID);
        

        return app;
    }

    /**
     * Handler for the '/register' POST endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void Register(Context context) {
        try {
            Account account = om.readValue(context.body(), Account.class);
            context.status(200).json(accountService.register(account));
        } catch (InvalidUsernameException | InvalidPasswordException | UserAlreadyExistsException e) {
            context.status(400);
        } catch (JsonProcessingException e) {
            System.out.println("Could not parse request body.");
            e.printStackTrace();
        }
    }

    /**
     * Handler for the '/login' POST endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void Login(Context context) {
        try {
            Account account = om.readValue(context.body(), Account.class);
            context.status(200).json(accountService.login(account));
        } catch (InvalidLoginException e) {
            context.status(401);
        } catch (JsonProcessingException e) {
            System.out.println("Could not parse request body.");
            e.printStackTrace();
        }
    }

    /**
     * Handler for the '/messages' POST endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postMessage(Context context) {
        try {
            Message message = om.readValue(context.body(), Message.class);
            context.status(200).json(messageService.postMessage(message));
        } catch (InvalidMessageTextException | InvalidUserIDException e) {
            context.status(400);
        } catch (JsonProcessingException e) {
            System.out.println("Could not parse request body.");
            e.printStackTrace();
        }
    }

    /**
     * Handler for the '/messages' GET endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessages(Context context) {
        context.status(200).json(messageService.getAllMessages());
    }

    /**
     * Handler for the '/messages/:id' GET endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getMessageByID(Context context) {
        int id = Integer.parseInt(context.pathParam("id"));
        Message message = messageService.getMessageByID(id);
        if (message == null)
            context.status(200);
        else
            context.status(200).json(message);
    }

    /**
     * Handler for the '/messages/:id' DELETE endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessageByID(Context context) {
        int id = Integer.parseInt(context.pathParam("id"));
        Message deletedMessage = messageService.deleteMessageByID(id);
        if (deletedMessage == null)
            context.status(200);
        else
            context.status(200).json(deletedMessage);
    }

    /**
     * Handler for the '/messages/:id' PATCH endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void patchMessageByID(Context context) {
        try {
            int id = Integer.parseInt(context.pathParam("id"));
            Message message = om.readValue(context.body(), Message.class);
            context.status(200).json(messageService.patchMessageByID(id, message));
        } catch (InvalidMessageTextException | InvalidMessageIDException e) {
            context.status(400);
        } catch (JsonProcessingException e) {
            System.out.println("Could not parse request body.");
            e.printStackTrace();
        }
    }

    /**
     * Handler for the '/accounts/:account_id/messages' GET endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesByAccountID(Context context) {
        int id = Integer.parseInt(context.pathParam("account_id"));
        context.status(200).json(messageService.getAllMessagesByAccountID(id));
    }
}