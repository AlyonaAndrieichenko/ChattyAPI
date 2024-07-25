package feedback;

import com.github.javafaker.Faker;
import post.Post;

public class FeedbackCreation {
    private static final Faker FAKER = new Faker();
    private static final String NAME = FAKER.name().fullName();
    private static final String EMAIL = FAKER.internet().emailAddress();
    private static final String CONTENT = FAKER.lorem().paragraph();

    public static Feedback getCreatedFeedback(){
        return new Feedback(NAME, EMAIL, CONTENT);
    }

}
