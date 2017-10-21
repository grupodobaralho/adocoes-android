package br.pucrs.ages.adocoes.Database.SharedPreferences;

public class UserBusiness {

    private String accessToken;
    private String userId;


    private static final UserBusiness ourInstance = new UserBusiness();

    public static UserBusiness getInstance() {
        return ourInstance;
    }

    public void updateAccessToken(String accessToken, String userId) {
        this.accessToken = accessToken;
        this.userId = userId;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.ACCESS_TOKEN, this.accessToken);
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.USER_ID, this.userId);
    }

    public String getAccessToken() {
        if (accessToken == null) {
            accessToken = SharedPreferencesOperations.loadFromPrefs(SharedPreferencesOperations.ACCESS_TOKEN);
        }
        return accessToken;
    }

    public String getUserId() {
        if (userId == null) {
            userId  = SharedPreferencesOperations.loadFromPrefs(SharedPreferencesOperations.ACCESS_TOKEN);
        }
        return userId;
    }
}
