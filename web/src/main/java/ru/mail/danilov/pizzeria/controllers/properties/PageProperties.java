package ru.mail.danilov.pizzeria.controllers.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageProperties {

    @Value("${users.page.path}")
    private String usersPagePath;
    @Value("${users.update.page.path}")
    private String usersUpdatePagePath;
    @Value("${login.page.path}")
    private String loginPagePath;
    @Value("${registration.page.path}")
    private String registrationPagePath;
    @Value("${items.page.path}")
    private String itemsPagePath;
    @Value("${items.admin.page.path}")
    private String itemsAdminPagePath;
    @Value("${items.browse.page.path}")
    private String itemsBrowsePagePath;
    @Value("${news.page.path}")
    private String newsPagePath;
    @Value("${items.create.page.path}")
    private String itemsCreatePagePath;
    @Value("${items.update.page.path}")
    private String itemsUpdatePagePath;
    @Value("${news.create.page.path}")
    private String newsCreatePagePath;
    @Value("${comments.page.path}")
    private String commentsPagePath;
    @Value("${error.page.path}")
    private String errorPagePath;
    @Value("${orders.page.path}")
    private String ordersPagePath;
    @Value("${orders.details.page.path}")
    private String ordersDetailsPagePath;
    @Value("${orders.bucket.page.path}")
    private String ordersBucketPagePath;
    @Value("${profile.page.path}")
    private String profilePagePath;
    @Value("${password.page.path}")
    private String passwordPagePath;
    @Value("${items.for.page.quantity}")
    private Long itemsForPageQuantity;
    @Value("${orders.for.page.quantity}")
    private Long ordersForPageQuantity;
    @Value("${users.for.page.quantity}")
    private Long usersForPageQuantity;
    @Value("${news.for.page.quantity}")
    private Long newsForPageQuantity;
    @Value("${comments.for.page.quantity}")
    private Long commentsForPageQuantity;

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getNewsPagePath() {
        return newsPagePath;
    }

    public String getItemsCreatePagePath() {
        return itemsCreatePagePath;
    }

    public String getItemsUpdatePagePath() {
        return itemsUpdatePagePath;
    }

    public String getNewsCreatePagePath() {
        return newsCreatePagePath;
    }

    public String getErrorPagePath() {
        return errorPagePath;
    }

    public String getOrdersPagePath() {
        return ordersPagePath;
    }

    public String getOrdersDetailsPagePath() {
        return ordersDetailsPagePath;
    }

    public String getProfilePagePath() {
        return profilePagePath;
    }

    public Long getItemsForPageQuantity() {
        return itemsForPageQuantity;
    }

    public Long getOrdersForPageQuantity() {
        return ordersForPageQuantity;
    }

    public Long getUsersForPageQuantity() {
        return usersForPageQuantity;
    }

    public Long getNewsForPageQuantity() {
        return newsForPageQuantity;
    }

    public Long getCommentsForPageQuantity() {
        return commentsForPageQuantity;
    }

    public String getOrdersBucketPagePath() {
        return ordersBucketPagePath;
    }

    public String getItemsAdminPagePath() {
        return itemsAdminPagePath;
    }

    public String getUsersUpdatePagePath() {
        return usersUpdatePagePath;
    }

    public String getItemsBrowsePagePath() {
        return itemsBrowsePagePath;
    }

    public String getPasswordPagePath() {
        return passwordPagePath;
    }

    public String getRegistrationPagePath() {
        return registrationPagePath;
    }

    public String getCommentsPagePath() {
        return commentsPagePath;
    }
}
