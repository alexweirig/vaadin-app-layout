package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.Styles;
import com.github.appreciated.app.layout.builder.PairComponentProvider;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.component.RoundImage;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultNotificationComponentProvider implements PairComponentProvider<NotificationHolder<DefaultNotification>, DefaultNotification> {
    @Override
    public Component getComponent(NotificationHolder holder, DefaultNotification info) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.addStyleName("ripple"); // for material theme
        wrapper.setSpacing(false);
        Label timeAgo = new Label(info.getTimeAgo());
        timeAgo.addStyleName(Styles.APP_BAR_NOTIFICATION_TIME);
        Label title = new Label(info.getTitle());
        title.addStyleName(Styles.APP_BAR_NOTIFICATION_TITLE);
        Label description = new Label(info.getDescription());
        description.addStyleName(Styles.APP_BAR_NOTIFICATION_DESCRIPTION);
        HorizontalLayout descriptionWrapper = new HorizontalLayout(description);
        if (info.getImage() != null) {
            RoundImage image = new RoundImage(info.getImage());
            descriptionWrapper.addComponent(image);
            descriptionWrapper.setExpandRatio(description, 1.0f);
        }
        wrapper.addComponent(new HorizontalLayout(title, timeAgo));
        wrapper.addComponent(descriptionWrapper);
        wrapper.addLayoutClickListener(layoutClickEvent -> holder.onNotificationClicked(info));
        wrapper.addStyleName(info.getStyle());
        return wrapper;
    }
}
