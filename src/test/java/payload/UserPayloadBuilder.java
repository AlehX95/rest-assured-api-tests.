package payload;

import java.util.List;

public class UserPayloadBuilder {

    // Método central que construye el JSON
	public static String buildUserPayload(String name, String job, Integer age, List<String> roles, String city, String zip) {

	    String payload = "{\n" +
	            "  \"name\": \"" + name + "\",\n" +
	            "  \"job\": \"" + job + "\"";

	    if (age != null) {
	        payload += ",\n  \"age\": " + age;
	    }

	    if (roles != null && !roles.isEmpty()) {
	        payload += ",\n  \"roles\": [";

	        for (int i = 0; i < roles.size(); i++) {
	            payload += "\"" + roles.get(i) + "\"";
	            if (i < roles.size() - 1) {
	                payload += ", ";
	            }
	        }

	        payload += "]";
	    }
	    if (city != null && zip != null) {
	        payload += ",\n  \"address\": " + buildAddress(city, zip);
	    }

	    payload += "\n}";
	    return payload;
	}
	
	//metodo para crear direcciones
	private static String buildAddress(String city, String zip) {
	    return "{\n" +
	            "    \"city\": \"" + city + "\",\n" +
	            "    \"zip\": \"" + zip + "\"\n" +
	            "}";
	}

	
    // ============================================================
    //                PAYLOADS POSITIVOS (válidos)
    // ============================================================

    // POST - Crear usuario (con valores dinámicos)
    public static String createUserPayload(String name, String job, Integer age, List<String> roles,String city, String zip) {
        return buildUserPayload(name, job, age, roles, city, zip);
    }

    // POST - Crear usuario (valores por defecto)
    public static String createUserPayload() {
        return buildUserPayload("Default Name", "Default Job", 20, null, null, null);
    }

    // PUT - Actualizar usuario
    public static String updateUserPayload(String name, String job, Integer age, List<String> roles, String city, String zip) {
        return buildUserPayload(name, job, age, roles, city, zip);
    }
    
    
    // ============================================================
    //                PAYLOADS NEGATIVOS (inválidos)
    // ============================================================

    // Sin nombre
    public static String userMissingName() {
        return "{\n" +
                "  \"name\": \"\",\n" +
                "  \"job\": \"QA\"\n" +
                "}";
    }

    // Sin trabajo
    public static String userMissingJob() {
        return "{\n" +
                "  \"name\": \"Ivan\",\n" +
                "  \"job\": \"\"\n" +
                "}";
    }

    // Edad negativa
    public static String userWithNegativeAge() {
        return "{\n" +
                "  \"name\": \"Ivan\",\n" +
                "  \"job\": \"QA\",\n" +
                "  \"age\": -5\n" +
                "}";
    }

    // Roles vacíos
    public static String userWithEmptyRoles() {
        return "{\n" +
                "  \"name\": \"Ivan\",\n" +
                "  \"job\": \"QA\",\n" +
                "  \"roles\": []\n" +
                "}";
    }

    // Address inválido (faltan city y zip)
    public static String userWithInvalidAddress() {
        return "{\n" +
                "  \"name\": \"Ivan\",\n" +
                "  \"job\": \"QA\",\n" +
                "  \"address\": {}\n" +
                "}";
    }

    // Address sin city
    public static String userWithAddressMissingCity() {
        return "{\n" +
                "  \"name\": \"Ivan\",\n" +
                "  \"job\": \"QA\",\n" +
                "  \"address\": {\n" +
                "    \"city\": \"\",\n" +
                "    \"zip\": \"D01\"\n" +
                "  }\n" +
                "}";
    }

    // Address sin zip
    public static String userWithAddressMissingZip() {
        return "{\n" +
                "  \"name\": \"Ivan\",\n" +
                "  \"job\": \"QA\",\n" +
                "  \"address\": {\n" +
                "    \"city\": \"Dublin\",\n" +
                "    \"zip\": \"\"\n" +
                "  }\n" +
                "}";
    }
    
    
}

