package mecals.mecalsapp;

import android.app.Activity;

/**
 * Created by Théo on 07/02/2017.
 */

public class GetRequest extends HttpAsync {

    public GetRequest(IRequestHandler handler) {
        super(handler, "GET");
    }
}
