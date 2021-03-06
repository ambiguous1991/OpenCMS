package com.jba.opencms.type.system.enu;

import java.util.HashMap;

public class SystemVariableKeys extends HashMap<String, String> {
    public final static String PAGE_NAME = "page.name";
    public final static String SYSTEM_DASHBOARD_DATE_FORMAT ="system.dashboard.dateformat";
    public final static String LOGIN_BACKDROP ="system.dashboard.login.backdrop";
    public final static String PAGE_HOMEPAGE="page.homepage";
    public final static String PAGE_PRESENTATION_OUTSIDE_SOURCES="page.presentation.outside.sources";
    public final static String PAGE_PRESENTATION_GLOBAL_CSS_NAME="page.presentation.global.css.name";
    public final static String PAGE_PRESENTATION_THEME_CSS_NAME="page.presentation.theme.css.name";

    public SystemVariableKeys() {
        put("PAGE_NAME", PAGE_NAME);
        put("SYSTEM_DASHBOARD_DATE_FORMAT", SYSTEM_DASHBOARD_DATE_FORMAT);
        put("LOGIN_BACKDROP", LOGIN_BACKDROP);
        put("PAGE_HOMEPAGE", PAGE_HOMEPAGE);
    }
}
