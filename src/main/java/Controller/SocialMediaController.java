package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

import Model.Account;
import Model.Message;

import Service.AccountService;
import Service.MessageService;

import Exceptions.AccountExceptions.*;
import Exceptions.MessageExceptions.*;

public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;
    ObjectMapper om;

    /**
     * Constructor for the social media controller.
     */
    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
        this.om = new ObjectMapper();
    }

    /**
     * Initializes the API and exposes its routes.
     * @return A Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::register);
        app.post("/login", this::login);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{id}", this::getMessageByID);
        app.post("/messages", this::postMessage);
        app.patch("/messages/{id}", this::patchMessageByID);
        app.delete("/messages/{id}", this::deleteMessageByID);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountID);
        
        return app;
    }

    /**
     * Handler for the <code>/register</code> <code>POST</code> endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void register(Context context) {
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
     * Handler for the <code>/login</code> <code>POST</code> endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void login(Context context) {
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
     * Handler for the <code>/messages</code> <code>POST</code> endpoint.
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
     * Handler for the <code>/messages</code> <code>GET</code> endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessages(Context context) {
        context.status(200).json(messageService.getAllMessages());
    }

    /**
     * Handler for the <code>/messages/{id}</code> <code>GET</code> endpoint.
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
     * Handler for the <code>/messages/{id}</code> <code>DELETE</code> endpoint.
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
     * Handler for the <code>/messages/{id}</code> <code>PATCH</code> endpoint.
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
     * Handler for the <code>/accounts/{account_id}/messages</code> <code>GET</code> endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesByAccountID(Context context) {
        int id = Integer.parseInt(context.pathParam("account_id"));
        context.status(200).json(messageService.getAllMessagesByAccountID(id));
    }
}
