package Modun3.view;

import Modun3.config.Config;
import Modun3.controller.CommentController;
import Modun3.controller.OderController;
import Modun3.model.cart.CartItem;
import Modun3.model.comment.Comment;
import Modun3.model.oder.Order;

import java.util.ArrayList;
import java.util.List;

public class CommentView {
    CommentController commentController = new CommentController();
    OderController oderController = new OderController();
    List<Comment> comments = new Config<Comment>().readFromFile(Config.PATH_COMMENT);
    List<Order> orders = new Config<Order>().readFromFile(Config.PATH_CART_ODER);

    public void showListComment() {
        comments = new Config<Comment>().readFromFile(Config.PATH_COMMENT);
        String alignFormat = "| %-4d | %-30s | %-15s |%n";
        System.out.format("+------+--------------------------------+-----------------+%n");
        System.out.format("|  ID  |            Commnent            |   Product name  |%n");
        System.out.format("+------+--------------------------------+-----------------+%n");
        for (Comment comment : comments
        ) {
            System.out.format(alignFormat, comment.getId(), comment.getComment(), comment.getOrder());
        }
        System.out.format("+------+--------------------------------+-----------------+%n");
        new ProfileView();
    }

    public void writeComment() {
        orders = new Config<Order>().readFromFile(Config.PATH_CART_ODER);
        String alignFormat = "| %-4d | %-15s | %-15s | %-20s |%n";
        System.out.format("+------+-----------------+-----------------+----------------------+%n");
        System.out.format("|  ID  |  Product name   |      Total      |        Date buy      |%n");
        System.out.format("+------+-----------------+-----------------+----------------------+%n");
        for (Order order : orders) {
            System.out.format(alignFormat, order.getId(), order.getCartItemList(), order.getTotal(), order.getDate());
        }
        System.out.format("+------+-----------------+-----------------+----------------------+%n\n");
        System.out.println("Enter the Product in Oder");
        int idOder = Config.scanner().nextInt();
        Order order = oderController.detailOder(idOder);
        if (order == null) {
            System.err.println("Not found ID! Try Again!");
            new ProfileView();
        } else {
            System.out.println("Enter comment");
            String comment = Config.scanner().nextLine();
            List<Comment> commentList = new Config<Comment>().readFromFile(Config.PATH_COMMENT);
            int idComment = 0;
            if (commentList.isEmpty()) {
                idComment = 1;
            } else {
                idComment = commentList.get(commentList.size() - 1).getId() + 1;
            }
            Comment newComment = new Comment(idComment, comment, order);
            commentController.addCommentProduct(newComment);
            System.out.println("Comment success!");
        }
        new ProfileView();
    }
}
