package mecals.mecalsapp;

/**
 * Created by Théo on 19/02/2017.
 */

public interface IRequestHandler {
    void onRequest(HttpResponse response, int identifier);
}
