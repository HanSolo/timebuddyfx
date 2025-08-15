## TimeBuddyFX

TimeBuddyFX is a JavaFX implementation of TimeBuddy which is my favorite iOS timezone app.
It will create a properties file named ```timebuddyfx.properties``` in your home folder, where
you can configure up to 12 cities you would like to show in the overview.

![Screenshot](https://github.com/HanSolo/timebuddyfx/raw/main/resources/screenshot.png)

Here is an example file:
```
<?xml version="1.0" encoding="UTF-16"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
<entry key="twenty_four_hours">true</entry>
<entry key="dark_mode">false</entry>
<entry key="mark_weekend_hours">true</entry>
<entry key="home_time_zone">Europe/Berlin</entry>
<entry key="time_zone_1">Münster,Europe/Berlin</entry>
<entry key="time_zone_2">San Francisco,PST</entry>
<entry key="time_zone_3">New York,US/Eastern</entry>
<entry key="time_zone_4">Sao Paulo,America/Sao_Paulo</entry>
<entry key="time_zone_5">London,Europe/London</entry>
<entry key="time_zone_6">Moscow/Europe/Moscow</entry>
<entry key="time_zone_7">Dubai,Asia/Dubai</entry>
<entry key="time_zone_8">New Dehli,IST</entry>
<entry key="time_zone_9">Singapore,Asia/Singapore</entry>
<entry key="time_zone_10">Tokyo,Asia/Tokyo</entry>
<entry key="time_zone_11">Sydney,Australia/Sydney</entry>
<entry key="time_zone_12"></entry>
</properties>
```
As you can see, you can define your home timezone e.g. Europe/Berlin and you can for each city
set a name and the timezone. 
Keep in mind that I do not check if the city and the timezone will match, this is up to you :)
You can also define if you would like to use 24h or 12h time format, if you would like to use dark mode
and if the weekends should be visualized in a different color.
The timezones need to be in the Java format (please find more info [here](https://howtodoinjava.com/java/date-time/supported-zone-ids-offsets/))
This is just a fun little project that I just created for the fun of it and it is by far not perfect :)
