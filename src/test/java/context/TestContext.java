package context;

import model.User;
import model.Advertisement;

public class TestContext {

    private User user;
    private Advertisement advertisement;
    private String advertisementUrl;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    private String updatedAdvertisementName;

    public String getUpdatedAdvertisementName() {
        return updatedAdvertisementName;
    }

    public void setUpdatedAdvertisementName(String updatedAdvertisementName) {
        this.updatedAdvertisementName = updatedAdvertisementName;
    }
    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl;
    }
}
