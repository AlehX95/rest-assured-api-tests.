package payload;


public class PostPayloadBuilder {

    // ============================================================
    //                  PAYLOADS PARA POSTS (JSONPlaceholder)
    // ============================================================

    // Builder dinámico para crear posts
    public static String createPostPayload(String title, String body, Integer userId) {
        return "{\n" +
                "  \"title\": \"" + title + "\",\n" +
                "  \"body\": \"" + body + "\",\n" +
                "  \"userId\": " + userId + "\n" +
                "}";
    }

    // Payload por defecto
    public static String createPostPayload() {
        return createPostPayload("Default Title", "Default Body", 1);
    }

    // ============================
    //        NEGATIVOS POSTS
    // ============================

    // Título vacío
    public static String postMissingTitle() {
        return "{\n" +
                "  \"title\": \"\",\n" +
                "  \"body\": \"QA\",\n" +
                "  \"userId\": 1\n" +
                "}";
    }

    // Body vacío
    public static String postMissingBody() {
        return "{\n" +
                "  \"title\": \"Test\",\n" +
                "  \"body\": \"\",\n" +
                "  \"userId\": 1\n" +
                "}";
    }

    // userId inválido
    public static String postInvalidUserId() {
        return "{\n" +
                "  \"title\": \"Test\",\n" +
                "  \"body\": \"QA\",\n" +
                "  \"userId\": -1\n" +
                "}";
    }

    // JSON mal formado (negativo estructural)
    public static String malformedPostJson() {
        return "{ \"title\": \"Test\" "; // intencionalmente mal
    }
}

