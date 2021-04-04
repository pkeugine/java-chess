package chess;

import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import chess.controller.WebController;
import chess.dao.GameDao;
import chess.domain.piece.Color;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {

        staticFileLocation("/public");
        final WebController webController = new WebController();
        final GameDao gameDao = new GameDao();
        final Gson gson = new Gson();

        get("/", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            webController.startGame();
            return render(model, "board.html");
        });

        get("/new", (req, res) -> {
            final Map<String, String> model = webController.startGame();
            return gson.toJson(model);
        });

        put("/move", (req, res) -> {
            final Map<String, String> requestBody = gson.fromJson(req.body(), HashMap.class);
            final Map<String, String> model = webController.move(requestBody.get("source"), requestBody.get("target"));
            return gson.toJson(model);
        });

        get("/currentState", (req, res) -> {
            final Map<String, String> model = new HashMap<>();
            model.put("turn", webController.showTurn());
            model.put("whiteScore", webController.showScore(Color.WHITE));
            model.put("blackScore", webController.showScore(Color.BLACK));
            return gson.toJson(model);
        });

        get("/availablePositions", (req, res) -> {
            final String source = req.queryParams("source");
            final List<String> movablePositions = webController.calculatePath(source);
            return gson.toJson(movablePositions);
        });

        get("/save", (req, res) -> {
            webController.save(gameDao);
            return gson.toJson(true);
        });

        get("/load", (req, res) -> {
            final Map<String, String> model = webController.load(gameDao);
            return gson.toJson(model);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
