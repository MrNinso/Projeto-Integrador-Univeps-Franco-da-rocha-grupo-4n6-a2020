package projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020.API.remote;

public class APIException extends Exception {
    private String ApiError;

    public APIException(String error) {
        super();
        this.ApiError = error;
    }

    public String getApiError() {
        return ApiError;
    }
}
