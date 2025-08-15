package eu.hansolo.fx.timebuddy;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class ZoneBox extends HBox {
    public  static final double   HEIGHT          = 50;
    public  static final double   SYMBOL_WIDTH    = 36;
    public  static final double   SPACER_WIDTH    = 100;
    private static final double   LEFT_MIN_WIDTH  = 175;
    private static final double   RIGHT_MIN_WIDTH = 100;
    private Color                 _backgroundColor;
    private ObjectProperty<Color> backgroundColor;
    private Color                 _symbolTextColor;
    private ObjectProperty<Color> symbolTextColor;
    private Color                 _titleColor;
    private ObjectProperty<Color> titleColor;
    private Color                 _subtitleColor;
    private ObjectProperty<Color> subtitleColor;
    private Color                 _timeColor;
    private ObjectProperty<Color> timeColor;
    private Color                 _dateColor;
    private ObjectProperty<Color> dateColor;
    private boolean               _twentyFourHour;
    private BooleanProperty       twentyFourHour;
    private boolean               _darkMode;
    private BooleanProperty       darkMode;
    private boolean               _markWeekendHours;
    private BooleanProperty       markWeekendHours;
    private boolean               _home;
    private BooleanProperty       home;
    private String                title;
    private TimeZone              homeTimeZone;
    private TimeZone              timeZone;
    private long                  offsetMinutes;
    private ZonedDateTime         dateTime;
    private ImageView             homeIcon;
    private Label                 symbolLabel;
    private Label                 titleLabel;
    private Label                 subtitleLabel;
    private Label                 timeLabel;
    private Label                 dateLabel;
    private HBox                  hours;


    // ******************** Constructors **************************************
    public ZoneBox() {
        this(true, "", TimeZone.getDefault(), TimeZone.getDefault(), true, false, false);
    }
    public ZoneBox(final boolean home, final String title, final TimeZone homeTimeZone, final TimeZone timeZone, final boolean twentyFourHour, final boolean darkMode, final boolean markWeekendHours) {
        this._home             = home;
        this.title             = title;
        this.homeTimeZone      = homeTimeZone;
        this.timeZone          = timeZone;
        this.dateTime          = ZonedDateTime.now(this.timeZone.toZoneId());
        this.offsetMinutes     = ChronoUnit.MINUTES.between(ZonedDateTime.now(this.homeTimeZone.toZoneId()).toLocalDateTime(), dateTime);
        this._twentyFourHour   = twentyFourHour;
        this._darkMode         = darkMode;
        this._markWeekendHours = markWeekendHours;

        if (isDarkMode()) {
            this._backgroundColor = Constants.DARK_BACKGROUND;
            this._symbolTextColor = Constants.DARK_SUBTITLE_COLOR;
            this._titleColor      = Constants.DARK_TITLE_COLOR;
            this._subtitleColor   = Constants.DARK_SUBTITLE_COLOR;
            this._timeColor       = Constants.DARK_TITLE_COLOR;
            this._dateColor       = Constants.DARK_SUBTITLE_COLOR;
            this.homeIcon         = new ImageView(Constants.DARK_HOME_ICON);
        } else {
            this._backgroundColor = Constants.LIGHT_BACKGROUND;
            this._symbolTextColor = Constants.LIGHT_SUBTITLE_COLOR;
            this._titleColor      = Constants.LIGHT_TITLE_COLOR;
            this._subtitleColor   = Constants.LIGHT_SUBTITLE_COLOR;
            this._timeColor       = Constants.LIGHT_TITLE_COLOR;
            this._dateColor       = Constants.LIGHT_SUBTITLE_COLOR;
            this.homeIcon         = new ImageView(Constants.LIGHT_HOME_ICON);
        }

        initGraphics();
    }


    private void initGraphics() {
        setBackground(new Background(new BackgroundFill(_backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        final Font bigFont   = Font.font(Constants.BIG_FONT_SIZE);
        final Font smallFont = Font.font(Constants.STD_FONT_SIZE);

        symbolLabel = new Label("");
        symbolLabel.setFont(smallFont);
        symbolLabel.setAlignment(Pos.CENTER);
        symbolLabel.setTextFill(_symbolTextColor);
        symbolLabel.setWrapText(false);
        symbolLabel.setPrefWidth(SYMBOL_WIDTH);
        symbolLabel.setMinWidth(SYMBOL_WIDTH);
        symbolLabel.setMaxWidth(SYMBOL_WIDTH);
        symbolLabel.setGraphicTextGap(0);
        if (this._home) {
            symbolLabel.setText("");
            symbolLabel.setGraphic(homeIcon);
        } else {
            final double offset  = this.offsetMinutes / 60.0;
            symbolLabel.setText((offset > 0 ? "+" : "") + Constants.OFFSET_FORMAT.format(offset));
            symbolLabel.setGraphic(null);
        }

        titleLabel = new Label(title);
        titleLabel.setFont(bigFont);
        titleLabel.setTextFill(_titleColor);
        titleLabel.setAlignment(Pos.BASELINE_LEFT);
        titleLabel.setWrapText(false);
        titleLabel.setMinWidth(LEFT_MIN_WIDTH);

        subtitleLabel = new Label(this.timeZone.getDisplayName(true, TimeZone.LONG, Locale.getDefault()));
        subtitleLabel.setFont(smallFont);
        subtitleLabel.setTextFill(_subtitleColor);
        subtitleLabel.setAlignment(Pos.BASELINE_LEFT);
        subtitleLabel.setWrapText(false);
        subtitleLabel.setMinWidth(LEFT_MIN_WIDTH);

        timeLabel = new Label(isTwentyFourHour() ? Constants.TWENTY_FOUR_HOUR_FORMAT.format(this.dateTime) : Constants.TWELVE_HOUR_FORMAT.format(this.dateTime));
        timeLabel.setFont(bigFont);
        timeLabel.setTextFill(_timeColor);
        timeLabel.setAlignment(Pos.BASELINE_RIGHT);
        timeLabel.setWrapText(false);
        timeLabel.setMinWidth(RIGHT_MIN_WIDTH);

        dateLabel = new Label(Constants.LOCALE_DATE_FORMAT.format(this.dateTime));
        dateLabel.setFont(smallFont);
        dateLabel.setTextFill(_dateColor);
        dateLabel.setAlignment(Pos.BASELINE_RIGHT);
        dateLabel.setWrapText(false);
        dateLabel.setMinWidth(RIGHT_MIN_WIDTH);

        // Title and Zone
        VBox leftBox = new VBox(3, titleLabel, subtitleLabel);
        leftBox.setAlignment(Pos.BASELINE_LEFT);

        // Time and Date
        VBox rightBox = new VBox(3, timeLabel, dateLabel);
        rightBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox.setMargin(rightBox, new Insets(0, 10, 0, 0));

        // Spacer between both areas
        Region spacer = new Region();
        spacer.setMaxWidth(SPACER_WIDTH);

        // Hours
        List<HourBox> hourBoxList = new ArrayList<>();
        LocalDateTime dateTime    = LocalDateTime.of(this.dateTime.toLocalDate(), this.dateTime.toLocalTime());
        int addOn = 0;
        if (this.isHome()) {
            dateTime = dateTime.toLocalDate().atStartOfDay();
        } else {
            final LocalDateTime homeZoneDateTime = ZonedDateTime.now(this.homeTimeZone.toZoneId()).toLocalDateTime();
            final long          minutes          = ChronoUnit.MINUTES.between(homeZoneDateTime, dateTime);
            dateTime = homeZoneDateTime.toLocalDate().atStartOfDay().plusMinutes(minutes);
            if (minutes > 0) { addOn = 1; }
        }
        for (int i = 0 ; i < 24 ; i++) {
            final HourBox hourBox = new HourBox(dateTime.plusHours(i + addOn), isTwentyFourHour(), isDarkMode(), isMarkWeekendHours());
            hourBoxList.add(hourBox);
        }
        this.hours = new HBox(hourBoxList.toArray(new HourBox[hourBoxList.size()]));

        setSpacing(5);
        setPadding(new Insets(5, 10, 5, 10));
        setAlignment(Pos.CENTER);
        setPrefHeight(HEIGHT);
        setMinHeight(HEIGHT);
        setMaxHeight(HEIGHT);
        getChildren().addAll(symbolLabel, leftBox, spacer, rightBox, hours);
    }

    public Color getBackgroundColor() { return null == this.backgroundColor ? this._backgroundColor : this.backgroundColor.get(); }
    public void setBackgroundColor(final Color backgroundColor) {
        if (null == this.backgroundColor) {
            this._backgroundColor = backgroundColor;
            setBackground(new Background(new BackgroundFill(this._backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            this.backgroundColor.set(backgroundColor);
        }
    }
    public ObjectProperty<Color> backgroundColorProperty() {
        if (null == this.backgroundColor) {
            this.backgroundColor = new ObjectPropertyBase<>(this._backgroundColor) {
                @Override protected void invalidated() { setBackground(new Background(new BackgroundFill(get(), CornerRadii.EMPTY, Insets.EMPTY))); }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "backgroundColor"; }
            };
            this._backgroundColor = null;
        }
        return backgroundColor;
    }

    public Color getSymbolTextColor() { return null == this.symbolTextColor ? this._symbolTextColor : this.symbolTextColor.get(); }
    public void setSymbolTextColor(final Color symbolTextColor) {
        if (null == this.symbolTextColor) {
            this._symbolTextColor = symbolTextColor;
            this.symbolLabel.setTextFill(this._symbolTextColor);
        } else {
            this.symbolTextColor.set(symbolTextColor);
        }
    }
    public ObjectProperty<Color> symbolTextColorProperty() {
        if (null == this.symbolTextColor) {
            this.symbolTextColor = new ObjectPropertyBase<>(this._symbolTextColor) {
                @Override protected void invalidated() { symbolLabel.setTextFill(get()); }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "symbolTextColor"; }
            };
            this._symbolTextColor = null;
        }
        return symbolTextColor;
    }

    public Color getTitleColor() { return null == this.titleColor ? this._titleColor : this.titleColor.get(); }
    public void setTitleColor(final Color titleColor) {
        if (null == this.titleColor) {
            this._titleColor = titleColor;
            this.titleLabel.setTextFill(this._titleColor);
        } else {
            this.titleColor.set(titleColor);
        }
    }
    public ObjectProperty<Color> titleColorProperty() {
        if (null == this.titleColor) {
            this.titleColor = new ObjectPropertyBase<>(this._titleColor) {
                @Override protected void invalidated() { titleLabel.setTextFill(get()); }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "titleColor"; }
            };
            this._titleColor = null;
        }
        return titleColor;
    }

    public Color getSubtitleColor() { return null == this.subtitleColor ? this._subtitleColor : this.subtitleColor.get(); }
    public void setSubtitleColor(final Color subtitleColor) {
        if (null == this.subtitleColor) {
            this._subtitleColor = subtitleColor;
            this.subtitleLabel.setTextFill(this._subtitleColor);
        } else {
            this.subtitleColor.set(subtitleColor);
        }
    }
    public ObjectProperty<Color> subtitleColorProperty() {
        if (null == this.subtitleColor) {
            this.subtitleColor = new ObjectPropertyBase<>(this._subtitleColor) {
                @Override protected void invalidated() { subtitleLabel.setTextFill(get()); }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "subtitleColor"; }
            };
            this._subtitleColor = null;
        }
        return subtitleColor;
    }

    public Color getTimeColor() { return null == this.timeColor ? this._timeColor : this.timeColor.get(); }
    public void setTimeColor(final Color timeColor) {
        if (null == this.timeColor) {
            this._timeColor = timeColor;
            this.timeLabel.setTextFill(this._timeColor);
        } else {
            this.timeColor.set(timeColor);
        }
    }
    public ObjectProperty<Color> timeColorProperty() {
        if (null == this.timeColor) {
            this.timeColor = new ObjectPropertyBase<>(this._timeColor) {
                @Override protected void invalidated() { timeLabel.setTextFill(get()); }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "timeColor"; }
            };
            this._timeColor = null;
        }
        return timeColor;
    }

    public Color getDateColor() { return null == this.dateColor ? this._dateColor : this.dateColor.get(); }
    public void setDateColor(final Color dateColor) {
        if (null == this.dateColor) {
            this._dateColor = dateColor;
            this.dateLabel.setTextFill(this._dateColor);
        } else {
            this.dateColor.set(dateColor);
        }
    }
    public ObjectProperty<Color> dateColorProperty() {
        if (null == this.dateColor) {
            this.dateColor = new ObjectPropertyBase<>(this._dateColor) {
                @Override protected void invalidated() { dateLabel.setTextFill(get()); }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "dateColor"; }
            };
            this._dateColor = null;
        }
        return dateColor;
    }

    public boolean isTwentyFourHour() { return null == this.twentyFourHour ? _twentyFourHour : this.twentyFourHour.get(); }
    public void setTwentyFourHour(final boolean twentyFourHour) {
        if (null == this.twentyFourHour) {
            this._twentyFourHour = twentyFourHour;
        } else {
            this.twentyFourHour.set(twentyFourHour);
        }
    }
    public BooleanProperty twentyFourHourProperty() {
        if (null == this.twentyFourHour) {
            this.twentyFourHour = new BooleanPropertyBase(_twentyFourHour) {
                @Override protected void invalidated() { }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "twentyFourHour"; }
            };
        }
        return twentyFourHour;
    }

    public boolean isDarkMode() { return null == this.darkMode ? _darkMode: this.darkMode.get(); }
    public void setDarkMode(final boolean darkMode) {
        if (null == this.darkMode) {
            this._darkMode = darkMode;
            if (this._darkMode) {
                setBackgroundColor(Constants.DARK_BACKGROUND);
                setTitleColor(Constants.DARK_TITLE_COLOR);
                setSubtitleColor(Constants.DARK_SUBTITLE_COLOR);
                setTimeColor(Constants.DARK_TITLE_COLOR);
                setDateColor(Constants.DARK_SUBTITLE_COLOR);
                homeIcon.setImage(Constants.DARK_HOME_ICON);
            } else {
                setBackgroundColor(Constants.LIGHT_BACKGROUND);
                setTitleColor(Constants.LIGHT_TITLE_COLOR);
                setSubtitleColor(Constants.LIGHT_SUBTITLE_COLOR);
                setTimeColor(Constants.LIGHT_TITLE_COLOR);
                setDateColor(Constants.LIGHT_SUBTITLE_COLOR);
                homeIcon.setImage(Constants.LIGHT_HOME_ICON);
            }
        } else {
            this.darkMode.set(darkMode);
        }
    }
    public BooleanProperty darkModeProperty() {
        if (null == this.darkMode) {
            this.darkMode = new BooleanPropertyBase(_darkMode) {
                @Override protected void invalidated() {
                    if (get()) {
                        setBackgroundColor(Constants.DARK_BACKGROUND);
                        setTitleColor(Constants.DARK_TITLE_COLOR);
                        setSubtitleColor(Constants.DARK_SUBTITLE_COLOR);
                        setTimeColor(Constants.DARK_TITLE_COLOR);
                        setDateColor(Constants.DARK_SUBTITLE_COLOR);
                        homeIcon.setImage(Constants.DARK_HOME_ICON);
                    } else {
                        setBackgroundColor(Constants.LIGHT_BACKGROUND);
                        setTitleColor(Constants.LIGHT_TITLE_COLOR);
                        setSubtitleColor(Constants.LIGHT_SUBTITLE_COLOR);
                        setTimeColor(Constants.LIGHT_TITLE_COLOR);
                        setDateColor(Constants.LIGHT_SUBTITLE_COLOR);
                        homeIcon.setImage(Constants.LIGHT_HOME_ICON);
                    }
                }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "darkMode"; }
            };
        }
        return this.darkMode;
    }

    public boolean isMarkWeekendHours() { return null == this.markWeekendHours ? _markWeekendHours : this.markWeekendHours.get(); }
    public void setMarkWeekendHours(final boolean markWeekendHours) {
        if (null == this.markWeekendHours) {
            this._markWeekendHours = markWeekendHours;
            refresh();
        } else {
            this.markWeekendHours.set(markWeekendHours);
        }
    }
    public BooleanProperty markWeekendHoursProperty() {
        if (null == this.markWeekendHours) {
            this.markWeekendHours = new BooleanPropertyBase(_markWeekendHours) {
                @Override protected void invalidated() { refresh(); }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "markWeekendHours"; }
            };
        }
        return this.markWeekendHours;
    }

    public boolean isHome() { return null == this.home ? this._home : this.home.get(); }
    public void setHome(final boolean home) {
        if (null == this.home) {
            this._home = home;
            if (this._home) {
                symbolLabel.setText("");
                symbolLabel.setGraphic(homeIcon);
            } else {
                final double offset = this.offsetMinutes / 60.0;
                symbolLabel.setText((offset > 0 ? "+" : "") + Constants.OFFSET_FORMAT.format(offset));
                symbolLabel.setGraphic(null);
            }
        }
    }
    public BooleanProperty homeProperty() {
        if (null == this.home) {
            this.home = new  BooleanPropertyBase(this._home) {
                @Override protected void invalidated() {
                    if (get()) {
                        symbolLabel.setText("");
                        symbolLabel.setGraphic(homeIcon);
                    } else {
                        final double offset = offsetMinutes / 60.0;
                        symbolLabel.setText((offset > 0 ? "+" : "") + Constants.OFFSET_FORMAT.format(offset));
                        symbolLabel.setGraphic(null);
                    }
                }
                @Override public Object getBean() { return ZoneBox.this; }
                @Override public String getName() { return "home"; }
            };
        }
        return home;
    }

    public void refresh() {
        dateTime = ZonedDateTime.now(timeZone.toZoneId());
        timeLabel.setText(isTwentyFourHour() ? Constants.TWENTY_FOUR_HOUR_FORMAT.format(dateTime) : Constants.TWELVE_HOUR_FORMAT.format(dateTime));
        dateLabel.setText(Constants.LOCALE_DATE_FORMAT.format(dateTime));

        List<HourBox> hourBoxList = new ArrayList<>();
        LocalDateTime dateTime    = LocalDateTime.of(this.dateTime.toLocalDate(), this.dateTime.toLocalTime());
        int addOn = 0;
        if (this.isHome()) {
            dateTime = dateTime.toLocalDate().atStartOfDay();
        } else {
            final LocalDateTime homeZoneDateTime = ZonedDateTime.now(this.homeTimeZone.toZoneId()).toLocalDateTime();
            final long          minutes          = ChronoUnit.MINUTES.between(homeZoneDateTime, dateTime);
            dateTime = homeZoneDateTime.toLocalDate().atStartOfDay().plusMinutes(minutes);
            if (minutes > 0) { addOn = 1; }
        }
        for (int i = 0 ; i < 24 ; i++) {
            final HourBox hourBox = new HourBox(dateTime.plusHours(i + addOn), isTwentyFourHour(), isDarkMode(), isMarkWeekendHours());
            hourBoxList.add(hourBox);
        }
        this.hours.getChildren().setAll(hourBoxList);
    }
}
