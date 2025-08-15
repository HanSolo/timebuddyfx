package eu.hansolo.fx.timebuddy;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


public class HourBox extends VBox {
    public static final double WIDTH = 36;
    private LocalDateTime dateTime;
    private boolean       twentyFourHour;
    private boolean       darkMode;
    private boolean       markWeekendHours;
    private boolean       isWeekend;
    private Label         titleLabel;
    private Label         subtitleLabel;


    public  HourBox(final LocalDateTime dateTime, final boolean twentyFourHour, final boolean darKMode, final boolean markWeekendHours) {
        final DayOfWeek dayOfWeek = dateTime.getDayOfWeek();

        this.dateTime         = dateTime;
        this.twentyFourHour   = twentyFourHour;
        this.darkMode         = darKMode;
        this.markWeekendHours = markWeekendHours;
        this.isWeekend        = DayOfWeek.SATURDAY == dayOfWeek || dayOfWeek == DayOfWeek.SUNDAY;
        initGraphics();
    }


    private void initGraphics() {
        final boolean useWeekendColors = this.markWeekendHours && this.isWeekend;
        final Font    bigFont          = Font.font(Constants.BIG_FONT_SIZE);
        final Font    smallFont        = Font.font(Constants.SMALL_FONT_SIZE);
        final int     hour             = dateTime.getHour();
        final Color   backgroundColor;
        final Color   foregroundColor;

        if (hour == 0) {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_MIDNIGHT_COLOR     : Constants.DARK_MIDNIGHT_COLOR     : useWeekendColors ? Constants.LIGHT_WE_MIDNIGHT_COLOR     : Constants.LIGHT_MIDNIGHT_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_MIDNIGHT_FGD_COLOR : Constants.DARK_MIDNIGHT_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_MIDNIGHT_FGD_COLOR : Constants.LIGHT_MIDNIGHT_FGD_COLOR;
        } else if ((hour > 0 && hour < 6) || hour > 21) {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_NIGHT_COLOR     : Constants.DARK_NIGHT_COLOR     : useWeekendColors ? Constants.LIGHT_WE_NIGHT_COLOR     : Constants.LIGHT_NIGHT_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_NIGHT_FGD_COLOR : Constants.DARK_NIGHT_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_NIGHT_FGD_COLOR : Constants.LIGHT_NIGHT_FGD_COLOR;
        } else if ((hour > 5 && hour < 8)) {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DAWN_COLOR     : Constants.DARK_DAWN_COLOR     : useWeekendColors ? Constants.LIGHT_WE_DAWN_COLOR     : Constants.LIGHT_DAWN_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DAWN_FGD_COLOR : Constants.DARK_DAWN_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_DAWN_FGD_COLOR : Constants.LIGHT_DAWN_FGD_COLOR;
        } else if (hour > 17 && hour < 22) {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DUSK_COLOR     : Constants.DARK_DUSK_COLOR     : useWeekendColors ? Constants.LIGHT_WE_DUSK_COLOR     : Constants.LIGHT_DUSK_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DUSK_FGD_COLOR : Constants.DARK_DUSK_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_DUSK_FGD_COLOR : Constants.LIGHT_DUSK_FGD_COLOR;
        } else {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DAY_COLOR     : Constants.DARK_DAY_COLOR     : useWeekendColors ? Constants.LIGHT_WE_DAY_COLOR      : Constants.LIGHT_DAY_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DAY_FGD_COLOR : Constants.DARK_DAY_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_DAWN_FGD_COLOR : Constants.LIGHT_DAY_FGD_COLOR;
        }

        if (hour == 0) {
            this.titleLabel    = new Label(Constants.MONTH_FORMATTER.format(this.dateTime));
            this.subtitleLabel = new Label(Constants.DAY_FORMATTER.format(this.dateTime));
        } else {
            this.titleLabel    = new Label(this.twentyFourHour ? Constants.HOUR_TWENTY_FOUR_FORMAT.format(dateTime) : Constants.HOUR_TWELVE_FORMAT.format(dateTime));
            this.subtitleLabel = new Label(this.twentyFourHour ? "" : Constants.AM_PM_FORMAT.format(dateTime));
        }

        this.titleLabel.setAlignment(Pos.CENTER);
        this.titleLabel.setTextFill(foregroundColor);
        this.titleLabel.setFont(bigFont);

        this.subtitleLabel.setAlignment(Pos.CENTER);
        this.subtitleLabel.setTextFill(foregroundColor);
        this.subtitleLabel.setFont(smallFont);

        CornerRadii radii;
        if (hour == 0) {
            radii = new CornerRadii(.25, 0, 0, .25, true);
        } else if (hour == 23) {
            radii = new CornerRadii(0, .25, .25, 0, true);
        } else {
            radii = new CornerRadii(0);
        }

        setBackground(new Background(new BackgroundFill(backgroundColor, radii, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, radii, new BorderWidths(0.25))));
        setPrefWidth(WIDTH);
        setMinWidth(WIDTH);
        setMaxWidth(WIDTH);
        setSpacing(0);
        setAlignment(Pos.CENTER);
        if (twentyFourHour && hour != 0) {
            getChildren().addAll(this.titleLabel);
        } else {
            getChildren().addAll(this.titleLabel, this.subtitleLabel);
        }
    }

    public boolean isDarkMode() { return this.darkMode; }
    public void setDarkMode(final boolean darkMode) {
        this.darkMode = darkMode;
        final boolean useWeekendColors = this.markWeekendHours && this.isWeekend;

        final int         hour = this.dateTime.getHour();
        final CornerRadii radii;
        if (hour == 0) {
            radii = new CornerRadii(.25, 0, 0, .25, true);
        } else if (hour == 23) {
            radii = new CornerRadii(0, .25, .25, 0, true);
        } else {
            radii = new CornerRadii(0);
        }
        final Color backgroundColor;
        final Color foregroundColor;

        if (hour == 0) {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_MIDNIGHT_COLOR     : Constants.DARK_MIDNIGHT_COLOR     : useWeekendColors ? Constants.LIGHT_WE_MIDNIGHT_COLOR     : Constants.LIGHT_MIDNIGHT_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_MIDNIGHT_FGD_COLOR : Constants.DARK_MIDNIGHT_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_MIDNIGHT_FGD_COLOR : Constants.LIGHT_MIDNIGHT_FGD_COLOR;
        } else if ((hour > 0 && hour < 6) || hour > 21) {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_NIGHT_COLOR     : Constants.DARK_NIGHT_COLOR     : useWeekendColors ? Constants.LIGHT_WE_NIGHT_COLOR     : Constants.LIGHT_NIGHT_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_NIGHT_FGD_COLOR : Constants.DARK_NIGHT_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_NIGHT_FGD_COLOR : Constants.LIGHT_NIGHT_FGD_COLOR;
        } else if ((hour > 5 && hour < 8)) {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DAWN_COLOR     : Constants.DARK_DAWN_COLOR     : useWeekendColors ? Constants.LIGHT_WE_DAWN_COLOR     : Constants.LIGHT_DAWN_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DAWN_FGD_COLOR : Constants.DARK_DAWN_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_DAWN_FGD_COLOR : Constants.LIGHT_DAWN_FGD_COLOR;
        } else if (hour > 17 && hour < 22) {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DUSK_COLOR     : Constants.DARK_DUSK_COLOR     : useWeekendColors ? Constants.LIGHT_WE_DUSK_COLOR     : Constants.LIGHT_DUSK_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DUSK_FGD_COLOR : Constants.DARK_DUSK_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_DUSK_FGD_COLOR : Constants.LIGHT_DUSK_FGD_COLOR;
        } else {
            backgroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DAY_COLOR     : Constants.DARK_DAY_COLOR     : useWeekendColors ? Constants.LIGHT_WE_DAY_COLOR      : Constants.LIGHT_DAY_COLOR;
            foregroundColor = this.darkMode ? useWeekendColors ? Constants.DARK_WE_DAY_FGD_COLOR : Constants.DARK_DAY_FGD_COLOR : useWeekendColors ? Constants.LIGHT_WE_DAWN_FGD_COLOR : Constants.LIGHT_DAY_FGD_COLOR;
        }
        setBackground(new Background(new BackgroundFill(backgroundColor, radii, Insets.EMPTY)));
        this.titleLabel.setTextFill(foregroundColor);
        this.subtitleLabel.setTextFill(foregroundColor);
    }
}
