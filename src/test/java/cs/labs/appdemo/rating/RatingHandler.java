package cs.labs.appdemo.rating;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RatingHandler extends AbstractHandler {

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String jsonResponse = "{" +
                "\"Rating\":\"2\"," +
                "\"ProductId\":\"10\"" +
                "}";

        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonResponse);
        response.getWriter().flush();

        baseRequest.setHandled(true);
    }
}
