package eu.hansolo.fx.timebuddy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


public class TimeBuddyFX extends Application {
    private static final double         SPACER           = 10;
    private              boolean        isTwentyFourHour = true;
    private              boolean        isDarkMode       = false;
    private              boolean        markWeekendHours = false;
    private              TimeZone       homeTimeZone     = TimeZone.getDefault();
    private              VBox           vbox             = new VBox();
    private              Pane           pane             = new Pane();
    private              long           lastTimerCall    = System.nanoTime();
    private              AnimationTimer timer;


    @Override public void init() {
        this.isDarkMode       = PropertyManager.INSTANCE.getBoolean(PropertyManager.PROPERTY_DARK_MODE, false);
        this.isTwentyFourHour = PropertyManager.INSTANCE.getBoolean(PropertyManager.PROPERTY_TWENTY_FOUR_HOURS, true);
        this.markWeekendHours = PropertyManager.INSTANCE.getBoolean(PropertyManager.PROPERTY_MARK_WEEKEND_HOURS, false);
        String homeTimeZoneValue = PropertyManager.INSTANCE.getString(PropertyManager.PROPERTY_HOME_TIME_ZONE);
        if (null != homeTimeZoneValue && !homeTimeZoneValue.isEmpty()) { this.homeTimeZone = TimeZone.getTimeZone(homeTimeZoneValue); }
        List<ZoneBox> zoneBoxes = new ArrayList<>();
        Constants.TIME_ZONE_PROPERTIES.forEach(timeZoneProperty -> {
            final String timeZone = PropertyManager.INSTANCE.getString(timeZoneProperty);
            if (null == timeZone || timeZone.isEmpty()) { return; }
            final String[] parts = timeZone.split(",");
            if (parts.length != 2) { return; }
            zoneBoxes.add(new ZoneBox(parts[1].equals(homeTimeZoneValue), parts[0], this.homeTimeZone, TimeZone.getTimeZone(parts[1]), this.isTwentyFourHour, this.isDarkMode, this.markWeekendHours));
        });
        vbox.setSpacing(SPACER);
        vbox.getChildren().setAll(zoneBoxes);

        int    hour = ZonedDateTime.now(homeTimeZone.toZoneId()).toLocalDateTime().getHour();
        double x    = 341 + 10 + hour * HourBox.WIDTH;
        double y    = 5;
        double w    = HourBox.WIDTH;
        double h    = vbox.getChildren().size() * ZoneBox.HEIGHT + (vbox.getChildren().size() - 1) * SPACER - 10;

        Rectangle rect = new Rectangle(x, y, w, h);
        rect.setArcWidth(14);
        rect.setArcHeight(14);
        rect.setStroke(isDarkMode ? Constants.DARK_RECT_STROKE : Constants.LIGHT_RECT_STROKE);
        rect.setFill(Color.TRANSPARENT);

        pane.getChildren().addAll(vbox, rect);
        pane.setBackground(new Background(new BackgroundFill(isDarkMode ? Constants.DARK_PANEL_BACKGROUND : Constants.LIGHT_PANEL_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                if (now - lastTimerCall > 10_000_000_000l) {
                    vbox.getChildren().forEach(node -> {
                        ZoneBox zoneBox = (ZoneBox) node;
                        zoneBox.refresh();
                    });

                    int hour = ZonedDateTime.now(homeTimeZone.toZoneId()).toLocalDateTime().getHour();
                    rect.setX(341 + 10 + hour * HourBox.WIDTH);
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(final Stage stage) {
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("TimeBuddyFX");
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);

        timer.start();
    }

    @Override public void stop() {
        Platform.exit();
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
