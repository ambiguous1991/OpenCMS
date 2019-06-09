package com.jba.opencms.type.system.enu;

import java.util.HashMap;

public class SystemVariableKeys extends HashMap<String, String> {
    public final static String PAGE_NAME = "page.name";
    public final static String SYSTEM_DASHBOARD_DATE_FORMAT ="system.dashboard.dateformat";
    public final static String LOGIN_BACKDROP ="system.dashboard.login.backdrop";

    public SystemVariableKeys() {
        put("PAGE_NAME", "page.name");
        put("SYSTEM_DASHBOARD_DATE_FORMAT", "system.dashboard.dateformat");
        put("LOGIN_BACKDROP", "system.dashboard.login.backdrop");
    }
}
