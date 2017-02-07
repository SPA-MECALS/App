package mecals.mecalsapp;

/**
 * Created by Théo on 07/02/2017.
 */

public class HttpResponse {
    private int m_status;
    private String m_content;

    public HttpResponse(int status, String content) {
        m_status = status;
        m_content = content;
    }

    public int getStatus() {
        return (m_status);
    }

    public String getContent() {
        return (m_content);
    }
}
