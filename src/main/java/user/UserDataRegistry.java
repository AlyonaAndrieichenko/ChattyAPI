package user;

import com.github.javafaker.Faker;

import static user.UserRole.ADMIN;
import static user.UserRole.USER;

public class UserDataRegistry {
    private static final Faker FAKER = new Faker();
    private static final String NAME = FAKER.name().firstName();
    private static final String SURNAME = FAKER.name().lastName();
    private static final String PASSWORD = FAKER.internet().password();
    private static final String NEW_PASSWORD = FAKER.internet().password();
    private static final String USER_ROLE = USER.toString().toLowerCase();
    private static final String ADMIN_ROLE = ADMIN.toString().toLowerCase();
    private static final String AVATAR_URL = "string";
    private static final String BIRTH_DATE = "1987-07-18T00:00:00.000Z";
    private static final String PHONE = "+55591098360";
    private static final String GENDER = "MALE";
    private static final String BACKGROUND_URL = "string";
    private static final boolean BLOCKED = true;

    private UserDataRegistry() {
    }

    public static AuthorizationUser getUserForLogin(AuthorizationUser authUser){
        AuthorizationUser userForLogin = new AuthorizationUser();
        userForLogin.setEmail(authUser.getEmail());
        userForLogin.setPassword(authUser.getPassword());

        return userForLogin;
    }

    public static AuthorizationUser getAdminRegistration(){
       return new AuthorizationUser(FAKER.internet().emailAddress(), PASSWORD, PASSWORD, ADMIN_ROLE);
    }

    public static AuthorizationUser getUserRegistration(){
        return new AuthorizationUser(FAKER.internet().emailAddress(), PASSWORD, PASSWORD, USER_ROLE);
    }

    public static User getUpdatedUser(String registeredEmail){
        return new User(registeredEmail, AVATAR_URL, NAME, SURNAME, BIRTH_DATE, PHONE, GENDER, BACKGROUND_URL, BLOCKED);
    }

    public static Password updatedPassword(AuthorizationUser authUser){
        authUser.setPassword(NEW_PASSWORD);
        return new Password(PASSWORD, NEW_PASSWORD, NEW_PASSWORD);
    }

    public  static  User getInvalidUser(){
        return new User(null, null, NAME, SURNAME, null, "", GENDER, BACKGROUND_URL, BLOCKED);
    }
}
