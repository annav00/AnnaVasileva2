package Selenium_hw3;

import java.util.List;
import java.util.ArrayList;

public class DataClass {
    public static final String HOME_PAGE_TITLE = "Home Page";

    public static final String EXPECTED_USER_NAME = "ROMAN IOVLEV";

    public static final List<String> TOOLBAR_TEXT = new ArrayList<>(
            List.of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"));

    public static final int BENEFIT_INDEX_PAGE_IMAGES_SIZE = 4;

    public static final List<String> BENEFIT_INDEX_PAGE_TEXT = new ArrayList<>(
            List.of(
                    "To include good practices\n" +
                            "and ideas from successful\n" +
                            "EPAM project",
                    "To be flexible and\n" +
                            "customizable",
                    "To be multiplatform",
                    "Already have good base\n" +
                            "(about 20 internal and\n" +
                            "some external projects),\n" +
                            "wish to get more…"));

    public static final List<String> NAVIGATIONBAR_TEXT = new ArrayList<>(
            List.of("Home", "Contact form", "Service", "Metals & Colors", "Elements packs"));

    public static final List<String> CHECKBOX_TEXT = new ArrayList<>(
            List.of("Water", "Earth",  "Wind", "Fire"));

    public static final String CHECKBOX_LOG = ": condition changed to true";

    public static final List<String> RADIO_TEXT = new ArrayList<>(
            List.of("Gold", "Silver",  "Bronze", "Selen"));

    public static final String RADIO_LOG = "metal: value changed to";

    public static final List<String> DROPDOWN_TEXT = new ArrayList<>(
            List.of("Red", "Green",  "Blue", "Yellow"));

    public static final String DROPDOWN_LOG = "Colors: value changed to";

}
