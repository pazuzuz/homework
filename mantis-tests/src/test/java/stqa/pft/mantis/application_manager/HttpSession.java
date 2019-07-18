package stqa.pft.mantis.application_manager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import stqa.pft.mantis.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app){
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login(User user) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", user.getUsername()));
        params.add(new BasicNameValuePair("password", user.getPassword()));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(post);
        String body = getTextFrom(response);
        return body.contains(String.format("<a href=\"/mantisbt-2.21.1/account_page.php\">%s</a>", user.getUsername()));
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean isLoggedInAs(User user) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpClient.execute(get);
        String body = getTextFrom(response);
        return body.contains(String.format("<a href=\"/mantisbt-2.21.1/account_page.php\">%s</a>", user.getUsername()));
    }
}
