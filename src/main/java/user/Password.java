package user;

import com.github.javafaker.Faker;

import java.util.Objects;


public class Password {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public Password() {
    }

    public Password(String password, String newPassword, String confirmPassword) {
        this.currentPassword = password;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(currentPassword, password1.currentPassword) && Objects.equals(newPassword, password1.newPassword) && Objects.equals(confirmPassword, password1.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPassword, newPassword, confirmPassword);
    }

    @Override
    public String toString() {
        return "Password{" +
                "currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }


}
