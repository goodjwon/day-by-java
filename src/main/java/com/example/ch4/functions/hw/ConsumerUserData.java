package com.example.ch4.functions.hw;

import java.util.function.Consumer;

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

public class ConsumerUserData {
    public static void main(String[] args) {
        User newUser = new User("홍길동", "test@example.com");

        Consumer<User> saveUser = user -> System.out.println(user.getName() + "," + user.getEmail());

        Consumer<User> sendWelcomeMessage = user -> System.out.println(user.getEmail());

        Consumer<User> finalConsumer = saveUser.andThen(sendWelcomeMessage);

        finalConsumer.accept(newUser);
    }
}
