package user;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class User {
    private String avatarUrl;
    private String name;
    private String surname;
    private String birthDate;
    private String phone;
    private String gender;
    private String backgroundUrl;
    private String password;
    @Expose(serialize = true, deserialize = false)
    private String email;
    @Expose(serialize = false, deserialize = true)
    private boolean blocked;
    @Expose(serialize = true, deserialize = false)
    private String id;
    @Expose(serialize = true, deserialize = false)
    private String role;

    public User(Object o, Object object, String name, String surname, Object o1, String s, String gender, String backgroundUrl, boolean blocked) {
    }

    public User(String avatarUrl, String name, String surname, String birthDate, String phone, String gender,
                String backgroundUrl, boolean blocked, String id, String role) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phone = phone;
        this.gender = gender;
        this.backgroundUrl = backgroundUrl;
        this.blocked = blocked;
        this.id = id;
        this.role = role;
    }

    public User(String avatarUrl, String name, String surname, String birthDate, String phone, String gender,
                String backgroundUrl, boolean blocked) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phone = phone;
        this.gender = gender;
        this.backgroundUrl = backgroundUrl;
        this.blocked = blocked;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(avatarUrl, user.avatarUrl) && Objects.equals(name, user.name)
                && Objects.equals(surname, user.surname) && Objects.equals(birthDate, user.birthDate)
                && Objects.equals(phone, user.phone) && Objects.equals(gender, user.gender)
                && Objects.equals(backgroundUrl, user.backgroundUrl)
                && Objects.equals(blocked, user.blocked)
                && Objects.equals(id, user.id)
                && Objects.equals(role, user.role);
    }

    //AVATAR_URL, NAME, SURNAME, BIRTH_DATE, PHONE, GENDER, BACKGROUND_URL, BLOCKED
    @Override
    public int hashCode() {
        return Objects.hash(avatarUrl, name, surname, birthDate, phone, gender, backgroundUrl, blocked, id, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", backgroundUrl='" + backgroundUrl + '\'' +
                ", blocked='" + blocked + '\'' +
                ", id='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
