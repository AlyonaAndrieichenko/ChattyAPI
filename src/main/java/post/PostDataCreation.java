package post;

import com.github.javafaker.Faker;

public class PostDataCreation {
    private static final Faker FAKER = new Faker();
    private static final String TITLE = FAKER.lorem().word();
    private static final String DESCRIPTION = FAKER.lorem().sentence();
    private static final String BODY = FAKER.lorem().paragraph();
    private static final String IMAGE_URL = FAKER.internet().image();
    private static final String PUBLISH_DATE = null;
    private static final boolean IS_DRAFT = false;

    public PostDataCreation() {
    }

    public static Post getCreatedPost(){
        return new Post(TITLE, DESCRIPTION, BODY, IMAGE_URL, PUBLISH_DATE, IS_DRAFT);
    }

    public static Post getUpdatedPost(){
        return new Post(FAKER.lorem().word(), FAKER.lorem().sentence(), FAKER.lorem().paragraph(), FAKER.internet().image(), IS_DRAFT);
    }

    public static Post getCreatedDraft(){
        return new Post(FAKER.lorem().word(), FAKER.lorem().sentence(), FAKER.lorem().paragraph(), FAKER.internet().image(), true);
    }


}
