package eu.hansolo.fx.timebuddy;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Constants {
    public static final String            HOME_FOLDER             = new StringBuilder(System.getProperty("user.home")).append(File.separator).toString();
    public static final List<String>      TIME_ZONE_PROPERTIES    = List.of(PropertyManager.PROPERTY_TIME_ZONE_1, PropertyManager.PROPERTY_TIME_ZONE_2, PropertyManager.PROPERTY_TIME_ZONE_3,
                                                                            PropertyManager.PROPERTY_TIME_ZONE_4, PropertyManager.PROPERTY_TIME_ZONE_5, PropertyManager.PROPERTY_TIME_ZONE_6,
                                                                            PropertyManager.PROPERTY_TIME_ZONE_7, PropertyManager.PROPERTY_TIME_ZONE_8, PropertyManager.PROPERTY_TIME_ZONE_9,
                                                                            PropertyManager.PROPERTY_TIME_ZONE_10, PropertyManager.PROPERTY_TIME_ZONE_11, PropertyManager.PROPERTY_TIME_ZONE_12);

    // Dark Color Scheme
    public static final Color             DARK_PANEL_BACKGROUND      = Color.rgb(34, 34, 34);
    public static final Color             DARK_BACKGROUND            = Color.rgb(69, 71, 72);
    public static final Color             DARK_TITLE_COLOR           = Color.WHITE;
    public static final Color             DARK_SUBTITLE_COLOR        = Color.DARKGRAY;
    public static final Image             DARK_HOME_ICON             = new Image(ZoneBox.class.getResourceAsStream("home_dark.png"), 20, 20, true, true);
    public static final Color             DARK_MIDNIGHT_COLOR        = Color.rgb(115, 139, 169);
    public static final Color             DARK_NIGHT_COLOR           = Color.rgb(138, 170, 209);
    public static final Color             DARK_DAWN_COLOR            = Color.rgb(234, 246, 248);
    public static final Color             DARK_DUSK_COLOR            = Color.rgb(234, 246, 248);
    public static final Color             DARK_DAY_COLOR             = Color.rgb(250, 250, 230);
    public static final Color             DARK_WE_MIDNIGHT_COLOR     = Color.rgb(172, 117, 119);
    public static final Color             DARK_WE_NIGHT_COLOR        = Color.rgb(209, 138, 138);
    public static final Color             DARK_WE_DAWN_COLOR         = Color.rgb(248, 234, 239);
    public static final Color             DARK_WE_DUSK_COLOR         = Color.rgb(248, 234, 239);
    public static final Color             DARK_WE_DAY_COLOR          = Color.rgb(248, 234, 239);
    public static final Color             DARK_MIDNIGHT_FGD_COLOR    = Color.WHITE;
    public static final Color             DARK_NIGHT_FGD_COLOR       = Color.WHITE;
    public static final Color             DARK_DAY_FGD_COLOR         = DARK_MIDNIGHT_COLOR;
    public static final Color             DARK_DAWN_FGD_COLOR        = DARK_MIDNIGHT_COLOR;
    public static final Color             DARK_DUSK_FGD_COLOR        = DARK_MIDNIGHT_COLOR;
    public static final Color             DARK_WE_MIDNIGHT_FGD_COLOR = Color.WHITE;
    public static final Color             DARK_WE_NIGHT_FGD_COLOR    = Color.WHITE;
    public static final Color             DARK_WE_DAY_FGD_COLOR      = DARK_WE_MIDNIGHT_COLOR;
    public static final Color             DARK_WE_DAWN_FGD_COLOR     = DARK_WE_MIDNIGHT_COLOR;
    public static final Color             DARK_WE_DUSK_FGD_COLOR     = DARK_WE_MIDNIGHT_COLOR;
    public static final Color             DARK_RECT_STROKE           = Color.BLACK;

    // Light Color Scheme
    public static final Color             LIGHT_PANEL_BACKGROUND      = Color.rgb(228, 228, 228);
    public static final Color             LIGHT_BACKGROUND            = Color.rgb(255, 255, 255);
    public static final Color             LIGHT_TITLE_COLOR           = Color.BLACK;
    public static final Color             LIGHT_SUBTITLE_COLOR        = Color.GRAY;
    public static final Image             LIGHT_HOME_ICON             = new Image(ZoneBox.class.getResourceAsStream("home_light.png"), 20, 20, true, true);
    public static final Color             LIGHT_MIDNIGHT_COLOR        = Color.rgb(115, 139, 169);
    public static final Color             LIGHT_NIGHT_COLOR           = Color.rgb(138, 170, 209);
    public static final Color             LIGHT_DAWN_COLOR            = Color.rgb(234, 246, 248);
    public static final Color             LIGHT_DUSK_COLOR            = Color.rgb(234, 246, 248);
    public static final Color             LIGHT_DAY_COLOR             = Color.rgb(250, 250, 230);
    public static final Color             LIGHT_WE_MIDNIGHT_COLOR     = Color.rgb(172, 117, 119);
    public static final Color             LIGHT_WE_NIGHT_COLOR        = Color.rgb(209, 138, 138);
    public static final Color             LIGHT_WE_DAWN_COLOR         = Color.rgb(248, 234, 239);
    public static final Color             LIGHT_WE_DUSK_COLOR         = Color.rgb(248, 234, 239);
    public static final Color             LIGHT_WE_DAY_COLOR          = Color.rgb(248, 234, 239);
    public static final Color             LIGHT_MIDNIGHT_FGD_COLOR    = Color.WHITE;
    public static final Color             LIGHT_NIGHT_FGD_COLOR       = Color.WHITE;
    public static final Color             LIGHT_DAY_FGD_COLOR         = LIGHT_MIDNIGHT_COLOR;
    public static final Color             LIGHT_DAWN_FGD_COLOR        = LIGHT_MIDNIGHT_COLOR;
    public static final Color             LIGHT_DUSK_FGD_COLOR        = LIGHT_MIDNIGHT_COLOR;
    public static final Color             LIGHT_WE_MIDNIGHT_FGD_COLOR = Color.WHITE;
    public static final Color             LIGHT_WE_NIGHT_FGD_COLOR    = Color.WHITE;
    public static final Color             LIGHT_WE_DAY_FGD_COLOR      = LIGHT_WE_MIDNIGHT_COLOR;
    public static final Color             LIGHT_WE_DAWN_FGD_COLOR     = LIGHT_WE_MIDNIGHT_COLOR;
    public static final Color             LIGHT_WE_DUSK_FGD_COLOR     = LIGHT_WE_MIDNIGHT_COLOR;
    public static final Color             LIGHT_RECT_STROKE           = Color.BLACK;


    public static final DateTimeFormatter TWENTY_FOUR_HOUR_FORMAT  = DateTimeFormatter.ofPattern("H:mm");
    public static final DateTimeFormatter TWELVE_HOUR_FORMAT       = DateTimeFormatter.ofPattern("h:mm a");
    public static final DateTimeFormatter LOCALE_DATE_FORMAT       = DateTimeFormatter.ofPattern("EE d MMM");
    public static final DateTimeFormatter HOUR_TWENTY_FOUR_FORMAT  = DateTimeFormatter.ofPattern("H");
    public static final DateTimeFormatter HOUR_TWELVE_FORMAT       = DateTimeFormatter.ofPattern("h");
    public static final DateTimeFormatter AM_PM_FORMAT             = DateTimeFormatter.ofPattern("a");
    public static final DateTimeFormatter MONTH_FORMATTER          = DateTimeFormatter.ofPattern("MMM");
    public static final DateTimeFormatter DAY_FORMATTER            = DateTimeFormatter.ofPattern("d");
    public static final DecimalFormat     OFFSET_FORMAT            = new DecimalFormat("#.#");

    public static final double            BIG_FONT_SIZE            = 14;
    public static final double            STD_FONT_SIZE            = 12;
    public static final double            SMALL_FONT_SIZE          = 10;
}
